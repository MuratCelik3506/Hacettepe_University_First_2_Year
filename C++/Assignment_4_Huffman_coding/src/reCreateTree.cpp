#include "reCreateTree.h"

void reCreateTree::splitWord(const string& str){
    splitArray.clear();
    string word;
    int i = 0;
    for (auto x : str)
    {
        if (x == ' '){
            splitArray.push_back(word);
            i++;
            word="";
        }
        else
            word += x;
    }
    splitArray.push_back(word);
}


void reCreateTree::CreateTree(ifstream &myTreeFile){
    string text;
    int timer = 0;
    while (getline(myTreeFile,text)){
        splitWord(text);

        stringstream geek(splitArray[1]);
        int convertSplitOne = 0;
        geek >> convertSplitOne;

        if(timer == 0){
            newRootNode = new treeNode();
            char* letterRoot = &splitArray[0][0];
            newRootNode->letter =  *letterRoot;
            newRootNode->count =convertSplitOne;
            timer = 1;
            continue;
        }
        treeNode* newNode = new treeNode();
        char* letterRoot = &splitArray[0][0];
        newNode->letter =  *letterRoot;
        newNode->count = convertSplitOne;
        newNode->binaryCode = splitArray[2];
        treeNode* initial = newRootNode;
        for (int i = 0 ; i < splitArray[2].size() ; i++){
            if(i == splitArray[2].size()-1){
                if(splitArray[2][i] == '0'){
                    initial->leftNode = newNode;
                }
                else if(splitArray[2][i] == '1'){
                    initial->rightNode = newNode;
                }
                break;
            }
            if(splitArray[2][i] == '0'){
                initial = initial->leftNode;
            }
            else if(splitArray[2][i] == '1'){
                initial = initial->rightNode;
            }
        }
    }
}
