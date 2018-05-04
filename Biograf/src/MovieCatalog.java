import java.util.ArrayList;

public class MovieCatalog {

	private static Movie movie1 = new Movie("Star Wars", 180);
	private static Movie movie2 = new Movie("Star Trek", 120);
	private static Movie movie3 = new Movie("Batman", 180);
	private static Movie movie4 = new Movie("Superman", 180);
	private static Movie movie5 = new Movie("Tomb Raider", 180);
	private static Movie movie6 = new Movie("The Avengers", 180);
	
	private static ArrayList <Movie> movieList = new ArrayList <Movie>();

	public static ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public static void setMovieList(ArrayList<Movie> movieList) {
		MovieCatalog.movieList = movieList;
	}
	public static void addMovies() {
		movieList.add(movie1);
		movieList.add(movie2);
		movieList.add(movie3);
		movieList.add(movie4);
		movieList.add(movie5);
		movieList.add(movie6);
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
