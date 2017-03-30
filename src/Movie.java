// An immutable passive data object (PDO) to represent item data
public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    public Movie(String id, String title, String year, String genres) {
        // just in case data file contains extra whitespace
        this.id = id.trim();
        this.title = title.trim();
        this.year = Integer.parseInt(year.trim());
        this.genres = genres;
    }

    public Movie(String id, String title, String year, String genres, String director,
                 String country, String poster, int minutes) {
        // just in case data file contains extra whitespace
        this.id = id.trim();
        this.title = title.trim();
        this.year = Integer.parseInt(year.trim());
        this.genres = genres;
        this.director = director;
        this.country = country;
        this.poster = poster;
        this.minutes = minutes;
    }

    // Returns ID associated with this item
    public String getID() {
        return id;
    }

    // Returns title of this item
    public String getTitle() {
        return title;
    }

    // Returns year in which this item was published
    public int getYear() {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres() {
        return genres;
    }

    public String getCountry() {
        return country;
    }

    public String getDirector() {
        return director;
    }

    public String getPoster() {
        return poster;
    }

    public int getMinutes() {
        return minutes;
    }

    // Returns a string of the item's information
    public String toString() {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}
