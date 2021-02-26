
#ifndef ASSIGNMENTTWO_CIRCLINKEDLIST_H
#define ASSIGNMENTTWO_CIRCLINKEDLIST_H

#include "Movie.h"
#include <iostream>

//***************************
//a circular singly linked list
//***************************

class CircLinkedList{
public:
    Movie data;
    CircLinkedList* ptr = nullptr;

    int pop(int num);
    int push(Movie &newMovie);
    Movie* getElement(int id);
    bool isEmpty();
    bool existMovie(int id);

};
#endif //ASSIGNMENTTWO_CIRCLINKEDLIST_H
