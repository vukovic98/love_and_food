package com.ftn.uns.ac.rs.love_and_food.tests.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ftn.uns.ac.rs.love_and_food.tests.FindPartnersAgeTest;
import com.ftn.uns.ac.rs.love_and_food.tests.LoveReportLiarsTest;
import com.ftn.uns.ac.rs.love_and_food.tests.LoveReportMVPsTest;
import com.ftn.uns.ac.rs.love_and_food.tests.MatchesByTimeIntervalTemplateTest;
import com.ftn.uns.ac.rs.love_and_food.tests.PartnerRequirementsTest;
import com.ftn.uns.ac.rs.love_and_food.tests.PersonalityTestTest;
import com.ftn.uns.ac.rs.love_and_food.tests.SoulmateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FindPartnersAgeTest.class, 
	LoveReportLiarsTest.class,
	LoveReportMVPsTest.class,
	MatchesByTimeIntervalTemplateTest.class,
	PartnerRequirementsTest.class,
	PersonalityTestTest.class,
	SoulmateTest.class
})
public class LoveSuite {
}