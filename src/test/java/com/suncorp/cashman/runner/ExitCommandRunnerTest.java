package com.suncorp.cashman.runner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.suncorp.cashman.command.CommandRunnerResponse;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ExitCommandRunnerTest {
	@InjectMocks
    private ExitCommandRunner exitRunner;
	
	@Test
	public void testExecute(){
		CommandRunnerResponse response = exitRunner.execute();
		Assert.assertEquals(CommandRunnerResponse.QUIT, response);
	}
}
