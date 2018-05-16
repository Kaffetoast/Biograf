import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

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

	public ArrayList<Integer> reserveManySeats(int row, int seat, int numOfSeats) {
		
		int reservations = 0;
		ArrayList <Integer> seats = new ArrayList<Integer>();
		
		for (int i = seat; i < seat+numOfSeats; i++) {
			
			if(i > this.hall.getCols()) {
				break;
			}
			if(!isReserved(row, i)) {
				this.seats[row][i] = true;
				seats.add(i);
				reservations++;
			} else {
				break;
			}
			if(reservations == numOfSeats) {
				return seats;
			}
		}
		
		for (int i = seat-1; i > seat-numOfSeats; i--) {
			
			if(i < 1) {
				return null;
			}
			if(!isReserved(row, i)) {
				this.seats[row][i] = true;
				seats.add(i);
				reservations++;
			} else {
				break;
			}
			
			if(reservations == numOfSeats) {
				return seats;
			}
		}
		return null;
	}
	
	public void displaySeats() {
		for(int i = 1; i <= this.hall.getRows(); i++) {
			
			String rowOfSeats = "";
			
			for(int j = 1; j <= this.hall.getCols(); j++) {
				if(this.seats[i][j]) {
					rowOfSeats += "X ";
				} else {
					rowOfSeats += "- ";
				}
			}
			
			System.out.println(rowOfSeats);
		}
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


