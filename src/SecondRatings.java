/**
 * Created by Damian on 3/29/2017.
 * <p>
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 * <p>
 * Write a description of SecondRatings here.
 * @author (your name)
 * @version (a version number or a date)
 * <p>
 * Write a description of SecondRatings here.
 * @author (your name)
 * @version (a version number or a date)
 * <p>
 * Write a description of SecondRatings here.
 * @author (your name)
 * @version (a version number or a date)
 * <p>
 * Write a description of SecondRatings here.
 * @author (your name)
 * @version (a version number or a date)
 */

/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        firstRatings.loadMovies(movieFile);
        firstRatings.loadRaters(ratingsFile);
        myMovies = firstRatings.getMovieArrayList();
        myRaters = firstRatings.getRaterArrayList();
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String movieID, int minimalRaters) {
        int numberOfRatings = 0;
        double sumOfRatings = 0;
        for (Rater currentRater : myRaters) {
            double rating = currentRater.getRating(movieID);
            if (rating != -1) {
                numberOfRatings++;
                sumOfRatings += rating;
            }
        }
        if (numberOfRatings < minimalRaters)
            return 0.00;
        return sumOfRatings / numberOfRatings;
    }

    public double getAverageByID(String movieID) {
        int numberOfRatings = 0;
        double sumOfRatings = 0;
        for (Rater currentRater : myRaters) {
            double rating = currentRater.getRating(movieID);
            if (rating != -1) {
                numberOfRatings++;
                sumOfRatings += rating;
            }
        }
        return sumOfRatings / numberOfRatings;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> moviesWithAverageRatings = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            double movieAverageRating = getAverageByID(movie.getID(), minimalRaters);
            if (movieAverageRating != 0.00) {
                Rating rating = new Rating(movie.getID(), movieAverageRating);
                moviesWithAverageRatings.add(rating);
            }
        }
        return moviesWithAverageRatings;
    }

    public String getTitle(String movieID) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(movieID))
                return movie.getTitle();
        }
        return "ID was not found";
    }

    public String getID(String title){
        for (Movie movie: myMovies){
            if (movie.getTitle().equals(title))
                return movie.getID();
        }
        return "NO SUCH TITLE";
    }

}