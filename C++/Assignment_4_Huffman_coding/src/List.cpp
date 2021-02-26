#include "List.h"

int timer = 0;

void writeTree(treeNode* mainNode)
{
    if (mainNode == nullptr)
        return;

    if (!mainNode->leftNode && !mainNode->rightNode)
    {
        cout<<" ---- "<<mainNode->letter<<" => " << mainNode->count<<" => " << mainNode->binaryCode<<"\n";
        timer--;
        return;
    }
    else
        cout<<"  +-  "<<mainNode->count<<"\n";

    if (mainNode->leftNode){
        timer++;
        for(int i = 0 ; i < timer;i++)
            cout<<"\t|";
        writeTree(mainNode->leftNode);
    }
    if (mainNode->rightNode){
        timer++;
        for(int i = 0 ; i < timer;i++)
            cout<<"\t|";
        writeTree(mainNode->rightNode);
    }
    timer--;
}
