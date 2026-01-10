#include "IntArray.h"   // kendi sınıfımızın tanımı burada
#include <iostream>     // ekrana yazmak için (cout vb.)
using namespace std;


IntArray::IntArray(const int arr_size){
      /*
      cout<< "this constructor!"<<endl;
      */

      if (arr_size <= 0) {
            size = 0;
            data = nullptr;

            /*
            in stack, you cannot create 0 size array. it is CE
            in heap, new int[0] is legal. but the compiler does not allocate a memory with 0 bytes.
            so, 
            data = new int[0] is not referring to a real address.  a pointer but meaningless.
            you cannot reach data[0] but can delete[] data; meaningless pointer.
            */
      } 

      else {
            size = arr_size;
            data = new int[size];

            /*
             when created, it is random numbers -garbage value-
            we are cleannig the slots.
            */

            for (int i = 0; i < size; i++){
                  data[i] = 0;
            }
      }
}

IntArray::~IntArray(){
      /* 
      cout << "deconstructor worked!"<< endl;
      it is deconstructed at the end of the main function.
      first created, deleted the last.
      last created, deleted the first.

      if srray has > 0 sizes, then the data pointer is pointing somewhere, not a nullptr.

      int* a
      int* b

      a, b can point to an intefer or an intefer array. 

      a = new int;  
      delete data;

      b = new int[size];
      delete[] data;

      */

      if (data) { 
            delete[] data;
      }
      /*
      nullptr çökmüyor. eğer tanımlandıysa ama initialize edilmediyse onu silemeyiz. olmayan bir şeyi serbest bırakıyor ve program çöker.
      */
}
int main(){
      IntArray arr(5);
      cout<< "helloo" << endl;
      return 0;
}

/*

int* p = new int[5];
p = nullptr;
delete[] p;

memory leak.

*/