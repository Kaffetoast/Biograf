import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cinema {

	public static void main(String[] args) {
		
		MovieHall liten = new MovieHall("Liten", 10, 25);
		MovieHall medel = new MovieHall("Medel", 20, 32);
		MovieHall stor = new MovieHall("Stor", 30, 40);
		
		Movie movie = new Movie("ETT", 180);
		Show show = new Show(liten, movie, LocalDateTime.now());
		
		Movie movie2 = new Movie("TVA", 180);
		Show show2 = new Show(liten, movie2, LocalDateTime.now());
		
		liten.addShow(show);
		liten.addShow(show2);
		
		liten.displaySchedule();
		/*
		
		public void hallMenu () {
			System.out.pritnln("1. liten");
			System.out.pritnln("2. medel");
			System.out.pritnln("3. stor");
		}
		
		System.out.println("1. Display schedule");
		System.out.println("2. Add show");
		System.out.println("3. Book seat");
		
		if (choice == 1) {
			System.out.println("Choose hall:");
			hallMenu();
			switch (choice) {
			case 1:
			break;
			case 2:
			break;
			case 3:
			break;
				
			}
			
			else if (choice == 2) {
			System.out.println("Add a new show");
				hallMenu();
				switch (choice)
				case 1:
				break:
				case 2:
				break:
				case 3:
				break;
			}
			
			else if (choice == 3) {
			System.out.println("Book a seat");
			hallMenu();
			switch (choice) {
			case 1:
			break:
			case 2:
			break;
			}
			
			}
			
		}*/
	}
	

}
