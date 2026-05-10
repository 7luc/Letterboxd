package Letterboxd;

public class UserNode {
   User data;
   UserNode next;

   public UserNode(User u){
      data = u;
      next = null;
   }

}