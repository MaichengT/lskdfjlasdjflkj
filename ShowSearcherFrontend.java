import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ShowSearcherFrontend implements IShowSearcherFrontend{
	
	Scanner sc;
	ShowSearcherBackend backend;
	
	/**
	 * Constructor that initializes the backend object we will be using
	 * and also initializes a Scanner using the System.in
	 * @param e
	 */
	public ShowSearcherFrontend(ShowSearcherBackend e) {
		this.backend = e;
		sc = new Scanner(System.in);
	}
	
	/**
	 * Constructor that initializes the ShowSearcherBackend object that 
	 * we will use throughout this class. Additionally this constructor
	 * initializes the Scanner with the String param s
	 * @param s
	 * @param e
	 */
	public ShowSearcherFrontend(String string, ShowSearcherBackend e) {
		this.backend = e;
		this.sc = new Scanner(string);
		System.out.println(string);
		
	}
	
	public void tester() {
		System.out.println(sc.next());
	}

	@Override
	public void runCommandLoop() {
		// TODO Auto-generated method stub
		System.out.println("Welcome to the Show Searcher App!");
		System.out.println("=================================");
		
		String c = "temp will change immediately once user enters command";//temp string to avoid initialize error
		
		while(!c.equals("Q") || !c.equals("4")) {//command loop that runs application until user quits
			displayCommandMenu();
			try {
				c = sc.next();//reads in the users input from the command menu
				System.out.println(c);
			}catch(InputMismatchException e) {
				System.out.println(e + "Restarting application");
			}
			
			c = c.toUpperCase();//converting to upper case to make comparisons easier
			
			if(c.equals("1") || c.equals("T")) {
				titleSearch();
			}
			else if(c.equals("2") || c.equals("Y")) {
				yearSearch();
			}
			else if(c.equals("F") || c.equals("3")) {//This else if statement completes all requirements
				helpFilter();
			}
			else if(c.equals("Q") || c.equals("4")) {
				return;
			}
			else {//This else activates if the user entered a command that was not an option and the 
				//loop restarts
				System.out.println("You entered an invalid command, please try again: ");
			}
			
			
		}
		
		System.out.println("Thank you for searching!");
		
	}
	
	private void helpFilter() {
		String input = "";
		/*try {
			input = sc.next();
		}catch(InputMismatchException e) {
			System.out.println(e + "Restarting Application");
			runCommandLoop();//if the input entered throws an exception the application will be restarted
		}
		input = input.toUpperCase();//easier for comparisons*/
		while(!input.equals("Q")) {
			
			//lines 71-98 get the proper toggling info to display to the user
			String n;//netflix toggle
			String h;//hulu toggle
			String p;//prime toggle
			String d;//disney toggle
			if(backend.getProviderFilter("Netflix")) {
				n = "x";
			}
			else {
				n = "_";
			}
			if(backend.getProviderFilter("Hulu")) {
				h = "x";
			}
			else {
				h = "_";
			}
			if(backend.getProviderFilter("Prime Video")) {
				p = "x";
			}
			else {
				p = "_";
			}
			if(backend.getProviderFilter("Disney+")) {
				d = "x";
			}
			else {
				d = "_";
			}
			
			//Lines 101-107 print the current toggling information to the user
			System.out.println("Providers that shows are being searched for:");
			System.out.println("_"+ n +"_ 1)   [N]etflix");
			System.out.println("_"+ h +"_ 2)   [H]ulu");
			System.out.println("_"+ p +"_ 3)   [P]rime Video");
			System.out.println("_"+ d +"_ 4)   [D]isney+");
			System.out.println("[Q]uit toggling provider filters");
			System.out.println("Choose the provider that you'd like to toggle the filter for:");
			
			try {
				input = sc.next();
			}catch(InputMismatchException e) {
				System.out.println(e + "Restarting application");
				runCommandLoop();
			}
			
			input = input.toUpperCase();
			
			//lines 119-138 run the toggling changes the user enters
			if(input.equals("1") || input.equals("N")) {
				backend.toggleProviderFilter("Netflix");
			}
			else if(input.equals("2") || input.equals("H")) {
				backend.toggleProviderFilter("Hulu");

			}
			else if(input.equals("3") || input.equals("P")) {
				backend.toggleProviderFilter("Prime Video");

			}
			else if(input.equals("4") || input.equals("D")) {
				backend.toggleProviderFilter("Disney+");
			}
			else if(input.equals("Q")) {
				
			}
			else {
				System.out.println("Input not an option, try again: ");
			}
			
		}
	}

	/**
	 * This method displays the command window as to save time
	 */
	@Override
	public void displayCommandMenu() {
		// TODO Auto-generated method stub
		
		System.out.println("Command Menu:");
		System.out.println("	1) Search by [T]itle Word");
		System.out.println("	2) Search by [Y]ear First Produced");
		System.out.println("	3) [F]ilter by Streaming Provider");
		System.out.println("	4) [Q]uit");
		System.out.println("Choose a command from the menu above: ");
		
		
	}

	
	@Override
	/**
	 * This method takes a list of shows that we want to be printed.
	 * It both formats and retrieves the data about the shows from 
	 * the IShow class.
	 */
	public void displayShows(List<IShow> shows) {
		// TODO reformat how the shows are printed
		for(int i = 0; i < shows.size(); i++) {
			String provider;//there is no get provider method in IShow so we 
			//must find the values current toggling values of each of the current providers.
			//This is done right before printing the shows
			if(shows.get(i).isAvailableOn("Netflix")) {
				provider = "Netflix";
			}
			else if(shows.get(i).isAvailableOn("Hulu")) {
				provider = "Hulu";
			}
			else if(shows.get(i).isAvailableOn("Prime Video")) {
				provider = "Prime Video";
			}
			else if(shows.get(i).isAvailableOn("Disney+")) {
				provider = "Disney+";
			}
			else {
				provider = "Provider not found";
			}
			
			//Prints the shows to the screen for the user to see
			System.out.println((i + 1) + ". " + shows.get(i).getTitle());
			System.out.println("	" + shows.get(i).getRating() + "/100 (" + shows.get(i).getYear() + ") on: " + provider); 
		}
		
	}

	@Override
	/**
	 * This method allows the user to pick a word they would like to search for 
	 * in our show database. It than sends the title the user entered to the 
	 * backend that completes and returns a list of the shows that contain the 
	 * title the user is searching for.
	 */
	public void titleSearch() {
		// TODO Auto-generated method stub
		System.out.println("Choose a word that you would like to search for: ");
		String word = "";
		try {
			word = sc.next();
		}catch(InputMismatchException e) {
			System.out.println(e + "Returning to the display board");
		}
		
		//gets the list of shows containing the searched title
		List<IShow> showsByTitle = backend.searchByTitleWord(word);
		
		//Backend returns null if the show is not found
		if(showsByTitle == null) {
			System.out.println("No shows found with word: " + word);
			System.out.println("Returning to the command menu");
		}
		else {
			//displays the shows with the corresponding titles
			displayShows(showsByTitle);
			return;
		}
		
		
	}

	@Override
	/**
	 * This method allows the user to search by the year the show was released. 
	 * This method then enters the user entered year to the backend which completes
	 * the search and returns a list of shows that correspond to the desired year.
	 */
	public void yearSearch() {
		// TODO Auto-generated method stub
		
		System.out.println("Choose a year that you would like to search for: ");
		int year = -1;
		try {
		year = sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println(e + " returning to the display board");
		}
		List<IShow> showsByYear = backend.searchByYear(year);
		
		//backend returns null if the show is not found
		if(showsByYear == null) {
			System.out.println("No shows found from: " + year);
			System.out.println("Returning to the command menu");
		}
		else {
			//prints the shows with the corresponding years
			displayShows(showsByYear);
		}
	}

}
