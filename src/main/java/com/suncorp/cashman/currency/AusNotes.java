package com.suncorp.cashman.currency;


public enum AusNotes {
	HUNDRED_AUD(100),
    FIFTY_AUD(50),
    TWENTY_AUD(20),
    TEN_AUD(10),
    FIVE_AUD(5);
	
	private final int value;
	
	AusNotes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
