package Letterboxd;

public class Library {
    private int CCount;
	private Content[] contents;
	private UserLinkedList users;

public Library(){
	contents = new Content[20];
	users = new UserLinkedList();
	CCount = 0;
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
	users.add(u);
		}

public User searchUser(String keyword){
	   return users.search(keyword);
	}

}