package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import control_layer.Controller;


public class LoginView extends View {
	private TextField username;
	private TextField password;
	private JLabel usernameText;
	private JLabel passwordText;
	private String user; 
	private String pass;
	
	public LoginView(Controller controller) {
		super("Login View", controller);
		this.setLayout(null);
		
		
		username = new TextField(20);
		username.setBounds(100,30,200,25);
		password = new TextField(20);
		password.setBounds(100,80,200,25);
		usernameText = new JLabel("User");
		usernameText.setBounds(20, 30, 30, 25);
		passwordText = new JLabel("Password");
		passwordText.setBounds(20,80,80,25);
		JButton login=new JButton("Login");
		login.setBounds(100,120,80,35);
		login.addActionListener(this);
		


		this.add(username);
		this.add(password);
		this.add(usernameText);
		this.add(passwordText);
		this.add(login);
		
		
		
		frame.pack();
		frame.repaint();
	    frame.setSize(400,400);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		user = username.getText();
		pass = password.getText();
		username.setText("");
		password.setText("");
		
	}



		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			
		}
    }
