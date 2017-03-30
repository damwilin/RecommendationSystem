import java.util.ArrayList;

/**
 * Created by Damian on 3/30/2017.
 */
public class ThirdRatings {
    private ArrayList<EfficientRater> myEfficientRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myEfficientRaters = firstRatings.loadRaters(ratingsFile);
    }



    public int getRaterSize() {
        return myEfficientRaters.size();
    }

    private double getAverageByID(String movieID, int minimalRaters) {
        int numberOfRatings = 0;
        double sumOfRatings = 0;
        for (EfficientRater currentEfficientRater : myEfficientRaters) {
            double rating = currentEfficientRater.getRating(movieID);
            if (rating != -1) {
                numberOfRatings++;
                sumOfRatings += rating;
            }
        }
        if (numberOfRatings < minimalRaters)
            return 0.00;
        return sumOfRatings / numberOfRatings;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> moviesWithAverageRatings = new ArrayList<Rating>();
        for (String id : movies) {
            double movieAverageRating = getAverageByID(id, minimalRaters);
            if (movieAverageRating > 0.00) {
                Rating rating = new Rating(id, movieAverageRating);
                moviesWithAverageRatings.add(rating);
            }
        }
        return moviesWithAverageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, IFilter filterCriteria){
        ArrayList<String> moviesWithFilter = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> moviesWithAverageRatings = new ArrayList<Rating>();
        for (String id : moviesWithFilter){
            double averageRating = getAverageByID(id, minimalRaters);
            if (averageRating > 0.00){
                Rating rating = new Rating(id, averageRating);
                moviesWithAverageRatings.add(rating);
            }
        }

        return moviesWithAverageRatings;
    }




}
