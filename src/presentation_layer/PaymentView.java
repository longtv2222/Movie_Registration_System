package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control_layer.Controller;
import data_layer.Card;
import data_layer.User;
import data_layer.Viewing;

public class PaymentView extends View {
	private JTextField TF_Name;
	private JTextField TF_CreditCard;
	private JTextField TF_CCV;
	private JTextField TF_ExpiryDate;

	private JPanel panel_Info;
	private JPanel panel_Button;

	private JComboBox<String> CB_creditCardNum;
	private String[] creditCardList;

	private Viewing view;
	private int theaterID;
	private int roomID;
	private int x_cor;
	private int y_cor;

	public PaymentView(Controller controller) {
		super("Payment View", controller);
	}

	public void updatePaymentVar(int theaterID, int roomID, Viewing view, int x_cor, int y_cor) {
		this.theaterID = theaterID;
		this.roomID = roomID;
		this.view = view;
		this.x_cor = x_cor;
		this.y_cor = y_cor;
	}

	public void updateUser() {

		if (controller.getUser().getIsRegistered() == true) {
			creditCardList = new String[controller.getUser().getCards().size()];
			for (int i = 0; i < controller.getUser().getCards().size(); i++) {
				creditCardList[i] = controller.getUser().getCards().get(i).getAccountNumber();
			}
			displayRegistered();
		} else
			displayOrdinary();
	}

	private void displayRegistered() {
		panel_Info = new JPanel(new GridLayout(3, 3));
		panel_Button = new JPanel();

		CB_creditCardNum = new JComboBox<String>(creditCardList);
		CB_creditCardNum.addActionListener(this);

		JButton BTN_Purchase = new JButton("Purchase");
		JButton BTN_Return = new JButton("Return");

		BTN_Purchase.addActionListener(this);
		BTN_Return.addActionListener(this);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(new JLabel(""));
		panel_Info.add(CB_creditCardNum);
		panel_Info.add(new JLabel(""));

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Button.add(BTN_Purchase);
		panel_Button.add(BTN_Return);

		this.add(BorderLayout.NORTH, panel_Info);
		this.add(BorderLayout.SOUTH, panel_Button);
	}

	private void displayOrdinary() {
		panel_Info = new JPanel(new GridLayout(10, 2));
		panel_Button = new JPanel();

		TF_Name = new JTextField();
		TF_CreditCard = new JTextField();
		TF_CCV = new JTextField();
		TF_ExpiryDate = new JTextField();

		JLabel LB_Name = new JLabel("      Legal Name: ");
		JLabel LB_CreditCard = new JLabel("      Credit Card: ");
		JLabel LB_CCV = new JLabel("      CCV: ");
		JLabel LB_ExpiryDate = new JLabel("      Expiry Date: ");

		JButton BTN_Purchase = new JButton("Purchase");
		JButton BTN_Return = new JButton("Return");

		BTN_Purchase.addActionListener(this);
		BTN_Return.addActionListener(this);

		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(LB_Name);
		panel_Info.add(TF_Name);

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
		panel_Info.add(new JLabel(""));
		panel_Info.add(new JLabel(""));

		panel_Info.add(BTN_Purchase);
		panel_Info.add(BTN_Return);

		this.add(BorderLayout.NORTH, panel_Info);
		this.add(BorderLayout.SOUTH, panel_Button);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "Return":
			panel_Button.removeAll();
			panel_Info.removeAll();
			controller.changeVisibility("seat");
			break;

		case "Purchase":
			panel_Button.removeAll();
			panel_Info.removeAll();
			controller.changeVisibility("seat");
			controller.reserveSeat(theaterID, roomID, view, x_cor, y_cor);
			break;

		case "comboBoxChanged":
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
	}

}
