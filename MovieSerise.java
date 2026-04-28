package Letterboxd;

public class MovieSerise extends Movie {
	int seasons;
	
	public MovieSerise(String t, int AR,int d, int s) {
		super(t,AR,d);
		this.seasons=s;
	}

	public double calculateScore(){
	    return averageReviewScore()/seasons;
	}
	
	public String toString() {
		return super.toString()+ " ,Movie_Serise [seasons=" + seasons + "]"+" ,Score"+calculateScore()+"/10";
	};
}
