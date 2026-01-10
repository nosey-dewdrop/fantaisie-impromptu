#include <iostream>
using namespace std;

int main(){
      int arr[5] = {10,20,30,40,50};

      // cout << "arr: " << arr << endl;     
      // cout << "&arr[0]: " << &arr[0] << endl;
      // cout << "arr[0] " << arr[0] << endl;
      // cout << "*arr " << *arr << endl;

      // cout << "&arr[1]: " << &arr[1] << endl;
      // cout << "arr[1] " << arr[1] << endl;

      // cout << "&arr[2]: " << &arr[2] << endl;
      // cout << "arr[2] " << arr[2] << endl;

      int* p = arr;
      cout << "*p: " << *p << endl; 
      cout << "p: " << p << endl;  
      p++;
      cout << "\n*p: " << *p << endl;  
      cout << "p: " << p << endl; 

      cout << "\n*(p+1): " << *(p+1) << endl;  
      cout << "(p+1): " << (p+1)  << endl; 

    return 0;
}