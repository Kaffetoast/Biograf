
public class Movie {

	private String title;
	private int length;
	private int id;
	
	public Movie(String title, int length) {
		this.title = title;
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Title: " + title + " | Length: " + length;
	}


	
	
	
}
