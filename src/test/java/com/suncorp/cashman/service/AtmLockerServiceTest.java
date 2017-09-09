package com.suncorp.cashman.service;

import java.util.Collections;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.util.CashmanConstants.CurrencyCode;

public class AtmLockerServiceTest {
	AtmLockerService service; 
	@Before
	public void beforeTest() {
		service = new AtmLockerService();
	}
	@Test
	public void testAddDenominationCount() {
		Denomination d = buildDenomination("FIVE",5);
		service.addDenominationCount(d, 5);
		Assert.assertTrue(service.isDenominationAvailable(d, 5));
	}
	@Test
	public void testDeductDenominationCount() {
		Denomination d = buildDenomination("FIVE",5);
		service.addDenominationCount(d, 5);
		service.deductDenominationCount(d, 2);
		Assert.assertTrue(service.isDenominationAvailable(d, 3));
	}
	@Test
	public void testCalculatMapToWithdraw() {
		Denomination fiveDollar = buildDenomination("FIVE",5);
		Denomination tenDollar = buildDenomination("TEN",10);
		service.addDenominationCount(tenDollar, 10);
		service.addDenominationCount(fiveDollar, 5);
		Map<Denomination, Integer> map = service.calculatMapToWithdraw(15);
		Assert.assertNotNull(map);
		Assert.assertEquals((Integer)1, map.get(tenDollar));
		Assert.assertEquals((Integer)1, map.get(fiveDollar));
		map = service.calculatMapToWithdraw(25);
		Assert.assertNotNull(map);
		Assert.assertEquals((Integer)2, map.get(tenDollar));
		Assert.assertEquals((Integer)1, map.get(fiveDollar));
		map = service.calculatMapToWithdraw(105);
		Assert.assertNotNull(map);
		Assert.assertEquals((Integer)10, map.get(tenDollar));
		Assert.assertEquals((Integer)1, map.get(fiveDollar));
		map = service.calculatMapToWithdraw(115);
		Assert.assertNotNull(map);
		Assert.assertEquals((Integer)10, map.get(tenDollar));
		Assert.assertEquals((Integer)3, map.get(fiveDollar));
		map = service.calculatMapToWithdraw(125);
		Assert.assertNotNull(map);
		Assert.assertEquals((Integer)10, map.get(tenDollar));
		Assert.assertEquals((Integer)5, map.get(fiveDollar));
		map = service.calculatMapToWithdraw(1);
		Assert.assertEquals(Collections.emptyMap(), map);
		map = service.calculatMapToWithdraw(130);
		Assert.assertEquals(Collections.emptyMap(), map);
	}
	private Denomination buildDenomination(String name, double value) {
		return new Denomination(name, value, CurrencyCode.AUSTRALIA_CURRENCY_CODE, CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL);
	}
}
