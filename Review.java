package Letterboxd;

public class Review {
	private int rating;
    private String comment;
    private User author;
    public Review(int r, String c, User a)
    		throws InvalidRatingException {
    		   if(r < 1 || r > 10)
    		      throw new InvalidRatingException(
    		      "Rating must be between 1 and 10");
    		   rating = r;
    		   comment = c;
    		   author = a;
    		}
public Review(Review r) {
    rating = r.rating;
    comment = r.comment;
    author = r.author;
}
public String toString() {
	        return "User:"+author+" ,Rating:"+rating+", Comment:"+ comment;
	    }

public int getRating() {
    return rating;
}

}