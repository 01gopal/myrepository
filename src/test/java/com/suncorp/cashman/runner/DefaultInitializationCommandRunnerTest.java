package com.suncorp.cashman.runner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.service.IAtmLockerService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultInitializationCommandRunnerTest {
	@InjectMocks
	private DefaultInitializationCommandRunner runner;
	
	@Mock
	IAtmLockerService atmLockerService;
	
	@Test
	public void testExecute() {
		CommandRunnerResponse response = runner.execute();
		Assert.assertEquals(CommandRunnerResponse.SUCCESS, response);
	}
}
