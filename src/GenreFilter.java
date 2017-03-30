/**
 * Created by Damian on 3/30/2017.
 */
public class GenreFilter implements IFilter {
    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
