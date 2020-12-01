package data_layer;

public class Movie {
	private int movieID;
	private String movieName;
	private double price;
	private int duration; // Duration in minutes.
	private boolean isPublic;

	public Movie(int movieID, String movieName, double price, int duration, boolean isPublic) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.price = price;
		this.duration = duration;
		this.isPublic = isPublic;
	}

	public String toString() {
		String display = "movieID: " + movieID + "\n Movie Name: " + movieName + "\t\tPrice: " + price + "\tDuration: "
				+ duration;
		return display;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public void setMoviePublic(boolean b) {
		isPublic = b;
	}
	
	public boolean getMoviePublic() {
		return isPublic;
	}
}
