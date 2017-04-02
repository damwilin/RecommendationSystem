import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Damian on 3/31/2017.
 */
public class FourthRatings {

    public FourthRatings() {
    }


    private double getAverageByID(String movieID, int minimalRaters) {
        int numberOfRatings = 0;
        double sumOfRatings = 0;
        for (IRater currentRater : RaterDatabase.getRaters()) {
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

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, IFilter filterCriteria) {
        ArrayList<String> moviesWithFilter = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> moviesWithAverageRatings = new ArrayList<Rating>();
        for (String id : moviesWithFilter) {
            double averageRating = getAverageByID(id, minimalRaters);
            if (averageRating > 0.00) {
                Rating rating = new Rating(id, averageRating);
                moviesWithAverageRatings.add(rating);
            }
        }

        return moviesWithAverageRatings;
    }

    private double dotProduct(IRater me, IRater r) {
        double dotProduct = 0;
        for (String movieID : me.getItemsRated()) {
            double meCurrentRating = me.getRating(movieID);
            double rCurrentRating = r.getRating(movieID);
            if (rCurrentRating != -1)
                dotProduct += ((meCurrentRating - 5) * (rCurrentRating - 5));
        }
        return dotProduct;
    }

    public ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarityList = new ArrayList<Rating>();
        for (IRater currentRater : RaterDatabase.getRaters()) {
            if (!currentRater.getID().equals(id)) {
                double dotProduct = dotProduct(RaterDatabase.getRater(id), currentRater);
                if (dotProduct > 0) {
                    similarityList.add(new Rating(currentRater.getID(), dotProduct));
                }
            }
        }
        Collections.sort(similarityList, Collections.reverseOrder());
        return similarityList;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> movieWeightedAverage = new ArrayList<Rating>();
        ArrayList<Rating> similarityList = getSimilarities(id);
        ArrayList<String> movieIDWithMinimalRaters = new ArrayList<String>();
        while (true) {
            if (similarityList.size() > numSimilarRaters)
                similarityList.remove(similarityList.size() - 1);
            else
                break;
        }
        for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
            int numMinimalRaters = 0;
            for (Rating rating : similarityList) {
                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
                    numMinimalRaters++;
                }
            }
            if (numMinimalRaters >= minimalRaters)
                movieIDWithMinimalRaters.add(movieID);
        }
        System.out.println(similarityList.size());
        System.out.println(movieIDWithMinimalRaters.size());

        for (String movieID : movieIDWithMinimalRaters) {
            double sumOfRating = 0;
            double numberOfRaters = 0;
            for (Rating rating : similarityList) {
                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
                    sumOfRating += (rating.getValue() * RaterDatabase.getRater(rating.getItem()).getRating(movieID));
                    numberOfRaters++;
                }
            }
            if (numberOfRaters > 0)
                movieWeightedAverage.add(new Rating(movieID, (sumOfRating / numberOfRaters)));
        }
        Collections.sort(movieWeightedAverage,Collections.reverseOrder());
        return movieWeightedAverage;
    }



    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, IFilter filterCriteria) {
        ArrayList<Rating> movieWeightedAverage = new ArrayList<Rating>();
        ArrayList<Rating> similarityList = getSimilarities(id);
        ArrayList<String> movieIDWithMinimalRaters = new ArrayList<String>();
        while (true) {
            if (similarityList.size() > numSimilarRaters)
                similarityList.remove(similarityList.size() - 1);
            else
                break;
        }
        for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
            int numMinimalRaters = 0;
            for (Rating rating : similarityList) {
                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
                    numMinimalRaters++;
                }
            }
            if (numMinimalRaters >= minimalRaters)
                movieIDWithMinimalRaters.add(movieID);
        }
        System.out.println(similarityList.size());
        System.out.println(movieIDWithMinimalRaters.size());

        for (String movieID : movieIDWithMinimalRaters) {
            double sumOfRating = 0;
            double numberOfRaters = 0;
            for (Rating rating : similarityList) {
                if (RaterDatabase.getRater(rating.getItem()).hasRating(movieID)) {
                    sumOfRating += (rating.getValue() * RaterDatabase.getRater(rating.getItem()).getRating(movieID));
                    numberOfRaters++;
                }
            }
            if (numberOfRaters > 0)
                movieWeightedAverage.add(new Rating(movieID, (sumOfRating / numberOfRaters)));
        }
        Collections.sort(movieWeightedAverage,Collections.reverseOrder());
        return movieWeightedAverage;
    }
}
