package Letterboxd;

public class Movie extends Content {
	protected int duration;
	
	public Movie (String t, int AR,int d) {
		super(t,AR);
		this.duration=d;
	}
	
	public  double calculateScore() {
	    return averageReviewScore();
		
	}

	public String toString() {
		
		return super.toString() + " ,Movie [duration=" + duration + "]"+" ,Score"+calculateScore()+"/10";
		
	}
}
