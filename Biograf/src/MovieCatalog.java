import db.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

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

		movieList.sort(Comparator.comparing(a -> a.getId()));
		System.out.println("movieID\t\tTitle\t\tLength ");
		for(Movie movie: movieList) {
			
			System.out.println(movie.getId() +")" + movie.getTitle() + "\t"+movie.getLength());

		}
	}



	public static Movie getMovieById(int id) {
		return movieList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
