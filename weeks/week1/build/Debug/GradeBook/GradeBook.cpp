#include <iostream>
using namespace std;

class GradeBook {
public:
   void displayMessage( int courseNo ) {
      cout << "Welcome to " << courseNo << endl;
   }
};

int main() {
   int cno;
   GradeBook G1;  // no "new" in c++ !
   cout << "Enter course no: ";
   cin >> cno;
   G1.displayMessage( cno );
   return 0;
}