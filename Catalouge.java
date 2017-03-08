import java.io.*;
import java.util.*;

public class Catalouge {
	//Read Fucntions
	public static void readAnArticle() {
		Console console = System.console();
		String issueYear = console.readLine("Issue Year: ");
		String issueNumber = console.readLine("Issue Number: ");
		String articlePage = console.readLine("Article Page: ");
		if (!issueYear.matches("^\\d{4}$")) errorHandler("Invalid year.");
		if (!issueNumber.matches("\\d+")) errorHandler("Issue number should be in digits.");
		if (!articlePage.matches("\\d+")) errorHandler("Article page should be in digits.");
		String fileName = "data/date/track/" + issueYear + "/issues/" + issueNumber + "/" + articlePage + ".txt"; String line = null;
        if (doesDirectoryExists("data/date/track/" + issueYear + "/issues/" + issueNumber) == false) errorHandler("Invalid information/does not exist");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Article Information: ");
            while((line = bufferedReader.readLine()) != null) {
                if (line.split(":")[0].equals("AuthorFName")) System.out.println("Author First Name: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("AuthorLName")) System.out.println("Author Last Name: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("ArticleTitle")) System.out.println("Article Title: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("ArticleBody")) System.out.println("Article Body: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("ArticleCategory")) System.out.println("Article Category: " + line.split(":")[1]);
            }
            bufferedReader.close();
            System.out.println("-----------------------------------------------------------------------");
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	public static void listAuthors() {
		String line = null; String fileName = "data/authors/authors.txt";
		try {
        	FileReader fileReader = new FileReader(fileName);
       		BufferedReader bufferedReader = new BufferedReader(fileReader);
       		System.out.println("List of Authors: ");
            while((line = bufferedReader.readLine()) != null) {
            	System.out.println(line);
		}
	} catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
    } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
    }
}

	public static void retrieveArticle(String filePath) {
        String fileName = filePath; String line = null; String line2 = null;
        String issueYear = null; String issueNumber = null; String articlePage = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("-----------------------------------------------------------------------");
            while((line = bufferedReader.readLine()) != null) {
            		if (line.split(":")[0].equals("IssueYear")) issueYear = line.split(":")[1];
					if (line.split(":")[0].equals("IssueNumber")) issueNumber = line.split(":")[1];
					if (line.split(":")[0].equals("ArticlePage")) articlePage = line.split(":")[1];
					if (line.matches("^ENDL$")) {
	                	FileReader fileReaderTwo = new FileReader("data/date/track/" + issueYear + "/issues/" + issueNumber + "/" + articlePage + ".txt");
	            		BufferedReader bufferedReaderTwo = new BufferedReader(fileReaderTwo);
			            while((line2 = bufferedReaderTwo.readLine()) != null) {
			                if (line2.split(":")[0].equals("AuthorFName")) System.out.println("Author First Name: " + line2.split(":")[1]);
			           		if (line2.split(":")[0].equals("AuthorLName")) System.out.println("Author Last Name: " + line2.split(":")[1]);
			           		if (line2.split(":")[0].equals("ArticleTitle")) System.out.println("Article Title: " + line2.split(":")[1]);
			           		if (line2.split(":")[0].equals("ArticleBody")) System.out.println("Article Body: " + line2.split(":")[1]);
			           		if (line2.split(":")[0].equals("ArticleCategory")) System.out.println("Article Category: " + line2.split(":")[1]);
		            }
		            bufferedReaderTwo.close();
		        }
            }
            System.out.println("-----------------------------------------------------------------------");
            bufferedReader.close();
            //System.out.println(System.out.printf("There are %d articles by this Author\n Articles: %s", list.size(), list));
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}

	//End Read Functions

	//Find Functions

	public static void findByAuthor(String filePath) {
        String fileName = filePath; String line = null; int counter = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("-----------------------------------------------------------------------");
            while((line = bufferedReader.readLine()) != null) {
                if (line.matches("^ENDL$")) { 
                	counter++;
	            	System.out.println("-----------------------------------------------------------------------");
                }
                if (line.split(":")[0].equals("ArticleTitle")) System.out.println("Article Title: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("IssueYear")) System.out.println("Article Issue Year: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("IssueNumber")) System.out.println("Article Issue Number: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("ArticlePage")) System.out.println("Article Page: " + line.split(":")[1]);
            }
            bufferedReader.close();
            System.out.println("Total of " + counter + " Articles Found For The Author");
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
    }
	public static void findByYear(String filePath) {
			File dir = new File(filePath);
			int numberOfSubfolders = 0;
			File listDir[] = dir.listFiles();
			for (int i = 0; i < listDir.length; i++) {
			    if (listDir[i].isDirectory()) {
			            numberOfSubfolders++;
			        }
			}
			System.out.println("There are currently " + numberOfSubfolders + " Issues for the given year.");
    }
    public static void findByCategory(String filePath) {
        String fileName = filePath; String line = null; int counter = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("-----------------------------------------------------------------------");
            while((line = bufferedReader.readLine()) != null) {
                if (line.matches("^ENDL$")) { 
                	counter++;
	            	System.out.println("-----------------------------------------------------------------------");
                }
                if (line.split(":")[0].equals("ArticleTitle")) System.out.println("Article Title: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("IssueYear")) System.out.println("Article Issue Year: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("IssueNumber")) System.out.println("Article Issue Number: " + line.split(":")[1]);
           		if (line.split(":")[0].equals("ArticlePage")) System.out.println("Article Page: " + line.split(":")[1]);
            }
            bufferedReader.close();
            System.out.println("Total of " + counter + " Articles Found For The Category");
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
    }
    public static void findByMonth(String filePath) {
		int count = new File(filePath).list().length;
		System.out.println("There are currently " + count + " Issues for this month.");
    }
    public static void findByBody(String stringMatch) {
    	try {
	    	Scanner txtscan = new Scanner(new File("data/excerpts/body/body.txt"));
	    	int noMatchesFlag = 1;
			while(txtscan.hasNextLine()){
	    		String str = txtscan.nextLine();
	    		// split by : then split by space for body article..compare.
	    		int foundMatchFlag = 0; String modifiedArticle = "";
	    		if (!str.equals("ENDL")) {
	    			if (str.split(":")[0].equals("ArticleBody")) {
	    				String strSplitA = str.split(":")[1]; // split by : to to get the body of the article
	    				String strSplitB[] = strSplitA.split(" "); //split by space to compare number of spaces with entered sentence
	    				int i = 0; int k = 0; int l = 0; String keyWord = "";//initiate vars
	    				while (i < strSplitB.length) {
	    					k = 0; keyWord = "";//reassign values to get specific length of words
	    					while(k < stringMatch.split(" ").length) {
	    						keyWord += strSplitB[l] + " ";
	    						k++; l++;
	    					}
    						if (stringMatch.equals(keyWord.trim())) {
    							//System.out.println("found a match: " + keyWord);
    							foundMatchFlag = 1; 
    							noMatchesFlag = 0;
    							modifiedArticle += "[ " + keyWord + "] ";
    						}else { modifiedArticle += keyWord; }

	    					i+= stringMatch.split(" ").length;//increase by the length of entered word/sentence
	    				}
	    				if (foundMatchFlag == 1) { 
	    					System.out.println("---------------------------------------------");
	    					System.out.println("Matched Article Information. Matched Text is between []");
	    					System.out.println("  \'");
	    					System.out.println("  \'---> " + txtscan.nextLine());
	    					System.out.println("  \'");
	    					System.out.println("  \'---> " + txtscan.nextLine());
	    					System.out.println("  \'");
	    					System.out.println("  \'---> " + txtscan.nextLine());
	    					System.out.println("  \'");
	    					System.out.println("  \'---> " + modifiedArticle);
	    					System.out.println("---------------------------------------------");
	    				}
	    			}
	    		}
			}
			if (noMatchesFlag == 1) { System.out.println("Could not find a match"); }
		}catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");              
        }catch(IndexOutOfBoundsException ex) {
        	System.out.println("Invalid input. Unnecessary spaces detected");
        }
	}

	public static void readByPosition() {
		String issueYear; 
		String issueNumber; 
		String articlePage; 
		String startPosition; 
		String endPosition;
		Console console = System.console();
		issueYear = console.readLine("Enter Issue Year: ");
		issueNumber = console.readLine("Enter Issue Number: ");
		articlePage = console.readLine("Enter Article Page: ");
		if (!issueYear.matches("^\\d{4}$")) errorHandler("Invalid year.");
		if (!issueNumber.matches("\\d+")) errorHandler("Issue number should be in digits.");
		if (!articlePage.matches("\\d+")) errorHandler("Article page should be in digits.");
		String fileName = "data/date/track/" + issueYear + "/issues/" + issueNumber + "/" + articlePage + ".txt"; String line = null;
        if (doesDirectoryExists("data/date/track/" + issueYear + "/issues/" + issueNumber) == false) errorHandler("Invalid information/does not exist");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("-----------------------------------------------------------------------");
            while((line = bufferedReader.readLine()) != null) {
           		if (line.split(":")[0].equals("ArticleBody")) {
           			System.out.println("*---> The current article has the following number of charachters " + line.split(":")[1].length() + " as its maximum. \n\nDo not exceed this limit to avoid any errors.");
   					System.out.println("-----------------------------------------------------------------------");
   					startPosition = console.readLine("Enter Start Position.\nPlease note that 0 represents the first charachter. : ");
					endPosition = console.readLine("Enter End Position: ");
					if (!startPosition.matches("\\d+")) errorHandler("Position should be in digits.");
					if (!endPosition.matches("\\d+")) errorHandler("Position should be in digits.");
           			System.out.println("\nResult:\n     '\n     '---> " + line.split(":")[1].substring(Integer.parseInt(startPosition), 
           															Integer.parseInt(endPosition)));
           		}
            }
            bufferedReader.close();
            System.out.println("-----------------------------------------------------------------------");
        } catch(FileNotFoundException ex) {
            System.out.println("Article does not exist. Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        } catch (StringIndexOutOfBoundsException ex) {
        	System.out.println("Number of available charachters has been exceeded.");
        }

	}

	public static void searchData() {
		System.out.println("Please select one of the following options to search for articles");
		System.out.println("1) Enter \"1\" to search by Author Name.\n2) Enter \"2\" to search by Categories\n3) Enter \"3\" to search by Year\n4) Enter \"4\" to search by Months\n5) Enter \"5\" to search by Article Title\n6) Enter \"6\" to search by article body.\n6) Enter \"7\" to read by positions.");
		String userInput = System.console().readLine();
		switch(userInput) {
			case "1":
				findByAuthor("data/authors/author/" + System.console().readLine("Enter Author First and Last Name: ").toLowerCase().replace(" ", ""));
				break;
			case "2":
				findByCategory("data/category/" + System.console().readLine("Enter Category: ").toLowerCase());
				break;
			case "3":
				userInput = System.console().readLine("Enter Issue Year: ");
				if (!userInput.matches("^\\d{4}$")) errorHandler("Invalid year.");
				else findByYear("data/date/track/" + userInput + "/issues/");
				break;
			case "4":
				findByMonth("data/date/track/" + System.console().readLine("Enter Year to check monthly issues: ") + "/months/");
				break;
			case "5":
				retrieveArticle("data/excerpts/titles/" + System.console().readLine("Enter article title: ").toLowerCase() + ".txt");
				break;
			case "6":
				//trim used because of spacing error when I loop
				findByBody(System.console().readLine("Enter a word or sentence to look for: ").trim());
				break;
			case "7":
				readByPosition();
				break;
			default:
				errorHandler("Invalid Input.");
		}
	}

	//End Find Functions
	
		//Create/Write Operations
    
    //write passed params to different dirs and files for easier navigation/data retreival.
	public static void createNewIssue(String issueName, String issueYear, String issueMonth, 
										String issueNumber, String issueCode) {
		try {
			//check if directory exists which means that an issue number already exists
			//error should be raised to avoid using multiple codes that links to the same issue
			if (doesDirectoryExists("data/date/track/" + issueYear + "/issues/" + issueNumber) == true) errorHandler(" *** Duplicate: same issue number for the same year found.");
			if (new File("data/date/track/" + issueYear + "/months/" + issueMonth + ".txt").exists()) errorHandler("An issue for this month is already released.");
			File issueDateFile = new File("data/date/dates.txt");
			File issueMonthFile = new File("data/date/track/" + issueYear + "/months/" + issueMonth + ".txt");
			File issueCodeFile = new File("data/code/ " + issueCode + ".txt");
			if (issueMonthFile.exists()) errorHandler("An issue for this month already exists");
			if (doesDirectoryExists("data/date/track/" + issueYear + "/months/") == false) createDirectory("data/date/track/" + issueYear + "/months/");
			createDirectory("data/date/track/" + issueYear + "/issues/" + issueNumber);
			//create directory with issue number as the name to include text files of page number with articles inside
			// if file doesnt exists, then create it
			if (!issueDateFile.exists()) { issueDateFile.createNewFile(); }
			if (!issueMonthFile.exists()) { issueMonthFile.createNewFile(); }
			if (!issueCodeFile.exists()) { issueCodeFile.createNewFile(); }
			FileWriter issueCodeFileWriter = new FileWriter(issueCodeFile.getAbsoluteFile());
			BufferedWriter issueCodeFileBufferWriter = new BufferedWriter(issueCodeFileWriter);
			issueCodeFileBufferWriter.write("IssueName:" + issueName + "\n" + "IssueYear:" + issueYear + "\n" + "IssueMonth:" + issueMonth + "\n" + "IssueNumber:" + issueNumber + "\n");
			issueCodeFileBufferWriter.close();

			if (checkDate(issueYear) == false) {
				FileWriter issueDateFileWriter = new FileWriter(issueDateFile.getAbsoluteFile(), true);
				BufferedWriter issueDateFileBufferWriter = new BufferedWriter(issueDateFileWriter);
				issueDateFileBufferWriter.write(issueYear + "\n");
				issueDateFileBufferWriter.close();
			}

			FileWriter issueMonthFileWriter = new FileWriter(issueMonthFile.getAbsoluteFile());
			BufferedWriter issueMonthFileBufferWriter = new BufferedWriter(issueMonthFileWriter);
			issueMonthFileBufferWriter.write(issueCode);
			issueMonthFileBufferWriter.close();

			System.out.println("*** Issue Data has been saved.");
		} catch (IOException e) {
			e.printStackTrace();
	}
}
	public static void writeArticles(String authorFirstName, String authorLastName, String articlePage,
										String articleTitle, String articleBody, String articleCategory, String issueNumber, String issueYear) {
		try {
			if (doesDirectoryExists("data/date/track/" + issueYear + "/issues/" + issueNumber) == false) errorHandler("Invalid Issue Number/issue number does not exist.");
			File createPages = new File("data/date/track/" + issueYear + "/issues/" + issueNumber + "/" + articlePage + ".txt");
			File authorsFile = new File("data/authors/authors.txt");
			File authorFile = new File("data/authors/author/" + authorFirstName + authorLastName);
			File categoryFile = new File("data/category/" + articleCategory);
			File titlesFile = new File("data/excerpts/titles/" + articleTitle.toLowerCase() + ".txt");
			File bodyFile = new File("data/excerpts/body/body.txt");
			// if file doesnt exists, then create it
			if (createPages.exists()) { errorHandler("This article page already exists"); }
			if (doesDirectoryExists("data/authors/author") == false) createDirectory("data/authors/author");
			if (doesDirectoryExists("data/category") == false) createDirectory("data/category");
			if (doesDirectoryExists("data/excerpts/titles") == false) createDirectory("data/excerpts/titles");
			if (!createPages.exists()) { createPages.createNewFile(); }
			if (!authorFile.exists()) { authorFile.createNewFile(); }
			if (!authorsFile.exists()) { authorsFile.createNewFile(); }
			if (!categoryFile.exists()) { categoryFile.createNewFile(); }
			if (!titlesFile.exists()) { titlesFile.createNewFile(); }
			if (!bodyFile.exists()) { bodyFile.createNewFile(); }
			documentWrite(createPages.getAbsoluteFile().toString(), "AuthorFName:" + authorFirstName + "\n" + "AuthorLName:" + authorLastName +
											"\n" + "ArticleTitle:" + articleTitle + "\n" + 
											"ArticleBody:" + articleBody + "\n" +
											"ArticleCategory:" + articleCategory, false);
			if (checkAuthor(authorFirstName + " " + authorLastName) == false) {
				documentWrite(authorsFile.getAbsoluteFile().toString(), authorFirstName + " " + authorLastName + "\n", true);
				sortDataInFileAlphabitically();
			}
			documentWrite(authorFile.getAbsoluteFile().toString(), "IssueYear:" + issueYear + "\n" + "IssueNumber:" + issueNumber + 
										"\n" + "ArticlePage:" + articlePage + "\n" + "ENDL", true);
			documentWrite(categoryFile.getAbsoluteFile().toString(), "IssueYear:" + issueYear + "\n" + "IssueNumber:" + issueNumber + 
										"\n" + "ArticlePage:" + articlePage + "\n" + "ArticleTitle:" + 
										articleTitle + "\n" + "ENDL", true);
			//leave it append just in case another article with the same name is created
			documentWrite(titlesFile.getAbsoluteFile().toString(), "IssueYear:" + issueYear + "\n" + "IssueNumber:" + issueNumber + 
										"\n" + "ArticlePage:" + articlePage + "\n" + "ENDL", true);

			documentWrite("data/excerpts/body/body.txt","ArticleBody:" + articleBody + "\n" + "IssueYear:" + issueYear + "\n" + "IssueNumber:" + issueNumber + 
										"\n" + "ArticlePage:" + articlePage + "\n" + "ENDL", true);

			System.out.println("*** Article has been created.");
		} catch (IOException e) {
			e.printStackTrace();
	}
}
	public static void documentWrite(String filePath, String dataToWrite, Boolean appendToFile) {
		try {
			File file = new File(filePath);
			if (appendToFile == true) {
				FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
				fileBufferWriter.write(dataToWrite);
				fileBufferWriter.close();
			} else {
				FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
				BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
				fileBufferWriter.write(dataToWrite);
				fileBufferWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	    public static void insertNewArticle(String issueNumber, String issueYear) {
    	Console console = System.console();
		String exit = "";
		while (!exit.equals("yes")) {
			if (!issueYear.matches("^\\d{4}$")) errorHandler("Invalid year.");
			if (!issueNumber.matches("\\d+")) errorHandler("Issue number should be in digits.");
			String authorFirstName = console.readLine("Author First Name: ");
			String authorLastName = console.readLine("Author Last Name: ");
			String articleTitle = console.readLine("Article Title: ");
			String articleBody = console.readLine("Article Body: ");
			String articleCategory = console.readLine("Article Category: ");
			String articlePage = console.readLine("Article Page: ");
			//validate user input before proceeding and adding data to a file
			if (!articlePage.matches("\\d+")) errorHandler("Issue number should be in digits.");
			if (userInputValidation(authorFirstName, authorLastName, articlePage, 
							articleTitle, articleBody, articleCategory)) {
			
									writeArticles(authorFirstName.toLowerCase(), authorLastName.toLowerCase(), articlePage, 
												articleTitle, articleBody, articleCategory, issueNumber, issueYear);
			}
			exit = console.readLine("Do you want to continue or exit? Type the word yes to exit\n");
		}
    }

    public static void insertNewIssue() {
		Console console = System.console();
		String issueAutoGeneratedCode = generateRandomCode();
		System.out.printf("Issue Code: %s\n", issueAutoGeneratedCode);
		String issueName = console.readLine("Issue Name: ");
		String issueYear = console.readLine("Issue Year: ");//don't forget 4 numbers only
		String issueMonth = console.readLine("Issue Month: ");
		String issueNumber = console.readLine("Issue Number: ");
		//validate user input before proceeding and adding data to a file
		if (intToMonth(issueMonth).equals("Invalid")) errorHandler("Invalid month. Month should be between 1-12.");
		if (!issueYear.matches("^\\d{4}$")) errorHandler("Invalid year.");
		if (!issueNumber.matches("\\d+")) errorHandler("Issue number should be in digits.");
		if (userInputValidation(issueName, issueYear, issueMonth, issueNumber)) {
		createNewIssue(issueName, issueYear, issueMonth, issueNumber, issueAutoGeneratedCode);
		} else { errorHandler("Invalid inputs."); } 
		System.out.println("\nEntering articles mode..\n");
		insertNewArticle(issueNumber, issueYear);
    }

    // End Create Operations

	public static boolean deleteIssue(File directory) {
	    if(directory.exists()) {
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteIssue(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}

	//Validation & Conversions

	//function to create directories based on the passed param.
	public static void createDirectory(String dirName) {
		File directoryName = new File(dirName);
		directoryName.mkdirs();
	}
	public static Boolean doesDirectoryExists(String dirName) {
		File directoryName = new File(dirName);
		if (directoryName.exists()) return true;
		else return false;
	} 
	
	public static void errorHandler(String errorMessage) {
		System.out.println("The following error occured: " + errorMessage);
		System.exit(1);
	}

	public static Boolean checkDate(String date) {
        String fileName = "data/date/dates.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                if (line.equals(date)) { 
                	return true;
                }
            }   
            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
        return false;
	}

	public static Boolean checkAuthor(String author) {
        String fileName = "data/authors/authors.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                if (line.matches(author)) { 
                	return true;
                }
            }   
            bufferedReader.close();         
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
        return false;
	}

	public static String intToMonth(String month) {
		switch (month) {
			case "1":
				return "Jan";
			case "2":
				return "Feb";
			case "3":
				return "March";
			case "4":
				return "April";
			case "5":
				return "May";
			case "6":
				return "June";
			case "7":
				return "July";
			case "8":
				return "Aug";
			case "9":
				return "Sept";
			case "10":
				return "Oct";
			case "11":
				return "Nov";
			case "12":
				return "Dec";
		}
		return "Invalid";
	}
	public static Boolean userInputValidation(String...passedInputs) {
        for (String input : passedInputs) {
			if (input.trim().isEmpty() || input.length() < 1 || input.contains("ENDL")) return false;
    	}
    	return true;
    }
	//generate random code to link data using this code
	public static String generateRandomCode() {
		final String alphabet = "0123456789ABCDE";
		String code = "";
		Random r = new Random();
		for (int i = 0; i < 10; i++) 
		{
        	code += alphabet.charAt(r.nextInt(alphabet.length()));
    	}	
    	return code;
	}

	public static void checkDirectoriesExistence() {
		if (doesDirectoryExists("data") == false) createDirectory("data");
		if (doesDirectoryExists("data/authors") == false) createDirectory("data/authors");
		if (doesDirectoryExists("data/authors/author") == false) createDirectory("data/authors/author");
		if (doesDirectoryExists("data/category") == false) createDirectory("data/category");
		if (doesDirectoryExists("data/code") == false) createDirectory("data/code");
		if (doesDirectoryExists("data/date") == false) createDirectory("data/date");
		if (doesDirectoryExists("data/date/track") == false) createDirectory("data/date/track");
		if (doesDirectoryExists("data/excerpts") == false) createDirectory("data/excerpts");
		if (doesDirectoryExists("data/excerpts/titles") == false) createDirectory("data/excerpts/titles");
		if (doesDirectoryExists("data/excerpts/body") == false) createDirectory("data/excerpts/body");
	}

    //END validations

	//Sorting 

	public static void sortDataInFileAlphabitically() {
			File fileName = new File("data/authors/authors.txt");		
			try {
			FileReader fileReader = new FileReader("data/authors/authors.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String inputLine;
			List<String> lineList = new ArrayList<String>();
			while ((inputLine = bufferedReader.readLine()) != null) {
				lineList.add(inputLine);
			}
			fileReader.close();
			Collections.sort(lineList);
			FileWriter fileWriter = new FileWriter(fileName.getAbsoluteFile());
			BufferedWriter fileWriterBuff = new BufferedWriter(fileWriter);
			for (String outputLine : lineList) {
				fileWriterBuff.write(outputLine + "\n");
			}
			fileWriterBuff.close();
			} catch(FileNotFoundException ex) {
            	System.out.println("Unable to open file '" + fileName + "'");                
        	} catch(IOException ex) { 
        	System.out.println("Error reading file '" + fileName + "'");                  
        }
    }

    public static void systemLogo() {
       System.out.println("||-------------------------------------------------------||");
       System.out.println("||.--.    .-._                        .----.             ||");
       System.out.println("|||==|____| |H|___            .---.___|\"\"\"\"|_____.--.___ ||");
       System.out.println("|||  |====| | |xxx|_          |+++|=-=|_  _|-=+=-|==|---|||");
       System.out.println("|||==|    | | |   |           |   |   |_\\/_|March|  | ^ |||");
       System.out.println("|||  |    | | |   |           |   |=-=|_/\\_|-=+=-|  | ^ |||");
       System.out.println("|||  |    | | |   |           |   |   |    |issue|  | ^ |||");
       System.out.println("|||==|====| |H|xxx|           |+++|=-=|\"\"\"\"|-=+=-|==|---|||");
       System.out.println("||`--^----'-^-^---'   `-' \"\"  '---^---^----^-----^--^---^||");
       System.out.println("||-------------------------------------------------------||");
       System.out.println("||-------------------------------------------------------||");
       System.out.println("||               ___                   .-.__.-----. .---.||");
       System.out.println("||              |===| .---.   __   .---| |XX|<(*)>|_|^^^|||");
       System.out.println("||         ,  /(|   |_|III|__|''|__|:2:|=|  |     |=| Q |||");
       System.out.println("||      _a'{ / (|===|+|   |++|  |==|   | |  |Illum| | R |||");
       System.out.println("||      '/\\/  _(|===|-|   |  |''|  |:0:|=|  |inati| | Y |||");
       System.out.println("||_____  -\\{ __(|   |-|   |  |  |  |   | |  |     | | Z |||");
       System.out.println("||       _(____)|===|+|[I]|DK|''|==|:0:|=|XX|<(*)>|=|^^^|||");
       System.out.println("||              `---^-^---^--^--'--^---^-^--^-----^-^---^||");
       System.out.println("||-------------------------------------------------------||");
       System.out.println("||__________Welcome to Cataloging System_________________||");
    }
    public static void userGuide() {
		  System.out.println(".-/|  78   ~~**~~      \\ /      ~~**~~   79  |\\-.");
		  System.out.println("`---------------------~___~--------------------''");
		  System.out.println("||/====================\\:/====================\\||");
		  System.out.println("||||                    :                    ||||");
		  System.out.println("||||   Read the user    :  Guide for help    ||||");
		  System.out.println("||||   & follow program :  instruction       ||||");
		  System.out.println("||||   as they appear   :                    ||||");
		  System.out.println("||||                    :                    ||||");
		  System.out.println("||||   Do not delete    : or Modify data     ||||");
		  System.out.println("||||                    : manually           ||||");
		  System.out.println("||||                    :                    ||||");
		  System.out.println("||||                    :                    ||||");
		  System.out.println("||||                    :                    ||||");
		  System.out.println("||||  Cataloging System :                    ||||");
		  System.out.println("||||___________________ : _____Catalog_______||||\n\n");
    }

    public static void main(String[] args) {
		Console console = System.console();
		checkDirectoriesExistence();
    	systemLogo();
		System.out.println("\n1) To insert new issue type 1.\n2) For help type number 2.\n3) For Searching type number 3.\n4) For Creating New Articles for existing issues type number 4.\n5) To open and read an article enter 5.\n6) To list all authors.\n7) Delete an Issue.");
    	String userChoice = console.readLine("Enter a number from the given list to proceed\n");
    	switch (userChoice) {
    		case "1":
    			insertNewIssue();
    			break;
    		case "2":
    			userGuide();
    			break;
    		case "3":
		    	searchData();
    			break;
    		case "4":
    			insertNewArticle(console.readLine("Enter issue number: "), console.readLine("Enter issue Year: "));
    			break;
    		case "5":
    			readAnArticle();
    			break;
    		case "6":
    			listAuthors();
    			break;
    		case "7":
    			if (deleteIssue(new File("data/date/track/" + console.readLine("Enter Issue Year: ") + "/issues/" + console.readLine("Enter Issue Number: ")))) { System.out.println("Issue has been deleted."); }
    			else { System.out.println("Issue does not exist"); }
    			break;
    		default:
    			errorHandler("Invalid choice.");
    	}
    }
}
