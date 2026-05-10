package Letterboxd;

public class Series extends Content {
	private int seasons;
	
	public Series(String t, int AR, int s) {
		super(t,AR);
		this.seasons=s;
		
	}
 
	public  double calculateScore() {
	    return averageReviewScore();		
	}

	public String toString() {
		return super.toString() +" ,Series [seasons=" + seasons + "]"+" ,Score"+calculateScore()+"/10";
	}
	
}
