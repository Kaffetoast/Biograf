import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class Show {
	
	private MovieHall hall;
	private Movie movie;
	private LocalDateTime startTime;
	
	private boolean[][] seats;
	
	public Show(MovieHall hall, Movie movie, LocalDateTime startTime) {
		this.movie = movie;
		this.startTime = startTime;
		this.hall = hall;
		
		this.seats = new boolean[hall.getRows()][hall.getCols()];
	}


	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	public LocalDateTime getEndTime() {
		return startTime.plusMinutes(movie.getLength());
	}

	public boolean reserveSeat(int row, int seat) {
		if (isReserved(row, seat)) {
			System.out.println("Error: Seat is already reserved!");
			return false;
		} else {
			System.out.println("Success: Seat successfully reserved!");
			this.seats[row][seat] = true;
			return true;
		}
	}

	public boolean reserveManySeats(int row, int seat, int tickets) {

		return false;
	}

	public boolean isReserved(int row, int seat) {
		return this.seats[row][seat];
	}

	public MovieHall getHall() {
		return hall;
	}

	@Override
	public String toString() {
		Movie movie = getMovie();
		return "Hall: "+ hall + "\nTime: " + getStartTime() + "\n" + "movie: " + getMovie().getTitle() +
		"\nLength: " + movie.getLength() + "\n--------------------";
	}

}


