package com.suncorp.cashman.runner;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;

@CashmanCommand(name=HelpCommandRunner.COMMAND)
public class HelpCommandRunner implements CashmanCommandRunner {
	public static final String COMMAND = "HELP";

	public CommandRunnerResponse execute() {
		System.out.println("::Enter any of the following command::");
		System.out.println("WITHDRAW - To withdraw amount");
		System.out.println("REPORT - To print total available currency");
		System.out.println("HELP - To options again");
		System.out.println("EXIT - To quit");
		return CommandRunnerResponse.SUCCESS;
	}
}
