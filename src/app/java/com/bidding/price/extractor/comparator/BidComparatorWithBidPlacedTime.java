package com.bidding.price.extractor.comparator;

import java.util.Comparator;

import com.bidding.price.extractor.dto.Bidder;

public class BidComparatorWithBidPlacedTime implements Comparator<Bidder>{
	
	@Override
	public int compare(Bidder o1, Bidder o2) {

		if (o1.getMaxPermissableBid().equals(o2.getMaxPermissableBid()))
			return (int) (o1.getBidTime()- o2.getBidTime());
		
		return o2.getMaxPermissableBid().intValue() - o1.getMaxPermissableBid().intValue();
	}

}
