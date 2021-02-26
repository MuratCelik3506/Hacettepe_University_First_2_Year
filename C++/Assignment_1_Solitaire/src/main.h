//
// Created by murat on 11/16/2020.
//

#ifndef SOLITAIRE_MAIN_H
#define SOLITAIRE_MAIN_H
#include <iostream>
#include <fstream>
#include <string>
#include "Card.h"
#include "Game.h"
#include "Write.h"
#include "Command.h"
using namespace std;
//****************************************
// program arguments from the command line
//****************************************
ifstream MyDeckFile;
ifstream MyCommandFile;
ofstream MyOutputFile;


void createTable();


void getCommand(const string& data);
 string splitArray[5];
int& returnFoundationIndex(char type);
int& returnPileIndex(int numberReturn);
Card& returnFoundation(char type);
Card& returnPile(int numberReturn);
bool correctMove( Card one,  Card two);


// Global object
 Game game;
 Write wrt;
 Command cmd;

#endif //SOLITAIRE_MAIN_H

