// --== CS400 Project One File Header ==--
// Name: Braeden Bertz
// CSL Username: bbertz
// Email: bbertz@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: Algorithm Engineer did not communicate with me, Frontend Engineer did

import java.util.List;
import java.util.Objects;

/**
 * Testing class for functionality of ShowSearcherBackend
 */
public class TestShowSearcherBackend {
    /**
     * Run the tests for method functionality verification
     * @param args args
     */
    public static void main(String[] args) {
        boolean x = test1();
        System.out.println(x);
        x = test2();
        System.out.println(x);
        x = test3();
        System.out.println(x);
        x = test4();
        System.out.println(x);
        x = test5();
        System.out.println(x);
    }

    /**
     * Test the searchByTitle method and the filter() method to
     * make sure that shows get filtered out properly from the return list
     * @return true if the searchByTitle and filter by provider methods
     *          return the correct values, don't throw unexpected errors
     *          and have no unintended consequences as seen in comments
     *          false if any of the above is false
     */
    private static boolean test1() {
        ShowSearcherBackend backend = new ShowSearcherBackend();

        //Find all shows with "and" in their title, in our test case this is 3 shows, show2, show4, show5, note it does by hardcoding
        List<IShow> andInTitle = backend.searchByTitleWord("aND");
        if (andInTitle.size() != 3) return false;
        //test functionality when search is not within any of the titles
        List<IShow> sevenInTitle = backend.searchByTitleWord("7");
        if (sevenInTitle != null) return false;
        //test functionality when searching for titles with different provider filters
        backend.toggleProviderFilter("NeTFlix");
        List<IShow> andInTitleAndProvidedInAnyBesidesNetflix = backend.searchByTitleWord("AND");
        if (andInTitleAndProvidedInAnyBesidesNetflix.size() != 2) return false;
        backend.toggleProviderFilter("disney+");
        List<IShow> notDisneyNotNetflix = backend.searchByTitleWord("and");
        //Only show4 fits the criteria
        if (notDisneyNotNetflix.size() != 1) return false;
        return true;
    }

    /**
     * Test the searchByYear method and the filter() method to
     * make sure that shows get filtered out properly from the return list
     * @return true if the searchByYear and filter by provider methods
     *          return the correct values, don't throw unexpected errors
     *          and have no unintended consequences as seen in comments
     *          false if any of the above is false
     */
    private static boolean test2() {
        //test year functionality
        ShowSearcherBackend backend = new ShowSearcherBackend();
        IShow show1 = new Show("Attack on Titan",  2002, 99, "Netflix Hulu");
        IShow show2 = new Show("Adventure Time: with Finn and Jake", 2, 1, "Disney+");
        IShow show3 = new Show("M.D.D.E.L.", 12098, 290382, "Netflix Prime Video");
        IShow show4 = new Show("Jake and Bake, the titan shifter", 999, 999, "Prime Video");
        IShow show5 = new Show("Snakes on a Plane: And DONUTS!!!", 2002, 2092, "Netflix");
        //Should only return 2 values, show1 and show 5
        List<IShow> nostalgicMovies = backend.searchByYear(2002);
        if (nostalgicMovies.size() != 2) return false;
        if (!Objects.equals(nostalgicMovies.get(0).getTitle(), show1.getTitle()) ||
                !Objects.equals(nostalgicMovies.get(1).getTitle(), show5.getTitle())) return false;
        nostalgicMovies = backend.searchByYear(0);
        if (nostalgicMovies != null) return false;
        backend.toggleProviderFilter("Netflix");
        //should only return show1
        nostalgicMovies = backend.searchByYear(2002);
        if (nostalgicMovies.size() != 1) return false;
        if (!Objects.equals(nostalgicMovies.get(0).getTitle(), show1.getTitle())) return false;

        return true;
    }

    /**
     * Test the add method and the filter() method to
     * make sure that shows get filtered out properly from the return list
     * @return true if the add and filter by provider methods
     *          return the correct values, don't throw unexpected errors
     *          and have no unintended consequences as seen in comments
     *          false if any of the above is false
     */
    private static boolean test3() {
        //test funcitonality of add method
        IShow show1 = new Show("Attack on Titan",  2002, 99, "Netflix Hulu");
        IShow show2 = new Show("Adventure Time: with Finn and Jake", 2, 1, "Disney+");
        IShow show3 = new Show("M.D.D.E.L.", 12098, 290382, "Netflix Prime Video");
        IShow show4 = new Show("Jake and Bake, the titan shifter", 999, 999, "Prime Video");
        IShow show5 = new Show("Snakes on a Plane: And DONUTS!!!", 2002, 2092, "Netflix");
        ShowSearcherBackend backend = new ShowSearcherBackend();
        backend.addShow(show1);
        backend.addShow(show2);
        backend.addShow(show3);
        backend.addShow(show4);
        backend.addShow(show5);
        if (backend.getNumberOfShows() != 5) return false;
        backend.searchByTitleWord("i");
        backend.setProviderFilter("netflix", false);
        backend.setProviderFilter("disney+", false);
        backend.searchByTitleWord("nsljfaljeifnasd");
        backend.getProviderFilter("hulu");
        if (backend.getNumberOfShows() != 5) return false;
        backend.addShow(new Show("lkasjdfj", 283, 2, "Prime Video"));
        if (backend.getNumberOfShows() != 6) return false;
        return true;
    }

    /**
     * Test the provider status methods to
     * make sure that shows get filtered out properly from the return list
     * @return true if the provider status filter methods
     *          return the correct values, don't throw unexpected errors
     *          and have no unintended consequences as seen in comments
     *          false if any of the above is false
     */
    private static boolean test4() {
        //test provider status functionality
        ShowSearcherBackend backend = new ShowSearcherBackend();
        backend.setProviderFilter("netflix", false);
        List<IShow> nostalgia = backend.searchByTitleWord("and");
        if (nostalgia.size() != 2) return false;
        backend.setProviderFilter("prime video", false);
        nostalgia = backend.searchByTitleWord("and");
        if (nostalgia.size() != 1) return false;
        backend.setProviderFilter("hulu", false);
        nostalgia = backend.searchByTitleWord("and");
        if (nostalgia.size() != 1) return false;
        backend.setProviderFilter("disney+", false);
        nostalgia = backend.searchByTitleWord("and");
        if (nostalgia != null) return false;
        backend.toggleProviderFilter("netFlix");
        backend.toggleProviderFilter("hulu");
        nostalgia = backend.searchByTitleWord("and");
        if (nostalgia.size() != 1) return false;
        backend.toggleProviderFilter("disney+");
        backend.toggleProviderFilter("prime viDEO");
        nostalgia = backend.searchByTitleWord("and");
        if (nostalgia.size() != 3) return false;
        return true;
    }

    /**
     * Test the all methods to with illegal or unexpected inputs
     * and ensure that no errors or false returns are committed
     * @return true if the methods bail out intentionally or
     *          return the correct values, don't throw unexpected errors
     *          and have no unintended consequences as seen in comments
     *          false if any of the above is false
     */
    private static boolean test5() {
        //test inputs that could give buggy behaviour
        //i.e., test non-expected inputs
        ShowSearcherBackend backend = new ShowSearcherBackend();
        if (backend.searchByYear(-1) != null) return false;
        if (backend.getProviderFilter("lsakdjflkasdjflkasjdlfkaslkdfjkdj")) return false;
        if (!backend.getProviderFilter("netflix")) return false;
        if (backend.getProviderFilter("")) return false;
        backend.toggleProviderFilter("prime video");
        if (backend.getProviderFilter("prime video")) return false;
        if (backend.getNumberOfShows() != 0) return false;
        if (backend.searchByTitleWord("") != null) return false;
        backend.addShow(null);
        if (backend.getNumberOfShows() != 0) return false;
        return true;
    }
}
