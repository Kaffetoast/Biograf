import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {

	private static BufferedReader input;
	private static CinemaModel model;

	public static void main(String[] args) throws NumberFormatException, IOException {

		MovieCatalog.addMovies();
		model = new CinemaModel();

		input = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			int choice = menu("1. Display schedule\n2. Add show\n3. Book seat", input);
			if (choice == -1)
				continue;

			switch (choice) {
			case 1:
				displayScheduleMenu();
				break;

			case 2: // Add show menu
				addShowMenu();
				break;
			case 3: // Reserve seat
				reserveSeatMenu();
				break;
			default:
				System.out.println("invalid choice");
			}
		}

	}

	private static void reserveSeatMenu() {
		MovieCatalog.showMovies();

		int movie = menu("Enter movie number: ", input);
		Movie selectedMovie = MovieCatalog.getMovie(movie);

		System.out.println("Enter date: ");
		String startDate;
		try {
			startDate = input.readLine();
			LocalDate date = LocalDate.parse(startDate);
			List<Show> list = new ArrayList<Show>();

			for (MovieHall hall : model.getHallList()) {
				list.addAll(hall.filterByMovieAndDate(selectedMovie.getTitle(), date));

				for (Show filteredShow : list) {
					System.out.println(filteredShow);

				}
			}

			int selectedId = menu("Enter show id: ", input);
			Show selectedShow = list.stream().filter(x -> x.getId() == selectedId).findFirst().orElse(null);

			int row = menu("Select row", input);
			int seat = menu("Select seat", input);

			selectedShow.reserveSeat(row, seat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void addShowMenu() {

		int step = 0;

		while (true) {

			int choice = menu("Choose hall\n1. Liten\n2. Medel\n3. Stor", input);

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
			default:
				continue;
			}
			MovieCatalog.showMovies();
			choice = menu("Choose movie", input);
			Movie movie = MovieCatalog.getMovie(choice);

			if (movie == null) {
				System.out.println("no movie with id + " + choice);
				return;
			}

			System.out.println("Start date: ");
			String startDate;
			try {
				startDate = input.readLine();
				System.out.println("Start time: ");
				String startTime = input.readLine();

				LocalDateTime time = LocalDateTime.of(LocalDate.parse(startDate), LocalTime.parse(startTime));
				hall.addShow(new Show(5, hall, movie, time));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static void displayScheduleMenu() {

		while (true) {
			int choice = menu("Choose hall\n1. Liten\n2. Medel\n3. Stor\n4. All", input);

			switch (choice) {
			case 0:
				System.exit(0);
			case 1: // Show liten
				model.getHallList().get(0).displaySchedule();
				return;

			case 2:
				model.getHallList().get(1).displaySchedule();
				return;
			case 3:
				model.getHallList().get(2).displaySchedule();
				return;
			case 4: // Show all
				for (MovieHall hall : model.getHallList()) {
					hall.displaySchedule();
				}
				return;
			default:
				System.out.println("Invalid choice!");

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
			System.out.println("Numbers only!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
