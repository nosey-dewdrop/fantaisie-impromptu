#ifndef GRADEBOOK_H
#define GRADEBOOK_H

class GradeBook {

public:
    GradeBook( const int no = 100 );

    ~GradeBook();

    void displayMessage( const int no ) const;
    void displayMessage() const;

    // sets the course number
    void setCourseNo( const int no );

    // returns the course number
    int getCourseNo() const;

    // sets the grades stored in the GradeBook object
    // the grades entered should be valid (in between 0 and 100)
    // all grades are reset to 0 if there is at least one invalid grade
    void setGrades( const int inGrades[] );

    // computes the average of grades
    void determineCourseAverage() const;

    // computes the letter grade distribution
    void findGradeDistribution() const;

    void addHomework( const int hwNo );
    void removeHomeworks();

    void listHomeworks() const;

    static const int numStudents = 10; // fixed number of students

private:
    int courseNo;               // course number
    int grades[ numStudents ];  // array that stores the grades

    int numHomeworks = 0; // number of homeworks
    int *homeworks;       // pointer to dynamically allocated array of homeworks
};

#endif // GRADEBOOK_H
