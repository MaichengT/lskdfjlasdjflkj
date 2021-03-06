// --== CS400 Project One File Header ==--
// Name: Braeden Bertz
// CSL Username: bbertz
// Email: bbertz@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: Algorithm Engineer did not communicate with me, Frontend Engineer did

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Implementation of the app for the ShowSearcher project. Used to start the program
 */
public class ShowSearcherApp {
    /**
     * Main for the program, loads the shows from our csv, intializes the sorted hash table
     * and initiates the backend and frontend for user engagement
     * @param args args
     */
    public static void main(String[] args) {
        ShowLoader loader = new ShowLoader(); //new ShowLoader();
        try {
            List<IShow> shows = loader.loadShows("src/shows_test.csv");
            ShowSearcherBackend backend = new ShowSearcherBackend(); //new ShowSearcherBackend();
            for (IShow show : shows) backend.addShow(show);
            ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend); //new ShowSearcherFrontend(backend);
            frontend.runCommandLoop();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
    }
}