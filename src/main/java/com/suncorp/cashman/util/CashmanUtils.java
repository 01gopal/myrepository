package com.suncorp.cashman.util;

import java.util.HashMap;
import java.util.Map;

import com.suncorp.cashman.util.CashmanConstants.CurrencyCode;

public class CashmanUtils {
	private static final Map<String, String> CODE_SYMBOL_MAP;
	static {
		CODE_SYMBOL_MAP = new HashMap<String, String>();
		CODE_SYMBOL_MAP.put(CurrencyCode.AUSTRALIA_CURRENCY_CODE, CurrencyCode.AUSTRALIA_CURRENCY_SYMBOL);
		CODE_SYMBOL_MAP.put(CurrencyCode.USA_CURRENCY_CODE, CurrencyCode.USA_CURRENCY_SYMBOL);
	}
	public static boolean isEmpty(Map<?, ?> map) {
		if (map != null && map.size() > 0) {
			return false;
		}
		return true;
	}
	public static boolean isNotEmpty(Map<?, ?> map) {
		if (map != null && map.size() > 0) {
			return true;
		}
		return false;
	}
	public static String findCurrencySymbol(String currencyCode) {
		return CODE_SYMBOL_MAP.get(currencyCode);
	}
}
