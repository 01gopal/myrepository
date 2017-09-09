package com.suncorp.cashman.runner;

import org.springframework.beans.factory.annotation.Autowired;

import com.suncorp.cashman.command.CashmanCommand;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.util.CustomerInput;


public abstract class InitializationCommandRunner implements CashmanCommandRunner {
	public abstract CommandRunnerResponse execute();
}
