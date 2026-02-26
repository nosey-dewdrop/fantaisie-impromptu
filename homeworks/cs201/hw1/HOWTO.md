# CS 201 - Homework 1: WizardPotionInventorySystem

**Student:** Damla  
**Course:** CS 201, Spring 2026  

---

## Files

- `WizardPotionInventorySystem.h` — Class interface
- `WizardPotionInventorySystem.cpp` — Class implementation

## How to Compile Locally

```bash
g++ -Wall -c WizardPotionInventorySystem.cpp
```

To compile with a test file:
```bash
g++ -Wall -o test main_test.cpp WizardPotionInventorySystem.cpp
./test
```

---

## Dijkstra Workflow

### 1. Send Files to Dijkstra (from Mac terminal)
```bash
scp /Users/damummyphus/fantaisie-impromptu/homeworks/cs201/hw1/WizardPotionInventorySystem.h \
    /Users/damummyphus/fantaisie-impromptu/homeworks/cs201/hw1/WizardPotionInventorySystem.cpp \
    su.bilge@dijkstra.cs.bilkent.edu.tr:~/
```

### 2. Connect to Dijkstra
```bash
ssh su.bilge@dijkstra.cs.bilkent.edu.tr
```
> Password: Bilkent email password (nothing appears on screen while typing, that's normal)  
> VPN must be on if you're off-campus

### 3. Build Tests
```bash
make -f /home/cs/saksoy/cs201/2026Spring/hw1/Makefile build
```

### 4. Run Tests
```bash
make -f /home/cs/saksoy/cs201/2026Spring/hw1/Makefile tests
```
> Every test should end with `[ PASSED ] 1 test.` ✅

### 5. Check Memory Leaks (Valgrind)
```bash
# Single test
valgrind --leak-check=full ./test5

# All tests at once
for i in 1 2 3 4 5; do
    echo "=== test$i ==="
    valgrind --leak-check=full ./test$i 2>&1 | tail -4
done
```
> You should see:
> ```
> All heap blocks were freed -- no leaks are possible
> ERROR SUMMARY: 0 errors from 0 contexts
> ```

### 6. Exit Dijkstra
```bash
exit
```

### 7. Zip and Submit to Moodle (from Mac terminal)
```bash
cd /Users/damummyphus/fantaisie-impromptu/homeworks/cs201/
zip sec1-Damla-SuBilge-22303635.zip hw1/WizardPotionInventorySystem.h hw1/WizardPotionInventorySystem.cpp
```
> ⚠️ Do NOT include any file with a `main` function in the zip!

---

> **Every time you change the code:** start from Step 1 again.

## Description

A potion inventory management system for Bilkent Academy of Wizardry. Each student wizard has a dynamically allocated potion inventory. All data structures use raw dynamic arrays (`new`/`delete[]`) with no STL containers.

### Supported Operations
- `addStudentWizard` — Add a new wizard to the system
- `removeStudentWizard` — Remove a wizard and discard all their potions
- `brewPotion` — Add a potion to a wizard's inventory
- `discardPotion` — Remove a potion from a wizard's inventory
- `transferPotion` — Move a potion from one wizard to another
- `showAllStudentWizards` — List all wizards alphabetically
- `showStudentWizard` — Show detailed info about a wizard
- `showPotion` — Show which wizards own a given potion