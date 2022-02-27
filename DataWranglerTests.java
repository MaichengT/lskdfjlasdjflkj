// --== CS400 Project One File Header ==--
// Name: Maicheng Thao
// CSL Username: maicheng
// Email: mthao43@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the ShowLoader and Show classes
 * 
 * @author Maicheng Thao
 *
 */
public class DataWranglerTests {
  
  /**
   * Tests compareTo() in Show class
   * 
   * @return true if compareTo() works as expected, otherwise false
   */
  public static boolean test1() { 
    // create ShowLoader object to load csv file with shows
    ShowLoader loader = new ShowLoader();
 
    // Show objects
    Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    Show show2 = new Show("'Allo 'Allo!", 1984, 68, "Netflix");
    Show show3 = new Show("Sister, Sister", 1994, 59, "Netflix Hulu");
    Show show4 = new Show("#blackAF", 2020, 54, "Netflix");
    Show show5 = new Show("Hello, Me!", 2021, 46, "Netflix");
    Show show6 = new Show("Say \"I Love You.\"", 2012, 56, "Hulu"); 
    Show show7 = new Show("LEGO Star Wars: Droid Tales", 2015, 46, "Disney+");

    // actual list of shows
    List<IShow> actual = new ArrayList<IShow>();
    
    // invalid file, file does not exist
    try {
      actual = loader.loadShows("file_not_found.csv");
    } 
    // FileNotFoundExceotion should be caught
    catch (FileNotFoundException fnf) {
      System.out.println(fnf.getMessage());
    } 
    // if other exceptions are caught, return false
    catch (Exception e) {
      System.out.println("error, unexpected exception");
      return false;
    }
    
    // valid file
    try {
      // pass csv file into ShowLoader object to load shows
      // set actual list to be list returned from loadShows()
      actual = loader.loadShows("shows_test.csv");
      
      // make sure compareTo() returns 1 when this Show's rating is greater than the Show it is
      // being compared to
      if (actual.get(0).compareTo(show2) != 1) {
        System.out.println("(1) error, compareTo() failed");
        return false;
      }
      // make sure compareTo() returns -1 when this Show's rating is less than the Show it is
      // being compared to
      if (actual.get(2).compareTo(show2) != -1) {
        System.out.println("(2) error, compareTo() failed");
        return false;
      }
      // make sure compareTo() returns 0 when this Show's rating is eqaul to the Show it is
      // being compared to
      if (actual.get(4).compareTo(show7) != 0) {
        System.out.println("(3) error, compareTo() failed");
        return false;
      }
    } 
    // FileNotFoundException should not be caught, return false
    catch (FileNotFoundException fnf) {
      System.out.print(fnf.getMessage());
      System.out.println("error, exception should not be caught");
      return false;
    } 
    // if other exceptions are caught, return false
    catch (Exception e) {
      System.out.println("error, unexpected exception");
      return false;
    }
    return true; 
  }
  
  /**
   * Tests getTitle() in Show class
   * 
   * @return true if compareTo() works as expected, otherwise false
   */
  public static boolean test2() {
    // create ShowLoader object to load csv file with shows
    ShowLoader loader = new ShowLoader();
    
    // expected list of shows
    List<IShow> expected = new ArrayList<IShow>();
    // Show objects to put in expected list
    Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    Show show2 = new Show("'Allo 'Allo!", 1984, 68, "Netflix");
    Show show3 = new Show("Sister, Sister", 1994, 59, "Netflix Hulu");
    Show show4 = new Show("#blackAF", 2020, 54, "Netflix");
    Show show5 = new Show("Hello, Me!", 2021, 46, "Netflix");
    Show show6 = new Show("Say \"I Love You.\"", 2012, 56, "Hulu"); 
    Show show7 = new Show("LEGO Star Wars: Droid Tales", 2015, 46, "Disney+");
    // add shows to list
    expected.add(show1);
    expected.add(show2);
    expected.add(show3);
    expected.add(show4);
    expected.add(show5);
    expected.add(show6);
    expected.add(show7);
    
    // actual list of shows
    List<IShow> actual = new ArrayList<IShow>();
    
    try {
      // pass csv file into ShowLoader object to load shows
      // set actual list to be list returned from loadShows()
      actual = loader.loadShows("shows_test.csv");
      
      // test title with no quotes/commas
      if (!actual.get(0).getTitle().equals(expected.get(0).getTitle())) {
        System.out.println("(1) error, getTitle() failed");
        return false;
      }
      // tests title with a comma
      if (!actual.get(4).getTitle().equals(expected.get(4).getTitle())) {
        System.out.println("(2) error, getTitle() failed");
        return false;
      }
      // tests title with quotes
      if (!actual.get(5).getTitle().equals(expected.get(5).getTitle())) {
        System.out.println("(3) error, getTitle() failed");
        return false;
      }
    } 
    // FileNotFoundException should not be caught, return false
    catch (FileNotFoundException fnf) {
      System.out.print(fnf.getMessage());
      System.out.println("error, exception should not be caught");
      return false;
    } 
    // if other exceptions are caught, return false
    catch (Exception e) {
      System.out.println("error, unexpected exception");
      return false;
    }
    return true;
  }
  
  /**
   * Tests getYear() in Show class
   * 
   * @return true if compareTo() works as expected, otherwise false
   */
  public static boolean test3() {
    // create ShowLoader object to load csv file with shows
    ShowLoader loader = new ShowLoader();
    
    // expected list of shows
    List<IShow> expected = new ArrayList<IShow>();
    // Show objects to put in expected list
    Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    Show show2 = new Show("'Allo 'Allo!", 1984, 68, "Netflix");
    Show show3 = new Show("Sister, Sister", 1994, 59, "Netflix Hulu");
    Show show4 = new Show("#blackAF", 2020, 54, "Netflix");
    Show show5 = new Show("Hello, Me!", 2021, 46, "Netflix");
    Show show6 = new Show("Say \"I Love You.\"", 2012, 56, "Hulu"); 
    Show show7 = new Show("LEGO Star Wars: Droid Tales", 2015, 46, "Disney+");
    // add shows to list
    expected.add(show1);
    expected.add(show2);
    expected.add(show3);
    expected.add(show4);
    expected.add(show5);
    expected.add(show6);
    expected.add(show7);
    
    // actual list of shows
    List<IShow> actual = new ArrayList<IShow>();
    
    try {
      // pass csv file into ShowLoader object to load shows
      // set actual list to be list returned from loadShows()
      actual = loader.loadShows("shows_test.csv");
      
      // test title with no quotes/commas
      if (actual.get(0).getYear() != 2008) {
        System.out.println("(1) error, getYear() failed");
        return false;
      }
      // test title with commas
      if (actual.get(4).getYear() != 2021) {
        System.out.println("(2) error, getYear() failed");
        return false;
      }
      // test title with quotes
      if (actual.get(5).getYear() != 2012) {
        System.out.println("(3) error, getYear() failed");
        return false;
      }
    } 
    // FileNotFoundException should not be caught, return false
    catch (FileNotFoundException fnf) {
      System.out.print(fnf.getMessage());
      System.out.println("error, exception should not be caught");
      return false;
    } 
    // if other exceptions are caught, return false
    catch (Exception e) {
      System.out.println("error, unexpected exception");
      return false;
    }
    return true;
  }
  
  /**
   * Tests getRating() in Show class
   * 
   * @return true if getRating() works as expected, otherwise false
   */
  public static boolean test4() { 
    // create ShowLoader object to load csv file with shows
    ShowLoader loader = new ShowLoader();
    
    // expected list of shows
    List<IShow> expected = new ArrayList<IShow>();
    // Show objects to put in expected list
    Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    Show show2 = new Show("'Allo 'Allo!", 1984, 68, "Netflix");
    Show show3 = new Show("Sister, Sister", 1994, 59, "Netflix Hulu");
    Show show4 = new Show("#blackAF", 2020, 54, "Netflix");
    Show show5 = new Show("Hello, Me!", 2021, 46, "Netflix");
    Show show6 = new Show("Say \"I Love You.\"", 2012, 56, "Hulu"); 
    Show show7 = new Show("LEGO Star Wars: Droid Tales", 2015, 46, "Disney+");
    // add shows to list
    expected.add(show1);
    expected.add(show2);
    expected.add(show3);
    expected.add(show4);
    expected.add(show5);
    expected.add(show6);
    expected.add(show7);
    
    // actual list of shows
    List<IShow> actual = new ArrayList<IShow>();
    
    try {
      // pass csv file into ShowLoader object to load shows
      // set actual list to be list returned from loadShows()
      actual = loader.loadShows("shows_test.csv");
      
      // test title with no quotes/commas
      if (actual.get(0).getRating() != 100) {
        System.out.println("(1) error, getRating() failed");
        return false;
      }
      // test title with commas
      if (actual.get(4).getRating() != 46) {
        System.out.println("(2) error, getRating() failed");
        return false;
      }
      // test title with quotes
      if (actual.get(5).getRating() != 56) {
        System.out.println("(3) error, getRating() failed");
        return false;
      }
    } 
    // FileNotFoundException should not be caught, return false
    catch (FileNotFoundException fnf) {
      System.out.print(fnf.getMessage());
      System.out.println("error, exception should not be caught");
      return false;
    } 
    // if other exceptions are caught, return false
    catch (Exception e) {
      System.out.println("error, unexpected exception");
      return false;
    }
    return true;
  }
  
  /**
   * Tests isAvailableOn() in Show class
   * 
   * @return true if isAvailableOn() works as expected, otherwise false
   */
  public static boolean test5() { 
    // create ShowLoader object to load csv file with shows
    ShowLoader loader = new ShowLoader();
    
    // expected list of shows
    List<IShow> expected = new ArrayList<IShow>();
    // Show objects to put in expected list
    Show show1 = new Show("Breaking Bad", 2008, 100, "Netflix");
    Show show2 = new Show("'Allo 'Allo!", 1984, 68, "Netflix");
    Show show3 = new Show("Sister, Sister", 1994, 59, "Netflix Hulu");
    Show show4 = new Show("#blackAF", 2020, 54, "Netflix");
    Show show5 = new Show("Hello, Me!", 2021, 46, "Netflix");
    Show show6 = new Show("Say \"I Love You.\"", 2012, 56, "Hulu"); 
    Show show7 = new Show("LEGO Star Wars: Droid Tales", 2015, 46, "Disney+");
    // add shows to list
    expected.add(show1);
    expected.add(show2);
    expected.add(show3);
    expected.add(show4);
    expected.add(show5);
    expected.add(show6);
    expected.add(show7);
    
    // actual list of shows
    List<IShow> actual = new ArrayList<IShow>();
    
    try {
      // pass csv file into ShowLoader object to load shows
      // set actual list to be list returned from loadShows()
      actual = loader.loadShows("shows_test.csv");
      
      // show with 1 provider
      if (!actual.get(0).isAvailableOn("Netflix")) {
        System.out.println("(1) error, isAvailableOn() failed");
        return false;
      }
      // shows with more than 1 provider
      if (!actual.get(2).isAvailableOn("Hulu")) {
        System.out.println("(2) error, getYear() failed");
        return false;
      }
      // show with provider other than Netflix to make sure other providers print
      if (!actual.get(6).isAvailableOn("Disney+")) {
        System.out.println("(3) error, getYear() failed");
        return false;
      }
    } 
    // FileNotFoundException should not be caught, return false
    catch (FileNotFoundException fnf) {
      System.out.print(fnf.getMessage());
      System.out.println("error, exception should not be caught");
      return false;
    } 
    // if other exceptions are caught, return false
    catch (Exception e) {
      System.out.println("error, unexpected exception");
      return false;
    }
  return true;
  }
  
  /**
   * This method runs all the test methods that tests HashTableMap
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("test1(): " + test1());
    System.out.println("test2(): " + test2());
    System.out.println("test3(): " + test3());
    System.out.println("test4(): " + test4());
    System.out.println("test5(): " + test5());
  }
}
