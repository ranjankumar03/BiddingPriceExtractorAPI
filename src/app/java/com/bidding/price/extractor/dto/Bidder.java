package com.bidding.price.extractor.dto;

import java.math.BigDecimal;
import java.util.Date;

public final class Bidder{
	
	private final String name;
	private final BigDecimal initialBid;
	private final BigDecimal maxBid;
	private final BigDecimal autoIncrement;
	private final long bidTime;
	private final BigDecimal maxPermissableBid;
	
	public Bidder(String name,BigDecimal initialBid,BigDecimal maxBid,BigDecimal increment, Date bidPlacedTime){
		this.name = name;
		this.initialBid = initialBid;
		this.maxBid = maxBid;
		this.autoIncrement = increment;
		this.bidTime = bidPlacedTime.getTime();
		this.maxPermissableBid = calculateMaxPermissableBid();
	}
	
	public BigDecimal calculateMaxPermissableBid() {
		BigDecimal maxValue = initialBid;
		while((maxValue.add(autoIncrement)).compareTo(maxBid)==0 || (maxValue.add(autoIncrement)).compareTo(maxBid)== -1){
			maxValue = maxValue.add(autoIncrement);
		}
		return maxValue;
	}

	public String getName() {
		return name;
	}
	public BigDecimal getInitialBid() {
		return initialBid;
	}
	public BigDecimal getMaxBid() {
		return maxBid;
	}
	
	public BigDecimal getAutoIncrement() {
		return autoIncrement;
	}

	public long getBidTime() {
		return bidTime;
	}

	public BigDecimal getMaxPermissableBid() {
		return maxPermissableBid;
	}

	

	@Override
	public String toString() {
		return "Bidder [name=" + name + ", initialBid=" + initialBid
				+ ", maxBid=" + maxBid + ", autoIncrement=" + autoIncrement
				+ ", bidTime=" + bidTime + ", maxPermissableBid="
				+ maxPermissableBid + "]";
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoIncrement == null) ? 0 : autoIncrement.hashCode());
		result = prime * result + (int) (bidTime);
		result = prime * result + ((initialBid == null) ? 0 : initialBid.hashCode());
		result = prime * result + ((maxBid == null) ? 0 : maxBid.hashCode());
		result = prime * result + ((maxPermissableBid == null) ? 0 : maxPermissableBid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Bidder other = (Bidder) obj;
		if (autoIncrement == null) {
			if (other.autoIncrement != null)
				return false;
		} else if (!autoIncrement.equals(other.autoIncrement))
			return false;
		if (bidTime != other.bidTime)
			return false;
		if (initialBid == null) {
			if (other.initialBid != null)
				return false;
		} else if (!initialBid.equals(other.initialBid))
			return false;
		if (maxBid == null) {
			if (other.maxBid != null)
				return false;
		} else if (!maxBid.equals(other.maxBid))
			return false;
		if (maxPermissableBid == null) {
			if (other.maxPermissableBid != null)
				return false;
		} else if (!maxPermissableBid.equals(other.maxPermissableBid))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
