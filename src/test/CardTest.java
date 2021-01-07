package test;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import data_layer.Card;

public class CardTest {

	@Test
	public void constructorTest() {
		String accountNumber = "112";
		String ccv = "113";
		Calendar expiryDate = Calendar.getInstance();
		expiryDate.clear(); // For default time

		Card card = new Card(accountNumber, ccv, expiryDate);
		assertEquals(accountNumber, card.getAccountNumber());
		assertEquals(ccv, card.getCcv());
		assertEquals(expiryDate, card.getExpiryDate());
	}

}
