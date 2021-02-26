//
// Created by murat on 11/17/2020.
//

#ifndef SOLITAIRE_GAME_H
#define SOLITAIRE_GAME_H
class Game{
public:

    Card fullDeck[52];
    int indexfullDeckindex = 0;
//*******************************
//*******  Pile Deck  ***********
//*******************************
    Card pileOne [13];
    int pileOneindex = 0;

    Card pileTwo  [14];
    int pileTwoindex = 0;

    Card pileThree [15];
    int pileThreeindex = 0;

    Card pileFour [16];
    int pileFourindex = 0;

    Card pileFive [17];
    int pileFiveindex = 0;

    Card pileSix [18];
    int pileSixindex = 0;

    Card pileSeven [19];
    int pileSevenindex = 0;

//*******************************
//******** Waste Deck ***********
//*******************************
    class Card waste [24];
    int wasteindex = 0;
    int wasteStatement = 0;

//*******************************
//***** Foundation Deck *********
//*******************************

    class Card foundationOneH[13];
    int foundationOneHindex = 0;
    class Card foundationTwoD[13];
    int foundationTwoDindex  = 0;
    class Card foundationThreeS[13];
    int foundationThreeSindex = 0;
    class Card foundationFourC  [13];
    int foundationFourCindex = 0;

};
#endif //SOLITAIRE_GAME_H
