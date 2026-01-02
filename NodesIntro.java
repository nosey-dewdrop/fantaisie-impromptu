import java.util.Scanner;

public class NodesIntro {
      
      /*
      - in java inner class must be a static. because you don't access it through an object solely.
      - node is like a chain of paperclip. each paper clip is a node. 
      - every paper clip has two info: a value and the next paperclip. 
      so, when we know the first paper clip, we have all the data for the chain.
      */

      
      static class Node{
            int val;
            Node next;
            
            Node(int val){
                  this.val = val;
                  this.next = null;
            }
      }

      public Node mergeTwoLists(Node list1, Node list2){
            
            return null;
      }

      public static void printNodeVal(Node head){
            Node current = head;
            while (current != null){
                  System.out.print(current.val);
                  if(current.next != null){
                        System.out.print(" -> ");
                  }
                  current = current.next;
            }
            System.out.print(" -> .");
      }
      public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
            // Node head = new Node(5);

            // Node node2 = new Node(3);
            // head.next = node2;

            // Node node3 = new Node(4);
            // node2.next = node3;

            //System.out.println(head.next.next.val);  

            Node head = null;
            Node current = null;
            boolean posInt = true;
            while(posInt){
                  System.out.print("enter an integer (-1) to exit: ");

                  if(scanner.hasNextInt()){
                        int n = scanner.nextInt();
                        scanner.nextLine();

                        if(n >= 0){
                              Node newNode = new Node (n);
                              if(head == null){
                                    head = newNode;
                                    current = head;
                              }
                              
                              else{
                                    current.next = newNode;
                                    current = current.next;
                              }
                        }
                        else{
                              posInt = false;
                        }

                        printNodeVal(head);
                  }
                  else{
                        scanner.nextLine();
                  }
            }

            printNodeVal(head);
            scanner.close();
            
      }
}
