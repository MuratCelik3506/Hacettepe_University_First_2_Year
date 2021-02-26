#include "CircLinkedList.h"
// push pop function
int CircLinkedList::push(Movie &newMovie){
    CircLinkedList *newNode = new CircLinkedList();
    newNode->data = newMovie;
    if(this->ptr == nullptr){
        this->ptr  = newNode;
        newNode->ptr = newNode;
        return 0;
    }
    CircLinkedList* initial = this->ptr;
    while(this->ptr != (*initial).ptr){
        initial = &*(*initial).ptr;
    }
    (*initial).ptr = newNode;
    newNode->ptr = &*(this->ptr);
    return 0;
}

int CircLinkedList::pop(int id){
    if( this->ptr == ((*(this->ptr)).ptr)){
        this->ptr = nullptr;
        return 0;
    }
    if ((*(this->ptr)).data.id == id) {
        this->ptr = ((*(this->ptr)).ptr);
    }
    CircLinkedList *initial = (this->ptr);
    while ((*((*initial).ptr)).data.id != id) {
        initial = (*initial).ptr;
    }
    (*initial).ptr = (*((*initial).ptr)).ptr;

    return 0;
}
// search element
Movie* CircLinkedList::getElement(int id){
    CircLinkedList* initial = this->ptr;

    while((*initial).data.id != id){
        initial = initial->ptr;
    }
    return &initial->data;

}
// control function
bool CircLinkedList::isEmpty() {
if(this->ptr == nullptr)
    return true;
    return false;
}

bool CircLinkedList::existMovie(int id){
    if(this->ptr == nullptr){
        return false;
    }
    CircLinkedList* initial = this->ptr;
    do{
        initial = (*initial).ptr;
        if((*initial).data.id == id)
            return true;
    }
    while (initial != this->ptr);
    return false;
}
