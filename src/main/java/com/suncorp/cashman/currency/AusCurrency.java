package com.suncorp.cashman.currency;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import com.suncorp.cashman.util.CashmanConstants;

public class AusCurrency extends Currency {
	public AusCurrency(List<Denomination> denominations) {
		super(denominations);
	}

	public static AusCurrency buildAusCurrency(EnumSet<AusCoins> validCoinsSet, EnumSet<AusNotes> validNotesSet) {
		List<Denomination> list = validCoinsSet.stream().map(c -> convertCoinToDenomination(c))
				.collect(Collectors.toList());
		list.addAll(validNotesSet.stream().map(n -> convertNotesToDenomination(n)).collect(Collectors.toList()));
		return new AusCurrency(list);
	}

	private static Denomination convertCoinToDenomination(AusCoins coin) {
		return new Denomination(coin.name(), coin.getValue(), CashmanConstants.CurrencyCode.AUSTRALIA_CURRENCY_CODE,
				CashmanConstants.CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL);
	}

	private static Denomination convertNotesToDenomination(AusNotes note) {
		return new Denomination(note.name(), note.getValue(), CashmanConstants.CurrencyCode.AUSTRALIA_CURRENCY_CODE,
				CashmanConstants.CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL);
	}
}
