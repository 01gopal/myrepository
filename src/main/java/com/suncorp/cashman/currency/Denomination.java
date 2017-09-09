package com.suncorp.cashman.currency;

public class Denomination {
	private final String faceName;
	private final double faceValue;
	private final String currencyCode;
	private final String currencySymbol;
	
	public Denomination(String faceName, double faceValue, String currencyCode, String currencySymbol) {
		this.faceName = faceName;
		this.faceValue = faceValue;
		this.currencyCode = currencyCode;
		this.currencySymbol = currencySymbol;
	}
	public String getFaceName() {
		return faceName;
	}
	public double getFaceValue() {
		return faceValue;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((currencySymbol == null) ? 0 : currencySymbol.hashCode());
		result = prime * result + ((faceName == null) ? 0 : faceName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(faceValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Denomination other = (Denomination) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (currencySymbol == null) {
			if (other.currencySymbol != null)
				return false;
		} else if (!currencySymbol.equals(other.currencySymbol))
			return false;
		if (faceName == null) {
			if (other.faceName != null)
				return false;
		} else if (!faceName.equals(other.faceName))
			return false;
		if (Double.doubleToLongBits(faceValue) != Double.doubleToLongBits(other.faceValue))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Denomination [faceName=" + faceName + ", faceValue=" + faceValue + ", currencyCode=" + currencyCode
				+ ", currencySymbol=" + currencySymbol + "]";
	}
	
}
