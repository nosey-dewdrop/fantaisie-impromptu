#include <iostream>     
using namespace std;

int main(){
      cout<< "helloo" << endl;

      cout << "🦋 page 1\n" << endl;
      cout<< "one dim array" << endl;
      int a1[2] ={1, 2};

      // should be declared through initilazation. cannot change it later. 
      // mutable. but cannot change in size because the adress of the memory is the same.
      cout<< "two dim array" << endl;
      int a2[2][3] = {{1,2,3 }, {4,5,6}};

      cout << "🦋 page 2\n" << endl;
      cout<< "first array element is an array holding three numbers." << endl;
      cout <<"first array 1 number:" << a2[0] << endl; // you only get the adress. cannot do this!!!!!
      cout <<"first array 1 number:" << a2[0][0] << endl;

      cout << "🦋 page 3\n" << endl;
      // should be constant if not it will gice an error. compiler should now how many bytes to allocate.
      // this is a stack array. 
      const int rows = 2;
      const int cols = 3;
      int arr[rows][cols]; // sabit, derleme anında biliniyor
      
      cout << "🦋 page 4\n" << endl;
      /**
      void printArray(int arr[], int size);
      void print2D(int arr[][3], int rows, int cols);
      compiler to calculTE THE ADRESS. because in memory, it is how it is reminded in memory. 
      ---/---
      so the size.
       */

      cout << "🦋 page 5 \n" << endl;
      /**
      * for 1d arrays.
      ** for 2d arrays.
       */

       /**
        * çok anlamadım. ama pointer yapınca hep sadece satırları alıyor. o yüzden sütunlar için loop açıp pointer yapmak lazım.
        * sonra da new ile heap'te açtığımız için bunları tek tek kapatmak lazım.
        * 
        */

      cout << "🦋 page 6 \n" << endl;
      

      return 0;
}
