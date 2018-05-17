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



	public static Movie getMovieById(int id) {
		return movieList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
