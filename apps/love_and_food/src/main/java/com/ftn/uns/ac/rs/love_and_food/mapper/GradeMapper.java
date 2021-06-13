package com.ftn.uns.ac.rs.love_and_food.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.love_and_food.dto.GradeDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Grade;

@Component
public class GradeMapper implements MapperInterface<Grade, GradeDTO> {

	@Override
	public Grade toEntity(GradeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GradeDTO toDTO(Grade g) {
		return new GradeDTO(g.getRestaurant().getRestaurant_id(), g.getAmbient_grade(), g.getAtmosphere_grade(),
				g.getService_grade(), g.getLocation_grade(), g.getOverall_grade(), g.getHospitability_grade());
	}

	@Override
	public List<Grade> toEntityList(List<GradeDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GradeDTO> toDTOList(List<Grade> entityList) {
		List<GradeDTO> dtos = new ArrayList<GradeDTO>();
		
		for(Grade g : entityList) {
			dtos.add(this.toDTO(g));
		}
		
		return dtos;
	}

}
