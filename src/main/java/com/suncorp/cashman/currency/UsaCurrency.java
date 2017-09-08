package com.suncorp.cashman.currency;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import com.suncorp.cashman.util.CashmanConstants;

public class UsaCurrency extends Currency {
	public UsaCurrency(List<Denomination> denominations) {
		super(denominations);
	}

	public static UsaCurrency buildUsaCurrency(EnumSet<UsaCoins> validCoinsSet, EnumSet<UsaNotes> validNotesSet) {
		List<Denomination> list = validCoinsSet.stream().map(c -> convertCoinToDenomination(c))
				.collect(Collectors.toList());
		list.addAll(validNotesSet.stream().map(n -> convertNotesToDenomination(n)).collect(Collectors.toList()));
		return new UsaCurrency(list);
	}

	private static Denomination convertCoinToDenomination(UsaCoins coin) {
		return new Denomination(coin.name(), coin.getValue(), CashmanConstants.CurrencyCode.USA_CURRENCY_CODE,
				CashmanConstants.CurrencyCode.USA_CURRENCY_SYMBOL);
	}

	private static Denomination convertNotesToDenomination(UsaNotes note) {
		return new Denomination(note.name(), note.getValue(), CashmanConstants.CurrencyCode.USA_CURRENCY_CODE,
				CashmanConstants.CurrencyCode.USA_CURRENCY_SYMBOL);
	}

}
