package com.hello.core.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Order {

	private Long memberID;
	private String itemName;
	private int itemPrice;
	private int discountPrice;
	
	public int calculatePrice() {
		return itemPrice - discountPrice;
	}
	
	@Override
	public String toString() {
		return "Order [memberID=" + memberID + ", itemName=" + itemName + ", itemPrice=" + itemPrice
				+ ", discountPrice=" + discountPrice + "]";
	}
}
