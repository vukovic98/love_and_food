package com.ftn.uns.ac.rs.love_and_food.tests.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ftn.uns.ac.rs.love_and_food.tests.love.CEPTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.FindPartnersAgeTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.LoveQueryTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.LoveReportLiarsTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.LoveReportMVPsTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.PartnerRequirementsTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.PersonalityTestTest;
import com.ftn.uns.ac.rs.love_and_food.tests.love.SoulmateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

//	LOVE TESTS

	FindPartnersAgeTest.class, 
	LoveQueryTest.class,
	LoveReportLiarsTest.class,
	LoveReportMVPsTest.class,
	PartnerRequirementsTest.class,
	PersonalityTestTest.class,
	SoulmateTest.class,
	CEPTest.class

//	FOOD TESTS

//	RestaurantRequirementsTest.class,
//	PerfectRestaurantTest.class,
//	BestGradedRestaurantsReportTest.class,
//	DecliningRestaurantsTest.class,
//	RisingRestaurantsTest.class,
//	MostVisitedRestaurantsInSeasonTest.class
})
public class TestSuite {
}