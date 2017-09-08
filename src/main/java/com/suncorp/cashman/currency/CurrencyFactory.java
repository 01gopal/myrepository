package com.suncorp.cashman.currency;

import java.util.EnumSet;

import com.suncorp.cashman.exceptions.CashmanExecutionException;
import com.suncorp.cashman.util.CashmanConstants;

public class CurrencyFactory {
	public Currency getCurrency(String currencyCode) {
		switch (currencyCode) {
		case CashmanConstants.CurrencyCode.AUSTRALIA_CURRENCY_CODE:
			return AusCurrency.buildAusCurrency(EnumSet.allOf(AusCoins.class), EnumSet.allOf(AusNotes.class));
		case CashmanConstants.CurrencyCode.USA_CURRENCY_CODE:
			return UsaCurrency.buildUsaCurrency(EnumSet.allOf(UsaCoins.class), EnumSet.allOf(UsaNotes.class));
		default:
			throw new CashmanExecutionException("Invalid currency type : " + currencyCode);
		}
	}
}
