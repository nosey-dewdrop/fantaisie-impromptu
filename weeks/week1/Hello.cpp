#include <iostream>
#include <string> 
using namespace std;

class Hello{
   public:
      void setInfo(string newName, int newAge){
            name = newName;
            age = newAge;
      }
      void sayHello() {
        cout << "\033[1;36mHi, I'm " << name << "! I'm " << age << " years old.\033[0m" << endl;
      }
   private:
      int age;
      string name;
};

int main(){
      Hello h1;

      string name;
      cout << "Enter name: "<< endl;
      cin >> name;

      int age;
      cout << "Enter age: " << endl;
      cin >> age;

      h1.setInfo(name, age);
      h1.sayHello();
      
      cout << "\033[1;35mBack to main! Objects live until program ends.\033[0m" << endl;
// \033[1;34m \033[0m
// \033 escape 1 bold, 34m blue
      return 0;
}