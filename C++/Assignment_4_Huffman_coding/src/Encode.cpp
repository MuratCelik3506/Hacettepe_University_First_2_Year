#include "Encode.h"

vector<treeNode*> nodeArr;
vector<treeNode*> encode;
string code = "";

void countFreq(string str) {
    int l = str.size();
    bool truth ;
    for (int i = 0; i < l; i++) {
        char charWord = str[i];
        if(charWord == ' '){
            charWord = '"';
        }
        if(charWord == '\n'){
            charWord = '~';
        }
        truth = true;
        for(int j = 0 ; j < nodeArr.size();j++){
            if(charWord == nodeArr[j]->letter){
                nodeArr[j]->count++;
                truth = false;
                break;
            }
        }
        if(truth){
            treeNode* newArrayNode = new treeNode();
            newArrayNode->letter = charWord;
            newArrayNode->count = 1;
            nodeArr.push_back(newArrayNode);
        }
    }
}


void chechBinaryCodeLeafNode(struct treeNode* node)
{
    if (node == nullptr)
        return;
    node->binaryCode = code;

    if (!node->leftNode && !node->rightNode)
    {
        node->binaryCode = code;
        code = code.substr(0,code.size()-1);
        return;
    }

    if (node->leftNode){
        code = code + "0";
        chechBinaryCodeLeafNode(node->leftNode);

    }
    if (node->rightNode){
        code = code + "1";
        chechBinaryCodeLeafNode(node->rightNode);
    }
    code = code.substr(0,code.size()-1);

}

void createTree(treeNode& rootNode){

    while (nodeArr.size() != 1){

        sort(nodeArr.begin(), nodeArr.end(), [](const treeNode* lhs, const treeNode* rhs) {
            return  rhs->count <lhs->count ;
        });

        treeNode* newArrayNode = new treeNode();

        newArrayNode->letter = '_';
        newArrayNode->leftNode = nodeArr[nodeArr.size()-1];
        newArrayNode->rightNode = nodeArr[nodeArr.size()-2];
        newArrayNode->count = newArrayNode->leftNode->count + newArrayNode->rightNode->count;
        string array2 = "0";
        string array3 = "1";
        nodeArr[nodeArr.size()-1]->binaryCode = nodeArr[nodeArr.size()-1]->binaryCode + array2;
        nodeArr[nodeArr.size()-2]->binaryCode = nodeArr[nodeArr.size()-2]->binaryCode + array3;

        nodeArr.insert(nodeArr.begin(),newArrayNode);
        nodeArr.pop_back();
        nodeArr.pop_back();



    }

    rootNode = *nodeArr[0];
}


void printPreorder(struct treeNode* node, ofstream &myFile)
{
    if (node == nullptr)
        return;
    myFile<<""<< node->letter << " " << node->count<<" "<<node->binaryCode << "\n";
    encode.push_back(node);
    printPreorder(node->leftNode,myFile);
    printPreorder(node->rightNode,myFile);
}


void encodeText(string myText){

    for(int i = 0 ; i < myText.length();i++){
        for(int j = 0 ; j < encode.size();j++){
            if(' ' == myText[i]){
                if(encode[j]->letter == '"'){
                    cout<<""<< encode[j]->binaryCode;
                }
            }
            else if(encode[j]->letter == myText[i]){


                cout<<""<< encode[j]->binaryCode;
            }
        }

    }
}