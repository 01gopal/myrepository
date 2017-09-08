package com.suncorp.cashman.currency;


public enum UsaNotes {
	HUNDRED_USD(100),
    FIFTY_USD(50),
    TWENTY_USD(20),
    TEN_USD(10),
    FIVE_USD(5);
	
	private final int value;
	
	UsaNotes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
