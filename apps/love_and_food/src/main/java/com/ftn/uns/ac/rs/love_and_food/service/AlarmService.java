package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.Alarm;
import com.ftn.uns.ac.rs.love_and_food.repository.AlarmRepository;

@Service
public class AlarmService {
	
	@Autowired
	private AlarmRepository alarmRepository;
	
	public Page<Alarm> findAllByPage(int pageNum) {
		ArrayList<Alarm> alarms = (ArrayList<Alarm>) this.alarmRepository.findAll();
		
		Pageable pageable = PageRequest.of(pageNum, 10);
		
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Alarm> list;

		if (alarms.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, alarms.size());
			list = alarms.subList(startItem, toIndex);
		}

		Page<Alarm> alarmsPage = new PageImpl<Alarm>(list, PageRequest.of(currentPage, pageSize),
				alarms.size());

		return alarmsPage;
	}
	
}
