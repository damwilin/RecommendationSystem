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
    private ArrayList<Movie> movieArrayList;

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public FirstRatings() {
        movieArrayList = new ArrayList<Movie>();
    }

    public void loadMovies(String filename){
        try{
            Reader in = new FileReader("data/" + filename);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            for (CSVRecord record : records) {
                Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));
                movieArrayList.add(movie);
            }
        }
        catch (IOException e){
            System.out.println("Error with reading file");
        }


    }


}

