import java.util.ArrayList;

public class MovieCatalog {

	private static Movie water = new Movie("PepsiCola", 180);
	private static Movie bottle = new Movie("Bottle", 120);
	private static Movie bonaqua = new Movie("Bonaqua", 180);
	
	private static ArrayList <Movie> movieList = new ArrayList <Movie>();

	public static ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public static void setMovieList(ArrayList<Movie> movieList) {
		MovieCatalog.movieList = movieList;
	}
	public static void addMovies() {
		movieList.add(water);
		movieList.add(bottle);
		movieList.add(bonaqua);
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
}
