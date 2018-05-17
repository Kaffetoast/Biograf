import db.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class CinemaData {

	private Database database;
	private ArrayList<MovieHall> hallData = new ArrayList<MovieHall>();
	private ArrayList<MovieHall> hallList = new ArrayList<MovieHall>(); // list of all movie halls

	public CinemaData() {
		this.database = new Database();
	}

	public void fetchHalls() {
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
			fetchShows(hall);
		}
		database.disconnect();
	}

	public void fetchShows(MovieHall hall) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String query = "SELECT * FROM \"Show\" INNER JOIN \"ShowInHall\" ON \"Show\".\"showID\" = \"ShowInHall\".\"showID\"" +
				"WHERE \"hallID\" = "+hall.getId() + ";";

		ResultSet rs = database.query(query);

		try {
			while (rs.next()) {
				int show = rs.getInt("showID");
				String dateString = rs.getString("starttime");
				LocalDateTime startTime = LocalDateTime.parse(dateString, formatter);

				int movieID = rs.getInt("movieID");
				Movie movie = MovieCatalog.getMovieById(movieID);

				hall.addShow(new Show(show, hall, movie, startTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		hall.getSchedule().sort(Comparator.comparing(a -> a.getStartTime()));
		//"SELECT * FROM \"Show\" INNER JOIN \"ShowInHall\" ON \"Show\".\"showID\" = \"ShowInHall\".\"showID\";
	}

	public int insertShow(MovieHall hall, Show show) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime date = show.getStartTime();
		String query = "INSERT INTO \"Show\" (starttime, \"movieID\") VALUES ('"
				+ date.format(formatter) + "', " + show.getMovie().getId() + ") RETURNING \"showID\";";
		ResultSet rs = database.query(query);
		int showID = -1;
		try {
			rs.next();
			showID = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		query = "INSERT INTO \"ShowInHall\" (\"showID\", \"hallID\") " +
				"VALUES (" + showID + ", " + hall.getId() + ")";

		database.insert(query);
		return showID;
	}

	public ArrayList<MovieHall> getHallList() {
		return hallList;
	}

	public MovieHall getHallById(int id) {
		return hallList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public void fetchSeats(Show show) {

		String query = "SELECT * FROM \"Seat\" WHERE \"showID\" = " + show.getId();
		ResultSet rs = database.query(query);

		show.clearSeats();

		try {
			while (rs.next()) {
				int col = rs.getInt("col");
				int row = rs.getInt("row");

				show.getSeats()[row][col] = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fetchMovies() {
		database.connect();
		String query = "SELECT * FROM \"Movie\"";
		database.query(query);

		ResultSet rs = database.query(query);

		try {
			while (rs.next()) {
				int id = rs.getInt("movieID");
				String title = rs.getString("title");
				int length = rs.getInt("length");

				MovieCatalog.addMovie(new Movie(title, length, id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		database.disconnect();
	}

	public void reserveSeat(Show selectedShow, int row, int seat) {
		String query = "INSERT INTO \"Seat\" VALUES (" + row + ", " + seat + ", " + selectedShow.getId() + ");";
		database.insert(query);
	}
}
