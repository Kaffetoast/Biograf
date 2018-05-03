
public class MovieCatalog {

	public static Movie getMovie(int id) {
		
		switch(id) {
		
		case 0:
			return new Movie("ETT", 180);
		case 1: 
			return new Movie("TVA", 120);
		default:
			return null;
		}
		
	}
}
