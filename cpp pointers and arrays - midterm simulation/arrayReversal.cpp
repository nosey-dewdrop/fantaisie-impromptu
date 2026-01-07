#include <iostream>
using namespace std;

void reverseArr(int arr[], int size){

      // pass by value: not able to mody. nevertheless, this time we give an array. which is a pointer. 
      //we modify it by the pointer itself.

      // just print. nonsense.
      int *end = arr + size - 1;
      for(int i = 0; i < size; i++){
            cout << *end << " ";
            end--;
      }

      // actually modify it!
      end = arr + size - 1;

      for(int i = 0; i < size / 2; i++){
            int temp = *(arr + i);
            *(arr + i) = *end;
            *end = temp;
            end--;
      }
}

int main(){

      int arr[] = {1,2,3,4,5};
      int size = 5;
      reverseArr(arr, size);

      for(int i = 0; i < size; i++){
            cout << arr[i] << " ";
      }

      return 0;
}
