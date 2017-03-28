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

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public FirstRatings() {
        movieArrayList = new ArrayList<Movie>();
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
            System.out.println("Error with reading file");
        }


    }

    public void testLoadMovies() {
        int homManyComediesMovie = 0;
        int howManyLongMovies = 0;
        HashMap<String, Integer> moviesByDirector = new HashMap<String, Integer>();
        loadMovies("ratedmovies_short.csv");
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
        for (String director : moviesByDirector.keySet())
            System.out.println(director + "\t" + moviesByDirector.get(director));
    }

}

