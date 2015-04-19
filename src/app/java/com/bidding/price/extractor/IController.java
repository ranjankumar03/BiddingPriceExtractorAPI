
package com.bidding.price.extractor;

import java.util.Comparator;




import com.bidding.price.extractor.dto.BidWinner;
import com.bidding.price.extractor.dto.Bidder;
import com.bidding.price.extractor.dto.Product;

public interface IController {
	
	public BidWinner findWinningBidder(Product product, Comparator<Bidder> comparator);

	public BidWinner findWinningBidder(Product product);
}
