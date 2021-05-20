package com.ftn.uns.ac.rs.love_and_food.mapper;

import java.util.List;

public interface MapperInterface<E,DTO> {
	
	E toEntity(DTO dto);
	
	DTO toDTO(E entity);
	
	List<E> toEntityList(List<DTO> dtoList);
	
	List<DTO> toDTOList(List<E> entityList);

}
