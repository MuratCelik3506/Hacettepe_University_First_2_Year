//
// Created by murat on 12/13/2020.
//

#ifndef ASSS3_CARGOTRUCK_H
#define ASSS3_CARGOTRUCK_H

#include "Packages.h"
#include <stack>

class CargoTruck : public Packages{ // truck carries packages
private:
    struct Mission{ // doublyLinkedList
        Mission* next;
        Mission* prev;
        Packages namePackage;
    };
    int powerTruck;
    Mission* head;
    int size;
public:
    CargoTruck();
    bool isEmpty();
    void removeElem(std::string popPackages, Packages &popPack);
    void pop(Packages &popPack) ;
    void add(Packages &newPackage);
    int sizeFunc();
};

CargoTruck::CargoTruck(){
    size = 0;
}
int CargoTruck::sizeFunc() {
    return size;
}

bool CargoTruck::isEmpty() {
    if(size==0)
        return true;
    return false;
}

void CargoTruck::pop(Packages &popPack) {
    if(!isEmpty()){
        size--;
        popPack = head->namePackage;
        head = head->next;
    }
}


void CargoTruck::removeElem(std::string popPackages, Packages &popPack){
    if(!isEmpty()) {
        int indices = stoi(popPackages);
        Mission* initial = head;

        for(int i = 0 ; i < indices;i++){
            initial = initial->next;
        }
        popPack = initial->namePackage;

        if(initial == head){
            head = head->next;
        }
        else if(initial->next == nullptr){
            initial->prev->next = nullptr;
        }
        else{
            initial->prev->next = initial->next;
            initial->next->prev = initial->prev;
        }

        size--;
    }

}
void CargoTruck::add(Packages &newPackage){
    Mission* newNode = new Mission();
    newNode->namePackage = newPackage;
    if(isEmpty()){
        head = newNode;
    }
    else{
        Mission* initial = head;
        while(initial->next != nullptr){
            initial = initial->next;
        }
        newNode->prev = initial;
        initial->next = newNode;
    }
    size++;

}

#endif //ASSS3_CARGOTRUCK_H
