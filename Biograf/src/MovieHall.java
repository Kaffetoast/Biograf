import java.util.ArrayList;

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
		//TODO: overlap test
		schedule.add(show);
	}

	public void displaySchedule() {
		System.out.print("Salong: " + this.name + "\n");
		for(Show show: schedule) {
			
			Movie movie = show.getMovie();
			System.out.println("time: "+show.getStartTime() + "\n" + "movie: " + show.getMovie().getTitle());
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
	
	
	
}
