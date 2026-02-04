#ifndef PLAYER_H
#define PLAYER_H
#include <string>
using namespace std;

class Player{
      private:
            int playerID;
            string username;
            string* cards;
            int cardCount;
      public:
            Player();
            Player(int id, string& name);
            ~Player();

            Player(const Player& other);              
            Player& operator=(const Player& other); 
    
            int getID() const;
            string getUsername() const;
            string* getCards() const;
            int getCardCount() const;

            void addCard(const string& card);
            void removeCard(const string& card);
            bool hasCard(const string& card) const;
            void clearCards();
            void setCards(string* newCards, int count);
};
#endif

/*
* returns a string pointer. starting address for a dynamicaly allocated array. 
1. destructor.
2. copy constructor.
3. assignment operator.
*/