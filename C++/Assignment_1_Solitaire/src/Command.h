//
// Created by murat on 11/17/2020.
//

#ifndef SOLITAIRE_COMMAND_H
#define SOLITAIRE_COMMAND_H

class Command{
public:
    int movePile(int sourcePile, int numberMove, int goalPile);
    int moveWastePile(int numberGoalPile);
    int moveFoundationWaste();
    int moveFoundationPile(int order);
    int openStock();
    int openHiddenCard(int number);
    void exitCommand();
};

#endif //SOLITAIRE_COMMAND_H
