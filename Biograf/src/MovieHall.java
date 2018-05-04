import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieHall {

	private int rows;
	private int cols;
	private String name;
	private int id;

	public int getId() {
		return id;
	}

	public MovieHall(String name, int rows, int cols, int id) {
		this.rows = rows;
		this.cols = cols;
		this.name = name;
		this.id = id;
		
		this.schedule = new ArrayList <Show>();
	}
	
	private ArrayList <Show> schedule;
	
	public void addShow(Show show) {
		for (Show oldShow: schedule) {
			if(isOverlap(show, oldShow)) {
				System.out.println("Shows overlap");
				return;
			}
		}
		schedule.add(show);
	}

	public List<Show> filterByMovie(List<Show> list, String title) {
		return schedule.stream().filter(x -> x.getMovie().getTitle() == title).collect(Collectors.toList());
	}
	
	public List<Show> filterByDate(List <Show> list, LocalDateTime date) {
		return list.stream().filter(x -> date.toLocalDate().isEqual(x.getStartTime().toLocalDate())).collect(Collectors.toList());
		
	}
	
	public List <Show> filterByMovieAndDate(String title, LocalDate date) {
		return schedule.stream().filter(x -> date.isEqual(x.getStartTime().toLocalDate())
				&& x.getMovie().getTitle() == title).collect(Collectors.toList());
	}
	
	
	public boolean isOverlap(Show newShow, Show oldShow) {
		if (newShow.getStartTime().compareTo(oldShow.getEndTime()) < 0
				&& oldShow.getStartTime().compareTo(newShow.getEndTime()) < 0) {
			return true;
		}
		return false;
	}

	public void displaySchedule() {

		if(schedule.isEmpty()) {
			System.out.println("No shows, sorry.");
			System.out.println("--------------------");
			return;
		}

		for(Show show: schedule) {
			System.out.println(show);
		}

	}

	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Show> getSchedule() {
		return schedule;
	}

	
	@Override
	public String toString() {
		return name;
	}

	
}
