
#ifndef WIZARDPOTIONINVENTORYSYSTEM_H
#define WIZARDPOTIONINVENTORYSYSTEM_H

#include <string>
using namespace std;

// structs are public by default unlike classes in cpp. they are not global or anything. 
struct Potion {
      string potionName;
      int strength;
};

struct StudentWizard{
      string name;
      string house;
      int potionsCount;
      Potion* potion;
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

            // this does not change datas of class. just reads.
            // const object cannot call non-const member functions.
            void showAllStudentWizards() const;
            void showStudentWizard(const string name) const;
            void showPotion(const string potionName) const;
      private:
            StudentWizard* wizards;
            int wizardCount;
};

#endif