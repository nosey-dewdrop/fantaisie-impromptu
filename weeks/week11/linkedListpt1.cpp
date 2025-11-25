#include <iostream>
using namespace std;

struct Node {
    int data;       
    Node* next;     
};

int main() {
    Node* n1 = new Node();
    n1->data = 10;

    Node* n2 = new Node();
    n2->data = 20;        

    Node* n3 = new Node();
    n3->data = 30; 
    
    Node* head = n1; // node türünden bir pointer.
    n1 -> next = n2;
    n2 -> next = n3;
    n3 -> next = nullptr;

    Node* temp = head; //temporary node.
    cout << head << endl;
    cout << &head << endl;

    cout << temp << endl;
    cout << &temp << endl;

    while(temp != nullptr){
      cout << temp -> data << " : " << endl;
      cout << "temp initial:" << temp << endl;
      cout << "head initial:" << head << endl;
      cout << "temp initial: (is it n1?)" << n1 << endl; //yes

      temp = temp -> next;
      
      cout << "temp final:" << temp << endl;
      cout << "temp final: (is it n2?)" << n2 << endl; //yes
    }

    cout << "end!" << endl;
    return 0;
}