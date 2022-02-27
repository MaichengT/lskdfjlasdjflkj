// --== CS400 Project One File Header ==--
// Name: Braeden Bertz
// CSL Username: bbertz
// Email: bbertz@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: Algorithm Engineer did not communicate with me, Frontend Engineer did

/**
 * Placeholder class for IShow implementation (i.e., Show)
 */
public class IShow {
    private String title;
    private String providers;
    private int year;
    private int rating;

    IShow(String t, String p, int y, int r){
        title = t;
        providers = p.toLowerCase();
        year = y;
        rating = r;
    }

        public String getTitle() {
        return title;
    }

        public int getYear() {
        return year;
    }

        public int getRating() {
        return rating;
    }

        public boolean isAvailableOn(String provider) {
        return this.providers.contains(provider.toLowerCase());
    }
}
