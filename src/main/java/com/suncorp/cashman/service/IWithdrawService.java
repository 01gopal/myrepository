package com.suncorp.cashman.service;

import com.suncorp.cashman.exceptions.CashmanValidationException;

public interface IWithdrawService {
	public void withdrawAmount(double amount) throws CashmanValidationException;
}
