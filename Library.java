package Letterboxd;

public class Library {
    private int CCount;
	private int UCount;
	private Content[] contents;
	private User[] users;

public Library(){
	contents = new Content[20];
	users = new User[20];
	CCount = 0;
	UCount = 0;
}

public void addContent(Content c){
	contents[CCount] = c;
	CCount++;
}

public void displayContent(){
	for (int i=0; i<CCount;i++) {
		System.out.println((i+1) + "-" + contents[i]);
		}
    }

	       
public Content searchContent(String keyword){
    return searchRecursive(keyword,0);
}

private Content searchRecursive(String keyword, int index){
    if(index >= CCount)
        return null;

    if(contents[index] != null && contents[index].matches(keyword))
        return contents[index];

    return searchRecursive(keyword, index + 1);
}

public void addUser(User u) {
	users[UCount] = u;
	UCount++;
}

public User searchUser(String keyword){
	for (int i =0;i<UCount;i++) {
	    if (users[i].matches(keyword)){
	        return users[i];
	        }
      }
	        return null;
	    }
	}