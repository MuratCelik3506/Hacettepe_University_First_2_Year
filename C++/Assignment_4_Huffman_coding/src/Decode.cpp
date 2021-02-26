#include "Decode.h"

void decode(ifstream &myDecodedFile, reCreateTree &tree){
    string inputText;
    while(getline(myDecodedFile,inputText)){
        treeNode* initial = tree.newRootNode;
        for(int i = 0 ; i < inputText.size() ; i++){

            if(inputText[i] == '0'){
                initial = initial->leftNode;
            }
            else if(inputText[i] == '1'){
                initial = initial->rightNode;
            }

            if (!initial->leftNode && !initial->rightNode)
            {
                if(initial->letter == '"'){
                    cout<<" ";
                }
                else
                    cout<<initial->letter;
                initial= tree.newRootNode;
            }
        }
    }


}