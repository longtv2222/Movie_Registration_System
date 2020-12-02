package control_layer;

//Class import
import data_layer.DBManager;
import data_layer.Movie;
import data_layer.OrdinaryUser;
import data_layer.RegisteredUser;
import data_layer.Reservation;
import data_layer.Room;
import data_layer.Theatre;
import data_layer.User;
import data_layer.Viewing;

import presentation_layer.MenuView;
import presentation_layer.SeatView;
import presentation_layer.View;
import presentation_layer.AccountView;
import presentation_layer.PaymentView;
import presentation_layer.ReservationView;

import java.awt.CardLayout;
import java.sql.SQLException;
import java.time.LocalDate;
//Library import
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import java.util.Properties;

public class Controller {
	private HashMap<Integer, Theatre> theaterList;
	private HashMap<Integer, Movie> movieList;
	private ArrayList<View> views;
	private User user;
	private JFrame GUI;
	private JPanel cards;
	private Timer timer;
	private int TicketNumber = 1000000;

	public Controller(HashMap<Integer, Theatre> theaterList, HashMap<Integer, Movie> movieList) {
		this.theaterList = theaterList;
		this.movieList = movieList;
		this.views = new ArrayList<View>();
		GUI = new JFrame("Movie Reservation Application");
		GUI.setResizable(false);
		// Intialize Day timer for sending reciepts
	}

	/**
	 * CODE FOR THIS FUNCTION BASED ON doraemon's stackoverflow post as seen here:
	 * 
	 * @source https://stackoverflow.com/questions/3649014/send-email-using-java
	 * 
	 */
	public void SendEmail(String username, String password, String recipientEmail, String ccEmail, String title,
			String message) throws AddressException, MessagingException {
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		SMTPTransport t;
		javax.mail.internet.MimeMessage msg;
		// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		// Get a Properties object
		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// props.put("mail.smtps.quitwait", "false");

		javax.mail.Session session = javax.mail.Session.getInstance(props, null);

		// Create a new message
		msg = new javax.mail.internet.MimeMessage(session);

		// Set the FROM and TO fields
		msg.setFrom(new InternetAddress(username));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());
//
		t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

	/*
	 * Load all information about theater, movie to java object.
	 */
	public void loadAllInfo() {
		try {
			DBManager.getInstance().populateMovie(movieList);
			DBManager.getInstance().populateTheater(theaterList, movieList);
			DBManager.getInstance().populateReservation(theaterList, movieList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isMoviePublic(String name) {
		Iterator<Entry<Integer, Movie>> itMovie = movieList.entrySet().iterator();
		while (itMovie.hasNext()) {
			Map.Entry<Integer, Movie> pair = itMovie.next();
			if (pair.getValue().getMovieName() == name) {
				if (pair.getValue().getMoviePublic() == true)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public void processReservation() {
		Theatre theatre = null;
		Movie movie = null;
		Room room = null;
		Viewing viewing = null;
		int theatreID = 0;
		int roomID = 0;

		// Get Account Information
		int x = ((SeatView) views.get(3)).getSelectedX();
		int y = ((SeatView) views.get(3)).getSelectedY();

		String movieStr = ((MenuView) views.get(1)).getSelectedMovie();
		String theatreStr = ((MenuView) views.get(1)).getSelectedTheatre();
		String timeStr = ((MenuView) views.get(1)).getSelectedTime();

		// Find Correct Value
		Iterator<Entry<Integer, Theatre>> itTheatre = theaterList.entrySet().iterator();
		while (itTheatre.hasNext()) {
			Map.Entry<Integer, Theatre> pair = itTheatre.next();
			if (pair.getValue().getName() == theatreStr) {
				theatre = pair.getValue();
				theatreID = (int) pair.getKey();
				break;
			}
		}

		// Find Correct Movie
		Iterator<Entry<Integer, Movie>> itMovie = movieList.entrySet().iterator();
		while (itMovie.hasNext()) {
			Map.Entry<Integer, Movie> pair = itMovie.next();
			if (pair.getValue().getMovieName() == movieStr) {
				movie = pair.getValue();
				break;
			}
		}

		// Find Correct Room
		String date = timeStr.split(" ")[0];
		int year = Integer.parseInt(date.split("/")[0]);
		int month = Integer.parseInt(date.split("/")[1]);
		int day = Integer.parseInt(date.split("/")[2]);

		String time = timeStr.split(" ")[1];
		int hours = Integer.parseInt(time.split(":")[0]);
		int minutes = Integer.parseInt(time.split(":")[1]);

		boolean exit = false;
		Iterator<Entry<Integer, Room>> itroom = theatre.getAllRooms().entrySet().iterator();
		while (itroom.hasNext() && exit == false) {
			Map.Entry<Integer, Room> pair = itroom.next();

			room = (Room) pair.getValue();
			roomID = (int) pair.getKey();
			// Extract the viewings
			ArrayList<Viewing> viewings = theatre.getRoom().get((int) pair.getKey()).getArrViewing();

			for (Viewing v : viewings) {
				// true if found correct viewing
				if (v.getMovie().getMovieName() == movieStr && v.getCalendar().get(Calendar.HOUR_OF_DAY) == hours
						&& v.getCalendar().get(Calendar.MINUTE) == minutes
						&& (v.getCalendar().get(Calendar.YEAR) - 1) == year
						&& (v.getCalendar().get(Calendar.MONTH) + 12) == month
						&& v.getCalendar().get(Calendar.DAY_OF_MONTH) == day) {
					viewing = v;
					exit = true;
					break;
				}
			}
		}

		// Double check were not over limit on the Registered User in a particular
		// viewing
		if (viewing.tooMuchRu() == true && user.getIsRegistered() == true) {
			JOptionPane.showMessageDialog(null, "Too Many Registered Users!");
		} else {
			Reservation newRes = new Reservation(movie, theatre, room, viewing, 12.99, x, y);
			if (user.getIsRegistered() == true)
				user.addReservations(newRes);
			viewing.addReservation(x, y, newRes);

			// Updating View in case the user goes back
			((SeatView) views.get(3)).displayReservations();

			// Send to Database
			reserveSeat(theatreID, roomID, viewing, x, y);

			// Send reciept
			StringBuilder sb = new StringBuilder();

			sb.append("To : " + user.getUserID() + "\n" + "Movie: " + movie.getMovieName() + "\n" + "Theatre: "
					+ theatre.getTheatreID() + "\n" + "Showing Time: " + viewing.getCalendar().get(Calendar.MONTH) + "/"
					+ viewing.getCalendar().get(Calendar.DAY_OF_MONTH) + "/" + viewing.getCalendar().get(Calendar.YEAR)
					+ " " + viewing.getCalendar().get(Calendar.HOUR_OF_DAY) + ":"
					+ viewing.getCalendar().get(Calendar.MINUTE) + "\n" + "Amount Paid: " + movie.getPrice() + "\n"
					+ "Ticket Number:" + TicketNumber++ + "\n");

			try {
				SendEmail("ensf480finalprojectemail@gmail.com", "ensfpassword1&", "ensf480finalprojectemail@gmail.com",
						"", "Your Movie Purchase", sb.toString());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean cancelReservation(Reservation r) {
		int[] res = { (r.getViewing().getCalendar().get(Calendar.YEAR) - 1),
				(r.getViewing().getCalendar().get(Calendar.MONTH) + 12),
				r.getViewing().getCalendar().get(Calendar.DAY_OF_MONTH),
				r.getViewing().getCalendar().get(Calendar.HOUR_OF_DAY),
				r.getViewing().getCalendar().get(Calendar.MINUTE) };
		Calendar calendar = Calendar.getInstance();

		int year = res[0] - calendar.get(Calendar.YEAR);
		int month = res[1] - (calendar.get(Calendar.MONTH) + 1);
		int day = res[2] - calendar.get(Calendar.DAY_OF_MONTH);
		int hour = res[3] - calendar.get(Calendar.HOUR_OF_DAY);

		int time = year * 8760 + month * 730 + day * 24 + hour;

		if (time < 71) {
			return false;
		} else {

			try {
				user.getReservations().remove(r);
				r.getViewing().removeReservation(r.getX(), r.getY());
				DBManager.getInstance().removeReservation(r, this.getUser().getUserID());
				if (!user.getIsRegistered()) {
					DBManager.getInstance().updateOrdinaryUserCreditDate((OrdinaryUser) user,
							r.getViewing().getCalendar());
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	// Functions to update the Menu Screen
	public String[] getTheatreNames() {
		ArrayList<String> theatreNames = new ArrayList<String>();
		Iterator it = theaterList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Theatre> pair = (Map.Entry) it.next();
			theatreNames.add(((Theatre) pair.getValue()).getName());
		}
		return theatreNames.toArray(new String[theatreNames.size()]);

	}

	public String[] getMovieNames(boolean loadAllMovies) {
		ArrayList<String> movieNames = new ArrayList<String>();
		Iterator it = movieList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (((Movie) pair.getValue()).getMoviePublic() == true) {
				movieNames.add(((Movie) pair.getValue()).getMovieName());
			} else if (((Movie) pair.getValue()).getMoviePublic() == false && loadAllMovies == true) {
				movieNames.add(((Movie) pair.getValue()).getMovieName());
			}

		}

		return movieNames.toArray(new String[movieNames.size()]);
	}

	public String getMovieTimes(String Theatre, String movie) {
		Theatre selectedTheatre = null;

		Iterator it = theaterList.entrySet().iterator();
		// Find the correct theatre index based off name
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			// If found get the selected theatre
			if (((Theatre) pair.getValue()).getName() == Theatre) {
				selectedTheatre = theaterList.get((int) pair.getKey());
				break;
			}
		}

		return selectedTheatre.getAllTimes(movie);
	}

	public void setViews(ArrayList<View> v) {
		this.views = v;
		cards = new JPanel(new CardLayout());
		cards.add(views.get(0), "login");
		cards.add(views.get(1), "menu");
		cards.add(views.get(2), "account");
		cards.add(views.get(3), "seat");
		cards.add(views.get(4), "payment");
		cards.add(views.get(5), "reservations");
		GUI.getContentPane().add(cards);
		GUI.pack();
		GUI.repaint();
		GUI.setSize(600, 600);
		GUI.setVisible(true);
	}

	public void changeVisibility(String visible) {
		if (visible == "menu")
			((MenuView) views.get(1)).loadInformation();

		if (visible == "account") {
			((AccountView) views.get(2)).loadAllInfo();
		}

		if (visible == "seat")
			((SeatView) views.get(3)).setUser(user);

		if (visible == "payment") {
			((PaymentView) views.get(4)).updateUser();
		}

		if (visible == "reservations") {
			((ReservationView) views.get(5)).update();
		}

		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, visible);
		GUI.repaint();
	}

	public void reserveSeat(int theaterID, int roomID, Viewing view, int x_cor, int y_cor) {
		try {
			// Need to update the database and the object
			Theatre th = theaterList.get(theaterID);
			Room room = th.getRoom().get(roomID);
			Movie movie = view.getMovie();
			Reservation r = new Reservation(movie, th, room, view, 12.99, x_cor, y_cor); // Assuming the price is 20!
			// Update Object
			// theaterList.get(theaterID).getRoom().get(roomID).getViewing(view).addReservation(x_cor,
			// y_cor, r);
			// Update database

			int movieID = -1;
			Iterator<Entry<Integer, Movie>> it = movieList.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Movie> pair = it.next();
				if (pair.getValue().getMovieName() == view.getMovie().getMovieName()) {
					movieID = pair.getKey();
					break;
				}

			}

			DBManager.getInstance().insertReservation(theaterID, roomID, view, x_cor, y_cor, user.getUserID(), movieID,
					r.getPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// TODO:
	// There is no current room selection. This means if two rooms have the same
	// movie at the same time it will
	// only select the first one

	public void updateViewInfo(String mv, String theatre, String td) {
		String date = td.split(" ")[0];
		int year = Integer.parseInt(date.split("/")[0]);
		int month = Integer.parseInt(date.split("/")[1]);
		int day = Integer.parseInt(date.split("/")[2]);

		String time = td.split(" ")[1];
		int hours = Integer.parseInt(time.split(":")[0]);
		int minutes = Integer.parseInt(time.split(":")[1]);

		Theatre selectedTheatre = null;
		int theatreID = -1;

		for (Map.Entry<Integer, Theatre> th : theaterList.entrySet()) {
			if (th.getValue().getName().equals(theatre)) {
				selectedTheatre = th.getValue();
				theatreID = th.getKey();
				break;
			}

		}

		Iterator<Entry<Integer, Room>> itroom = selectedTheatre.getAllRooms().entrySet().iterator();
		while (itroom.hasNext()) {
			Map.Entry<Integer, Room> pair = itroom.next();
			// Extract the viewings
			ArrayList<Viewing> viewings = selectedTheatre.getRoom().get((int) pair.getKey()).getArrViewing();

			for (Viewing v : viewings) {
				// true if found correct viewing
				if (v.getMovie().getMovieName() == mv && v.getCalendar().get(Calendar.HOUR_OF_DAY) == hours
						&& v.getCalendar().get(Calendar.MINUTE) == minutes
						&& (v.getCalendar().get(Calendar.YEAR) - 1) == year
						&& (v.getCalendar().get(Calendar.MONTH) + 12) == month
						&& v.getCalendar().get(Calendar.DAY_OF_MONTH) == day) {
					((SeatView) views.get(3)).setCurrentView(v, theatreID, pair.getKey());
					return;
				}
			}
		}
	}

	public boolean validateRegisteredUser(String email, String password) {
		try {
			if (DBManager.getInstance().validateRegisteredUser(email, password)) {
				// Created an object of user with given database id.
				this.user = (RegisteredUser) DBManager.getInstance().getRegisteredUser(email);
				DBManager.getInstance().populateUserCard(user); // Populate card information of user
				// Populate reservation of user with referenced object from theater list
				DBManager.getInstance().populateUserReservation(user, theaterList, movieList);

				// indicate in parent class that is registered
				((RegisteredUser) user).setPassword(password);
				user.setIsRegistered(true);

				timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						int day = ((RegisteredUser) user).getCalendar().get(Calendar.DAY_OF_MONTH);
						int month = ((RegisteredUser) user).getCalendar().get(Calendar.MONTH) + 12;
						int year = ((RegisteredUser) user).getCalendar().get(Calendar.YEAR) - 1;
						LocalDate today = LocalDate.now();

						if (today.getDayOfMonth() == day && month == today.getMonthValue() && today.getYear() == year) {
							// Send email
						}
					}
				}, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Validate ordinary user. If user doesn't exist, create ordinary user with the
	 * given email.
	 */
	public boolean validateOrdinaryUser(String email) {
		try {
			this.user = DBManager.getInstance().getOrdinaryUser(email);
			DBManager.getInstance().populateUserReservation(user, theaterList, movieList); // Populate reservation.

			Calendar r = ((OrdinaryUser) user).getCalendar();
			int[] res = { (r.get(Calendar.YEAR) - 1), (r.get(Calendar.MONTH) + 12), r.get(Calendar.DAY_OF_MONTH),
					r.get(Calendar.HOUR_OF_DAY), r.get(Calendar.MINUTE) };
			Calendar calendar = Calendar.getInstance();

			int year = res[0] - calendar.get(Calendar.YEAR);
			int month = res[1] - (calendar.get(Calendar.MONTH) + 1);
			int day = res[2] - calendar.get(Calendar.DAY_OF_MONTH);
			int hour = res[3] - calendar.get(Calendar.HOUR_OF_DAY);

			int time = year * 8760 + month * 730 + day * 24 + hour;
			System.out.println(year + "+" + month + "+" + day + "+" + hour);

			if (time < 8759) {
				((OrdinaryUser) user).setCredit(0);
			} else {

			}
			return true;
		} catch (SQLException e) {
			// System.out.println("User with given email already existed!");
			// e.printStackTrace();
			return false;
		}
	}

	public User getUser() {
		return user;
	}

}
