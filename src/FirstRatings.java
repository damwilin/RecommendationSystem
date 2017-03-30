import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by Damian on 3/28/2017.
 */
public class FirstRatings {


    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        Iterable<CSVRecord> records = loadFile(filename);
        for (CSVRecord record : records) {
            Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
            movieArrayList.add(movie);
        }
        return movieArrayList;
    }

    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> efficientRaterList = new ArrayList<EfficientRater>();
        Iterable<CSVRecord> records = loadFile(filename);
        for (CSVRecord record : records) {
            EfficientRater efficientRater = new EfficientRater(record.get("rater_id"));
            int numberOfRater = -1;
            for (int i = 0; i < efficientRaterList.size(); i++) {
                if (efficientRaterList.get(i).getID().equals(efficientRater.getID()))
                    numberOfRater = i;
            }
            if (numberOfRater == -1) {
                efficientRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                //mozna usunac ta linijke, chyba i else
                efficientRaterList.add(efficientRater);
            } else
                efficientRaterList.get(numberOfRater).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));

        }
        return efficientRaterList;
    }

    private Iterable<CSVRecord> loadFile(String filename) {
        Iterable<CSVRecord> records = null;
        try {
            Reader in = new FileReader("data/" + filename);
            records = CSVFormat.EXCEL.withHeader().parse(in);
        } catch (IOException e) {
            System.out.println("Reading file error: " + filename);
        }
        return records;
    }

/*
    public void testLoadMovies() {
        int homManyComediesMovie = 0;
        int howManyLongMovies = 0;
        int howManyMoviesByDirector = 0;
        String maxDirector = null;
        HashMap<String, Integer> moviesByDirector = new HashMap<String, Integer>();
        loadMovies("ratedmoviesfull.csv");
        for (Movie movie : movieArrayList)
            System.out.println(movie);
        System.out.println(movieArrayList.size());
        for (Movie movie : movieArrayList) {
            if (movie.getGenres().contains("Comedy"))
                homManyComediesMovie++;
            if (movie.getMinutes() > 150)
                howManyLongMovies++;
            String currentDirector = movie.getDirector();
            if (!moviesByDirector.containsKey(currentDirector))
                moviesByDirector.put(currentDirector, 1);
            else
                moviesByDirector.put(currentDirector, moviesByDirector.get(currentDirector) + 1);
        }
        System.out.println("Comedy movies: " + homManyComediesMovie);
        System.out.println("Long movies: " + howManyLongMovies);
        for (String director : moviesByDirector.keySet()) {
            System.out.println(director + "\t" + moviesByDirector.get(director));
            if (moviesByDirector.get(director) > howManyMoviesByDirector) {
                howManyMoviesByDirector = moviesByDirector.get(director);
                maxDirector = director;
            }
        }
        System.out.println("There is max: " + howManyMoviesByDirector + " per director: " + maxDirector);
    }


    public void testLoadRaters() {
        loadRaters("ratings.csv");
        int numberOfRaters = efficientRaterList.size();
        System.out.println("Number of raters : " + "\t" + numberOfRaters + "\n");

        for (EfficientRater currentEfficientRater : efficientRaterList) {
            String id = currentEfficientRater.getID();
            System.out.println("PlainIRater id: " + "\t" + id);
            int numberOfRatings = currentEfficientRater.numRatings();
            System.out.println("Number of ratings" + "\t" + numberOfRatings);
            ArrayList<String> itemsRated = currentEfficientRater.getItemsRated();
            for (String item : itemsRated) {
                System.out.println(item + "\t" + currentEfficientRater.getRating(item));
            }
            System.out.println("\n");
        }


        int maxSizeRatings = 0;
        String selectedRater = "193";
        String movieTitle = "1798709";
        int howManyWithMovieTitle = 0;
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (EfficientRater currentEfficientRater : efficientRaterList) {
            if (currentEfficientRater.numRatings() > maxSizeRatings)
                maxSizeRatings = currentEfficientRater.numRatings();

            if (currentEfficientRater.hasRating(movieTitle))
                howManyWithMovieTitle++;

            if (currentEfficientRater.getID().equals(selectedRater))
                System.out.println("PlainIRater with number " + selectedRater + " has " + currentEfficientRater.numRatings() + " ratings");

            for (String currentTitle : currentEfficientRater.getItemsRated()) {
                if (!moviesRated.contains(currentTitle))
                    moviesRated.add(currentTitle);
            }
        }
        System.out.println("\nMaximum number of ratings: " + maxSizeRatings + "\n" + "Raters with this value: ");
        for (EfficientRater currentEfficientRater : efficientRaterList) {
            if (currentEfficientRater.numRatings() == maxSizeRatings)
                System.out.println(currentEfficientRater.getID());
        }
        System.out.println("Movie: " + movieTitle + " was rated by " + howManyWithMovieTitle);
        System.out.println("There is " + moviesRated.size() + " movies rated");
    }
*/
}

