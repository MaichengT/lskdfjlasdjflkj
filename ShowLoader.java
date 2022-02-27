// --== CS400 Project One File Header ==--
// Name: Maicheng Thao
// CSL Username: maicheng
// Email: mthao43@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: <any optional extra notes to your grader>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to load a list of Show objects from a csv file
 * 
 * @author Maicheng Thao
 *
 */
public class ShowLoader implements IShowLoader {

  /**
   * This method loads the list of movies described within a CSV file.
   * @param filepath is relative to executable's working directory
   * @return a list of show objects that were read from specified file
   */
  @Override
  public List<IShow> loadShows(String filepath) throws FileNotFoundException {
    File file = new File(filepath); // creates File object using String filepath
    // if the file does not exist, throw FileNotFoundException 
    if (!file.exists()) {
      throw new FileNotFoundException("file not found");
    }
    
    // if file does exist, traverse file to get information and put into a list
    List<IShow> list = new ArrayList<IShow>();
    
    Scanner scan = new Scanner(file, "UTF-8");
    // set nextLine() to be heading of csv file
    String heading = scan.nextLine();
    
    // traverse through csv file and get correct components to be put into Show objects
    while (scan.hasNextLine()) {
      String title = "";     // title of show
      int year = 0;          // year show was produced
      int rating = 0;        // show ratings
      String providers = ""; // the sites that the show is available on
      // set the current line to String row
      String row = scan.nextLine(); 
     
      // if row contains quotes
      // title
      if (row.contains("\"")) {
        int i = 0;     // index
        int count = 0; // counter for number of characters leading to quotations
        // traverse through String row
        // increment index and count until current char is a quotation mark
        while (i < row.length()) {
          char character = row.charAt(i);
          if (character == '"') {
            break;
          }
          count++;
          i++;
        }
        // cut title from the start of String title including first quotation mark
        // ex: title:Weirdest, Bestest, Truest",2018,all,,14/100,0,0,0,1,1
        title = row.substring(count + 1);
        
        // remove characters leading up to quotes starting from end
        i = title.length() - 1; // set index to start from end of title String
        count = 0;              // reset counter
        // traverse through String title
        // decrement index and increment count until current char is a quotation mark
        while (i > 0) {
          char character = title.charAt(i);
          if (character == '"') {
            break;
          }
          count++;
          i--;
        }
        // put current title (title currently does not have characters leading up to first
        // quotation mark, title start with first letter of show and contains the rest of 
        // the String row ex: tmpYear:Weirdest, Bestest, Truest",2018,all,,14/100,0,0,0,1,1) 
        // into String tmpYear to be used to find year
        String tmpYear = title; 
        // cut title from the end including first quotation mark (starting from end)
        // ex: title:Weirdest, Bestest, Truest
        title = title.substring(0, title.length() - count - 1); 
        
        // replace double quotes with single quotes if title has double quotes
        title = title.replace("\"\"", "\"");
        
        // year
        // cut tmpYear so title is removed from String
        // ex: tmpYear:,2018,all,,14/100,0,0,0,1,1
        tmpYear = tmpYear.substring(tmpYear.length() - count);
        // set tmpYear to be the integer portion
        // ex: year:2008
        tmpYear = tmpYear.substring(1, 5);
        // parse tempyear into an Integer and put into year
        year = Integer.parseInt(tmpYear);
        
        // rating 
        // split String row at commas and put into col array
        String[] col = row.split(",");
        // set length to equal the length of array col
        int length = col.length;
        // set String tmpRating to be col[length - 6] (counting from the end of String row, 
        // 6 represents the other columns before the rating column
        String tmpRating = col[length - 6];
        i = 0;     // reset index 
        count = 0; // reset counter
        // traverse tmpRating String 
        // increment index and count until the slash '/'
        while (i < tmpRating.length()) {
          char character = tmpRating.charAt(i);
          if (character == '/') {
            break;
          }
          count++;
          i++;
        }
        // set tmpRating to be from index 0 to index count (index before slash)
        tmpRating = tmpRating.substring(0, count);
        // parse tmpRating into Integer and put into rating
        rating = Integer.parseInt(tmpRating);
        
        // providers
        // if col[length - 5] is a 1, add Netflix to providers String
        if (col[length - 5].equals("1")) {
          providers += "Netflix ";
        }
        // if col[length - 4] is a 1, add Hulu to providers String
        if (col[length - 4].equals("1")) {
          providers += "Hulu ";
        }
        // if col[length - 3] is a 1, add Prime Video to providers String
        if (col[length - 3].equals("1")) {
          providers += "Prime Video ";
        }
        // if col[length - 2] is a 1, add Disney+ to providers String
        if (col[length - 2].equals("1")) {
          providers += "Disney+ ";
        }
        providers = providers.trim(); // trim whitespaces
      }
      // for show titles that do not have commas/quotation marks
      else {
        // split String row at each comma
        String[] col = row.split(",");
        // title
        // set title to column 2 (column 2 contains the show titles)
        title = col[2];
        
        // year
        // create scanner
        Scanner scanInt = new Scanner(col[3]);
        // scan through index 3 of col array (column 3 contains the years)
        // set year to nextInt() and break
        while (scanInt.hasNextInt()) {
          year = scanInt.nextInt();
          break;
        }
        scanInt.close(); // close scanner
        
        // rating
        // split col[6] (column 6 contains the ratings) at the slash '/'
        // create String array tmpRating and set it to the split of col[6] 
        // scan through tmpRating array
        // set rating to nextInt() and break
        String[] tmpRating = col[6].split("/");
        scanInt = new Scanner(tmpRating[0]);
        while (scanInt.hasNextInt()) {
          rating = scanInt.nextInt();
          break;
        }
        scanInt.close(); // close scanner
        
        // providers
        // if column 7 is a 1, put Netflix into providers String
        if (col[7].equals("1")) {
          providers += "Netflix ";
        }
        // if column 8 is a 1, put Hulu into providers String
        if (col[8].equals("1")) {
          providers += "Hulu ";
        }
        // if column 9 is a 1, put Prime Video into providers String
        if (col[9].equals("1")) {
          providers += "Prime Video ";
        }
        // if column 10 is a 1, put Disney+ into providers String
        if (col[10].equals("1")) {
          providers += "Disney+ ";
        }
        providers.trim(); // trim whitespaces
      }
        // create Show object and add show into the list
        Show show = new Show(title, year, rating, providers);
        list.add(show);
    }
    scan.close(); // close the scanner
    return list; // return the list
    }
}
