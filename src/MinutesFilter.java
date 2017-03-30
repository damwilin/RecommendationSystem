/**
 * Created by Damian on 3/30/2017.
 */
public class MinutesFilter implements IFilter {
    private int minMinutes;
    private int maxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes;
    }
}
