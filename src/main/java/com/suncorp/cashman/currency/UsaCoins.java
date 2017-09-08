package com.suncorp.cashman.currency;

public enum UsaCoins {
	TWENTY_CENT(0.20),
    FIFTY_CENT(0.50),
    FIVE_CENT(0.05),
    TEN_CENT(0.10),
    ONE_USD(1),
    TWO_USD(2);
	
	private final double value;
	UsaCoins(double value) {
		this.value = value;
	}
	public double getValue() {
		return value;
	}
}
