import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Damian on 3/31/2017.
 */
public class MovieRunnerSimilarRatings {

    public void printAverageRatings(int minimalRaters) {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.addRatings("ratings_short.csv");
        FourthRatings fourthRatings = new FourthRatings();
        int numberOfRatings = 0;
        for (IRater currentRater : RaterDatabase.getRaters()) {
            numberOfRatings += currentRater.numRatings();
        }
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Number of raters: " + RaterDatabase.size());
        System.out.println("Numbers of ratings: " + numberOfRatings);

        //Printing movies with average
        ArrayList<Rating> ratingArrayList = fourthRatings.getAverageRatings(minimalRaters);
        System.out.println("Found: " + ratingArrayList.size() + " movies");
        Collections.sort(ratingArrayList);
        for (Rating currentRating : ratingArrayList) {
            System.out.println(currentRating.getValue() + "\t" + MovieDatabase.getTitle(currentRating.getItem()));
        }
    }


    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings_short.csv");
        FourthRatings fourthRatings = new FourthRatings();
        System.out.println("Number of movies: " + MovieDatabase.size());
        System.out.println("Numbers of raters: " + RaterDatabase.size());
        ArrayList<Rating> ratingListWithFilter = fourthRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        Collections.sort(ratingListWithFilter);
        System.out.println("Found: " + ratingListWithFilter.size() + " movies");
        for (Rating currentRating : ratingListWithFilter) {
            System.out.println("\n" + currentRating.getValue() + " "
                    + MovieDatabase.getTitle(currentRating.getItem())
                    + "\nYear: " + MovieDatabase.getYear(currentRating.getItem())
                    + "\nGenred: " + MovieDatabase.getGenres(currentRating.getItem()));
        }

    }

    public void testGetSimilatisies(int minimalRaters) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> similaritiesList= fourthRatings.getSimilarities("1");
        for (Rating rating : similaritiesList){
            System.out.println(rating.getItem() + "\t" + rating.getValue());
        }
    }

    public void printSimilarRatings(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatings("71",20,5);
        for (Rating rating : ratingArrayList){
            System.out.println(MovieDatabase.getTitle(rating.getItem())  + "\t" + rating.getValue());
        }
    }

    public void printSimilarRadingsByGenre(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));
        for (Rating rating : ratingArrayList){
            System.out.println(MovieDatabase.getTitle(rating.getItem())  + "\t" + rating.getValue()+"\n"+MovieDatabase.getGenres(rating.getItem()));

        }
    }

    public void printSimilarRadingsByDirector(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating rating : ratingArrayList){
            System.out.println(MovieDatabase.getTitle(rating.getItem())  + "\t" + rating.getValue()+"\n"+MovieDatabase.getDirector(rating.getItem()));

        }
    }

    public void printSimilarRadingsByGenreAndMinutes(String raterID,int numSimilarRaters,int minimalRaters,String genre, int minMin, int maxMin){
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new MinutesFilter(minMin,maxMin));
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,allFilters);
        for (Rating rating : ratingArrayList){
            System.out.println(MovieDatabase.getTitle(rating.getItem())  + "\t" + rating.getValue()+"\t"+MovieDatabase.getMinutes(rating.getItem()) + "\n" + MovieDatabase.getGenres(rating.getItem()));

        }
    }

    public void printSimilarRadingsByYearAfterAndMinutes(String raterID,int numSimilarRaters,int minimalRaters,int year, int minMin, int maxMin){
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new MinutesFilter(minMin,maxMin));
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.addRatings("ratings.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,allFilters);
        for (Rating rating : ratingArrayList){
            System.out.println(MovieDatabase.getTitle(rating.getItem())  + "\t" + rating.getValue()+"\t"+MovieDatabase.getMinutes(rating.getItem()) + "\n" + MovieDatabase.getGenres(rating.getItem()));

        }
    }

}


