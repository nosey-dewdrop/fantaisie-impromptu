#include <iostream>
using namespace std;

void print(int arr[], int size){
      cout << endl;
      for(int i=0;i < size ; i++){
            int *p = &arr[i];
            cout << "\n&arr" << "["<< i <<"]" << ": " << p << endl;
            cout << "p: " << p << endl;
            cout << "arr" << "["<< i <<"]" << ": " << arr[i] << endl;
            cout << "*p: " << *p << endl;
      }
}

void print_ptr(int* arr, int size){
      cout << endl;
      for(int i=0; i < size ; i++){
            cout << *(arr + i) << " ";
      }
}

/*

void print(int arr[], int size);
void print(int* arr, int size);

*/
int main(){
      int arr[3] = {10,20,30};
      int * p = arr;
      // ilk elemanın adresi değil, başlangıç adresi. bunu bilince kalanına ulaşabiliyoruz.
      // bellekte ardışık yer tutuyorlar. elemanlar yan yana durur. başka değişken girmiyor.
      
      
      cout << "\narr: \n" << arr << endl;
      cout << "p: \n" << p << endl;

      cout << "\n*arr: \n" << *arr << endl;
      cout << "*p: \n" << *p << endl;

      print(p, 3);
      // print(arr, 3);
      // print(&arr[0], 3);

      // int arr_ptr[2] = {2, 4};
      // int *x = arr_ptr;

      // print_ptr(arr_ptr, 2);
      // print_ptr(x, 2);

      // print_ptr(&arr_ptr[0], 2);
      // print_ptr(*x, 2); 
      // olabilirdi ama kendisi sadece adres alıyor. bir değer almıyor.
}

/**
 * 
 * 
 
 */