package Letterboxd;

public class User implements Searchable {
	private static int counter = 1;
	private int UID;
	private String username;
    private Review[] UReviewList;
	private int reviewCount;

public User(String name) {
    UID = counter++;
	username = name;
	UReviewList = new Review[10];
	reviewCount = 0;
}

public boolean matches(String keyword) {
	return username.toLowerCase().contains(keyword.toLowerCase());}
	
public void addReview(Review r) {
	UReviewList[reviewCount] = new Review(r);
	reviewCount++;
}
	
public void displayReviews() {
	for (int i =0;i<reviewCount;i++) {
	    System.out.println(UReviewList[i]);
	}
}

public String toString() {
	        return username +"(" + UID + ")";
	    }
}