// --== CS400 Project One File Header ==--
// Name: Braeden Bertz
// CSL Username: bbertz
// Email: bbertz@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: Algorithm Engineer did not communicate with me, Frontend Engineer did
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implements IShowSearcherBackend for the backend functionality of the program
 * Can find shows from sorted hash table using just one word in title, or the year of the
 * show, and can filter out shows that don't fit the provider criteria
 * selected and toggled from the frontend engineer
 */
public class ShowSearcherBackend implements IShowSearcherBackend {
    //Hashtable 1 used for searching when given the year
    HashTableSortedSets<Integer, IShow> year;
    //Hashtable 2 used for searching when given a title word
    HashTableSortedSets<String, IShow> title;
    //Sample list of providers
    private final String[] providers = {"Netflix", "Prime Video", "Hulu", "Disney+"};
    //The filtering status of the provider in the corresponding index of providers, default is inclusion
    boolean[] filterStatus = {true, true, true, true};

    /**
     * Default constructor that initializes the HashTableSortedSets with default constructors
     */
    ShowSearcherBackend(){
        year = new HashTableSortedSets<>();
        title = new HashTableSortedSets<>();
    }

    /**
     * Add a show to the HashTableSortedSets for both year and title words
     * Each show will have n additions to the title HashTableSortedSets where
     * n is the number of words in the title of the show. That way, if the user
     * attempts to search for a show with any word in the show's title, we can
     * use that search's hash and find it in the title HashTableSortedSets
     *      Example: addShow("Adventure Time: with Finn and Jake").... but as a show
     *      Then searching "Adventure" "Time:" "with" "Finn" "and" "Jake" all as
     *      separate searches will give in the returnable set
     *      "Adventure Time: with Finn and Jake"...
     *      however, a search of "time" will not return "Adventure Time: With finn and Jake"...
     * NOTE: The search of "FINN" and the search of "finn" or any variation of case sensitive
     *       searching will return the same associated shows, in this case Adventure time: with...
     * @param show The show that we will be putting into the maps
     */
    @Override
    public void addShow(IShow show) {
        if (show == null) return;//Can't input a null show
        //Add the show via the year it was produced
        year.add(show.getYear(), show);
        //Add each of the words in the title to the title hashtable so that
        //if the user inputs any of the words in the show, they will
        //get the show back from the hashtable
        //For special titles i.e. "Hello, carla" we want the user to be able to search "hello" or "carla" or "hello,"
        //so we can also add the words with their special characters added
        String[] titleWordsInShow = show.getTitle().split(" ");
        for (String titleWord : titleWordsInShow) {
            titleWord = titleWord.toLowerCase();

            title.add(titleWord, show);
            String spcialTitle = formatSpecialWord(titleWord);
            if (!titleWord.equals(spcialTitle)) title.add(spcialTitle, show);
        }
    }

    private String formatSpecialWord(String titleWord) {
        char[] specialCharcters = {',', '?', '"', '\\', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '{','}', '`', '~', ',','.'};
        for (Character c : specialCharcters) {
            if (titleWord.contains(""+c)) titleWord = titleWord.replaceAll(""+c,"");
        }
        return titleWord;
    }

    /**
     * Return the index of the provider supplied within the providers[]
     * @param provider The string representation of the provider we want the index for
     * @return the index of providers with the same string value as param, -1 if providers
     *          doesn't have that provider in it
     */
    private int findProviderIndex(String provider){
        for (int i = 0; i < providers.length; i++) {
            if (providers[i].equals(provider)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Calculate the number of shows that have been input into the HashTableSortedSets
     * This is equivalent to the size of the year HashTableSortedSets since we only add
     * any given show once to teh whole table since it has only 1 year, whereas
     * title HashTableSortedSets can have multiple values with the same show since
     * that show can have more than 1 word as the title
     * @return The number of unique shows that have been added to the HashTableSortedSets
     */
    @Override
    public int getNumberOfShows() {
        return year.size();
    }

    /**
     * Set the filter for the given provider to the given boolean value
     * @param provider The provider we want to change the filter status for
     * @param filter What we want to change the filter status to
     * @implNote If the @param provider is not present in our provider list, we don't change
     *          any of the provider's statuses
     */
    @Override
    public void setProviderFilter(String provider, boolean filter) {
        int i = findProviderIndex(provider);
        if (i != -1) {
            filterStatus[i] = filter;
        }
    }

    /**
     * Return the filter status associated with the given provider
     * @param provider The provider we want to know the filter status of
     * @return The filter status of the provider
     */
    @Override
    public boolean getProviderFilter(String provider) {
        int i = findProviderIndex(provider);
        if(i == -1) {
            //provider wasn't in the predefined list of providers, idk what to do
            return false;
        }
        else return filterStatus[i];
    }

    /**
     * Toggle the filter of the given provider to the opposite value
     * i.e., true -> false and false->true
     * @param provider The string of the provider they want to toggle the filter of
     */
    @Override
    public void toggleProviderFilter(String provider) {
        int i = findProviderIndex(provider);
        if (i == -1) return;
        else {
            filterStatus[i] = !filterStatus[i];
        }
    }

    /**
     * Returns a list of shows that 1) have within their title the given word search term
     * and 2) the shows all have at least 1 active provider status (true)
     * @param word The word we are searching the titles for matches
     * @return A list of shows as described above. If there are no shows that match
     *          the criteria, we return null
     */
    @Override
    public List<IShow> searchByTitleWord(String word) {
        //First, we must format the word so that it is in the same format as our hashes
        //otherwise "Finn" and "finn" would give different hashcodes and thus wouldn't be in our database
        //but case sensitivity does not matter
        word = word.toLowerCase();
        //Now, get all results that have word in the title of the show
        try {
            List<IShow> shows = title.get(word);
            //If there are no shows that match our criteria, we return null
            if (shows == null) return null;
            shows = filterShows(shows);
            if (shows.size() == 0) return null;
            return shows;
        } catch (NoSuchElementException e){
            //Search didn't have any terms that matched, return null
            return null;
        }
    }

    /**
     * Filter a list of shows, removing ones that have no providers toggled on
     * @param shows The list of shows we are filtering
     * @return The filtered list of shows as described above
     */
    private List<IShow> filterShows(List<IShow> shows) {
        shows.removeIf(e -> {
            for (int i = 0; i < filterStatus.length; i++) {
                //check to see if the provider filter status is on and the show is provided by that provider
                //if so, then we don't need to remove since at least one provider
                //not being filtered out is available
                if (filterStatus[i] && e.isAvailableOn(providers[i])) return false;
            }
            return true;
        });
        return shows;
    }

    /**
     * Returns a list of shows that 1) have their year = to the given year
     * and 2) the shows all have at least 1 active provider status (true)
     * @param year The year we are searching the shows for matches
     * @return A list of shows as described above. If there are no shows that match
     *          the criteria, we return null
     */
    @Override
    public List<IShow> searchByYear(int year) {
        try {
            List<IShow> shows = this.year.get(year);
            if (shows == null) return null;
            shows = filterShows(shows);
            if (shows.size() == 0) return null;
            return shows;
        } catch (NoSuchElementException e){
            //no shows with search year
            return null;
        }
    }
}
