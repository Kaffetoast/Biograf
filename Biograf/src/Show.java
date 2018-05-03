import java.time.LocalDateTime;

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

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	
}


