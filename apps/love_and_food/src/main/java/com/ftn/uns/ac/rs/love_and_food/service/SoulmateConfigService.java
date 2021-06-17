package com.ftn.uns.ac.rs.love_and_food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.SoulmateConfigDTO;
import com.ftn.uns.ac.rs.love_and_food.model.SoulmateConfig;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.SoulmateConfigRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class SoulmateConfigService {
	
	@Autowired
	private SoulmateConfigRepository soulmateConfigRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public SoulmateConfig findConfiguration(String userEmail) {
		
		User user = this.userRepository.findByEmail(userEmail);
		
		return soulmateConfigRepository.findByUserId(user.getId());
	}
	
	public void updateConfiguration(String userEmail, SoulmateConfigDTO soulmateConfigDTO) {
		
		User user = this.userRepository.findByEmail(userEmail);
		SoulmateConfig soulmateConfig = soulmateConfigRepository.findByUserId(user.getId());
		
		soulmateConfig.setSmokingPoints(soulmateConfigDTO.getSmokingPoints());
		soulmateConfig.setAlcoholPoints(soulmateConfigDTO.getAlcoholPoints());
		soulmateConfig.setEducationPoints(soulmateConfigDTO.getEducationPoints());
		soulmateConfig.setTraitPoints(soulmateConfigDTO.getTraitPoints());
		soulmateConfig.setIncomePoints(soulmateConfigDTO.getIncomePoints());
		soulmateConfig.setReligionPoints(soulmateConfigDTO.getReligionPoints());
		
		soulmateConfigRepository.save(soulmateConfig);
		
	}

}
