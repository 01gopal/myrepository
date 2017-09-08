package com.suncorp.cashman.config;

import org.springframework.context.annotation.Bean;

import com.suncorp.cashman.cache.CashmanRunnerCommandCache;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.runner.ExitCommandRunner;
import com.suncorp.cashman.runner.HelpCommandRunner;
import com.suncorp.cashman.runner.InitializationCommandRunner;
import com.suncorp.cashman.runner.ReportCommandRunner;
import com.suncorp.cashman.runner.WithdrawCommandRunner;
import com.suncorp.cashman.service.AtmLockerService;
import com.suncorp.cashman.service.IAtmLockerService;
import com.suncorp.cashman.service.IWithdrawService;
import com.suncorp.cashman.service.WithdrawService;
import com.suncorp.cashman.util.CustomerInput;

public class CashmanConfig {
	@Bean
	public CashmanCommandRunner helpCommandRunner() {
		return new HelpCommandRunner();
	}
	@Bean
	public CashmanCommandRunner reportCommandRunner() {
		return new ReportCommandRunner();
	}
	@Bean
	public CashmanCommandRunner exitCommandRunner() {
		return new ExitCommandRunner();
	}
	@Bean
	public CashmanCommandRunner withdrawCommandRunner() {
		return new WithdrawCommandRunner();
	}
	@Bean
	public InitializationCommandRunner initializationCommandRunner() {
		return new InitializationCommandRunner();
	}
	@Bean
	public CashmanRunnerCommandCache cashmanRunnerCommandCache() {
		return new CashmanRunnerCommandCache();
	}
	
	@Bean
	public CustomerInput customerInput() {
		return new CustomerInput();
	}

	@Bean
	public IWithdrawService withdrawService() {
		return new WithdrawService();
	}
	
	@Bean
	public IAtmLockerService atmLockerService() {
		return new AtmLockerService();
	}
}
