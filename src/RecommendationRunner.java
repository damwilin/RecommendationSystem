import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Damian on 4/2/2017.
 */
public class RecommendationRunner implements IRecommender {
    Random random = new Random();
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> moviesChoosen = new ArrayList<String>();
        for (int i=0; i<10; i++){
         int index = random.nextInt()* MovieDatabase.size();
            moviesChoosen.add(MovieDatabase.filterBy(new TrueFilter()).get(index));
        }
        return moviesChoosen;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> answerList = fourthRatings.getSimilarRatings(webRaterID,10,5);
        for (Rating rating : answerList){
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + MovieDatabase.getGenres(rating.getItem()));
        }


    }
}
