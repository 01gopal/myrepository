package com.suncorp.cashman.runner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.exceptions.CashmanValidationException;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.service.IWithdrawService;
import com.suncorp.cashman.util.CustomerInput;

@RunWith(MockitoJUnitRunner.class)
public class WithdrawCommandRunnerTest {
	@InjectMocks
	private WithdrawCommandRunner runner;
	@Mock
	CustomerInput input;
	
	@Mock
	IAtmLockerService atmLockerService;
	
	@Mock
	IWithdrawService withdrawService;
	
	@Before
	public void beforeTest() {
		Mockito.when(atmLockerService.getAcceptedCurrency()).thenReturn("$");
	}
	
	@Test
	public void testExecute(){
		Mockito.when(input.takeCustomerInput(Mockito.anyString())).thenReturn("500");
		CommandRunnerResponse response = runner.execute();
		Assert.assertEquals(CommandRunnerResponse.SUCCESS, response);
	}
	@Test
	public void testExecuteZeroAmount(){
		Mockito.when(input.takeCustomerInput(Mockito.anyString())).thenReturn("0");
		CommandRunnerResponse response = runner.execute();
		Assert.assertEquals(CommandRunnerResponse.FAILED, response);
	}
	@Test
	public void testExecuteInvalidAmount(){
		Mockito.when(input.takeCustomerInput(Mockito.anyString())).thenReturn("abc");
		CommandRunnerResponse response = runner.execute();
		Assert.assertEquals(CommandRunnerResponse.FAILED, response);
	}
	@Test
	public void testExecuteException() throws CashmanValidationException{
		Mockito.when(input.takeCustomerInput(Mockito.anyString())).thenReturn("50");
		Mockito.doThrow(Exception.class).when(withdrawService).withdrawAmount(Mockito.anyDouble());
		CommandRunnerResponse response = runner.execute();
		Assert.assertEquals(CommandRunnerResponse.FAILED, response);
	}
}
