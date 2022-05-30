package UserProfile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Scanner;


/*InteractiveProgram performs the following tasks:
	1. Creates a interactive environment for users to perform different functions
	2. A helper function at the bottom to help creating the search results within a CSV file.
	3. Here is the menu of the program:
		1. Search a key
		2. View Search History
		3. View Search Summary
		4. Delete Search History
		5. Change default saved file path
		6. View default saved file path
		7. Exit program
*/

public class UserProfile {
	private LinkedHashMap<String, String> historicalData = new LinkedHashMap<String, String>(); //Data Collection to keep track of all search histories
	private String fileSaveLocation;
	String userName;
	String currentDate;
	final SimpleDateFormat FORMATTER= new SimpleDateFormat("MM-dd-yyyy HH.mm.ss z"); //Time format to be used in this project
	
	//Main class that instantiate the user profile and the interactive program
	public UserProfile(String userName) throws IOException {
		this.userName= userName;
		Date date = new Date(System.currentTimeMillis());
		currentDate = FORMATTER.format(date);
	
		System.out.println(
				"Please enter a default absolute location where you would like the file to be saved? (You may change the path at later time)"
				);
		
		setFileLocation();
		
		Boolean profileActive = true;
		while (profileActive) {
			Integer userAction = userOptions(userName);
			
			if( userAction == 1) {
				System.out.println("\nPlease enter the key word that you want to search?");
				searchKeyWord();
			} else if (userAction == 2 ) {
				getHistoricalSearches();
			} else if (userAction == 3 ) {
				getHistoricalSearchedQuantity();
			} else if (userAction == 4 ) {
				deleteAllSearchHistories();
			} else if (userAction ==5) {
				System.out.println("\nPlease enter your new file directory:");
				setFileLocation();
			} else if (userAction == 6 ) {
				getFileLocation();
			} else if (userAction == 7) {
				System.out.println("Thank you for viewing Aidan's CS622 HW_3 assignment.");
				profileActive = false;
			} else {System.out.println("Please enter a valid entry");}
		}
	}
	
	//Default Home Page, it prints out the navigation menu
	public static Integer userOptions(String userName) {
		Scanner input = new Scanner(System.in);
		
		System.out.println(
				"\nHi " + userName + ", what would you like to do? \n1. Search a key\n2. View Search History\n3. View Search Summary\n4. Delete Search History\n5. Change default saved file path\n6. View default saved file path\n7. Exit program"
		);
		Integer answer = input.nextInt();
		return answer;
	}
	
	//Option 1 Searches keyword from a user through calling a helper function called createResultsFile
	public void searchKeyWord() throws IOException {
		String keyWord;
		Scanner input = new Scanner(System.in);
		keyWord = input.nextLine();
		
		//create timestamp
		Date date = new Date(System.currentTimeMillis());
		currentDate = FORMATTER.format(date);
		
		//search file
		createResultsFile(keyWord, currentDate);
		
		//store data to historicalData
		historicalData.put(currentDate, keyWord);
	}
	
	//Option 2. Polls a summary of all searches (timestamp, keyword)
	public void getHistoricalSearches() {
		System.out.println("\n" + userName + "'s Search History");
		
		if (historicalData.size() ==0) {
			System.out.println("You have no search inquiries");
		} else {
			for ( String i  : historicalData.keySet()) {
				System.out.println("Keyword '" + historicalData.get(i) + "' "+ "was searched at " + i +"\n" );
			}
		}
	}
	
	//Option 3. Polls a number of times that a particular keyword has searched in the past
	public void getHistoricalSearchedQuantity() {
		LinkedHashMap<String, Integer> counter = new LinkedHashMap<String, Integer>();
		
		//counter function by using a LinkedHashMap
		for (String i : historicalData.values()) {
		     if (counter.get(i) == null ) {counter.put(i, 1);
		    } else {
		    	int num= counter.get(i);
		    	counter.remove(i);
		    	counter.put(i, num+1);
		    }
		 }
		
		//Printer statements with conditions
		if (historicalData.size() ==0) {
			System.out.println("You have no search inquiries");
		} else {
			System.out.println("\n" + "Keyword Frequencies");
			for ( String i  : counter.keySet()) {
				System.out.println("Keyword '" + i + "' "+ counter.get(i) + " time(s).\n");
			} 
		}
	}
	
	//Option 4. Deletes all search histories
		public void deleteAllSearchHistories() {
			historicalData.clear();
			System.out.println("\nSearch History Deleted. \nNumber of Searches: " + historicalData.size()+ "\n");
		}
		
	//Option 5. Change file directory
	public void setFileLocation() {
		Scanner input = new Scanner(System.in);
		fileSaveLocation = input.nextLine();
		char lastChar = fileSaveLocation.charAt(fileSaveLocation.length()-1);
		
		if (String.valueOf(lastChar).equals("/")) {
		} else {
			fileSaveLocation = fileSaveLocation.concat("/");
		}
		
		System.out.println("Your file directories has been saved as: " + fileSaveLocation);
	};
	
	//Option 6. View file directory
	public void getFileLocation() {
		System.out.println("Your file directories is: " + fileSaveLocation);
	};
	
	//Helper Function. It creates a file that stores the result of a keyword search into a custom built CSV file
	private void createResultsFile(String keyword, String curDate) throws IOException {
		final String MASTER_FILE_LOCATION = "/Users/aidanpro/Documents/Programming/Eclipse Java/CS622_HW3/AssignmentThree/mergedFile.csv";
		long searchStart = System.currentTimeMillis(); //Starts counting the time
		String fileName = keyword.concat(" " + curDate+ ".csv"); //Creating file name
		String resultFileLocation = fileSaveLocation.concat(fileName); //Creating file's absolute location
		long searchDuration = 0;
		
		try {
			BufferedWriter createAFile = new BufferedWriter( new FileWriter(resultFileLocation)); //create a CSV file at designated location
			createAFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Save Directory not valid, cannot save the result file.");
		}
		
		try {
			BufferedReader obj = new BufferedReader(new FileReader(MASTER_FILE_LOCATION));
			BufferedWriter resultsFile = new BufferedWriter(new FileWriter(resultFileLocation));	
			String line = "";
			int i = 1;
			
			// This While loop reads all the lines and split the CSV data into a String Array. ANy String Arrays (bad data) less than length of 7 will be discard
		    while ((line = obj.readLine()) != null) {
		    	if (line.contains(keyword)) {
		    		String[] values = line.split(",");
		    		
		    		if (values.length > 7) { //the IF function is to filter out any broken data that triggers OutOfIndex errors
				  		resultsFile.write(i + " Fund Raised Percent: " + values [7] + " Closed Date: " + values[4] + "\n"); 
				  		i = i + 1;
		    		}
		    	}
		   
		    }
			    
		    obj.close();
			resultsFile.close();
			long searchEnd = System.currentTimeMillis();
	        searchDuration = searchEnd - searchStart;
	        System.out.println( fileName + " file saved and used " + searchDuration *.001 + " seconds");
	            
		} catch (Exception e) {
				e.printStackTrace();
				
			}
		
	}
}
