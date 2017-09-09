package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.service.IAtmLockerService;

@CashmanCommand(name=ReportCommandRunner.COMMAND)
public class ReportCommandRunner implements CashmanCommandRunner {
	public static final String COMMAND = "REPORT";

	@Autowired
	IAtmLockerService atmLockerService;
	
	public CommandRunnerResponse execute() {
		System.out.println(":::: Suncorp ATM REPORT ::::");
		atmLockerService.printAllAvilableDenomination();
		return CommandRunnerResponse.SUCCESS;
	}
}
