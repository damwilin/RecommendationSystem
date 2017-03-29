import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Damian on 3/29/2017.
 */
public class MovieRunnerAverage {
    public void printAverageRatings(int minimalRaters) {
        SecondRatings secondRatings = new SecondRatings();
        System.out.println("Number of movies: " + secondRatings.getMovieSize());
        System.out.println("Numbers of ratings: " + secondRatings.getRaterSize());

        //Printing movies with average
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(minimalRaters);
        Collections.sort(ratingArrayList);
        for (Rating currentRating : ratingArrayList) {
            System.out.println(currentRating.getValue() + "\t" + secondRatings.getTitle(currentRating.getItem()));
        }
    }

    public void getAverageRatingOneMovie(String movieTitle) {
        SecondRatings secondRatings = new SecondRatings();
        String movieID = secondRatings.getID(movieTitle);
        double averageRating = secondRatings.getAverageByID(movieID);
        System.out.println(movieTitle + "\t" + averageRating);
    }
}
