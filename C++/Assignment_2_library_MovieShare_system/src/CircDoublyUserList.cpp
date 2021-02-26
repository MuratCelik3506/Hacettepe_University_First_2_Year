#include <iostream>
#include "CircDoublyUserList.h"

// push pop function
int CircDoublyUserList::push(User &user){
    CircDoublyUserList* newNode = new CircDoublyUserList();
    newNode->data = user;
    if(this->next == nullptr){
        this->next  = newNode;
        this->prev  = newNode;
        newNode->next = newNode;
        newNode->prev = newNode;
        return 0;
    }
    this->next->prev->next = newNode;
    newNode->next = this->next;
    newNode->prev = (*(this->next)).prev;
    (*(this->next)).prev = newNode;
    return 0;
}

int CircDoublyUserList::pop(int num) {
    if(this->next == nullptr)
        return 0;
    if(this->next == this->next->next){
        CircDoublyUserList *initial = this->next;
        this->next = nullptr;
        this->prev = nullptr;
        delete initial;
        return 0;
    }
    if (this->next->data.id == num) {
        this->next = this->next->next;
        this->prev = this->next->next;
    }
    CircDoublyUserList *initial = this->next;
    while ((*initial).data.id != num) {
        initial = (*initial).next;
    }
    (*((*initial).prev)).next = ((*initial).next);
    (*((*initial).next)).prev = ((*initial).prev);
    delete initial;
    return 0;
}

// search element
CircDoublyUserList* CircDoublyUserList::getElement(int id){
    CircDoublyUserList* initial = this->next;
    while((*initial).data.id != id){
        initial = (*initial).next;
    }
    return initial;
}

// control function
bool CircDoublyUserList::isEmpty(){
    if(this->next == nullptr)
        return true;
    return false;
}

bool CircDoublyUserList::existUser(int id){
    if(this->next == nullptr)
        return false;
    CircDoublyUserList* initial = this->next;
    do{
        initial = initial->next;
        if((*initial).data.id == id)
            return true;
    }
    while (initial != this->next);
    return false;
}