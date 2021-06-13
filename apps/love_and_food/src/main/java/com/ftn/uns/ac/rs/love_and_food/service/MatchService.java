package com.ftn.uns.ac.rs.love_and_food.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.DateRangeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.MatchDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.MatchMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.repository.UserMatchRepository;

@Service
public class MatchService {

	@Autowired
	private UserMatchRepository matchRepository;

	@Autowired
	private KieSession kieSession;
	
	private final MatchMapper matchMapper = new MatchMapper();

	public Match findByInitiator(Long id) {
		return this.matchRepository.findByInitiator(id);
	}

	public Page<MatchDTO> findAll(int pageNum) {
		QueryResults results = kieSession.getQueryResults("getAllMatches");
		List<Match> matches = new ArrayList<>();

		for (QueryResultsRow row : results) {
			Match match = (Match) row.get("$match");
			matches.add(match);
		}
		
		Pageable pageable = PageRequest.of(pageNum, 10);
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Match> list;
        
        if (matches.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, matches.size());
            list = matches.subList(startItem, toIndex);
        }
        
        List<MatchDTO> matchDTOs = matchMapper.toDTOList(list);

        Page<MatchDTO> matchPage
          = new PageImpl<MatchDTO>(matchDTOs, PageRequest.of(currentPage, pageSize), matches.size());
		

		return matchPage;
	}

	public List<Match> findInRange(DateRangeDTO dateRangeDTO) {
		try {
			InputStream template = new FileInputStream(
					"..\\drools-spring-kjar\\src\\main\\resources\\rules\\templates\\matchesByTimeInterval.drt");

			// Compile template to generate new rules
			List<DateRangeDTO> arguments = new ArrayList<>();
			arguments.add(dateRangeDTO);
			ObjectDataCompiler compiler = new ObjectDataCompiler();
			String drl = compiler.compile(arguments, template);

			// Save rule to drl file
			FileOutputStream drlFile = new FileOutputStream(
					new File("..\\drools-spring-kjar\\src\\main\\resources\\rules\\matchesByTimeInterval.drl"));
			drlFile.write(drl.getBytes());
			drlFile.close();

			// Update Rules project
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
			request.setGoals(Arrays.asList("clean", "install"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M2_HOME")));
			invoker.execute(request);

			// Fire new rule
			List<Match> matches = new ArrayList<Match>();
			kieSession.setGlobal("resultMatches", matches);
			kieSession.getAgenda().getAgendaGroup("match-interval").setFocus();
			kieSession.fireAllRules();
			return matches;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean hasAMatch(long id) {
		int count = this.matchRepository.hasAMatch(id);

		return count > 0 ? true : false;
	}
}
