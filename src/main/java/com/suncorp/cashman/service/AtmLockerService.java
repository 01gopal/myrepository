package com.suncorp.cashman.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.suncorp.cashman.currency.Denomination;
import com.suncorp.cashman.util.CashmanConstants.CurrencyCode;

public class AtmLockerService implements IAtmLockerService {
	Map<Denomination, Integer> denominatonMap;
	private double totalAmountAvailable = 0.0;
	private String acceptedCurrencySymbol = CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL;

	public AtmLockerService() {
		this.denominatonMap = new HashMap<Denomination, Integer>();
	}

	@Override
	public void addDenominationCount(Denomination denomination, Integer count) {
		if (denominatonMap.containsKey(denomination)) {
			denominatonMap.put(denomination, denominatonMap.get(denomination) + count);
		} else {
			denominatonMap.put(denomination, count);
		}
		totalAmountAvailable += denomination.getFaceValue() * count;
	}

	@Override
	public boolean deductDenominationCount(Denomination denomination, Integer count) {
		if (denominatonMap.containsKey(denomination)) {
			if (denominatonMap.get(denomination).equals(count)) {
				denominatonMap.remove(denomination);
				totalAmountAvailable -= denomination.getFaceValue() * count;
			} else if (denominatonMap.get(denomination) > count) {
				denominatonMap.put(denomination, denominatonMap.get(denomination) - count);
				totalAmountAvailable -= denomination.getFaceValue() * count;
			} else {
				System.out.println("Not enough denomination=" + denomination + " for " + count + "count deduction ");
				return false;
			}
			return true;
		} else {
			System.out.println("Not enough denomination=" + denomination + " for " + count + "count deduction ");
			return false;
		}
	}
	
	@Override
	public boolean deductDenominationCount(Map<Denomination, Integer> deductMap) {
		for (Entry<Denomination, Integer> e : deductMap.entrySet()) {
			if(!isDenominationAvailable(e.getKey(), e.getValue())) {
				return false;
			}
		}
		for (Entry<Denomination, Integer> e : deductMap.entrySet()) {
			deductDenominationCount(e.getKey(), e.getValue());
		}
		return true;
	}

	public boolean isDenominationAvailable(Denomination denomination, Integer count) {
		if (denominatonMap.containsKey(denomination)) {
			if (denominatonMap.get(denomination) >= (count)) {
				return true;
			} else {
//				System.out.println("Not enough denomination=" + denomination + " for " + count + "count deduction ");
				return false;
			}
		} else {
//			System.out.println("Not enough denomination=" + denomination + " for " + count + "count deduction ");
			return false;
		}
	}

	@Override
	public boolean isAvailableForWithdrawl() {
		if (totalAmountAvailable > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Map<Denomination, Integer> calculatMapToWithdraw(double amount) {
		List<Denomination> list = 
				denominatonMap.keySet().stream().filter(k -> denominatonMap.get(k) > 0).collect(Collectors.toList());
		list.sort(Comparator.comparing(Denomination::getFaceValue).reversed());
		LinkedList<Denomination> denominations = new LinkedList<Denomination>(list);
		Map<Denomination, Integer> mapNeeded = new HashMap<Denomination, Integer>();
		double tempAmount = amount;
		for (int i = 0; i < denominations.size(); i++) {
			mapNeeded.clear();
			tempAmount = amount;
			for (int j = 0; j < denominations.size(); j++) {
				Denomination d = denominations.get(j);
				if (tempAmount == 0) {
					break;
				}
				if (d.getFaceValue() > amount) {
					continue;
				}
				int countNeed = (int) (tempAmount / d.getFaceValue());
				if (countNeed > getMaxCount(d)) {
					countNeed = getMaxCount(d);
				}
				tempAmount -= d.getFaceValue() * countNeed;
				mapNeeded.put(d, countNeed);
			}
			if (tempAmount == 0) {
				return mapNeeded;
			}
			denominations.addLast(denominations.removeFirst());
		}
		return Collections.emptyMap();
	}

	private int getMaxCount(Denomination d) {
		return denominatonMap.get(d);
	}

	@Override
	public void printAllAvilableDenomination() {
		String ccySumbol = "";
		for (Entry<Denomination, Integer> e : denominatonMap.entrySet()) {
			ccySumbol = e.getKey().getCurrencySymbol();
			System.out.println(ccySumbol + e.getKey().getFaceValue() + "\t x " + e.getValue() + "\t = " + ccySumbol
					+ e.getKey().getFaceValue() * e.getValue());
		}
		System.out.println("TOTAL Amount available = " + ccySumbol + totalAmountAvailable);
	}

	@Override
	public String getAcceptedCurrency() {
		return acceptedCurrencySymbol;
	}
	
	@Override
	public void setAcceptedCurrency(String acceptedCurrencySymbol) {
		this.acceptedCurrencySymbol = acceptedCurrencySymbol;
	}
	
	
}
