import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CinemaModel {

    MovieHall liten = new MovieHall("Liten", 10, 25, 1);
    MovieHall medel = new MovieHall("Medel", 20, 32, 2);
    MovieHall stor = new MovieHall("Stor", 30, 40, 3);
    private ArrayList <MovieHall> hallData = new ArrayList <MovieHall>(Arrays.asList(

    ));

    private ArrayList <Show> showData = new ArrayList<Show>(Arrays.asList(
            new Show(0, liten, MovieCatalog.getMovie(0), LocalDateTime.now()),
            new Show(1, stor, MovieCatalog.getMovie(1), LocalDateTime.now()),
            new Show(2, medel, MovieCatalog.getMovie(0), LocalDateTime.now())
    ));

    private ArrayList<MovieHall> hallList = new ArrayList<MovieHall>(); //list of all movie halls

    public CinemaModel() {

        fetchHalls();

        for(MovieHall hall: hallList) {
            fetchShows(hall);
        }

    }
    public void fetchHalls() {
        hallList.add(liten);
        hallList.add(medel);
        hallList.add(stor);
    }

    public void fetchShows(MovieHall hall) {

        for(Show show: showData) {
            if(show.getHall() == hall) {
                hall.addShow(show);
            }
        }

    }

    public ArrayList<MovieHall> getHallList() {
        return hallList;
    }

}
