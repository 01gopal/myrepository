package com.suncorp.cashman.currency;

public enum AusCoins {
	TWENTY_CENT(0.20),
    FIFTY_CENT(0.50),
    FIVE_CENT(0.05),
    TEN_CENT(0.10),
    ONE_AUD(1),
    TWO_AUD(2);
	
	private final double value;
	AusCoins(double value) {
		this.value = value;
	}
	public double getValue() {
		return value;
	}
}
