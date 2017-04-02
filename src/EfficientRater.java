import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Damian on 3/30/2017.
 */
public class EfficientRater implements IRater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String myID) {
        this.myID = myID;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item))
            return true;
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myRatings.containsKey(item))
            return myRatings.get(item).getValue();
        else
            return -1;
    }


    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String key : myRatings.keySet()) {
            list.add(key);
        }

        return list;
    }
}
