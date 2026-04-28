package Letterboxd;

public abstract class Content implements Searchable {
    protected String title;
	protected int ageRating;
    private Review[] CReviewList;
	private int reviewCount;

public Content(String t, int AR) {
	title = t;
	ageRating = AR;
	CReviewList = new Review[10];
	reviewCount = 0;
}

public boolean matches(String keyword) {
	return title.toLowerCase().contains(keyword.toLowerCase());}

public void addReview(Review r) {
		   CReviewList[reviewCount++]=new Review(r);
}

public void displayReviews() {
	for (int i = 0;i<reviewCount;i++) {
	    System.out.println(CReviewList[i]);
	    }
}

public abstract double calculateScore();

public double averageReviewScore() {
    if(reviewCount == 0)
        return 0;
    int sum = 0;
    for(int i = 0; i < reviewCount; i++) {
        sum += CReviewList[i].getRating();
    }
    
    double score=(double)sum/reviewCount;;
    return score;
}

public String toString() {
	return "Title:"+ title +" ,Age Rating:"+ ageRating;
	    }
	}