package com.mobile.food.facility.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mobile.food.facility.constants.MessageCodes;
import com.mobile.food.facility.dto.response.FoodTruckResponse;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FoodTrackServiceTest {
	
	@Autowired
	FoodTruckService foodTrackService;
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testgetFoodTruckById_success() {
		FoodTruckResponse response = foodTrackService.getFoodTruckById("AP2FC30885-C838-4E40-9DC3-6DCAD9DEC437");
		assertEquals("200", response.getStatus());
	}
	
	@Test
	public void testgetFoodTruckById_Failue() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage(MessageCodes.INVALID_ID);
		foodTrackService.getFoodTruckById("C838-4E40-9DC3-6DCAD9DEC437");
	}
	
	

}
