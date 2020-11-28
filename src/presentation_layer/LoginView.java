package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
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
	
	public LoginView(Controller controller) {
		super("Login View", controller);
		this.setLayout(null);
		
		
		username = new TextField("");
		password = new TextField("");
		usernameText = new JLabel("sssss");
		usernameText.setBounds(20, 30, 30, 30);
		
		JLabel label = new JLabel("Login: ");
		label.setBounds(20, 50, 30, 30);
		
		this.add(new JLabel("Login: "));
		this.add(new JLabel("Password: "));
		this.add(label);

		this.add(username);
		this.add(password);
		this.add(usernameText);
		
		//frame.add(SL);
		
		frame.pack();
		frame.repaint();
	    frame.setSize(400,400);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		
		
		//cmp.draw(g);
	}
	
	

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//usernameText.draw(g);
		//cmp = password = new TextBox(cmp, 20, 100, 200, 30);
		
		System.out.println("dd");
	}

}
