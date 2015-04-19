package com.bidding.price.extractor.controller;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.bidding.price.extractor.AuctionAlgorithmExecutor;
import com.bidding.price.extractor.IController;
import com.bidding.price.extractor.comparator.BidComparatorWithBidPlacedTime;
import com.bidding.price.extractor.constant.AuctionSimulatorConstants;
import com.bidding.price.extractor.dto.BidWinner;
import com.bidding.price.extractor.dto.Bidder;
import com.bidding.price.extractor.dto.Product;
import com.bidding.price.extractor.executor.AuctionAlgorithmExecutorImpl;

public final class AuctionSimulatorControllerImpl implements IController {

	private static final Logger log = Logger.getLogger(AuctionSimulatorControllerImpl.class);

	private final AuctionAlgorithmExecutor executor;
	private final Comparator<Bidder> defaultBidComparator;


	public AuctionSimulatorControllerImpl() {
		this.executor = new AuctionAlgorithmExecutorImpl();
		this.defaultBidComparator = new BidComparatorWithBidPlacedTime();
	}
	
	
	public BidWinner findWinningBidder(Product product) {
		return findWinningBidder(product, defaultBidComparator);
	}

	public BidWinner findWinningBidder(Product product, Comparator<Bidder> comparator) {

		if(product == null)
			throw new IllegalArgumentException(AuctionSimulatorConstants.INVALID_PRODUCT_CANNOT_BE_NULL);
		
		if (product.getBidders() ==null || product.getBidders().size() <= 0) 
			throw new IllegalArgumentException(AuctionSimulatorConstants.INVALID_BIDDER_COUNT_FROM_SOURCE);
		
		log.info("Finding Winning Bid for "+product.getProductName()+"having Bidder count is "+product.getBidders().size()+"from source");
		
		return executor.findWinningBidder(product, comparator);
		
	}
}
