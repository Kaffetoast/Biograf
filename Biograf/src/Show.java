import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

public class Show {
	
	private MovieHall hall;
	private Movie movie;
	private LocalDateTime startTime;
	private int id;
	
	private boolean[][] seats;
	
	public Show(int id, MovieHall hall, Movie movie, LocalDateTime startTime) {
		this.movie = movie;
		this.startTime = startTime;
		this.hall = hall;
		this.id = id;
		this.seats = new boolean[hall.getRows()+1][hall.getCols()+1];
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

	public boolean reserveManySeats(int row, int seat, int numOfSeats) {
		
		int reservations = 0;
		
		for (int i = seat; i < seat+numOfSeats; i++) {
			
			if(i > this.hall.getCols()) {
				break;
			}
			if(!isReserved(row, i)) {
				this.seats[row][i] = true;
				reservations++;
			} else {
				break;
			}
			if(reservations == numOfSeats) {
				return true;
			}
		}
		
		for (int i = seat-1; i > seat-numOfSeats; i--) {
			
			if(i < 0) {
				return false;
			}
			if(!isReserved(row, i)) {
				this.seats[row][i] = true;
				reservations++;
			} else {
				break;
			}
			
			if(reservations == numOfSeats) {
				return true;
			}
		}
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		Movie movie = getMovie();
		return "Movie: " + getMovie().getTitle() + "\nHall: "+ hall + "\nTime: " + this.getStartTime().format(formatter) + "\nLength: " + movie.getLength() + "\nShow id: "+ id + "\n--------------------";
	}


	public int getId() {
		return this.id;
	}

}


