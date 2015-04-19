package com.bidding.price.extractor.dto;

import java.math.BigDecimal;

public final class BidWinner {

	private final BigDecimal winnerBidValue;
	private final Bidder bidWinner;

	public BidWinner(BigDecimal bidValueOfWinner, Bidder bidWinner) {
		this.winnerBidValue = bidValueOfWinner;
		this.bidWinner = bidWinner;
	}

	public BigDecimal getBidValueOfWinner() {
		return winnerBidValue;
	}

	public Bidder getBidWinner() {
		return bidWinner;
	}

	@Override
	public String toString() {
		return "BidWwinner [bidValueOfWinner=" + winnerBidValue
				+ ", bidWinner=" + bidWinner + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bidWinner == null) ? 0 : bidWinner.hashCode());
		result = prime * result + ((winnerBidValue == null) ? 0 : winnerBidValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidWinner other = (BidWinner) obj;
		if (bidWinner == null) {
			if (other.bidWinner != null)
				return false;
		} else if (!bidWinner.equals(other.bidWinner))
			return false;
		if (winnerBidValue == null) {
			if (other.winnerBidValue != null)
				return false;
		} else if (!winnerBidValue.equals(other.winnerBidValue))
			return false;
		return true;
	}

}
