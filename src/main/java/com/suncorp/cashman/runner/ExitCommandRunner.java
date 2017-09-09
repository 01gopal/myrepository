package com.suncorp.cashman.runner;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;

@CashmanCommand(name=ExitCommandRunner.COMMAND)
public class ExitCommandRunner implements CashmanCommandRunner{
	public static final String COMMAND = "EXIT";
	public CommandRunnerResponse execute() {
		System.out.println("\n:::: THANKS for visiting SunCorp ATM, Have a Nice Day!! ::::\n");
		return CommandRunnerResponse.QUIT;
	}
	
}
