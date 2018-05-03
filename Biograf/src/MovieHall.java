import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieHall {

	private int rows;
	private int cols;
	private String name;
	
	public MovieHall(String name, int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.name = name;
		
		this.schedule = new ArrayList <Show>();
	}
	
	private ArrayList <Show> schedule;
	
	public void addShow(Show show) {
		for (Show oldShow: schedule) {
			if(isOverlap(show, oldShow)) {
				System.out.println("OVERLAPP!!!!!!!!!!!!! ABORT!!!!!");
				return;
			}
		}

		schedule.add(show);
	}

	public List<Show> filterByMovie(String title) {
		return schedule.stream().filter(x -> x.getMovie().getTitle() == title).collect(Collectors.toList());
	}
	
	public List<Show> filterByDate(LocalDateTime date) {
		return schedule.stream().filter(x -> date.toLocalDate().isEqual(x.getStartTime().toLocalDate())).collect(Collectors.toList());
		
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
		System.out.print("Salong: " + this.name + "\n");
		
		if(schedule.isEmpty()) {
			System.out.println("No shows, sorry.");
			return;
		}
		for(Show show: schedule) {

			Movie movie = show.getMovie();
			System.out.println("time: " + show.getStartTime() + "\n" + "movie: " + show.getMovie().getTitle());
			System.out.println("Length: " + movie.getLength());
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Show> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<Show> schedule) {
		this.schedule = schedule;
	}
	
	@Override
	public String toString() {
		return name;
	}

	
}
