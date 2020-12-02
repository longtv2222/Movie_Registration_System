package presentation_layer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
				if(controller.cancelReservation(reservations.get(index))) {
				JOptionPane.showMessageDialog(null, "Reservation Cancelled");
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
