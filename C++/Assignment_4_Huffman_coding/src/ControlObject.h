//
// Created by murat on 1/1/2021.
//

#ifndef ASSIGNMENT5_CONTROLOBJECT_H
#define ASSIGNMENT5_CONTROLOBJECT_H

#include <iostream>
#include "Encode.h"
#include "List.h"
#include "Decode.h"
#include "treeNode.h"
#include "GiveChar.h"

using namespace std;

// my Admin class

class ControlObject {
public:
    void encodeMethod(int num, char** arr);
    void decodeMethod(int num, char** arr);
    void listMethod(int num, char** arr);
    void findCharMethod(int num, char** arr);
};


#endif //ASSIGNMENT5_CONTROLOBJECT_H
