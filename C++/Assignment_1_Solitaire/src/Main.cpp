#include "main.h"
//  Piazza Note : https://piazza.com/class/kfm7izkk4mh4c8?cid=42_f19
//  if I first run "dos2unix *" on our DEV server then "g++ -std=c++11 *.cpp -o main" Everything works as wonders!


// b21827263 Murat Celik - 203 Assignment 1
//***********************************************
//**************    MAIN    *********************
//***********************************************

int main(int argc, char *argv[]){
    MyDeckFile.open(argv[1]);
    string deckString;
    while (getline (MyDeckFile, deckString)) {
        Card newCard; // Read deck file and create object from Card class
        newCard.nameCard = deckString;
        string tmpOne =deckString.substr(1, 2);
        int newCardNum = stoi(tmpOne);
        newCard.typeCard = deckString[0];
        newCard.numCard = newCardNum;
        newCard.hidden = false;
        game.fullDeck[game.indexfullDeckindex] = newCard;
        game.indexfullDeckindex++;
    }
    MyDeckFile.close();
    MyOutputFile.open(argv[3]);
    createTable();
    MyOutputFile<<"\n";
    wrt.writeFile();
    MyCommandFile.open(argv[2]);
    string commandsString;
    while (getline (MyCommandFile, commandsString)) {
        MyOutputFile<<commandsString<<"\n";
        getCommand(commandsString);

        MyOutputFile<<"\n****************************************\n\n";
        wrt.writeFile();
    }
    MyCommandFile.close();

    // control winning
    if((game.foundationOneH[game.foundationOneHindex-1].nameCard == "H13") && (game.foundationTwoD[game.foundationTwoDindex-1].nameCard == "D13") &&
       (game.foundationThreeS[game.foundationThreeSindex-1].nameCard == "S13") && (game.foundationFourC[game.foundationFourCindex-1].nameCard == "C13") ){

        MyOutputFile<<"\n****************************************\n\n"<<
                    "You Win!\n\n"<<
                    "Game Over!\n";
        cout<<"DONE!";

        MyOutputFile.close();
        return 0;
    }
    MyOutputFile.close();
    cout<<"DONE!";
    return 0 ;
}

//**********************************************
//*************** Create Table *****************
//**********************************************
void def(int num){
    Card *PileArray = &returnPile(num);
    int *indexSource = &returnPileIndex(num);
    if(*indexSource<num+1){
        game.fullDeck[game.indexfullDeckindex-1].hidden = true;
        (*(PileArray + *indexSource)) = game.fullDeck[game.indexfullDeckindex-1];
        (*indexSource)++;
        game.indexfullDeckindex--;
        if(*indexSource==num+1){
            (*(PileArray + *indexSource-1)).hidden = false;
        }

    }
}
void createTable(){
    for(int i = 0 ; i <7;i++){
        def(0);
        def(1);
        def(2);
        def(3);
        def(4);
        def(5);
        def(6);
    }
}

//**********************************************
//*************** Write File *******************
//**********************************************
void Write::writeFile(){
    wrt.writeStock();
    wrt.writeWaste();
    MyOutputFile<<"        ";
    wrt.writeFoundation();
    MyOutputFile<<"\n\n";
    wrt.writePile();

}

void Write::writeStock(){
    if(game.indexfullDeckindex == 0){
        MyOutputFile<<"___ ";
    }
    else {
        MyOutputFile<<"@@@ ";
    }
}

bool Write::writeWaste(){
    if(game.wasteindex==0){
        MyOutputFile<<"___ ___ ___ ";
        return 1;
    }
    Card writeWaste[24];
    int writeWasteIndex = 0;
    Card tmp[24];
    int tmpIndex = 0;
    int i = 0;
    if(game.wasteindex != 0 && game.wasteStatement == 0){
        game.wasteStatement++;
    }
    while(i < game.wasteStatement){
        tmp[tmpIndex] = game.waste[game.wasteindex-1];
        writeWaste[writeWasteIndex] = game.waste[game.wasteindex-1];
        writeWasteIndex++;
        tmpIndex++;
        game.wasteindex--;
        i++;
    }
    while (tmpIndex != 0){
        game.waste[game.wasteindex] = tmp[tmpIndex-1];
        game.wasteindex++;
        tmpIndex--;
    }
    i = 0;
    while(i<3){
        if(writeWasteIndex != 0){
            MyOutputFile<<writeWaste[writeWasteIndex-1].nameCard<<" ";
            writeWasteIndex--;
        }
        else
            MyOutputFile<<"___ ";
        i++;
    }
    return true;
}

void Write::writeFoundation(){
    char array[] = {'H','D','S','C'};
    for(char x : array){
        Card *foundArray = &returnFoundation(x);
        int *indexFound = &returnFoundationIndex(x);
        if(*indexFound == 0)
            MyOutputFile<<"___ ";
        else
            MyOutputFile<<(*(foundArray+*indexFound-1)).nameCard << " ";
    }
}

bool writelastIndex(Card oldCard){
    if(oldCard.hidden){
        MyOutputFile<<"@@@";
        return true;
    }
    MyOutputFile<<oldCard.nameCard;
    return true;
}

void Write::writePile() {
    for (int i = 0; i < 19; i++) {
        if(game.pileOneindex<=i && game.pileTwoindex<=i && game.pileThreeindex <=i && game.pileFourindex <=i &&
           game.pileFiveindex <=i && game.pileSixindex <=i && game.pileSevenindex <=i)
            break;

        if(i>=game.pileOneindex){MyOutputFile<<"   ";}
        else{writelastIndex(game.pileOne[i] );}
        MyOutputFile<<"   ";
        if(i>=game.pileTwoindex){MyOutputFile<<"   ";}
        else{ writelastIndex(game.pileTwo[i]);}
        MyOutputFile<<"   ";
        if(i>=game.pileThreeindex){MyOutputFile<<"   ";}
        else{writelastIndex(game.pileThree[i]);}
        MyOutputFile<<"   ";
        if(i>=game.pileFourindex){MyOutputFile<<"   ";}
        else{writelastIndex(game.pileFour[i]);}
        MyOutputFile<<"   ";
        if(i>=game.pileFiveindex){MyOutputFile<<"   ";}
        else{writelastIndex(game.pileFive[i]);}
        MyOutputFile<<"   ";
        if(i>=game.pileSixindex){MyOutputFile<<"   ";}
        else{writelastIndex(game.pileSix[i]);}
        MyOutputFile<<"   ";
        if(i>=game.pileSevenindex){MyOutputFile<<"   ";}
        else{writelastIndex(game.pileSeven[i]);}

        MyOutputFile<<"\n";
    }
    MyOutputFile<<"\n";
}

//**********************************************
//************ COMMAND WORD CONTROL ************
//**********************************************

void split(const string& str){
    string word;
    int i = 0;
    for (auto x : str)
    {
        if (x == ' '){
            splitArray[i]=word;
            i++;
            word="";
        }
        else
            word += x;
    }
    splitArray[i]=word;
}

void getCommand(const string& data){
    split(data);
    if(splitArray[0] == "move"){
        if(splitArray[1] == "pile")
            cmd.movePile(stoi(splitArray[2]),stoi(splitArray[3]),stoi(splitArray[4]));
        else if(splitArray[1] == "waste")
            cmd.moveWastePile(stoi(splitArray[2]));
        else{
            if(splitArray[3] == "waste")
                cmd.moveFoundationWaste();
            else if(splitArray[3] == "pile")
                cmd.moveFoundationPile(stoi(splitArray[4]));
        }
    }

    else if(splitArray[0] == "open"){
        if(splitArray[1] == "from")
            cmd.openStock();
        else
            cmd.openHiddenCard(stoi(splitArray[1]));
    }
    else if(splitArray[0] == "exit"){
        cmd.exitCommand();
    }
}


//***************************************************
//************** COMMAND LIST ***********************
//***************************************************

// 1) open from stock
int Command::openStock(){
    if(game.indexfullDeckindex == 0){
        if(game.wasteindex == 0){
            MyOutputFile<<"\nInvalid Move!\n";
            return 1;
        }
        for(int i = game.wasteindex; i>0;i--){
            game.fullDeck[game.indexfullDeckindex] = game.waste[i-1];
            game.indexfullDeckindex++;
        }
        game.wasteindex = 0;
        return 0;
    }
    if(game.indexfullDeckindex<3){
        game.wasteStatement = game.indexfullDeckindex;
        for(int i = 0 ; i<game.wasteStatement;i++){
            game.waste[game.wasteindex] = game.fullDeck[game.indexfullDeckindex-1];
            game.indexfullDeckindex--;
            game.wasteindex++;
        }
        game.indexfullDeckindex =0;
        return 0;
    }
    for(int i = 0 ; i<3;i++){
        game.waste[game.wasteindex] = game.fullDeck[game.indexfullDeckindex-1];
        game.indexfullDeckindex--;
        game.wasteindex++;

    }
    game.wasteStatement = 3;
    return 0;
}
// 2) move to foundation [ pile <pile_num> ] [ waste ]
int Command::moveFoundationWaste(){
    if(game.wasteindex == 0){
        MyOutputFile<<"\nInvalid Move!\n";
        return 1;
    }
    Card* goalPileArray = &returnFoundation(game.waste[game.wasteindex-1].typeCard);
    int* indexGoal= &returnFoundationIndex(game.waste[game.wasteindex-1].typeCard);
    if((*indexGoal == 0 && game.waste[game.wasteindex-1].numCard != 1)){
        MyOutputFile<<"\nInvalid Move!\n";
        return 1;
    }
    else if((*indexGoal == 0 && game.waste[game.wasteindex-1].numCard == 1)){
        (*(goalPileArray+*indexGoal)) = game.waste[game.wasteindex-1];
        (*indexGoal)++;
        game.wasteindex--;
        game.wasteStatement--;
        return 0;
    }
    if(((*(goalPileArray+*indexGoal-1)).numCard + 1) == game.waste[game.wasteindex-1].numCard){

        (*(goalPileArray+*indexGoal)) = game.waste[game.wasteindex-1];
        (*indexGoal)++;
        game.wasteindex--;
        game.wasteStatement--;
        return 0;
    }
    MyOutputFile<<"\nInvalid Move!\n";
    return 1;
}

int Command::moveFoundationPile(int order) {
    Card *sourcePileArray = &returnPile(order);
    int *indexSource = &returnPileIndex(order);
    int *indexFoundationGoal = &returnFoundationIndex((*(sourcePileArray+*indexSource-1)).typeCard);
    Card *goalFoundationArray = &returnFoundation((*(sourcePileArray+*indexSource-1)).typeCard);

    if (*indexFoundationGoal == 0) {
        if ((*(sourcePileArray + *indexSource - 1)).numCard == 1) {
            *(goalFoundationArray) = *(sourcePileArray + *indexSource - 1);
            (*indexFoundationGoal)++;
            (*indexSource)--;
            return 0;
        }
    }
    if (*indexSource == 0) {
        MyOutputFile<<"\nInvalid Move!\n";
        return 1;
    }
    if ((*(goalFoundationArray+*indexFoundationGoal-1)).numCard + 1 != (*(sourcePileArray+*indexSource-1)).numCard) {
        MyOutputFile<<"\nInvalid Move!\n";
        return 1;
    }
    *(goalFoundationArray + *indexFoundationGoal) = *(sourcePileArray+*indexSource-1);
    (*indexFoundationGoal)++;
    (*indexSource)--;
    return 0;
}

/*3) move [ pile <source_pile_num> <source_pile_card_index> ] <destination_pile_num>
[ waste ] <destination_pile_num>
[ foundation <source_foundation_num> ] <destination_pile_num>*/

int Command::movePile(int sourcePile, int numberMove, int goalPile) {
    Card* sourcePileArray = &returnPile(sourcePile);
    Card* goalPileArray = &returnPile(goalPile);
    int* indexSource = &returnPileIndex(sourcePile);
    int* indexGoal = &returnPileIndex(goalPile);
    if ((*indexGoal == 0 && (*(sourcePileArray+*indexSource-1)).numCard == 13) ||
        correctMove(*(sourcePileArray+*indexSource-1- numberMove), *(goalPileArray+*indexGoal-1))) {
        for (int i = numberMove, j = 0; i >= 0; i--) {
            if (*indexSource == 0)
                return 1;
            *(goalPileArray + *indexGoal + j) = *(sourcePileArray+*indexSource-1-i);
            j++;
        }
        *indexGoal += (numberMove + 1);
        *indexSource -= (numberMove + 1);
        return 0;
    }
    MyOutputFile<<"\nInvalid Move!\n";
    return 1;
}

int Command::moveWastePile(int numberGoalPile){
    Card* goalPileArray = &returnPile(numberGoalPile);
    int* indexGoal= &returnPileIndex(numberGoalPile);
    if(*indexGoal == 0) {
        if (game.waste[game.wasteindex - 1].numCard == 13) {
            *(goalPileArray+*indexGoal) = game.waste[game.wasteindex - 1];
            (*indexGoal)++;
            game.wasteindex--;
            game.wasteStatement--;
            return 0;
        } else {
            MyOutputFile<<"\nInvalid Move!\n";
            return 1;
        }
    }
    else if(correctMove(game.waste[game.wasteindex-1],*(goalPileArray+*indexGoal-1))){
        *(goalPileArray+*indexGoal) = game.waste[game.wasteindex-1];
        (*indexGoal)++;
        game.wasteindex--;
        game.wasteStatement--;
        return 0;
    }
    else {
        MyOutputFile<<"\nInvalid Move!\n";
        return 1;
    }
}

// 4) open <pile_num>
int Command::openHiddenCard(int number){
    Card* goalPileArray = &returnPile(number);
    int* indexGoal= &returnPileIndex(number);
    if((*indexGoal == 0) || ((*(goalPileArray+*indexGoal-1)).hidden == false)){
        MyOutputFile<<"\nInvalid Move!\n";
        return 1;
    }
    (*(goalPileArray+*indexGoal-1)).hidden = false;
    return 0;
}

// 5) exit
void Command::exitCommand(){
        MyOutputFile<<"\n****************************************" << "\n\nGame Over!\n";
        MyOutputFile.close();
        cout<<"DONE!";
        exit(0);
    }

//*********************************************************
//*************** CORRECT MOVE CONTROL ********************
//*********************************************************

bool correctMoveNum(Card one, Card two) {
    return one.numCard+1 == two.numCard;
}

bool correctMove(  Card one,   Card two){
    if(one.typeCard == 'H' && (two.typeCard == 'C' || two.typeCard== 'S'))
        return correctMoveNum(one,two);
    else if(one.typeCard == 'D' && (two.typeCard == 'C' || two.typeCard == 'S'))
        return correctMoveNum(one,two);
    else if(one.typeCard == 'S' && (two.typeCard == 'H' || two.typeCard == 'D'))
        return correctMoveNum(one,two);
    else if(one.typeCard == 'C' && (two.typeCard == 'H' || two.typeCard == 'D'))
        return correctMoveNum(one,two);
    return false;
}

//*********************************************************
//********** RETURN CARD ARRAY WITH POINTER ***************
//*********************************************************

Card& returnPile(int numberReturn){
    switch(numberReturn){
        case 0: return game.pileOne[0];
        case 1: return game.pileTwo[0];
        case 2: return game.pileThree[0];
        case 3: return game.pileFour[0];
        case 4: return game.pileFive[0];
        case 5: return game.pileSix[0];
        case 6: return game.pileSeven[0];
        default:;
    }
    return game.pileSeven[0];
}

Card& returnFoundation(char type){
    switch(type){
        case 'H': return game.foundationOneH[0];
        case 'D': return game.foundationTwoD[0];
        case 'S': return game.foundationThreeS[0];
        case 'C': return game.foundationFourC[0];
        default:;

    }
    return game.foundationFourC[0];
}
int& returnPileIndex(int numberReturn){
    switch(numberReturn){
        case 0: return game.pileOneindex;
        case 1: return game.pileTwoindex;
        case 2: return game.pileThreeindex;
        case 3: return game.pileFourindex;
        case 4: return game.pileFiveindex;
        case 5: return game.pileSixindex;
        case 6: return game.pileSevenindex;
        default:;

    }
    return game.wasteindex;
}
int& returnFoundationIndex(char type){
    switch(type){
        case 'H': return game.foundationOneHindex;
        case 'D': return game.foundationTwoDindex;
        case 'S': return game.foundationThreeSindex;
        case 'C': return game.foundationFourCindex ;
        default:;

    }
    return game.foundationFourCindex;
}

