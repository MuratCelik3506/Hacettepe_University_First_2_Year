#include "GiveChar.h"

void findNode(string findChar, treeNode* root, treeNode& initial){

        if (root == NULL)
            return ;

        if (root->letter == findChar[0]){
                   initial = *root;
        }

        findNode(findChar,root->leftNode,initial);
        findNode(findChar,root->rightNode,initial);

}
