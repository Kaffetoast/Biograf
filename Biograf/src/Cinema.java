import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cinema {

	public static void main(String[] args) throws NumberFormatException, IOException {

		MovieCatalog.addMovies();
		CinemaModel model = new CinemaModel();


		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			int choice = menu("1. Display schedule\n2. Add show\n3. Book seat", input);

			if (choice == 1) {
				choice = menu("Choose hall\n1. Liten\n2. Medel\n3. Stor\n4. All", input);

				switch (choice) {
				case 1: // Show liten
					model.getHallList().get(0).displaySchedule();
					break;

				case 2:
					model.getHallList().get(1).displaySchedule();
					break;
				case 3:
					model.getHallList().get(2).displaySchedule();
					break;
				case 4: // Show all
					for (MovieHall hall : model.getHallList()) {
						hall.displaySchedule();
					}

					break;
				case 0:
					System.exit(0);

				}
			} else if (choice == 2) { // Add show menu
				choice = menu("Choose hall\n1. Liten\n2. Medel\n3. Stor", input);

				MovieHall hall = null;
				switch (choice) {
				case 1:
					hall = model.getHallList().get(0);
					break;
				case 2:
					hall = model.getHallList().get(1);
					break;
				case 3:
					hall = model.getHallList().get(2);
					break;
				}
				MovieCatalog.showMovies();
				choice = menu("Choose movie", input);
				Movie movie = MovieCatalog.getMovie(choice);

				if (movie == null) {
					System.out.println("no movie with id + " + choice);
					return;
				}

				System.out.println("Start date: ");
				String startDate = input.readLine();

				System.out.println("Start time: ");
				String startTime = input.readLine();

				LocalDateTime time = LocalDateTime.of(LocalDate.parse(startDate), LocalTime.parse(startTime));
				hall.addShow(new Show(hall, movie, time));

			} else if(choice == 3) { //Reserve seat
				
				MovieCatalog.showMovies();

				int movie = menu("Enter movie number: ", input);
				Movie selectedMovie = MovieCatalog.getMovie(movie);
				
				System.out.println("Enter date: ");
				String startDate = input.readLine();
				
				LocalDate date = LocalDate.parse(startDate);
				
				for(MovieHall hall: model.getHallList()) {
					List <Show> list = hall.filterByMovieAndDate(selectedMovie.getTitle(), date);
					
					for(Show filteredShow : list) {
						System.out.println(filteredShow);
					}
				}
			}
		}
		
	}

	public static int menu(String choices, BufferedReader input) {
		System.out.println(choices);
		int choice;
		try {
			choice = Integer.parseInt(input.readLine());
			return choice;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
