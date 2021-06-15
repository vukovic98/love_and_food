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

import com.ftn.uns.ac.rs.love_and_food.dto.FilterAlarmDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Alarm;

@Service
public class AlarmService {
	
	@Autowired
    private KieSession kieSession;
	
	public Page<Alarm> findAllByPage(int pageNum) {
		QueryResults results = kieSession.getQueryResults( "getAllAlarms" );
		List<Alarm> alarms = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    Alarm alarm = ( Alarm ) row.get( "$alarm" );
		    alarms.add(alarm);
		}
		
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
	
	public Page<Alarm> findAllByPage(int pageNum, FilterAlarmDTO filterAlarmDTO) {
		QueryResults results = kieSession.getQueryResults( "getAllAlarmsByAlarmType", filterAlarmDTO.getAlarmType());
		List<Alarm> alarms = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    Alarm alarm = ( Alarm ) row.get( "$alarm" );
		    alarms.add(alarm);
		}
		
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
