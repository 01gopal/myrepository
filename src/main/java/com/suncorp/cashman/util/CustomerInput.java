package com.suncorp.cashman.util;

import java.util.Scanner;

import org.springframework.beans.factory.DisposableBean;


public class CustomerInput implements DisposableBean{
	private final Scanner input;
	
	public CustomerInput() {
		input = new Scanner(System.in);
	}
	
	public String takeCustomerInput(String message) {
		printMessage(message);
		return input.next();
	}

	private void printMessage(String message) {
		System.out.printf(">> " + message);
	}

	public void destroy() throws Exception {
		if (input != null) {
			input.close();
		}
	}
}
