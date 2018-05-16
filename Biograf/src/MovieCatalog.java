import db.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieCatalog {
	private static ArrayList <Movie> movieList = new ArrayList <Movie>();

	public static ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public static void setMovieList(ArrayList<Movie> movieList) {
		MovieCatalog.movieList = movieList;
	}


	public static void addMovie(Movie movie) {
        movieList.add(movie);
	}
	public static Movie getMovie(int index) {
		return movieList.get(index);
	}						
	
	public static void showMovies() {
		
		int i = 0;
		System.out.println("Title\t\tLength ");
		for(Movie movie: movieList) {
			
			System.out.println(i +"." + movie.getTitle() + "\t"+movie.getLength());
			i = i +1;
		}
	}

    public static void fetchMovies(Database database) {
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

	public static Movie getMovieById(int id) {
		return movieList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
