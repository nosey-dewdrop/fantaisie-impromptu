#ifndef INTARRAY_H
#define INTARRAY_H
#include <stdexcept>

class IntArray {
public:
    IntArray(const int = 0);
    ~IntArray();

private:
    int size;
    int* data; 
};

#endif


/*

    only holds an adress in the stack. 
    we can create the real array in the heap. "new"
    data = new int[size];

    when we say int data[10]; there is an automatic array in the stack. 
    size is immutable. deleted after the funkstion ends.


- STACK: normal variable or object. deleted automatically.
- HEAP: new / malloc. delete or free.

void foo() {
   int a = 10;          // stack - scope is this function. deleted automatically when the function is over.
   int* b = new int;    // heap - deleted intentionally.
}

- MEMORY LEAK: 

- SCOPE:
int globalVar = 5; 

class Example {
   int member; // class scope
   void func() { int local; } // local scope
};

int main() {

    int a = 1;
    {
        int b = 2;
        cout << a << b;  // they both are accesible.
    }
    cout << a;  // b is not living anymore!!!!

}

void bar() {
    int* ptr = new int;
}
- ptr is on the stack. 
- new int is on heap. 
- DYNAMIC ARRAY:  compile time değil run-time'da boyutu belli. 
*/