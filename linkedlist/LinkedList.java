package linkedlist;
import java.util.Scanner;
public class LinkedList {
/*
    a data type that is like an array but
    not only does it stores its own data but also stores 
    a reference to another data
    which makes these data linked to each other
    
    and with the use of a reference 
    */
    public static Node head;
    public static class Node{//basically creating an object within this class
        int data; //current data
        Node next; //reference for the next data
        
        Node(int givenData){ //constructor of the Node class and will be used to set data into an actual data
            this.data = givenData;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node head = new Node(6);//how to normally assign numbers
        head.next = new Node(4);//how to assign numbers next to it
    /*
        how to assign a next variable with its own name 
    */
        Node b = new Node(4);
        head.next=b;
        int count=0;
        while(count!=5){
            System.out.print("Enter New Num: ");
            int newNum = sc.nextInt();
            System.out.println(addNode(newNum));
            count++;
        }
        print();
        
        
    }
    static int addNode(int num){
        int count = 1;
        Node newCurrent = new Node(num);

        if(head==null){
            head=newCurrent;
        }else{
            while(head.next != null){
                head.next = head.next.next;
                count+=1;
            }
            head.next=newCurrent;
            count+=1;
        }
        return count;
    }
    static void print(){
        System.out.println("");
        System.out.print(head);
        while(head.next != null){
            System.out.print(head.next.toString()+" ");
            head.next = head.next.next;
        }
    }
}
