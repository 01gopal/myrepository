package com.suncorp.cashman.currency;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.suncorp.cashman.util.CashmanConstants.CurrencyCode;

public class CurrencyFactoryTest {
	@Test
	public void testGetCurrency() {
		CurrencyFactory factory = new CurrencyFactory();
		Currency ccy = factory.getCurrency(CurrencyCode.AUSTRALIA_CURRENCY_CODE);
		List<Denomination> dns = ccy.getDenominations();
		for (Denomination d : dns) {
			Assert.assertEquals(CurrencyCode.AUSTRALIA_CURRENCY_CODE, d.getCurrencyCode());
			Assert.assertEquals(CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL, d.getCurrencySymbol());
		}
	}
}
