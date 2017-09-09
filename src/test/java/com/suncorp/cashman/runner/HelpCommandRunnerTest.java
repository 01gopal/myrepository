package com.suncorp.cashman.runner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.suncorp.cashman.command.CommandRunnerResponse;

@RunWith(MockitoJUnitRunner.class)
public class HelpCommandRunnerTest {
	@InjectMocks
	private HelpCommandRunner runner;

	@Test
	public void testExecute(){
		CommandRunnerResponse response = runner.execute();
		Assert.assertEquals(CommandRunnerResponse.SUCCESS, response);
	}
}
