package com.bidding.price.extractor.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.bidding.price.extractor.AuctionAlgorithmExecutor;
import com.bidding.price.extractor.comparator.BidComparatorWithBidPlacedTime;
import com.bidding.price.extractor.comparator.BidComparatorWithInitialBidValue;
import com.bidding.price.extractor.dto.Bidder;
import com.bidding.price.extractor.dto.Product;
import com.bidding.price.extractor.executor.AuctionAlgorithmExecutorImpl;

public class AuctionAlgorithmExecutorImplTest {

	@SuppressWarnings("serial")
	
	
	/*@Test
	public void testBicycleBidForSuccessLoadTest(){

		int max_bids[] = new int[] { 1, 300, 400 };
		for (int bidCount : max_bids) {
			List<Bidder> bidderListProd1 = new ArrayList<Bidder>();
			Bidder expectedWinningBid = getBids(bidCount, bidderListProd1);
			Product productBicycle = new Product("Bicycle", "Bicycle Desc",
					bidderListProd1);
			AuctionAlgorithmExecutor executor = new AuctionAlgorithmExecutorImpl();
			com.sapient.state.street.auction.simulator.dto.Bidder bidder=executor.findWinningBidder(productBicycle);	
			assertEquals(expectedWinningBid.getName(), bidder.getName());
		}

	}

	private Bidder getBids(int bidCount, List<Bidder> bidderListProd1) {

		Random random = new Random(bidCount);
		Bidder wbidder=null;
		int lastFactor = 0;
		for (int index = 0; index <= bidCount; index++) {
			int factor = Math.round(random.nextInt() * (index));
			Bidder bidder = new Bidder("A" + factor, factor * 7, factor * 19,
					factor * 3,System.currentTimeMillis(),0);
			bidderListProd1.add(bidder);
			if (factor > lastFactor) {
				wbidder = bidder;
			}
		}
		return wbidder;
	}*/
	
	@Test
	public void testWinner_singleBidder() {

		Bidder bidder1 = new Bidder("Alice", new BigDecimal("79"), new BigDecimal("83"), new BigDecimal("3"), new Date());
		List<Bidder> bidder = new ArrayList<Bidder>() {
			{
				add(bidder1);
			}
		};
		Product product = new Product("Bicycle", "Bicycle Desc",
				bidder);

		AuctionAlgorithmExecutor executor = new AuctionAlgorithmExecutorImpl();

		assertEquals("Alice", executor.findWinningBidder(product, new BidComparatorWithBidPlacedTime()).getBidWinner().getName());
		assertEquals(new BigDecimal("79"), executor.findWinningBidder(product, new BidComparatorWithBidPlacedTime()).getBidValueOfWinner());
	}
	
	@Test
	public void testTieBidder_BidPlacedTime() {

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

		AuctionAlgorithmExecutor executor = new AuctionAlgorithmExecutorImpl();

		assertEquals("Alice", executor.findWinningBidder(product, new BidComparatorWithBidPlacedTime()).getBidWinner().getName());

	}
	
	@Test
	public void testTieBidder_BidInitialValue() {

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

		AuctionAlgorithmExecutor executor = new AuctionAlgorithmExecutorImpl();

		assertEquals("Aaron", executor.findWinningBidder(product, new BidComparatorWithInitialBidValue()).getBidWinner().getName());

	}


}
