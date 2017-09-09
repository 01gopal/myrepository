package com.suncorp.cashman.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.exceptions.CashmanValidationException;
import com.suncorp.cashman.util.CashmanConstants.CurrencyCode;

@RunWith(MockitoJUnitRunner.class)
public class WithdrawServiceTest {
	@InjectMocks
	private WithdrawService service;
	
	@Mock
	IAtmLockerService atmLockerService;
	
	@Before
	public void beforeTest() {
		Mockito.when(atmLockerService.getAcceptedCurrency()).thenReturn("$");
		Mockito.when(atmLockerService.isAvailableForWithdrawl()).thenReturn(true);
		Mockito.when(atmLockerService.calculatMapToWithdraw(Mockito.anyDouble())).thenReturn(buildMap());
		Mockito.when(atmLockerService.deductDenominationCount(Mockito.any())).thenReturn(true);
	}
	
	@Test
	public void testWithdrawAmountTrue() throws CashmanValidationException {
		Assert.assertTrue(service.withdrawAmount(25));
	}
	
	@Test
	public void testWithdrawAmountFalse1() throws CashmanValidationException {
		Mockito.when(atmLockerService.calculatMapToWithdraw(Mockito.anyDouble())).thenReturn(null);
		Assert.assertFalse(service.withdrawAmount(25));
	}
	
	@Test
	public void testWithdrawAmountFalse2() throws CashmanValidationException {
		Mockito.when(atmLockerService.deductDenominationCount(Mockito.any())).thenReturn(false);
		Assert.assertFalse(service.withdrawAmount(25));
	}
	@Test(expected=CashmanValidationException.class)
	public void testWithdrawAmountInvalid1() throws CashmanValidationException {
		service.withdrawAmount(0);
	}
	@Test(expected=CashmanValidationException.class)
	public void testWithdrawAmountInvalid2() throws CashmanValidationException {
		Mockito.when(atmLockerService.isAvailableForWithdrawl()).thenReturn(false);
		service.withdrawAmount(25);
	}

	private Map<Denomination, Integer> buildMap() {
		Map<Denomination, Integer> map = new HashMap<Denomination, Integer>();
		map.put(new Denomination("FIVE", 5, CurrencyCode.AUSTRALIA_CURRENCY_CODE, CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL), 5);
		return map;
	}
}
