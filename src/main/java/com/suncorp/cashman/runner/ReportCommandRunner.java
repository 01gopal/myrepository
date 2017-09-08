package com.suncorp.cashman.runner;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;

@CashmanCommand(name=ReportCommandRunner.COMMAND)
public class ReportCommandRunner implements CashmanCommandRunner {
	public static final String COMMAND = "REPORT";

	public CommandRunnerResponse execute() {
		System.out.println("Do report..");
		return CommandRunnerResponse.SUCCESS;
	}
}
