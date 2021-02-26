
#ifndef ASSIGNMENT5_TREENODE_H
#define ASSIGNMENT5_TREENODE_H

#include <string>

class treeNode
{
public:
    char letter;
    int count;
    std::string binaryCode = "";
    treeNode *leftNode = nullptr;
    treeNode *rightNode = nullptr;

};

#endif //ASSIGNMENT5_TREENODE_H
