import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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

		int step = 0;
		Movie selectedMovie = null;
		Show selectedShow = null;
		List<Show> list = null;
		int numOfSeats = 0;

		while (true) {
			if (step == 0) {
				MovieCatalog.showMovies();

				int movie = menu("Enter movie number: ", input);
				if (movie == -1)
					continue;

				if (movie >= MovieCatalog.getMovieList().size()) {
					System.out.println("no such movie id");
					continue;
				}

				selectedMovie = MovieCatalog.getMovie(movie);
				step++;
			}

			try {
				if (step == 1) {
					System.out.println("Enter date: (YYYY-MM-dd)");
					String startDate;

					startDate = input.readLine();
					LocalDate date = LocalDate.parse(startDate);
					list = new ArrayList<Show>();

					for (MovieHall hall : model.getHallList()) {
						list.addAll(hall.filterByMovieAndDate(selectedMovie.getTitle(), date));
					}

					for (Show filteredShow : list) {
						System.out.println(filteredShow);
					}

					if (list.isEmpty()) {
						System.out.println("Not showing movie on that date");
						return;
					}
					step++;
				}

				if (step == 2) {
					int selectedId = menu("Enter show id: ", input);
					if (selectedId == -1)
						continue;

					selectedShow = list.stream().filter(x -> x.getId() == selectedId).findFirst().orElse(null);

					if (selectedShow == null) {
						System.out.println("id does not exit!");
						continue;
					}
					step++;
				}

				if (step == 3) {
					numOfSeats = menu("Enter amount of seats: ", input);
					if (numOfSeats == -1) {
						continue;
					}

					step++;
				}

				String spreadOutSeating = null;
				if (step == 4) {
					System.out.println("Do you want seats to be spread out? (y/n)");
					spreadOutSeating = input.readLine();

					step++;
				}

				int row = 0;
				int seat = 0;
				int reservations = 0;
				if (step == 5) {

					while (true) {
						selectedShow.displaySeats();
						
						String output = "Select row (1 - " + selectedShow.getHall().getRows() + ")";
						row = menu(output, input);

						if (row > selectedShow.getHall().getRows() || row < 1) {
							System.out.println("that seat doesn't exist!");
							continue;
						}
						output = "Select seat (1 - " + selectedShow.getHall().getCols() + ")";
						seat = menu(output, input);
						if (seat > selectedShow.getHall().getCols() || seat < 1) {
							System.out.println("that seat doesn't exist!");
							continue;
						}

						if(spreadOutSeating.toLowerCase().equals("n")) {
							if (selectedShow.reserveManySeats(row, seat, numOfSeats)) {
								System.out.println("Successfully reserved " + numOfSeats + " seat(s)");
								selectedShow.displaySeats();
							} else {
								System.out.println(numOfSeats + " seats are not available at this position.");
								continue;
							}
							break;
						} else if(spreadOutSeating.toLowerCase().equals("y")) {
							System.out.println((reservations+1)+" / "+numOfSeats + " seats reserved." );
							if(selectedShow.reserveSeat(row, seat)) {
								reservations++;
								if(reservations == numOfSeats) return;
							} 
						}
					}

					return;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DateTimeParseException e) {
				System.out.println("Wrong format");
			}
		}

	}

	private static void addShowMenu() {

		int choice;
		MovieHall hall = null;
		Movie movie = null;
		int step = 0;
		LocalDate startDate = null;
		LocalTime startTime = null;
		String start = null;

		while (true) {
			if (step == 0) {

				choice = menu("Choose hall\n1. Liten\n2. Medel\n3. Stor", input);

				switch (choice) {
				case 1:
					hall = model.getHallList().get(0);
					step++;
					break;
				case 2:
					hall = model.getHallList().get(1);
					step++;
					break;
				case 3:
					hall = model.getHallList().get(2);
					step++;
					break;
				default:
					System.out.println("not a valid choice.");
				}
			}

			if (step == 1) {

				MovieCatalog.showMovies();
				choice = menu("Choose movie", input);
				if (choice == -1)
					continue;
				if (choice >= MovieCatalog.getMovieList().size()) {
					System.out.println("No such movie id.");
					continue;
				}

				movie = MovieCatalog.getMovie(choice);

				if (movie == null) {
					System.out.println("no movie with id + " + choice);
				} else {
					step++;
				}
			}

			try {

				if (step == 2) {
					System.out.println("Start date: (YYYY-MM-dd)");
					start = input.readLine();
					startDate = LocalDate.parse(start);
					step++;
				}
				if (step == 3) {
					System.out.println("Start time: (HH:mm)");
					start = input.readLine();
					startTime = LocalTime.parse(start);

					LocalDateTime time = LocalDateTime.of(startDate, startTime);
					
					if(!hall.addShow(new Show(model.generateId(), hall, movie, time))) {
						System.out.println("Show overlaps with other show!");
					}
					return;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DateTimeParseException e) {
				System.out.println("Wrong format");

			}
		}

	}

	private static void displayScheduleMenu() {

		while (true) {
			int choice = menu("Choose hall\n0. Liten\n1. Medel\n2. Stor\n3. All", input);
			if(choice == -1) {
				continue;
			}
			if (choice < model.getHallList().size()) {
				model.getHallList().get(choice).displaySchedule();
				return;
			} else if (choice == model.getHallList().size()) { // All
				for (MovieHall hall : model.getHallList()) {
					hall.displaySchedule();
				}
				return;
			} else {
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
