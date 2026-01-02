
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

            int chainLength = 0;
            while(proceed){
                  System.out.print("\nenter a string: ");
                  String str = scanner.nextLine().toLowerCase();

                  if(str.isEmpty() || str.equals("exit")){
                        proceed = false;
                  }
