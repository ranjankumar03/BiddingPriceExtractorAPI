package com.bidding.price.extractor.comparator;

import java.util.Comparator;

import com.bidding.price.extractor.dto.Bidder;

public class BidComparatorWithInitialBidValue implements Comparator<Bidder> {

	@Override
	public int compare(Bidder o1, Bidder o2) {

		if (o1.getMaxPermissableBid().equals(o2.getMaxPermissableBid()))
			return o2.getInitialBid().intValue() - o1.getInitialBid().intValue();
		
		return o2.getMaxPermissableBid().intValue() - o1.getMaxPermissableBid().intValue();
	}
}
