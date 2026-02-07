#include <iostream>
using namespace std;

#include "GradeBook.h" // include definition of class GradeBook

GradeBook::GradeBook( const int no ) {
    setCourseNo( no );
    for ( int i = 0; i < numStudents; i++ ) {
        grades[ i ] = 0;
    }
    homeworks = nullptr;
    numHomeworks = 0;  
}

GradeBook::~GradeBook() {}

void GradeBook::displayMessage( const int no ) const {
    cout << "Welcome to the grade book for CS " << no << "!" << endl;
}

void GradeBook::displayMessage() const {
    cout << "Welcome to the grade book for CS " << getCourseNo() << "!" << endl;
}

void GradeBook::setCourseNo( const int no ) {
    if ( ( no >= 100 ) && ( no <= 999 ) ) {
        courseNo = no;
        cout << "Course no is set to " << no << endl;

    }
    else {
        cout << no << " is an invalid course no" << endl;
        cout << "Course no is set to " << courseNo << endl;
        //courseNo = 100;
    }
}

int GradeBook::getCourseNo() const {
    return courseNo;
}

void GradeBook::setGrades( const int inGrades[] ) {
    bool valid = true;
    for ( int i = 0; ( i < numStudents ) && ( valid == true ); i++ ) {
        if ( ( inGrades[ i ] < 0 ) || ( inGrades[ i ] > 100 ) ) {
            cout << "Grade " << inGrades[ i ] << " is not valid" << endl;
            valid = false;
        }
        else {
            grades[ i ] = inGrades[ i ];
        }
    }
    if ( valid == true ) {
        cout << "Grades are set" << endl;
    }
    else {
        cout << "All grades are reset back to 0" << endl;
        for ( int i = 0; i < numStudents; i++ ) {
            grades[ i ] = 0;
        }
    }
}

void GradeBook::determineCourseAverage() const {
    double avg = 0.0;
    for ( int i = 0; i < numStudents; i++ ) {
        avg += grades[ i ];
    }
    avg /= numStudents;
    cout << "Average grade is " << avg << endl;
}

void GradeBook::findGradeDistribution() const {
    int aCount = 0, bCount = 0, cCount = 0, dCount = 0, fCount = 0;
    for ( int i = 0; i < numStudents; i++ ) {
        switch ( grades[ i ] / 10 ) {
            case 10: case 9:
                aCount++;
                break;      // necessary to exit switch
            case 8:
                bCount++;
                break;
            case 7:
                cCount++;
                break;
            case 6:
                dCount++;
                break;
            default:
                fCount++;
                break; // optional; will exit switch anyway
        }
    }

    cout << "Number of students who received each letter grade:" << endl
         << "A: " << aCount << endl
         << "B: " << bCount << endl
         << "C: " << cCount << endl
         << "D: " << dCount << endl
         << "F: " << fCount << endl;
}

void GradeBook::addHomework(const int hwNo) {
    numHomeworks++;
    int *temp = new int[numHomeworks];
    for (int i = 0; i < numHomeworks - 1; i++) {
        temp[i] = homeworks[i];
    }
    temp[numHomeworks - 1] = hwNo;

    if(homeworks != nullptr){
        delete[] homeworks;
    }

    homeworks = temp;
    cout << "New homework is added." << endl;
}

void GradeBook::listHomeworks() const {
    cout << "Homeworks: " << endl;
    for (int i = 0; i < numHomeworks; i++) {
        cout << '*' << homeworks[i] << endl;
    }
}

void GradeBook::removeHomeworks() {
    delete[] homeworks;
    numHomeworks = 0;
    cout << "All homeworks are removed." << endl;
}
