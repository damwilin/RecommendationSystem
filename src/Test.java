/**
 * Created by Damian on 3/28/2017.
 */
public class Test {
    public static void main(String[] args) {
        FirstRatings firstRatings = new FirstRatings();
        //firstRatings.testLoadMovies();
        //firstRatings.testLoadRaters();
        //MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
        //movieRunnerAverage.printAverageRatings(50);
        //movieRunnerAverage.getAverageRatingOneMovie("Vacation");
        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
        //movieRunnerWithFilters.printAverageRatings(35);
        //movieRunnerWithFilters.printAverageRatingsByYear(20,2000);
        //movieRunnerWithFilters.printAverageRatingsByGenre(20,"Comedy");
        //movieRunnerWithFilters.printAverageRatingsByMinutes(5,105,135);
        //movieRunnerWithFilters.printAverageRatingsByDirector(4,"Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        //movieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre(8,1990,"Drama");
        movieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes(3,"Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack",90,180);

    }
}
