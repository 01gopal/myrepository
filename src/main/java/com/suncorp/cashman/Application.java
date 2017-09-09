package com.suncorp.cashman;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suncorp.cashman.cache.CashmanRunnerCommandCache;
import com.suncorp.cashman.command.CashmanCommandRunner;
import com.suncorp.cashman.command.CommandRunnerResponse;
import com.suncorp.cashman.config.CashmanConfig;
import com.suncorp.cashman.exceptions.CashmanExecutionException;
import com.suncorp.cashman.runner.DefaultInitializationCommandRunner;
import com.suncorp.cashman.runner.HelpCommandRunner;
import com.suncorp.cashman.util.CustomerInput;

public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CashmanConfig.class);
		CashmanRunnerCommandCache cache = context.getBean(CashmanRunnerCommandCache.class);
		CustomerInput input = context.getBean(CustomerInput.class);
		try {
			CommandRunnerResponse response = executeCommand(DefaultInitializationCommandRunner.COMMAND, cache);
			if (response.equals(CommandRunnerResponse.FAILED)) {
				System.out.println("Unable to initialize ATM, EXIT!!");
				return;
			}

			System.out.println("\n:::: Welcome to Suncorp ATM ::::\n");
			response = executeCommand(HelpCommandRunner.COMMAND, cache);
			while (response != null && !response.equals(CommandRunnerResponse.QUIT)) {
				try {
					System.out.println("\n------------------------------------\n");
					String userCommand = input.takeCustomerInput("Enter command: ");
					response = executeCommand(userCommand, cache);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Something bad happend, try again!!");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}
	private static CommandRunnerResponse executeCommand(String command, CashmanRunnerCommandCache cache) {
		CashmanCommandRunner runner = cache.getRunnerCommand(command);
		if (runner != null) {
			return runner.execute();
		} else {
			System.out.println("Invalid command = " + command + ", Try again!!");
			return executeCommand(HelpCommandRunner.COMMAND, cache);
		}
	}
}
