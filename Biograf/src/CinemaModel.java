import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CinemaModel {

	private static int idCounter = 5;
	MovieHall liten = new MovieHall("Liten", 4, 4, 1);
	MovieHall medel = new MovieHall("Medel", 8, 8, 2);
	MovieHall stor = new MovieHall("Stor", 16, 16, 3);
	private ArrayList<MovieHall> hallData = new ArrayList<MovieHall>();

	private ArrayList<MovieHall> hallList = new ArrayList<MovieHall>(); // list of all movie halls

	public CinemaModel() {

		fetchHalls();

		for (MovieHall hall : hallList) {
			fetchShows(hall);
		}

	}

	public void fetchHalls() {
		hallList.add(liten);
		hallList.add(medel);
		hallList.add(stor);
	}

	public void fetchShows(MovieHall hall) {
		
		//Add randomly generated shows
		Random ran = new Random();
		
		for (int i = 0; i < 10; i++) {
			int randomMovie = ran.nextInt((MovieCatalog.getMovieList().size()));
			int randomDay = ran.nextInt(31);
			int randomHour = ran.nextInt(24);

			Show show = new Show(generateId(), hall, MovieCatalog.getMovie(randomMovie),
					LocalDateTime.now().plusDays(randomDay).plusHours(randomHour));

			hall.addShow(show);
		}

	}

	public ArrayList<MovieHall> getHallList() {
		return hallList;
	}

	public int generateId() {
		this.idCounter++;
		return this.idCounter;
	}

}
