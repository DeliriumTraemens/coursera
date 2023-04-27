/* *****************************************************************************
 *  Name:              Nick Oskin
 *  Coursera User ID:  123456
 *  Last modified:     27.04.2023
 **************************************************************************** */

public class LinkedStackOfStrings {
    private Node first = null;

    private class Node{
        String item;
        Node next;
    }
    boolean isEmpty(){
        return first==null;
    };
    void push(String item){
        Node oldFirst = first; // Now first is NULL (Node oldFirst=NULL)
        first = new Node(); // But Now it is Created physically
        first.item = item;
        first.next = oldFirst; // Add the null-ed pointer for the  next
    };
    String pop(){
        String item = first.item;
        first = first.next;
        return item;
    };
    int size(){
        return 0;
    }

}
