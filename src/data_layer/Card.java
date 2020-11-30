package data_layer;

import java.util.Calendar;

public class Card {
	private String accountNumber;
	private String ccv;
	private Calendar expiryDate;

	public Card(String accountNumber, String ccv, Calendar expiryDate) {
		this.setAccountNumber(accountNumber);
		this.setCcv(ccv);
		this.setExpiryDate(expiryDate);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public Calendar getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
