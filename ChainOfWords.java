import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChainOfWords{

      static class Node{
            String str;
            Node next;
            
            Node(String str){
                  this.str = str;
                  this.next = null;
            }
      }

      public static void printNodes(Node head){

            Node current = head;
            while(current != null){
                  System.out.print(current.str);

                  if(current.next != null){
                        System.out.print(" -> ");
                  }
                  current = current.next;
            }

            //System.out.print(" end!");
      }

      public static boolean isValidWord(String word) {
            try {
                  HttpClient client = HttpClient.newHttpClient();
                  HttpRequest request = HttpRequest.newBuilder()
                  .uri(URI.create("https://sozluk.gov.tr/gts?ara=" + word))
                  .build();
                  
                  HttpResponse<String> response = client.send(request, 
                  HttpResponse.BodyHandlers.ofString());
                  
                  // Eğer kelime varsa response boş değildir
                  return !response.body().contains("[]");
                  
            } 
            
            catch (Exception e) {
                  return false;
            }
      }
      public static void main(String[] args){

            Scanner scanner = new Scanner(System.in);

            Node head = null;
            Node current = null;


            boolean proceed = true;      

            while(proceed){
                  System.out.print("\nenter a string: ");
                  String str = scanner.nextLine().toLowerCase();

                  if(str.isEmpty() || str.equals("exit")){
                        proceed = false;
                  }

                  else{
                        Node newNode = new Node(str);
                        if(head == null){
                              head = newNode;
                              current = head;
                        }
                        else{
                              if(current.str.charAt(current.str.length() -1 ) == str.charAt(0)){
                                    current.next = newNode;
                                    current = current.next; 
                              }
                        }
                        printNodes(head);
                  }
            }

            printNodes(head);
            scanner.close();
      }
}