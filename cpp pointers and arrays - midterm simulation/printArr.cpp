#include <iostream>
using namespace std;


void printArr(int arr[], int size){
      int *ptr = arr;
      for(int i = 0; i < size;  i++){
            cout << *ptr << " ";
            ptr++;
      }
}
int main(){

      int arr[] = {1,2,3,4,5};
      int size = 5;
      printArr(arr, size);
      return 0;

}