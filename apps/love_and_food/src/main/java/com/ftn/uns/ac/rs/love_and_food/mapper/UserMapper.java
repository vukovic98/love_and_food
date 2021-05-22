package com.ftn.uns.ac.rs.love_and_food.mapper;

import java.util.List;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.model.User;

public class UserMapper implements MapperInterface<User, UserDTO>{

	@Override
	public User toEntity(UserDTO dto) {
		return new User(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getSurname(),
				dto.getDateOfBirth(), dto.getIncome(), dto.getGender(), dto.getSexualOrientation(),
				dto.getEducation(), dto.getReligion(), dto.getChildren(), dto.getDesiredRelationship(),
				dto.getLocation(), dto.isAlchocol(), dto.isSmoking());
	}

	@Override
	public UserDTO toDTO(User entity) {
		return new UserDTO(entity.getId(),entity.getName(), entity.getSurname(),
				 entity.getEmail(), entity.getPassword(), entity.getDateOfBirth(),
				entity.getGender(), entity.getSexualOrientation(), entity.getEducation(),  
				entity.getIncome(), entity.getReligion(), entity.getChildren(), entity.getDesiredRelationship(),
				entity.getLocation(), entity.isAlchocol(), entity.isSmoking(), entity.getPersonalityTraits());	}

	@Override
	public List<User> toEntityList(List<UserDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> toDTOList(List<User> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

}
