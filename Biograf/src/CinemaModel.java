import db.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class CinemaModel {

	private static int idCounter = 5;

	private ArrayList<MovieHall> hallData = new ArrayList<MovieHall>();

	private ArrayList<MovieHall> hallList = new ArrayList<MovieHall>(); // list of all movie halls

	public CinemaModel() {

	}

	public void fetchHalls(Database database) {
		database.connect();
		String query = "SELECT * FROM \"Hall\"";
		database.query(query);

		ResultSet rs = database.query(query);

		try {
			while (rs.next()) {
				int id = rs.getInt("hallID");
				String name = rs.getString("name");
				int cols = rs.getInt("cols");
				int rows = rs.getInt("rows");
				hallList.add(new MovieHall(name, rows, cols, id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		hallList.sort(Comparator.comparing(a -> a.getId()));

		for (MovieHall hall : hallList) {
			fetchShows(hall, database);
		}
		database.disconnect();
	}

	public void fetchShows(MovieHall hall, Database database) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//Add randomly generated shows
		Random ran = new Random();
		
		for (int i = 0; i < 10; i++) {
			int randomMovie = ran.nextInt((MovieCatalog.getMovieList().size()));
			int randomDay = ran.nextInt(31);
			int randomHour = ran.nextInt(24);
			int id = generateId();
			Show show = new Show(generateId(), hall, MovieCatalog.getMovie(randomMovie),
					LocalDateTime.now().plusDays(randomDay).plusHours(randomHour));

			hall.addShow(show);

			LocalDateTime date = LocalDateTime.now().plusDays(randomDay).plusHours(randomHour);
			String query = "INSERT INTO \"Show\" (starttime, \"movieID\") VALUES ('"
					+ date.format(formatter) + "', " + randomMovie + ") RETURNING \"showID\";";
			ResultSet rs = database.query(query);
			int showID = -1;
			try {
				rs.next();
				 showID = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String query2 = "INSERT INTO \"ShowInHall\" (\"showID\", \"hallID\") " +
					"VALUES (" + showID + ", " + hall.getId() + ")";

			database.insert(query2);
		}

	}

	public ArrayList<MovieHall> getHallList() {
		return hallList;
	}

	public MovieHall getHallById(int id) {
		return hallList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public int generateId() {
		this.idCounter++;
		return this.idCounter;
	}

}
