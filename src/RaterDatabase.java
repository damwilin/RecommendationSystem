import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Damian on 3/31/2017.
 */
public class RaterDatabase {
        private static HashMap<String,IRater> ourRaters;

    private static void initialize() {
        // this method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<String,IRater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,IRater>();
            addRatings("data/" + filename);
        }
    }

    public static void addRatings(String filename) {
        initialize();
        try {
            Reader in = new FileReader("data/" + filename);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
            for (CSVRecord record : records){
                String id = record.get("rater_id");
                String item = record.get("movie_id");
                String rating = record.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
            }
        }
        catch (IOException e){
            System.out.println("Problem with file: " + filename);
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        IRater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }

    public static IRater getRater(String id) {
        initialize();

        return ourRaters.get(id);
    }

    public static ArrayList<IRater> getRaters() {
        initialize();
        ArrayList<IRater> list = new ArrayList<IRater>(ourRaters.values());

        return list;
    }

    public static int size() {
        return ourRaters.size();
    }



}

