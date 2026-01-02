
            while(proceed){
                  System.out.print("\nenter a string: ");
                  String str = scanner.nextLine();

                  Node newNode = new Node(str);
                  if(head == null){
                        head = newNode;
                        current = head;
                  }
                  else{
                        if(current.str.charAt(current.str.length -1 ) == ){
                             current.next = newNode;
                              current = current.next; 
                        }
                        // current.next = newNode;
                        // current = current.next;
                  }
                  printNodes(head);
            }
