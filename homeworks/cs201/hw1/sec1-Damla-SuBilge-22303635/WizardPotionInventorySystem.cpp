#include "WizardPotionInventorySystem.h"
#include <iostream>
#include <string>
using namespace std;

WizardPotionInventorySystem::WizardPotionInventorySystem() {
      wizards = nullptr;
      wizardCount = 0;
}

WizardPotionInventorySystem::~WizardPotionInventorySystem() {
      for (int i = 0; i < wizardCount; i++) {
            delete[] wizards[i].potions; 
      }

      delete[] wizards;
}

int WizardPotionInventorySystem::findWizard(const string& name) const {
      for (int i = 0; i < wizardCount; i++) {
            if (wizards[i].name == name) return i;
      }
      return -1;
}

int WizardPotionInventorySystem::findPotion(int wizardIdx, const string& potionName) const {
      for (int i = 0; i < wizards[wizardIdx].potionCount; i++) {
            if (wizards[wizardIdx].potions[i].potionName == potionName) return i;
      }
      return -1;
}

int WizardPotionInventorySystem::getTotalStrength(int wizardIdx) const {
      int total = 0;

      for (int i = 0; i < wizards[wizardIdx].potionCount; i++) {
            total += wizards[wizardIdx].potions[i].strength;
      }
      
      return total;
}

void WizardPotionInventorySystem::addStudentWizard(const string name, const string house) {
      if (findWizard(name) != -1) {
            cout << "Cannot add student wizard. Student wizard " << name << " already exists." << endl;
            return;
      }

      /*
      we create a newArray pointer. left side is on the stack and right side is on the heap. 
      we delete the old array from the heap.
      we make the old pointer point to the new array which new pointer points.
      as new pointer is on the stack, we don't care about it, it will be deleted. */

      StudentWizard* newArr = new StudentWizard[wizardCount + 1];

      for (int i = 0; i < wizardCount; i++) {
            newArr[i].name = wizards[i].name;
            newArr[i].house = wizards[i].house;
            newArr[i].potions = wizards[i].potions;
            newArr[i].potionCount = wizards[i].potionCount;
      }

      newArr[wizardCount].name = name;
      newArr[wizardCount].house = house;
      newArr[wizardCount].potions = nullptr;
      newArr[wizardCount].potionCount = 0;

      delete[] wizards;
      wizards = newArr;
      wizardCount++;

      cout << "Added student wizard " << name << "." << endl;
}

void WizardPotionInventorySystem::removeStudentWizard(const string name) {

      int idx = findWizard(name);
      if (idx == -1) {
            cout << "Cannot remove student wizard. Student wizard " << name << " does not exist." << endl;
            return;
      }

      // if we can remove.
      delete[] wizards[idx].potions;
      StudentWizard* newArr = nullptr;

      if (wizardCount - 1 > 0) {

            newArr = new StudentWizard[wizardCount - 1];
            int j = 0;
            for (int i = 0; i < wizardCount; i++) {
                  if (i == idx) continue;
                  newArr[j].name = wizards[i].name;
                  newArr[j].house = wizards[i].house;
                  newArr[j].potions = wizards[i].potions;
                  newArr[j].potionCount = wizards[i].potionCount;
                  j++;
            }

      }

      delete[] wizards;
      wizards = newArr;
      wizardCount--;
      cout << "Removed student wizard " << name << "." << endl;
}

void WizardPotionInventorySystem::brewPotion(const string studentName,
            const string potionName,
            const int strength) {

      int idx = findWizard(studentName);
      if (idx == -1) {
            cout << "Cannot brew potion. Student wizard " << studentName << " does not exist." << endl;
            return;
      }

      if (findPotion(idx, potionName) != -1) {
            cout << "Cannot brew potion. Potion already exists in potion inventory of " << studentName << "." << endl;
            return;
      }
      
      Potion* newPotions = new Potion[wizards[idx].potionCount + 1];
      for (int i = 0; i < wizards[idx].potionCount; i++) {
            newPotions[i] = wizards[idx].potions[i];
      }

      newPotions[wizards[idx].potionCount].potionName = potionName;
      newPotions[wizards[idx].potionCount].strength = strength;

      delete[] wizards[idx].potions;
      wizards[idx].potions = newPotions;
      wizards[idx].potionCount++;

      cout << "Brewed potion " << potionName << " for student wizard " << studentName << "." << endl;
}

void WizardPotionInventorySystem::discardPotion(const string studentName,
                  const string potionName) {
      int idx = findWizard(studentName);
      if (idx == -1) {
            cout << "Cannot discard potion. Student wizard " << studentName << " does not exist." << endl;
            return;
      }
      int pIdx = findPotion(idx, potionName);

      if (pIdx == -1) {
            cout << "Cannot discard potion. Potion does not exist in potion inventory of " << studentName << "." << endl;
            return;
      }

      Potion* newPotions = nullptr;
      if (wizards[idx].potionCount - 1 > 0) {
            newPotions = new Potion[wizards[idx].potionCount - 1];
            int j = 0;
            for (int i = 0; i < wizards[idx].potionCount; i++) {
                  if (i == pIdx) continue;
                  newPotions[j++] = wizards[idx].potions[i];
            }
      }

      delete[] wizards[idx].potions;
      wizards[idx].potions = newPotions;
      wizards[idx].potionCount--;
      cout << "Discarded potion " << potionName << " from student wizard " << studentName << "." << endl;
}

void WizardPotionInventorySystem::transferPotion(const string potionName,
                  const string fromStudent,
                  const string toStudent) {

      int fromIdx = findWizard(fromStudent);
      int toIdx = findWizard(toStudent);
      if (fromIdx == -1 || toIdx == -1) {
            cout << "Cannot transfer potion. One or both student wizards do not exist." << endl;
            return;
      }

      int pIdx = findPotion(fromIdx, potionName);
      if (pIdx == -1) {
            cout << "Cannot transfer potion. Potion does not exist in potion inventory of " << fromStudent << "." << endl;
            return;
      }

      if (findPotion(toIdx, potionName) != -1) {
            cout << "Cannot transfer potion. Potion already exists in potion inventory of " << toStudent << "." << endl;
            return;
      }

      // save potion data
      Potion p = wizards[fromIdx].potions[pIdx];
      // add to toStudent
      Potion* newPotions = new Potion[wizards[toIdx].potionCount + 1];
      for (int i = 0; i < wizards[toIdx].potionCount; i++) {
            newPotions[i] = wizards[toIdx].potions[i];
      }

      newPotions[wizards[toIdx].potionCount] = p;
      delete[] wizards[toIdx].potions;
      wizards[toIdx].potions = newPotions;
      wizards[toIdx].potionCount++;

      // remove from fromStudent
      Potion* newFromPotions = nullptr;
      if (wizards[fromIdx].potionCount - 1 > 0) {
            newFromPotions = new Potion[wizards[fromIdx].potionCount - 1];
            int j = 0;
            for (int i = 0; i < wizards[fromIdx].potionCount; i++) {
                  if (i == pIdx) continue;
                  newFromPotions[j++] = wizards[fromIdx].potions[i];
            }
      }

      delete[] wizards[fromIdx].potions;
      wizards[fromIdx].potions = newFromPotions;
      wizards[fromIdx].potionCount--;
      cout << "Transferred potion " << potionName << " from " << fromStudent << " to " << toStudent << "." << endl;
}

void WizardPotionInventorySystem::showAllStudentWizards() const {
      cout << "Student wizards in the system:" << endl;

      if (wizardCount == 0) {
            cout << "None" << endl;
            return;
      }

      // Sort alphabetically (selection sort)
      int* order = new int[wizardCount];
      for (int i = 0; i < wizardCount; i++) order[i] = i;

      for (int i = 0; i < wizardCount - 1; i++) {
            for (int j = i + 1; j < wizardCount; j++) {
                  if (wizards[order[i]].name > wizards[order[j]].name) {
                        int tmp = order[i]; order[i] = order[j]; order[j] = tmp;
                  }
            }
      }

      for (int i = 0; i < wizardCount; i++) {
            int idx = order[i];
            cout << wizards[idx].name << ", House: " << wizards[idx].house
            << ", " << wizards[idx].potionCount << " potion(s), "
            << getTotalStrength(idx) << " total strength." << endl;
      }

      delete[] order;
}

void WizardPotionInventorySystem::showStudentWizard(const string name) const {
      int idx = findWizard(name);
      
      if (idx == -1) {
            cout << "Student wizard " << name << " does not exist." << endl;
            return;
      }

      cout << "Student wizard:" << endl;
      cout << wizards[idx].name << ", House: " << wizards[idx].house
      << ", " << wizards[idx].potionCount << " potion(s), "
      << getTotalStrength(idx) << " total strength." << endl;
      cout << "Potions:" << endl;

      for (int i = 0; i < wizards[idx].potionCount; i++) {
            cout << wizards[idx].potions[i].potionName << ", strength "
            << wizards[idx].potions[i].strength << "." << endl;
      }
}

void WizardPotionInventorySystem::showPotion(const string potionName) const {
      
      // Collect wizards who have it, sorted alphabetically
      int* found = new int[wizardCount];
      int foundCount = 0;

      for (int i = 0; i < wizardCount; i++) {
            if (findPotion(i, potionName) != -1) {
                  found[foundCount++] = i;
            }
      }

      if (foundCount == 0) {
            cout << "Potion " << potionName << " does not exist." << endl;
            delete[] found;
            return;
      }

      // Sort found alphabetically
      for (int i = 0; i < foundCount - 1; i++) {
            for (int j = i + 1; j < foundCount; j++) {
                  if (wizards[found[i]].name > wizards[found[j]].name) {
                        int tmp = found[i]; found[i] = found[j]; found[j] = tmp;
                  }
            }
      }

      cout << "Potion \"" << potionName << "\" found in " << foundCount << " student wizard(s):" << endl;
      for (int i = 0; i < foundCount; i++) {
            int wIdx = found[i];
            int pIdx = findPotion(wIdx, potionName);
            cout << i + 1 << ". " << wizards[wIdx].name << ", strength "
            << wizards[wIdx].potions[pIdx].strength << "." << endl;
      }
      
      delete[] found;
}