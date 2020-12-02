package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control_layer.Controller;
import data_layer.Card;
import data_layer.RegisteredUser;
import data_layer.User;

public class AccountView extends View {
	private String[] creditCardList;
	private JTextField TF_Username;
	private JTextField TF_Password;
	private JTextField TF_RegDate;
	private JTextField TF_Email;
	private JTextField TF_CreditCard;
	private JTextField TF_CCV;
	private JTextField TF_ExpiryDate;

	// credit card information
	private ArrayList<String> accountNumList = new ArrayList<String>();
	private ArrayList<String> ccvList = new ArrayList<String>();
	private ArrayList<String> expiryList = new ArrayList<String>();
	private int currentCard = 0;

	public AccountView(Controller controller) {
		super("Account View", controller);
		// TODO Auto-generated constructor stub

		//
		JPanel panel_Info = new JPanel(new GridLayout(14, 2));
		JPanel panel_Button = new JPanel();
		// JPanel panel_username = new JPanel(new GridLayout(1,2));
		// JPanel panel_password = new JPanel(new GridLayout(1,2));

		// Text Fields
		TF_Username = new JTextField();
		TF_Password = new JTextField();
		TF_RegDate = new JTextField();
		TF_Email = new JTextField();
		TF_CreditCard = new JTextField();
		TF_CCV = new JTextField();
		TF_ExpiryDate = new JTextField();

		// Labels
		JLabel LB_Username = new JLabel("      Username: ");
		JLabel LB_Password = new JLabel("      Password: ");
		JLabel LB_RegDate = new JLabel("      Registration Date: ");
		JLabel LB_Email = new JLabel("      Email: ");
		JLabel LB_CreditCard = new JLabel("      Credit Card: ");
		JLabel LB_CCV = new JLabel("      CCV: ");
		JLabel LB_ExpiryDate = new JLabel("      Expiry Date: ");

		// Button
		JButton BTN_Save = new JButton("Save Changes");
		JButton BTN_Return = new JButton("Return");
		JButton BTN_NextCC = new JButton("Next Credit Card");
		JButton BTN_Reservations = new JButton("My Reservations");
		BTN_Save.addActionListener(this);
		BTN_Return.addActionListener(this);
		BTN_NextCC.addActionListener(this);
		BTN_Reservations.addActionListener(this);

		// Adding components
		// panel_username.add(LB_Username);
		// panel_username.add(TF_Username);

		// panel_password.add(LB_Password);
		// panel_password.add(TF_Password);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_Username);
		panel_Info.add(TF_Username);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_Password);
		panel_Info.add(TF_Password);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_RegDate);
		panel_Info.add(TF_RegDate);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_Email);
		panel_Info.add(TF_Email);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_CreditCard);
		panel_Info.add(TF_CreditCard);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_CCV);
		panel_Info.add(TF_CCV);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_ExpiryDate);
		panel_Info.add(TF_ExpiryDate);

		// Buttons
		panel_Button.add(BTN_Save);
		panel_Button.add(BTN_Reservations);
		panel_Button.add(BTN_Return);
		panel_Button.add(BTN_NextCC);

		this.add(BorderLayout.NORTH, panel_Info);
		this.add(BorderLayout.SOUTH, panel_Button);

		// frame.setVisible(true);
	}

	public void loadAllInfo() {
		User u = controller.getUser();

		TF_Username.setText(String.valueOf(u.getUserID()));
		TF_Email.setText(u.getEmail());

		for (Card c : u.getCards()) {
			System.out.println("dd");
			accountNumList.add(c.getAccountNumber());
			ccvList.add(c.getCcv());
			expiryList.add(
					new String(c.getExpiryDate().get(Calendar.YEAR) + "/" + c.getExpiryDate().get(Calendar.MONTH)));
		}

		// make sure there are cards before attempt to load them
		if (ccvList.size() > 0) {
			TF_CreditCard.setText(accountNumList.get(currentCard));
			TF_CCV.setText(ccvList.get(currentCard));
			TF_ExpiryDate.setText(expiryList.get(currentCard));
		}

		// Load registered user's information
		if (u.getIsRegistered() == true) {
			RegisteredUser ru = (RegisteredUser) u;
			TF_Password.setText(ru.getPassword());
			Calendar calendar = ((RegisteredUser) u).getCalendar();
			TF_RegDate.setText(new String(calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/"
					+ calendar.get(Calendar.DAY_OF_MONTH)));
		}
		// Load non registered user's information
		else {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {

		case "Save Changes":
			ReservationView x = new ReservationView(controller);
			break;

		case "My Reservations":
			if(!controller.getUser().getIsRegistered()) {
				JOptionPane.showMessageDialog(null, "WARNING:\n	15% Admin fee will be applied to any cancellations since you are not a registered user!");
			}
			controller.changeVisibility("reservations");
			break;
			
		case "Return":
			controller.changeVisibility("menu");
			break;

		case "Next Credit Card":
			TF_CreditCard.setText(accountNumList.get(currentCard));
			TF_CCV.setText(ccvList.get(currentCard));
			TF_ExpiryDate.setText(expiryList.get(currentCard));
			currentCard++;
			if (currentCard >= ccvList.size())
				currentCard = 0;
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

}
