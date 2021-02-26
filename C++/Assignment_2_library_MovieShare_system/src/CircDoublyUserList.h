#include "User.h"

//***************************
//a circular doubly linked list
//***************************

class CircDoublyUserList {
public:
    CircDoublyUserList* prev = nullptr;
    User data;
    CircDoublyUserList* next = nullptr;

    int pop(int num);
    int push(User &newMovie);
    CircDoublyUserList* getElement(int id);
    bool isEmpty();
    bool existUser(int id);

};


