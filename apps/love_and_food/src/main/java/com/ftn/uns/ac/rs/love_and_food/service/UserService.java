package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
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
	
	public Page<UserDTO> findAll(int pageNum) {
		QueryResults results = kieSession.getQueryResults( "getAllUsers" );
		List<User> users = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    User user = ( User ) row.get( "$user" );
		    users.add(user);
		}
		
		Pageable pageable = PageRequest.of(pageNum, 10);
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;
        
        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }
        
        List<UserDTO> userDTOs = userMapper.toDTOList(list);

        Page<UserDTO> usersPage
          = new PageImpl<UserDTO>(userDTOs, PageRequest.of(currentPage, pageSize), users.size());
		
		return usersPage;
	}
}
