
public class FrontendDeveloperTests {
	
	public static boolean test1() {//search by title
		//tests searching by a title, the placeholder class returns only the show with title: "title"
		 // 1. Create a new TextUITester object for each test, and
        // pass the text that you'd like to simulate a user typing as only argument.
        TextUITester tester = new TextUITester("t title q");

        // 2. Run the code that you want to test here:
        ShowSearcherFrontend obj = new ShowSearcherFrontend(new ShowSearcherBackend());
        obj.runCommandLoop(); // (this code should read from System.in and write to System.out)

        // 3. Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        if(output.startsWith("Welcome to the Show Searcher App!") && //checks to make sure the system runs correctly
           output.contains("1. Title")) {//tests to make sure "Title" is outprinted to the system.
            System.out.println("Test passed.");
        }
        else {
            System.out.println("Test FAILED");
            return false;
        }
        
        if(!output.contains("(0)")) {//tests to see if the proper year for the show is outputted after 
        	//searching for the title
        	return false;
        }
        
        	
		
		return true;
	}
	
	public static boolean test2() {//tests search by year
		//the placeholder class returns only a show from the year 0, only check to see if
		//it is being properly displayed/formatted.
		
		//tests searching by a title, the placeholder class returns only the show with title: "title"
		 // 1. Create a new TextUITester object for each test, and
       // pass the text that you'd like to simulate a user typing as only argument.
       TextUITester tester = new TextUITester("y 0 q");

       // 2. Run the code that you want to test here:
       ShowSearcherFrontend obj = new ShowSearcherFrontend(new ShowSearcherBackend());
       obj.runCommandLoop(); // (this code should read from System.in and write to System.out)

       // 3. Check whether the output printed to System.out matches your expectations.
       String output = tester.checkOutput();
       if(output.startsWith("Welcome to the Show Searcher App!") && //checks to make sure the system runs correctly
          output.contains("1. Title")) {//tests to make sure "Title" is outprinted to the system.
           System.out.println("Test passed.");
       }
       else {
           System.out.println("Test FAILED");
           return false;
       }
       
       if(!output.contains("(0)")) {//tests to see if the proper year for the show is outputted after 
       	//searching for the title
       	return false;
       }
       
       	
		
		return true;
		
		//return true;
	}

	public static boolean test3() {//tests toggling a filter
	//tests the initial output of the filter, as it logically works 
	//when the filter is chosen to be toggled, if i wanted to test the 
	//test the change itself I would need access to a fully functional Backend
		
		// 1. Create a new TextUITester object for each test, and
	       // pass the text that you'd like to simulate a user typing as only argument.
	       TextUITester tester = new TextUITester("f n q q");

	       // 2. Run the code that you want to test here:
	       ShowSearcherFrontend obj = new ShowSearcherFrontend(new ShowSearcherBackend());
	       obj.runCommandLoop(); // (this code should read from System.in and write to System.out)

	       // 3. Check whether the output printed to System.out matches your expectations.
	       String output = tester.checkOutput();
	       if(output.startsWith("Welcome to the Show Searcher App!") && //checks to make sure the system runs correctly
	          output.contains("====")) {//tests to make sure "Title" is outprinted to the system.
	           System.out.println("Test passed.");
	       }
	       else {
	           System.out.println("Test FAILED");
	           return false;
	       }

	       if(!output.contains("_x_")) {//tests to see if the proper year for the show is outputted after 
	       	//searching for the title
	    	   System.out.println("False 1");
	       	return false;
	       }
	       
	       	
			
			return true;
	
	
	}

	public static boolean test4() {//tests the quit command
	

		// 1. Create a new TextUITester object for each test, and
	       // pass the text that you'd like to simulate a user typing as only argument.
	       TextUITester tester = new TextUITester("q");

	       // 2. Run the code that you want to test here:
	       ShowSearcherFrontend obj = new ShowSearcherFrontend(new ShowSearcherBackend());
	       obj.runCommandLoop(); // (this code should read from System.in and write to System.out)

	       // 3. Check whether the output printed to System.out matches your expectations.
	       String output = tester.checkOutput();
	       if(output.startsWith("Welcome to the Show Searcher App!") && //checks to make sure the system runs correctly
	          output.contains("====")) {//tests to make sure "Title" is outprinted to the system.
	           System.out.println("Test passed.");
	       }
	       else {
	           System.out.println("Test FAILED");
	           return false;
	       }

	       
	       
	       if(output.contains("(0)")) {
	    	   return false;//this would implie year is being searched for and shows are being displayed
	       }
	       if(output.contains("Title")) {//This would be true if the shows were being displayed
	    	   return false;
	       }
	       if(output.contains("[N]etflix")) {//This would be true if it tries to filter
	    	   return false;
	       }
	       	
			
			return true;
	
	
	
	}

	/**
	 * This method tests to see whether the appearance of what filter is bering
	 * toggled correctly changes once a filter is chosen. Side Note: This method 
	 * will not be able to return true until a working version of toggle is created
	 * @return
	 */
	public static boolean test5() {
		// 1. Create a new TextUITester object for each test, and
	       // pass the text that you'd like to simulate a user typing as only argument.
	       TextUITester tester = new TextUITester("f n q q");

	       // 2. Run the code that you want to test here:
	       ShowSearcherFrontend obj = new ShowSearcherFrontend(new ShowSearcherBackend());
	       obj.runCommandLoop(); // (this code should read from System.in and write to System.out)

	       // 3. Check whether the output printed to System.out matches your expectations.
	       String output = tester.checkOutput();
	       if(output.startsWith("Welcome to the Show Searcher App!") && //checks to make sure the system runs correctly
	          output.contains("====")) {//tests to make sure "Title" is outprinted to the system.
	           System.out.println("Test passed.");
	       }
	       else {
	           System.out.println("Test FAILED");
	           return false;
	       }

	       
	       if(!output.contains("___")) {
	    	   System.out.println("___");
	    	   return false;
	       }
	       if(!output.contains("_x_")) {
	    	   System.out.println("_x_");
	    	   return false;
	       }
	    	   
	       if(output.contains("(0)")) {
	    	   return false;//this would implie year is being searched for and shows are being displayed
	       }
	       if(output.contains("Title")) {//This would be true if the shows were being displayed
	    	   return false;
	       }
	       if(!output.contains("[N]etflix")) {//This would be true if it tries to filter
	    	   return false;
	       }
	       	
			
			return true;
	
	
	
	}
	
	
	public static void main(String[] args) {
		System.out.println("HERE!");
		System.out.println("1: " + test1());
		System.out.println("2: " + test2());
		System.out.println("3: " + test3());
		System.out.println("4: " + test4());
		System.out.println("5: " + test5());
        
	}

}
