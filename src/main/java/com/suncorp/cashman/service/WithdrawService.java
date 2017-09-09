package com.suncorp.cashman.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.exceptions.CashmanValidationException;
import com.suncorp.cashman.util.CashmanUtils;

public class WithdrawService implements IWithdrawService {

	@Autowired
	IAtmLockerService atmLockerService;
	
	public void withdrawAmount(double amount) throws CashmanValidationException {
		String ccySymbol = atmLockerService.getAcceptedCurrency();
		validateAmount(ccySymbol, amount);
		Map<Denomination, Integer> withdrawMap = atmLockerService.calculatMapToWithdraw(amount);
//		System.out.println(withdrawMap);
		if (CashmanUtils.isEmpty(withdrawMap)) {
			System.out.println("We are Sorry, It is not possible for us to despense amount = " + ccySymbol + amount + "!!");
		} else if (atmLockerService.deductDenominationCount(withdrawMap)) {
			System.out.println("Please collect your amount = " + ccySymbol + amount);
		} else {
			System.out.println("Not engough cash in ATM for amount = " + amount + ", try again with less amount!!");
		}
	}


	private void validateAmount(String ccySymbol, double amount) throws CashmanValidationException {
		if (amount <= 0) {
			throw new CashmanValidationException("Entered amount (" + ccySymbol + amount +  ") negative or zero");
		} else if (!atmLockerService.isAvailableForWithdrawl()) {
			throw new CashmanValidationException("ATM is Empty or Not available for withdrawal!");
		}
	}

}
