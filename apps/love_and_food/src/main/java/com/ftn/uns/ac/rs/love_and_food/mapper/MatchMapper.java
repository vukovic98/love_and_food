package com.ftn.uns.ac.rs.love_and_food.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ftn.uns.ac.rs.love_and_food.dto.MatchDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;

public class MatchMapper implements MapperInterface<Match, MatchDTO>{

	@Override
	public Match toEntity(MatchDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchDTO toDTO(Match entity) {
		return new MatchDTO(entity.getId(), entity.getInitiator().getEmail(),
				entity.getSoulmate().getEmail(), entity.getMatchDate());
	}

	@Override
	public List<Match> toEntityList(List<MatchDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatchDTO> toDTOList(List<Match> entityList) {
		List<MatchDTO> matchDTOs = new ArrayList<MatchDTO>();
		for(Match match : entityList) {
			matchDTOs.add(toDTO(match));
		}
		return matchDTOs;
	}

}
