package presentation_layer;

import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import control_layer.Controller;

public class LoginView extends View {
	private TextField username;
	private JPasswordField password;
	private JLabel usernameText;
	private JLabel passwordText;
	private String user;
	private String pass;

	public LoginView(Controller controller) {
		super("Login View", controller);
		this.setLayout(null);

		username = new TextField(20);
		username.setBounds(100, 30, 200, 25);
		password = new JPasswordField(20);
		password.setBounds(100, 80, 200, 25);
		usernameText = new JLabel("User");
		usernameText.setBounds(20, 30, 30, 25);
		passwordText = new JLabel("Password");
		passwordText.setBounds(20, 80, 80, 25);
		JButton login = new JButton("Login");
		login.setBounds(100, 120, 80, 35);
		login.addActionListener(this);

		// set default login
		username.setText("joe@gmail.com");
		password.setText("123123");

		this.add(username);
		this.add(password);
		this.add(usernameText);
		this.add(passwordText);
		this.add(login);

	}

	/*
	 * Action button for logging in.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		user = username.getText();
		pass = password.getText();

		switch (e.getActionCommand()) {

		case "Login":
			boolean status = false;
			if (pass.isEmpty()) {
				status = controller.validateOrdinaryUser(user);
				if (status)
					JOptionPane.showMessageDialog(null, "Ordinary user logged in succesfully");
				else
					JOptionPane.showMessageDialog(null, "Login Failed");
			} else {
				status = controller.validateRegisteredUser(user, pass);
				if (status)
					JOptionPane.showMessageDialog(null, "Registered user logged in succesfully");
				else
					JOptionPane.showMessageDialog(null, "Login Failed");
			}
			if (status)
				controller.changeVisibility("menu");

			break;
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

}
