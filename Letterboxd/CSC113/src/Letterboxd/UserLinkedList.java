package Letterboxd;

import java.io.PrintWriter;

public class UserLinkedList {

    private UserNode head;

    public void add(User u) {
        UserNode n = new UserNode(u);
        if (head == null) { head = n; return; }
        UserNode temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = n;
    }

    public User search(String keyword) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.data.matches(keyword)) return temp.data;
            temp = temp.next;
        }
        return null;
    }

    public void printAll(PrintWriter writer) {
        UserNode temp = head;
        while (temp != null) {
            writer.println(temp.data.toString());
            temp = temp.next;
        }
    }
}
