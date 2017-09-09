package com.suncorp.cashman.service;

import java.util.Map;

import com.suncorp.cashman.currency.Denomination;

public interface IAtmLockerService {
	boolean isAvailableForWithdrawl();
	void printAllAvilableDenomination();
	void addDenominationCount(Denomination denomination, Integer count);
	boolean deductDenominationCount(Map<Denomination, Integer> deductMap);
	boolean deductDenominationCount(Denomination denomination, Integer count);
	Map<Denomination, Integer> calculatMapToWithdraw(double amount);
	String getAcceptedCurrency();
	void setAcceptedCurrency(String currencySymbol);
}
