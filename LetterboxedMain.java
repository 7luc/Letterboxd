package Letterboxd;
import java.util.Scanner;
public class LetterboxedMain {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Library lib = new Library();
		boolean exit=true;
		System.out.println("Welcome to LetterBoxd");
		do{
			System.out.println();
			System.out.println("1-Add Content");
			System.out.println("2-Rate Content");
			System.out.println("3-Search Content");
			System.out.println("4-Show All Content");
			System.out.println("5-Create User Account");
			System.out.println("6-Search for User");
			System.out.println("0-Exit");
			System.out.print("What do you want to do?:");
			int choice=scan.nextInt();
			System.out.println();
			switch (choice) {
			
			case (1):
				System.out.print("Is it a Movie or a Serise? (answer with one word):");
				String answer=scan.next();
				if(answer.equalsIgnoreCase("Movie")) {
					System.out.print("Is it a movie series? (yes/no): ");
					String ans = scan.next();
					if(ans.equalsIgnoreCase("yes")) {
					    System.out.print("How many parts?: ");
					    int parts = scan.nextInt();
					    if (parts<=1) {
					    	System.out.println("The series must contain more than one movie");
					    }
					    for(int i = 0; i < parts; i++) {
					        scan.nextLine();
					        String title;
					        while(true) {				  
					        	System.out.print("Enter title of part " + (i+1) +": ");
					        	title = scan.nextLine();
					        	Content existingContent = lib.searchContent(title);
					        	if(existingContent != null)
					        	    System.out.println("Content already exists\nPlease try again");
					        	else
					        		break;
					        }
					        System.out.print("Enter age rating: ");
					        int age = scan.nextInt();
					        System.out.print("Enter duration (Minutes): ");
					        int duration = scan.nextInt();
					        MovieSerise m = new MovieSerise(title, age, duration,parts);
					        lib.addContent(m);
					    }
					}
					else if(ans.equalsIgnoreCase("no")){
					    scan.nextLine();
					    String title;
					    while(true) {
					    System.out.print("Enter movie title: ");
					    title = scan.nextLine();
					    Content existingContent = lib.searchContent(title);
			        	if(existingContent != null)
			        	    System.out.println("Content already exists\nPlease try again");
			        	else
			        		break;
			        }
					    System.out.print("Enter age rating: ");
					    int age = scan.nextInt();
					    System.out.print("Enter duration (Minutes): ");
					    int duration = scan.nextInt();
					    Movie m = new Movie(title, age, duration);
					    lib.addContent(m);
					}
					else {
						System.out.println("Wrong input !!");
						break;
					}
				}
				
				else if(answer.equalsIgnoreCase("Serise")) {
					    System.out.print("How many seasons?: ");
					    int seasons = scan.nextInt();
					    for(int i = 0; i < seasons; i++) {
					        scan.nextLine();
					        String title;
					        while(true) {
					        System.out.print("Enter title of season" + (i+1)+ ": ");
					        title = scan.nextLine();
					        Content existingContent = lib.searchContent(title);
				        	if(existingContent != null)
				        	    System.out.print("Content already exists\nPlease try again");
				        	else
				        		break;
				        }
					        System.out.print("Enter age rating: ");
					        int age = scan.nextInt();
					        Series s = new Series(title, age, seasons);
					        lib.addContent(s);
					    }
					}
				 else {
						System.out.println("Wrong input !!");
						break;
				 }
				
				System.out.println("Content created successfully");
				break;
					
			case (2):
				System.out.print("Enter the Content name: ");
			scan.nextLine();
			String keyword2 = scan.nextLine();
			Content c = lib.searchContent(keyword2);
			if (c == null) {
				System.out.println("Content not found");
				break;
			}
			
			System.out.print("Enter your username: ");
			String username = scan.next();
			User u = lib.searchUser(username);
			if (u == null) {
				System.out.println("User not found");
				break;
			}
			System.out.print("Enter rating (1-10): ");
			int rating = scan.nextInt();
			scan.nextLine();
			
			System.out.print("Enter comment: ");
			String comment = scan.nextLine();
			Review r = new Review(rating, comment, u);
			u.addReview(r);
			c.addReview(r);
			System.out.println("Review added successfully");
				break;
			
			case (3):
		        scan.nextLine();
				System.out.print("Enter the Content name: ");
				String keyword = scan.nextLine();
			    Content result = lib.searchContent(keyword);
			    if (result != null) {
			        System.out.println(result);
			        System.out.println("Reviews:");
			        result.displayReviews();
			    } 
			    else {
			        System.out.println("Content not found");
			    }
			    break;
			case (4):
				
				lib.displayContent();
				break;

			case (5):
				System.out.print("Enter your Username:");
				String name;
				
				while(true) {
				name=scan.next();
				if (name.contains(" "))
					System.out.print("You can't have a space in your username\nPlease try again: ");
				User existingUser = lib.searchUser(name);
				if(existingUser != null)
				    System.out.print("Username already exists\nPlease try again: ");
				else if (name.isEmpty())
					System.out.print("You can't have an empty username\nPlease try again: ");
				else 
					break;
				}	
				User U=new User(name);
				lib.addUser(U);
				System.out.println("User created successfully");
				break;

			case (6):
			    System.out.print("Enter the Username: ");
			    String keyword3 = scan.next();
			    User resultUser = lib.searchUser(keyword3);
			    if (resultUser != null) {
			        System.out.println(resultUser);
			        resultUser.displayReviews();
			    }
			    else {
			        System.out.println("User not found");
			    }
			    break;
			
			case (0):
				exit=false;
				System.out.println("See you next time <3 ");
			break;
			
			default:
				System.out.println("Wrong choise, Please try again");
			}
		}
		
		while(exit);
	}

}
