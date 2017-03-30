import java.util.ArrayList;

/**
 * Created by Damian on 3/30/2017.
 */
public class DirectorsFilter implements IFilter {
    String director;

    public DirectorsFilter(String director) {
        this.director = director;
    }

    private ArrayList<String> getDirectorsList() {
        ArrayList<String> directorsList = new ArrayList<String>();
        int pos = 0;
        String directorToSub = director;
        String currentDirector;
        while (true) {
            directorToSub = directorToSub.substring(pos);
            int commaIndex = directorToSub.indexOf(",");
            if (commaIndex != -1) {
                currentDirector = directorToSub.substring(0, commaIndex);
                directorsList.add(currentDirector);
                pos = commaIndex + 1;
            } else {
                currentDirector = directorToSub;
                directorsList.add(currentDirector);
                break;
            }
        }
        return directorsList;
    }

    @Override
    public boolean satisfies(String id) {
        ArrayList<String> directorsList = getDirectorsList();
        for (String name : directorsList)
            if (MovieDatabase.getDirector(id).contains(name))
                return true;
        return false;
    }
}
