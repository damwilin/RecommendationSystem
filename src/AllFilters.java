/**
 * Created by Damian on 3/30/2017.
 */

import java.util.ArrayList;

public class AllFilters implements IFilter {
    ArrayList<IFilter> filters;

    public AllFilters() {
        filters = new ArrayList<IFilter>();
    }

    public void addFilter(IFilter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for (IFilter f : filters) {
            if (!f.satisfies(id)) {
                return false;
            }
        }

        return true;
    }

}
