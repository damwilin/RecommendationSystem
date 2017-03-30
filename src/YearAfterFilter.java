/**
 * Created by Damian on 3/30/2017.
 */
public class YearAfterFilter implements IFilter {
    private int myYear;

    public YearAfterFilter(int year) {
        myYear = year;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }

}

