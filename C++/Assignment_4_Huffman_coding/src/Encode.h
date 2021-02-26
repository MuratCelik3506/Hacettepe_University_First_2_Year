
#ifndef ASSIGNMENT5_NODE_H
#define ASSIGNMENT5_NODE_H
#include <iostream>
#include <string>
#include "treeNode.h"
#include <vector>
#include <algorithm>
#include <fstream>

using namespace std;



void countFreq(string str) ;
void chechBinaryCodeLeafNode(struct treeNode* node);
void createTree(treeNode& rootNode);
void printPreorder(struct treeNode* node, ofstream &myFile);
void encodeText(string myText);
#endif //ASSIGNMENT5_NODE_H
