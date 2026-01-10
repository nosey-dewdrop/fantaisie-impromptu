#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;
};

int main() {
      Node* one = new Node();
      Node* second = new Node();
      Node* third = new Node();

      cout << "before" << endl;
      cout << one -> next << endl;
      cout << second -> next << endl;
      cout << third -> next << endl;
      // burada -> kullanmamızın nedeni next bir pointer diy edeğil. node'lar da bir pointer.
            
      one->next = second;
      second->next = third;
      third->next = nullptr;  

      cout << "after" << endl;
      cout << one -> next << endl;
      cout << second -> next << endl;
      cout << third -> next << endl;

      one ->data = 10;
      second->data = 20;
      third->data = 30;


      cout << one -> data << endl;
      cout << second -> data<< endl;
      cout << third -> data << endl;

      cout << "the fourth one" << endl;
      Node four = Node();
      cout << four.data << endl;
      cout << four.next << endl;


    
//     Node* current = one;
//     while (current != nullptr) {
//         cout << current->data << " -> ";
//         current = current->next;
//     }
//     cout << "NULL" << endl;
    
    return 0;
}
