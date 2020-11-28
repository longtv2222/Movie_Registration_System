package data_layer;

import java.util.Calendar;

public class Card {
	private String accountNumber;
	private String ccv;
	private Calendar expiryDate;

	public Card(String accountNumber, String ccv, Calendar expiryDate) {
		this.accountNumber = accountNumber;
		this.ccv = ccv;
		this.expiryDate = expiryDate;
	}
}
