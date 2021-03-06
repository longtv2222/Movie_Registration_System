package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control_layer.Controller;
import data_layer.OrdinaryUser;
import data_layer.Reservation;

public class ReservationView extends View{
	
	private int index;
	private ArrayList<Reservation> reservations;

	public ReservationView(Controller controller) {
		super("", controller);
	}
	
	public void update() {
		this.removeAll();
		this.setLayout(new GridLayout(2,1));
		reservations = controller.getUser().getReservations();
		JList<Object> list = new JList<Object>((reservations.toArray()));
		list.setSelectedIndex(0);
		index=0;
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        list.addListSelectionListener(new ListSelectionListener()  
        {  
            public void valueChanged(ListSelectionEvent e)  
            {  
            	index=list.getSelectedIndex();
            }

        }); 
       
        JButton BTN_cancel = new JButton("Cancel");
        JButton BTN_return = new JButton("Return");
        BTN_cancel.addActionListener(this);
        BTN_return.addActionListener(this);
        
        JPanel BTN=new JPanel();
        		BTN.add(BTN_cancel);
        		BTN.add(BTN_return);
       
        		
        this.add(new JScrollPane(list));
        this.add(BTN);
    
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {	
	
			case "Cancel":
				if(reservations.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You have no reservations");
				}else {
					//Must make the string before sending it
					StringBuilder sb = new StringBuilder();
					sb.append("You have cancelled your ticket for " + reservations.get(index).getViewing().getMovie().getMovieName() + 
							" on: " + reservations.get(index).getViewing().getCalendar().get(Calendar.MONTH) + "/" + reservations.get(index).getViewing().getCalendar().get(Calendar.DAY_OF_MONTH));
					if(!controller.getUser().getIsRegistered()) {
						double credit=((OrdinaryUser)controller.getUser()).getCredit();
						((OrdinaryUser)controller.getUser()).setCredit(credit+reservations.get(index).getViewing().getMovie().getPrice()*0.85);
					}
					
					if(controller.cancelReservation(reservations.get(index))) {
						
						try {
							
							controller.SendEmail("ensf480finalprojectemail@gmail.com", "ensfpassword1&","ensf480finalprojectemail@gmail.com", ""
									, "Your Cancellation", sb.toString());
							JOptionPane.showMessageDialog(null, "Reservation Cancelled");
						} catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}else {
					JOptionPane.showMessageDialog(null, "Reservation Not Cancelled: Movie is within 72 hrs");
				}
				}
				controller.changeVisibility("account");
			break;
			
			case "Return":
				controller.changeVisibility("account");
				break;
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
