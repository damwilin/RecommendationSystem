import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Damian on 3/28/2017.
 */
public class Test {
    public static void main (String[]args) throws IOException {
        FirstRatings firstRatings = new FirstRatings();
        firstRatings.loadMovies("ratedmoviesfull.csv");
        ArrayList<Movie> movieArrayList = firstRatings.getMovieArrayList();
        for (Movie movie : movieArrayList)
            System.out.println(movie);
        System.out.println(movieArrayList.size());
    }
}
