package com.bidding.price.extractor.dto;

import java.util.ArrayList;
import java.util.List;

public final class Product {
	
	private final String productName;
	private final String productDesc;
	private final List<Bidder> bidders;
	
	public Product(String productName,String productDesc,List<Bidder> bidders){
		this.productName = productName;
		this.productDesc = productDesc;
		this.bidders = new ArrayList<Bidder>(bidders);
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
	
	public List<Bidder> getBidders() {
		return bidders;
	}
	
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productDesc="
				+ productDesc + ", bidders=" + bidders + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bidders == null) ? 0 : bidders.hashCode());
		result = prime * result + ((productDesc == null) ? 0 : productDesc.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
		Product other = (Product) obj;
		if (bidders == null) {
			if (other.bidders != null)
				return false;
		} else if (!bidders.equals(other.bidders))
			return false;
		if (productDesc == null) {
			if (other.productDesc != null)
				return false;
		} else if (!productDesc.equals(other.productDesc))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
	
	
	

}
