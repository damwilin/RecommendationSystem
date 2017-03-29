import java.util.ArrayList;

/**
 * Created by Damian on 3/28/2017.
 */
public class Test {
    public static void main (String[]args) {
        FirstRatings firstRatings = new FirstRatings();
        //firstRatings.testLoadMovies();
        //firstRatings.testLoadRaters();
        MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
        movieRunnerAverage.printAverageRatings(20);
        //movieRunnerAverage.getAverageRatingOneMovie("Vacation");

    }
}
