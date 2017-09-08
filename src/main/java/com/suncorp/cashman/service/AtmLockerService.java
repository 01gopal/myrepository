package com.suncorp.cashman.service;

import java.util.HashMap;
import java.util.Map;

import com.suncorp.cashman.currency.Denomination;

public class AtmLockerService implements IAtmLockerService{
	Map<Denomination, Integer> denominatonMap;
	
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
	}
	
	@Override
	public boolean deductDenominationCount(Denomination denomination, Integer count) {
		if (denominatonMap.containsKey(denomination)) {
			if (denominatonMap.get(denomination).equals(count)) {
				denominatonMap.remove(denomination);
			} else if (denominatonMap.get(denomination) > count) {
				denominatonMap.put(denomination, denominatonMap.get(denomination) - count);
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
	public boolean isAvailable() {
		if (denominatonMap != null && denominatonMap.size() > 0) {
			return true;
		}
		return false;
	}
}
