package com.suncorp.cashman.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.runner.HelpCommandRunner;



@RunWith(MockitoJUnitRunner.class)
public class CashmanRunnerCommandCacheTest {
	@InjectMocks
	CashmanRunnerCommandCache runnerCache;
	@Mock
	ApplicationContext context;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetRunnerCommand() {
		Mockito.when(context.getBean(Mockito.any(Class.class))).thenReturn(new HelpCommandRunner());
		CashmanCommandRunner runner = runnerCache.getRunnerCommand("HELP");
		Assert.assertEquals(HelpCommandRunner.class, runner.getClass());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetRunnerCommandNewInstance() {
		Mockito.when(context.getBean(Mockito.any(Class.class))).thenThrow(Exception.class);
		CashmanCommandRunner runner = runnerCache.getRunnerCommand("HELP");
		Assert.assertEquals(HelpCommandRunner.class, runner.getClass());
	}
}
