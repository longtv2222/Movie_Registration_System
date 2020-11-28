package data_layer;

public class Movie {
	private int movieID;
	private String movieName;
	private double price;
	private int duration; // Duration in minutes.

	public Movie(int movieID, String movieName, double price, int duration) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.price = price;
		this.duration = duration;
	}

	public String toString() {
		String display = "movieID: " + movieID + "\n Movie Name: " + movieName + "\t\tPrice: " + price + "\tDuration: "
				+ duration;
		return display;
	}
}
