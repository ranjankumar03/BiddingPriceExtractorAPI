package com.bidding.price.extractor.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.bidding.price.extractor.IController;
import com.bidding.price.extractor.comparator.BidComparatorWithInitialBidValue;
import com.bidding.price.extractor.controller.AuctionSimulatorControllerImpl;
import com.bidding.price.extractor.dto.Bidder;
import com.bidding.price.extractor.dto.Product;

public class AuctionSimulatorControllerImplTest {
	
	@Test
	public void testBicycleBidForSuccess(){
		/* Setting Bid price for Bicycle product */
		Bidder bicycleBidder1 = new Bidder("Alice", new BigDecimal("50"), new BigDecimal("79"), new BigDecimal("3"),new Date());
		Bidder bicycleBidder2 = new Bidder("Aaron", new BigDecimal("60"), new BigDecimal("82"), new BigDecimal("2"),new Date());
		Bidder bicycleBidder3 = new Bidder("Amanda", new BigDecimal("56"), new BigDecimal("85"), new BigDecimal("5"),new Date());
		@SuppressWarnings("serial")
		List<Bidder> bidders = new ArrayList<Bidder>() {
			{
				add(bicycleBidder1);
				add(bicycleBidder2);
				add(bicycleBidder3);
			}
		};
		Product bicycleProduct = new Product("Bicycle", "Bicycle Desc", bidders);
		IController controller = new AuctionSimulatorControllerImpl();
	
		assertEquals(new BigDecimal("82"), controller.findWinningBidder(bicycleProduct).getBidValueOfWinner());
	}

	@Test
	public void testBoatBidForSuccess(){

		Bidder boatBidder1 = new Bidder("Alice", new BigDecimal("2500"), new BigDecimal("3000"), new BigDecimal("500"),  new Date());
		Bidder boatBidder2 = new Bidder("Aaron", new BigDecimal("2800"), new BigDecimal("3100"), new BigDecimal("201"),new Date());
		Bidder boatBidder3 = new Bidder("Amanda", new BigDecimal("2501"), new BigDecimal("3200"), new BigDecimal("247"),new Date());
		@SuppressWarnings("serial")
		List<Bidder> bidders = new ArrayList<Bidder>() {
			{
				add(boatBidder1);
				add(boatBidder2);
				add(boatBidder3);
			}
		};

		Product boatProduct = new Product("Boat", "Boat Desc", bidders);
		IController controller = new AuctionSimulatorControllerImpl();

		assertEquals(new BigDecimal("3001"), controller.findWinningBidder(boatProduct).getBidValueOfWinner());
	}

	@Test
	public void testScooterBidForSuccess(){

		Bidder bidder4 = new Bidder("Alice", new BigDecimal("700"), new BigDecimal("725"), new BigDecimal("2"),new Date());
		Bidder bidder5 = new Bidder("Aaron", new BigDecimal("599"), new BigDecimal("725"), new BigDecimal("15"),new Date());
		Bidder bidder6 = new Bidder("Amanda", new BigDecimal("625"), new BigDecimal("725"), new BigDecimal("8"),new Date());
		@SuppressWarnings("serial")
		List<Bidder> bidders = new ArrayList<Bidder>() {
			{
				add(bidder4);
				add(bidder5);
				add(bidder6);
			}
		};

		Product scooterProduct = new Product("Scooter", "Scooter Desc", bidders);
		IController controller = new AuctionSimulatorControllerImpl();

		assertEquals(new BigDecimal("722"), controller.findWinningBidder(scooterProduct).getBidValueOfWinner());
	}

	@Test
	public void testDynamicBidderListForSuccess() {

		Bidder bidder1 = new Bidder("Alice", new BigDecimal("2500"), new BigDecimal("3000"), new BigDecimal("500"), new Date());
		Bidder bidder2 = new Bidder("Aaron", new BigDecimal("2800"), new BigDecimal("3100"), new BigDecimal("201"), new Date());
		Bidder bidder3 = new Bidder("Amanda", new BigDecimal("2501"), new BigDecimal("3200"), new BigDecimal("247"), new Date());
		Bidder bidder4 = new Bidder("Amanda", new BigDecimal("2502"), new BigDecimal("3200"), new BigDecimal("248"), new Date());
		@SuppressWarnings("serial")
		List<Bidder> bidders = new ArrayList<Bidder>() {
			{
				add(bidder1);
				add(bidder2);
				add(bidder3);
				add(bidder4);
			}
		};

		Product product = new Product("Bicycle", "Bicycle Desc", bidders);
		IController controller = new AuctionSimulatorControllerImpl();

		assertEquals(new BigDecimal("3001"), controller.findWinningBidder(product).getBidValueOfWinner());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCondition_nullProduct() {
		
		Product bicycleProduct = null;
		IController controller = new AuctionSimulatorControllerImpl();
		
		assertEquals(new BigDecimal("82"), controller.findWinningBidder(bicycleProduct).getBidValueOfWinner());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCondition_nullBidders(){
	
		@SuppressWarnings("serial")
		List<Bidder> bidders = new ArrayList<Bidder>() {
			{
			}
		};
		Product bicycleProduct = new Product("Bicycle", "Bicycle Desc", bidders);
		IController controller = new AuctionSimulatorControllerImpl();
	
		assertEquals(new BigDecimal("82"), controller.findWinningBidder(bicycleProduct).getBidValueOfWinner());
	}
	
	@Test
	public void testTieBidder() {

		Bidder bidder1 = new Bidder("Aaron", new BigDecimal("80"), new BigDecimal("82"), new BigDecimal("2"), new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(1)));
		Bidder bidder2 = new Bidder("Alice", new BigDecimal("79"), new BigDecimal("83"), new BigDecimal("3"),new Date());
		Bidder bidder3 = new Bidder("Amanda", new BigDecimal("56"), new BigDecimal("81"), new BigDecimal("5"),new Date());
		@SuppressWarnings("serial")
		List<Bidder> tieBidders = new ArrayList<Bidder>() {
			{
				add(bidder1);
				add(bidder2);
				add(bidder3);
			}
		};
		Product product = new Product("Bicycle", "Bicycle Desc",tieBidders);
		IController controller = new AuctionSimulatorControllerImpl();

		assertEquals("Aaron", controller.findWinningBidder(product, new BidComparatorWithInitialBidValue()).getBidWinner().getName());

	}




}
