#include "ControlObject.h"

ofstream myFile;
ifstream readFile;

void ControlObject::encodeMethod(int num, char** arr){

        string  word ;
        string allWord;
        readFile.open(arr[2]);
        while(getline(readFile,word)){// read input file
            allWord = allWord + word;
        }
        readFile.close();
        transform(allWord.begin(), allWord.end(), allWord.begin(), ::tolower);  // all string should be lowerCase
        treeNode root;
        countFreq(allWord);                 // count frequency for each char
        createTree(root);               // create tree according to frequency
        chechBinaryCodeLeafNode(&root);    // all node has a binar code

        myFile.open("myTree.txt");
        printPreorder(&root,myFile);
        myFile.close();

        encodeText(allWord);  // display encoding result
}

void ControlObject::decodeMethod(int num, char** arr){
    class reCreateTree tree;

    readFile.open("myTree.txt"); // read previously saved tree map file
    tree.CreateTree(readFile);
    readFile.close();

    readFile.open(arr[2]);
    decode(readFile,tree); // display encoding result
    readFile.close();
}

void ControlObject::listMethod(int num, char** arr){
    class reCreateTree tree;
    readFile.open("myTree.txt"); // read previously saved tree map file
    tree.CreateTree(readFile);
    readFile.close();

    writeTree(tree.newRootNode);  // list tree map
}

void ControlObject::findCharMethod(int num, char** arr){
    class reCreateTree tree;
    readFile.open("myTree.txt"); // read previously saved tree map file
    tree.CreateTree(readFile);
    readFile.close();
    treeNode* initial = new treeNode;
    findNode(arr[2],tree.newRootNode,*initial);
    cout<<initial->binaryCode<<"\n"; // display code
}