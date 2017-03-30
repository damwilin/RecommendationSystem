import java.util.ArrayList;

/**
 * Created by Damian on 3/30/2017.
 */
public interface IRater {
    public void addRating(String item, double rating);

    public boolean hasRating(String item);

    public String getID();

    public double getRating(String item);

    public int numRatings();

    public ArrayList<String> getItemsRated();

}
