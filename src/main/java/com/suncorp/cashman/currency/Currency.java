package com.suncorp.cashman.currency;

import java.util.List;

public abstract class Currency {
	private final List<Denomination> denominations;
	
	public Currency(List<Denomination> denominations) {
		this.denominations = denominations;
	}
	public List<Denomination> getDenominations() {
		return denominations;
	}
}
