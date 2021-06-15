package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.FilterUserDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserMVPDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private KieSession kieSession;
	
	private final UserMapper userMapper = new UserMapper();
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public Page<UserMVPDTO> findAll(int pageNum) {
		QueryResults results = kieSession.getQueryResults( "getAllUsers" );
		List<UserRatingDTO> users = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    User user = ( User ) row.get( "$user" );
		    double rating = (double) row.get("$averageRating");
		    users.add(new UserRatingDTO(user, rating));
		}
		
		Pageable pageable = PageRequest.of(pageNum, 10);
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserRatingDTO> list;
        
        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }
        List<UserMVPDTO> userDTOs = userMapper.toUserMVPDTOlist(list);

        Page<UserMVPDTO> usersPage
          = new PageImpl<UserMVPDTO>(userDTOs, PageRequest.of(currentPage, pageSize), users.size());
		
		return usersPage;
	}
	
	public Page<UserMVPDTO> filterAll(int pageNum, FilterUserDTO filterUserDTO) {
		// dobavljanje svih
		QueryResults results = kieSession.getQueryResults( "getAllUsers" );
		Set<UserRatingDTO> users = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
		    User user = ( User ) row.get( "$user" );
		    double rating = (double) row.get("$averageRating");
		    users.add(new UserRatingDTO(user, rating));
		}
		
		// filtriranje svih
		if (!filterUserDTO.getEmail().equals("")) {
			Set<UserRatingDTO> filterUsers = new HashSet<>();
			results = kieSession.getQueryResults( "getAllUsersByEmail", filterUserDTO.getEmail());
			
			for ( QueryResultsRow row : results ) {
			    User user = ( User ) row.get( "$user" );
			    double rating = (double) row.get("$averageRating");
			    filterUsers.add(new UserRatingDTO(user, rating));
			}
			// filtriranje
			users.retainAll(filterUsers);
		} else if (!filterUserDTO.getName().equals("")) {
			Set<UserRatingDTO> filterUsers = new HashSet<>();
			results = kieSession.getQueryResults( "getAllUsersByName", filterUserDTO.getName());
			
			for ( QueryResultsRow row : results ) {
			    User user = ( User ) row.get( "$user" );
			    double rating = (double) row.get("$averageRating");
			    filterUsers.add(new UserRatingDTO(user, rating));
			}
			// filtriranje
			users.retainAll(filterUsers);
		} else if (!filterUserDTO.getTrait().equals("")) {
			Set<UserRatingDTO> filterUsers = new HashSet<>();
			results = kieSession.getQueryResults( "getAllUsersByPersonality", filterUserDTO.getTrait());
			
			for ( QueryResultsRow row : results ) {
			    User user = ( User ) row.get( "$user" );
			    double rating = (double) row.get("$averageRating");
			    filterUsers.add(new UserRatingDTO(user, rating));
			}
			// filtriranje
			users.retainAll(filterUsers);
		} else if (filterUserDTO.getLowerRating() != -1) {
			Set<UserRatingDTO> filterUsers = new HashSet<>();
			results = kieSession.getQueryResults( "getAllUsersWithRatingAbove", filterUserDTO.getLowerRating());
			
			for ( QueryResultsRow row : results ) {
				List<User> usersQuery = (List<User>) row.get( "$users" );
				List<Double> userRatings = (List<Double>) row.get( "$userRatings" );
				for (int i = 0; i < usersQuery.size(); i++) {
					filterUsers.add(new UserRatingDTO(usersQuery.get(i), userRatings.get(i)));
				}
			}
			
			// filtriranje
			users.retainAll(filterUsers);
		} else if (filterUserDTO.getUpperRating() != -1) {
			Set<UserRatingDTO> filterUsers = new HashSet<>();
			results = kieSession.getQueryResults( "getAllUsersWithRatingBelow", filterUserDTO.getUpperRating());
			
			for ( QueryResultsRow row : results ) {
				List<User> usersQuery = (List<User>) row.get( "$users" );
				List<Double> userRatings = (List<Double>) row.get( "$userRatings" );
				for (int i = 0; i < usersQuery.size(); i++) {
					filterUsers.add(new UserRatingDTO(usersQuery.get(i), userRatings.get(i)));
				}
			}
			
			// filtriranje
			users.retainAll(filterUsers);
		}
		
		Pageable pageable = PageRequest.of(pageNum, 10);
		
		List<UserRatingDTO> userList = new ArrayList<>(users);
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserRatingDTO> list;
        
        if (userList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, userList.size());
            list = userList.subList(startItem, toIndex);
        }
        List<UserMVPDTO> userDTOs = userMapper.toUserMVPDTOlist(list);

        Page<UserMVPDTO> usersPage
          = new PageImpl<UserMVPDTO>(userDTOs, PageRequest.of(currentPage, pageSize), userList.size());
		
		return usersPage;
	}
}
