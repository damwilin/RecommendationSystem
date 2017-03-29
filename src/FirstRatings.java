import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Damian on 3/28/2017.
 */
public class FirstRatings {
    private ArrayList<Movie> movieArrayList;
    private ArrayList<Rater> raterArrayList;

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public ArrayList<Rater> getRaterArrayList() {
        return raterArrayList;
    }

    public FirstRatings() {
        movieArrayList = new ArrayList<Movie>();
        raterArrayList = new ArrayList<Rater>();
    }

    public void loadMovies(String filename) {
        try {
            Reader in = new FileReader("data/" + filename);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            for (CSVRecord record : records) {
                Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
                movieArrayList.add(movie);
            }
        } catch (IOException e) {
            System.out.println("Reading file error: " + filename);
        }
    }

    public void loadRaters(String filename) {
        Iterable<CSVRecord> records = loadFile(filename);
        for (CSVRecord record : records) {
            Rater rater = new Rater(record.get("rater_id"));
            int numberOfRater = numberOfRaterInArrayList(rater);
            if (numberOfRater == -1) {
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                raterArrayList.add(rater);
            } else
                raterArrayList.get(numberOfRater).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
        }
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

    private int numberOfRaterInArrayList(Rater rater) {
        String raterId = rater.getID();
        for (int i = 0; i < raterArrayList.size(); i++) {
            if (raterArrayList.get(i).getID().equals(rater.getID()))
                return i;
        }
        return -1;
    }

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
        int numberOfRaters = raterArrayList.size();
        System.out.println("Number of raters : " + "\t" + numberOfRaters + "\n");

        for (Rater currentRater : raterArrayList) {
            String id = currentRater.getID();
            System.out.println("Rater id: " + "\t" + id);
            int numberOfRatings = currentRater.numRatings();
            System.out.println("Number of ratings" + "\t" + numberOfRatings);
            ArrayList<String> itemsRated = currentRater.getItemsRated();
            for (String item : itemsRated) {
                System.out.println(item + "\t" + currentRater.getRating(item));
            }
            System.out.println("\n");
        }


        int maxSizeRatings = 0;
        String selectedRater = "193";
        String movieTitle = "1798709";
        int howManyWithMovieTitle = 0;
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (Rater rater : raterArrayList) {
            if (rater.numRatings() > maxSizeRatings)
                maxSizeRatings = rater.numRatings();

            if (rater.hasRating(movieTitle))
                howManyWithMovieTitle++;

            if (rater.getID().equals(selectedRater))
                System.out.println("Rater with number " + selectedRater + " has " + rater.numRatings() + " ratings");

            for (String currentTitle : rater.getItemsRated()) {
                if (!moviesRated.contains(currentTitle))
                    moviesRated.add(currentTitle);
            }
        }
        System.out.println("\nMaximum number of ratings: " + maxSizeRatings + "\n" + "Raters with this value: ");
        for (Rater rater : raterArrayList) {
            if (rater.numRatings() == maxSizeRatings)
                System.out.println(rater.getID());
        }
        System.out.println("Movie: " + movieTitle + " was rated by " + howManyWithMovieTitle);
        System.out.println("There is " + moviesRated.size() + " movies rated");
    }

}

