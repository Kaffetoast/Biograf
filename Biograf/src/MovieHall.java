import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieHall {

	private int rows;
	private int cols;
	private String name;
	private int id;
	private ArrayList <Show> schedule;

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
	

	
	public boolean addShow(Show show) {
		for (Show oldShow: schedule) {
			if(isOverlap(show, oldShow)) {
				return false;
			}
		}
		schedule.add(show);

		return true;
	}

	public List<Show> filterByMovie(int id) {
		return schedule.stream().filter(x -> x.getMovie().getId() == id).collect(Collectors.toList());
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

		schedule.forEach(x-> System.out.println(x));
	}

	public void displayScheduleByMovieID(int id) {

		List <Show> filteredSchedule;
		filteredSchedule =  schedule.stream().filter(x -> x.getMovie().getId() == id).collect(Collectors.toList());

		if(filteredSchedule.isEmpty()) {
			System.out.println("No shows, sorry.");
			System.out.println("--------------------");
			return;
		}

		filteredSchedule.sort(Comparator.comparing(x -> x.getStartTime()));
		filteredSchedule.forEach(x-> System.out.println(x));

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
