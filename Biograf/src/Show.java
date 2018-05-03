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
		this.setHall(hall);
		
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

		boolean currentSeat = this.seats[row][seat];
		if (currentSeat) {
			System.out.println("Error: Seat is already reserved!");
			return false;
		} else {
			System.out.println("Success: Seat successfully reserved!");
			this.seats[row][seat] = true;
			return true;
		}
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public MovieHall getHall() {
		return hall;
	}

	public void setHall(MovieHall hall) {
		this.hall = hall;
	}

	@Override
	public String toString() {
		return "[hall=" + hall + ", movie=" + movie.getTitle() + ", startTime=" + startTime + "]";
	}

}


