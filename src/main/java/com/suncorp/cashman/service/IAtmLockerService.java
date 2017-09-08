package com.suncorp.cashman.service;

import com.suncorp.cashman.currency.Denomination;

public interface IAtmLockerService {
	public boolean isAvailable();
	public void addDenominationCount(Denomination denomination, Integer count);
	public boolean deductDenominationCount(Denomination denomination, Integer count);
}
