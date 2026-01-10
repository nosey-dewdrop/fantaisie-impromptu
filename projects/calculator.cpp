// calculator is such a basic app. i will do it anyways.
#include <iostream>  
#include <cmath>  // because we need math functions
using namespace std; 

// did not understand namespaces.
namespace damla {
    void selam() {
      cout << "merhaba ben Damla!" << endl;
    }
}

// in cpp the order is mattered. you cannot do something before defining it.
// why include iostream? because we need to do input and output

double add(int a, int b) {
            return a + b;
};// cpp başka bir fonksiyonyu main içerisine yazamazsın.

// fonksiyon sadece int parametre alabilir. double verirsen derleyici double'ı otomatik olarak inte dönüştütür.
// type casting. bu veri kaybetmene neden olabilir.
int subtract(int a, int b) {
            return a - b;
};// cpp başka bir fonksiyonyu main içerisine yazamazsın.

int multiply(int a, int b) {
    return a * b;
};

// here are the member funcitons which we defined inside the class.
class Calculator{
  public:
    int add_member(int a, int b) {
        return a + b;
    }
    int subtract_member(int a, int b) {
        return a - b;
    }
    int multiply_member(int a, int b) {
        return a * b;
    }
};

int main(){
      damla::selam();
      cout<< "🦋today is 30.09.25. you are doing well girl boss. don't worry about anything, anyone."
      << "it is over but it left a scar. it does not hurt you anymore. unless you let it." << endl;
      cout << "welcome to the calculator app!" << endl;

      int a, b;
      cout << "enter a and b:"<< endl;
      cin >> a >> b; 

      Calculator c;
      
      // cout << "enter b: " << endl;
      // cin >> b;    

      cout << "a = " << a << "\nb = " << b << endl;
      cout<< "(this is global function) a + b = " << add(a, b) << endl;
      cout<< "(this is member function) a + b = " << c.add_member(a, b) << endl;

}

