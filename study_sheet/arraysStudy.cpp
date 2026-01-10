#include <iostream>
using namespace std;


int main(){
      int numbers[3] = {10, 20, 30}; // on the stack. size is strict while compiling. 
      cout << "the address: "<<numbers << " actually the adress of the first element!!" << endl; 

      int *num = numbers;     
      cout << "the first element: " << num[0] << "\n";
      cout << "the second element: " << *(numbers+1) << "\n";

      int* arr = new int[5];
      // arr is a pointer. this array is dynamic so th esize can be changed while running.
  

      cout << "\nfirst array: " << endl;

      for(int i=0; i<5; i++){
            arr[i] = (i+1)*10;
            cout << arr[i] << " ";
      }

      int* bigger = new int[6];

      for(int i = 0; i < 6; i++){
            bigger[i] = arr[i];
      }
      bigger[5] = 60;

      delete[] arr; // delete the. 3 place from memory. not the pointer. pointer is deleted automatically.
      arr = bigger; // arr refers to what bigger pointer used to point.

      cout << "\nsecond array: " << endl;
      for(int i=0; i<6; i++){
            cout << arr[i] << " ";
      }

      
      return 0;
};