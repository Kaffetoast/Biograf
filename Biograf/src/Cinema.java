import java.time.LocalDateTime;

public class Cinema {

	public static void main(String[] args) {
		
		MovieHall liten = new MovieHall("Liten", 10, 25);
		MovieHall medel = new MovieHall("Medel", 20, 32);
		MovieHall stor = new MovieHall("Stor", 30, 40);
		
		Movie movie = new Movie("ETT", 180);
		Show show = new Show(liten, movie, LocalDateTime.now());
		
		Movie movie2 = new Movie("TVA", 180);
		Show show2 = new Show(liten, movie2, LocalDateTime.now());
		
		liten.addShow(show);
		liten.addShow(show2);
		
		liten.displaySchedule();
		
	}

}
