package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control_layer.Controller;

public class AccountView extends View{
	private String[] creditCardList;
	
	
	
	public AccountView(Controller controller) {
		super("Account View", controller);
		// TODO Auto-generated constructor stub
		//frame = new JFrame("Account View");
        //frame.setSize(400, 400);

        //
        JPanel panel_Info = new JPanel(new GridLayout(14,2));
        JPanel panel_Button = new JPanel();
        //JPanel panel_username = new JPanel(new GridLayout(1,2));
        //JPanel panel_password = new JPanel(new GridLayout(1,2));
		
        //Text Fields
        JTextField TF_Username = new JTextField();
        JTextField TF_Password = new JTextField();
        JTextField TF_RegDate = new JTextField();
        JTextField TF_Email = new JTextField();
        JTextField TF_CreditCard = new JTextField();
        JTextField TF_CCV = new JTextField();
        JTextField TF_ExpiryDate = new JTextField();
        
        
        //Labels
        JLabel LB_Username = new JLabel("      Username: ");
        JLabel LB_Password = new JLabel("      Password: ");
        JLabel LB_RegDate = new JLabel("      Registration Date: ");
        JLabel LB_Email = new JLabel("      Email: ");
        JLabel LB_CreditCard = new JLabel("      Credit Card: ");
        JLabel LB_CCV = new JLabel("      CCV: ");
        JLabel LB_ExpiryDate = new JLabel("      Expiry Date: ");
        
        //Button
        JButton BTN_Save = new JButton("Save Changes");
        JButton BTN_Return = new JButton("Return");
        JButton BTN_NextCC = new JButton("Next Credit Card");
        BTN_Save.addActionListener(this);
        BTN_Return.addActionListener(this);
        BTN_NextCC.addActionListener(this);
        
        //Adding components
        //panel_username.add(LB_Username);
        //panel_username.add(TF_Username);
       
        
        //panel_password.add(LB_Password);
        //panel_password.add(TF_Password);
       
        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));
        
        panel_Info.add(LB_Username);      panel_Info.add(TF_Username);
       
        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));
        
        panel_Info.add(LB_Password);      panel_Info.add(TF_Password);
        
        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));
        
        panel_Info.add(LB_RegDate);       panel_Info.add(TF_RegDate);
        
        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));

        panel_Info.add(LB_Email);         panel_Info.add(TF_Email);
        
        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));
        
        panel_Info.add(LB_CreditCard);    panel_Info.add(TF_CreditCard);
        
        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));
        
        panel_Info.add(LB_CCV);           panel_Info.add(TF_CCV);

        panel_Info.add(new JLabel(""));   panel_Info.add(new JLabel(""));
        
        panel_Info.add(LB_ExpiryDate);    panel_Info.add(TF_ExpiryDate);
        
        //Buttons
        panel_Button.add(BTN_Save);
        panel_Button.add(BTN_Return);
        panel_Button.add(BTN_NextCC);
        
        this.add(BorderLayout.NORTH, panel_Info);
        this.add(BorderLayout.SOUTH, panel_Button);
        
        //frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		
			case "Save Changes":
				break;
				
			case "Return":
				
				controller.changeVisibility("menu");
				break;
				
			case "Next Credit Card":
				break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
