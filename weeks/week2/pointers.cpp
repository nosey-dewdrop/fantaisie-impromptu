
#include <iostream>  
#include <cmath>  // because we need math functions
using namespace std; 

//./build/pointers
class Book {
      public:
            Book(int id = 0) {   
                  this->id = id;
            }
            void displayId() {   
                  cout << "book id: " << id << endl;
            }
      private:
            int id;  
};

int main(){

      Book* B1 = new Book; // HEAP 1 pointer object.
      //pointer keeps the adress ıf the objects. so you need to use ->
      B1 -> displayId();

      Book* B2 = new Book[5]; // HEAP 5 object
      B2[0] = Book(201);       
      B2[0].displayId();

      Book B3[3]; // STACK 3 object
      //Book B3[3] = { Book(301), Book(302), Book(303) }; //de yapabilirdin.
      B3[0] = Book(301);
      B3[1] = Book(302);
      B3[2] = Book(303);

      int x = 5;
      int* y = &x; // gave it an address.

      cout << "\nx = " << x; 

      cout << "\ny = " << y ; 
      //0x16d88f174 y only holds an adress of x.

      cout << "\n*y (indirect) = " << *y;
      // *y goes to the adress x, brings the value.

      *y = 55; // change the value by indirect access
      cout << "\nnew x value = " << x << endl;

      int** z = &y;
      cout << "\nz = " << z;
      // adress of y.

      cout << "\n*z = " << *z;
      //address of x.

      cout << "\n**z = " << **z;
      // the x itself!!! goes to the adress, adress holds an address. 
      // goes to the address of the address. brings the value.

      **z = 100; // super indirect access
      cout << "\nnew x value = " << x << endl;

      // int arr[3] = {10, 20, 30};
      // cout << arr[1];    
      // cout << *(arr+1); 

      int a = 10;
      int b = 20;

      int *p1 = &a;
      int *p2 = &b;

      cout<< "\na = " << a << endl;
      cout<< "b = " << b << endl;

      cout<< "\np1 = " << p1 << endl;
      cout<< "p2 = " << p2 << endl;

      cout<< "\n*p1 = " << *p1 << endl;
      cout<< "*p2 = " << *p2 << endl;

      p2 = p1;
      cout << "\nafter assignment, *p2 = " << *p2;
      if (p1 == p2) cout << "\np1 and p2 point to the same location."<<endl;

      int arr[3] = {11, 22, 33};
      int* p1_arr = arr; // went to adress of arr[0]. dereferance it, result is arr[0]
 
      cout << "\n*p1_arr = " << *p1_arr << endl; // goes to the first one.
      cout << "p1_arr = " << p1_arr << endl; 
      cout << "&arr = " << &arr << endl; 

      int copy = arr[0]; // new integer created by COPYING arr[0]
      int* copy_p1 = &copy;

      cout << "\ncopy_p1 = " << *copy_p1 << endl;
      cout << "copy_p1 = " << copy_p1 << endl;



      //SWAPPING

      int k = 5;
      int q = 7;

      cout << "\nbefore swapping a = " << k << endl;
      cout << "before swapping b = " << q << endl;

      cout << "before swapping &a = " << &k << endl;
      cout << "before swapping &b = " << &q << endl;

      int* pointer1 = &k;
      int* pointer2 = &q;

      //a ve b adreslerini aldık. adresi değiştirmeden oradaki integerları değiştirmemiz gerek.
      int temp = *pointer1; //p1 pointerının gösterdiği yerdeki integer değeri temporary'ye verdik.
      *pointer1 = *pointer2; //p1'İn gösterdiği adresteki yere yeni bir değer koyalım. bu değer p2'deki değer olsun.
      *pointer2 = temp; //p2'nin gösterdiği yerdeki değeri de temp yapalım. 

      cout << "\ntemp = " << temp << endl;
      cout << "after swapping k = " << k << endl;
      cout << "after swapping q = " << q << endl;

      cout << "after swapping &k = " << &k << endl;
      cout << "after swapping &q = " << &q << endl;

      //değerler değişti ama adresler değişmedi. 

      return 0;
}

/**
 * STACK:
 * HEAP:
 * SCOPE: Yoy know the adress, you can access it through pointer.
 * DEREF: *
 * ADRESS: &
 */