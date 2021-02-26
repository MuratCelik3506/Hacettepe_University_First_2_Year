
#ifndef ASSIGNMENT5_RECREATETREE_H
#define ASSIGNMENT5_RECREATETREE_H
#include <fstream>
#include <vector>
#include "treeNode.h"
#include <iostream>
#include <sstream>
#include <string>
using namespace std;


// read file and reCreate tree map
class reCreateTree{
public:
    vector<treeNode*> vDecodeTree;
    vector<string> splitArray;
    treeNode* newRootNode;

    void CreateTree(ifstream &myTreeFile);
    void splitWord(const string& str);
    };




#endif //ASSIGNMENT5_RECREATETREE_H
