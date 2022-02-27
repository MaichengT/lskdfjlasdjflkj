// --== CS400 Project One File Header ==--
// Name: Maicheng Thao
// CSL Username: maicheng
// Email: mthao43@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: <any optional extra notes to your grader>

/**
 * This class represents the data about one specific show in our dataset
 * 
 * @author Maicheng Thao
 *
 */
public class Show implements IShow{
  // data field
  private String title;
  private int year;
  private int rating;
  private String providers;
  
  
  /**
   * constructor args (String title, int year, int rating, String providers)
   * where the providers string includes the names of every streaming source
   * 
   * @param title the title of the show
   * @param year the year the show was produced
   * @param rating the Rotten Tomatoes Rating 
   * @param providers the streaming sources the show is availble on
   */
  public Show(String title, int year, int rating, String providers) {
    this.title = title;
    this.year = year;
    this.rating = rating;
    this.providers = providers;
  }
  
  /**
   * compares ratings
   * 
   * @ return 0 if the show ratings are the same, 1 if this show has a higher rating,
   * or -1 if this show's rating is less than the show it is being compared to
   */
  @Override
  public int compareTo(IShow o) {
    if(this.getRating() > o.getRating()) {
      return 1;
    }
    if(this.getRating() < o.getRating()) {
      return -1;
    }
    return 0; 
  }

  /**
   * retrieve the title of this show object
   * 
   * @ return title of show
   */
  @Override
  public String getTitle() {
    return this.title;
  }

  /**
   * retrieve the year that this show was first produced
   * 
   * @ return year show was produced
   */
  @Override
  public int getYear() {
    return this.year;
  }

  /**
   * retrieve the Rotten Tomatoes Rating (out of 100)
   * 
   * @return Rotten Tomatoes Rating
   */
  @Override
  public int getRating() {
    return this.rating;
  }

  /**
   * checks show availability
   * 
   * @return true if the show is available on the given provider
   */
  @Override
  public boolean isAvailableOn(String provider) {
    if (this.providers.contains(provider)) {
      return true;
    }
    return false;
  }

}
