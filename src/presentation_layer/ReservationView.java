package presentation_layer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
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
		reservations = controller.getUser().getReservations();
		JList list = new JList<Object>((reservations.toArray()));
		JFrame view=new JFrame("view");
		list.setSelectedIndex(0);  
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        list.addListSelectionListener(new ListSelectionListener()  
        {  
            public void valueChanged(ListSelectionEvent e)  
            {  
            	index=list.getSelectedIndex();
            }

        }); 
        view.setSize(300,300);
        view.add(new JScrollPane(list));
        view.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
