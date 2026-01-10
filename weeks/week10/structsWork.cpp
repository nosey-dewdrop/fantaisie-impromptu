#include <iostream>
#include <string>
using namespace std;

struct Student {
    string name;
    int id;
    double gpa;
};

int main() {
    Student s1;
    s1.name = "Ahmet";
    s1.id = 12345;
    s1.gpa = 3.75;

    cout << "Name: " << s1.name << endl;
    cout << "ID: " << s1.id << endl;
    cout << "GPA: " << s1.gpa << endl;
    
    return 0;
}