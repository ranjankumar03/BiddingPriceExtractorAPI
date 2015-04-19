package com.bidding.price.extractor.executor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;

import com.bidding.price.extractor.AuctionAlgorithmExecutor;
import com.bidding.price.extractor.dto.BidWinner;
import com.bidding.price.extractor.dto.Bidder;
import com.bidding.price.extractor.dto.Product;

public class AuctionAlgorithmExecutorImpl implements AuctionAlgorithmExecutor {

	@Override
	public BidWinner findWinningBidder(Product product, Comparator<Bidder> comparator) {

		if (product.getBidders().size() == 1)
			return new BidWinner(product.getBidders().get(0).getInitialBid(), product.getBidders().get(0));

		return processBids(product, comparator);
	}

	
	private BidWinner processBids(Product product, Comparator<Bidder> comparator) {

		Collections.sort(product.getBidders(), comparator);

		BidWinner maxBidderObj = getWinningBidder(product);

		return maxBidderObj;
	}

	private BidWinner getWinningBidder(Product product) {

		BigDecimal winnerMaxFinalValue = product.getBidders().get(0).getMaxPermissableBid();
		BigDecimal runnerUpMaxFinalValue = product.getBidders().get(1).getMaxPermissableBid();
		BigDecimal incrementFactor = product.getBidders().get(0).getAutoIncrement();
		BigDecimal factor = (winnerMaxFinalValue.subtract(runnerUpMaxFinalValue)).divide(incrementFactor,3, RoundingMode.CEILING);
		BigDecimal winningBidValue = winnerMaxFinalValue.subtract(new BigDecimal(factor.intValue()*incrementFactor.intValue()));
		
		if (winningBidValue.compareTo(runnerUpMaxFinalValue)==0 && winnerMaxFinalValue.compareTo(runnerUpMaxFinalValue)!=0) {
			winningBidValue = winningBidValue.add(incrementFactor);
		}
		return new BidWinner(winningBidValue, product.getBidders().get(0));
	}
}
