import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Damian on 3/30/2017.
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(int minimalRaters) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());

        //Printing movies with average
        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatings(minimalRaters);
        System.out.println("Found: " + ratingArrayList.size() + " movies");
        Collections.sort(ratingArrayList);
        for (Rating currentRating : ratingArrayList) {
            System.out.println(currentRating.getValue() + "\t" + MovieDatabase.getTitle(currentRating.getItem()));
        }
    }

    public void printAverageRatingsByYear(int minimalRaters, int year) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());
        ArrayList<Rating> ratingListWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(year));
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println(currentRating.getValue() + " " + MovieDatabase.getTitle(currentRating.getItem()) + "\t" + MovieDatabase.getYear(currentRating.getItem()));
        }

    }


    public void printAverageRatingsByGenre(int minimalRaters, String genre) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());
        ArrayList<Rating> ratingListWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, new GenreFilter(genre));
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println(currentRating.getValue() + " " + MovieDatabase.getTitle(currentRating.getItem()) + "\n\t" + MovieDatabase.getGenres(currentRating.getItem()));
        }

    }

    public void printAverageRatingsByMinutes(int minimalRaters, int minMin, int maxMin) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings();
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());
        ArrayList<Rating> ratingListWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(minMin, maxMin));
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println(currentRating.getValue() + " " + MovieDatabase.getTitle(currentRating.getItem()) + "\nDirectors: " + MovieDatabase.getMinutes(currentRating.getItem()));
        }

    }

    public void printAverageRatingsByDirector(int minimalRaters, String directors) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings();
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());
        ArrayList<Rating> ratingListWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter(directors));
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println(currentRating.getValue() + " " + MovieDatabase.getTitle(currentRating.getItem()) + "\tTime: " + MovieDatabase.getDirector(currentRating.getItem()));
        }

    }

    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings();
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());
        ArrayList<Rating> ratingListWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println("\n" + currentRating.getValue() + " "
                    + MovieDatabase.getTitle(currentRating.getItem())
                    + "\nYear: " + MovieDatabase.getYear(currentRating.getItem())
                    + "\nGenred: " + MovieDatabase.getGenres(currentRating.getItem()));
        }

    }

    public void printAverageRatingsByDirectorsAndMinutes(int minimalRaters, String directors, int minMin, int maxMin) {
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter(directors));
        allFilters.addFilter(new MinutesFilter(minMin, maxMin));

        MovieDatabase.initialize("ratedmoviesfull.csv");
        ThirdRatings thirdRatings = new ThirdRatings();
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of ratings: " + thirdRatings.getRaterSize());
        ArrayList<Rating> ratingListWithFilter = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println("\n" + currentRating.getValue() + " "
                    + MovieDatabase.getTitle(currentRating.getItem())
                    + "\nTime: " + MovieDatabase.getMinutes(currentRating.getItem())
                    + "\nDirectors: " + MovieDatabase.getDirector(currentRating.getItem()));
        }

    }
}

