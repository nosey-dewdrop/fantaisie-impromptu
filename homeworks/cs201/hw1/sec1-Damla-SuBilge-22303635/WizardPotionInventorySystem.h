// Name: Damla
// CS 201, Spring 2026, Homework 1

#ifndef WIZARDPOTIONINVENTORYSYSTEM_H
#define WIZARDPOTIONINVENTORYSYSTEM_H

#include <string>
using namespace std;

struct Potion {
    string potionName;
    int strength;
};

struct StudentWizard {
    string name;
    string house;
    Potion* potions;
    int potionCount;
};

class WizardPotionInventorySystem {
public:
    WizardPotionInventorySystem();
    ~WizardPotionInventorySystem();

    void addStudentWizard(const string name, const string house);
    void removeStudentWizard(const string name);

    void brewPotion(const string studentName,
                    const string potionName,
                    const int strength);

    void discardPotion(const string studentName,
                       const string potionName);

    void transferPotion(const string potionName,
                        const string fromStudent,
                        const string toStudent);

    void showAllStudentWizards() const;
    void showStudentWizard(const string name) const;
    void showPotion(const string potionName) const;

private:
    StudentWizard* wizards;
    int wizardCount;

    int findWizard(const string& name) const;
    int findPotion(int wizardIdx, const string& potionName) const;
    int getTotalStrength(int wizardIdx) const;
};

#endif