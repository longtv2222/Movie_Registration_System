package data_layer;

import java.util.Calendar;

public class RegisteredUser extends User {
	private String userName;
	private String password;

	public RegisteredUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public void createAnnualInvoice() {
	}
}
