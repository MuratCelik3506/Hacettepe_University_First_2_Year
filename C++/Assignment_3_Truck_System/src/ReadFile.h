//
// Created by murat on 12/13/2020.
//

#ifndef ASSS3_READFILE_H
#define ASSS3_READFILE_H
#include <fstream>
#include "Queue.h"
#include "Stack.h"
#include "City.h"
#include <vector>

using namespace std;

ofstream output;
ifstream destsFile;
ifstream missionFile;
ifstream packFile;
ifstream truckFile;
string getLineString;

City* start;
City* mid;
City* target;

vector<string> splitVector;
vector<City*> cityArr;
string splitArray[6];

void splitINVector(const string& str, char r);
void splitINArray(const string& str, char r);
void display(string outFile);
void destFileFunc(string destFileSS);
void packagesFileFunc(string packagesFilesSS);
void truckFileFunc(string truckFileSS);
void missionFileFunc(string missionFileSS);
#endif //ASSS3_READFILE_H



//*****************************************
//    read file and create object
//*****************************************
// read dests file, create a city, save in cityArr
void destFileFunc(string destFileSS){
    destsFile.open(destFileSS);
    while (getline(destsFile, getLineString)) {
        City *newCity = new City();
        newCity->name = getLineString;
        cityArr.push_back(newCity);
    }    destsFile.close();
}


// read packages file, create a package, save package which city has package
void packagesFileFunc(string packagesFilesSS){
    packFile.open(packagesFilesSS);
    while (getline(packFile, getLineString)) {
        splitINArray(getLineString, ' '); //splitArray[0] means package code; splitArray[1] means city name
        Packages *newPack = new Packages();
        newPack->name = splitArray[0];
        City *currentCity;
        for (int i = 0; i < cityArr.size(); i++) {
            if (splitArray[1] == cityArr[i]->name) {
                currentCity = cityArr[i];
            }
        }
        currentCity->package.push(*newPack);
    }    packFile.close();
}

//read trucks file, create a truck, save truck which city has truck

void truckFileFunc(string truckFileSS){
    truckFile.open(truckFileSS);
    while (getline(truckFile, getLineString)) {
        splitINArray(getLineString, ' ');  //splitArray[0] means truck code; splitArray[1] means city name ; splitArray[0] means truck power
        CargoTruck *newTruck = new CargoTruck();
        newTruck->name = splitArray[0];
        City *currentCity;
        for (int i = 0; i < cityArr.size(); i++) {
            if (splitArray[1] == cityArr[i]->name) {
                currentCity = cityArr[i];
            }
        }
        currentCity->truck.enqueue(*newTruck);
    }    truckFile.close();
}

//*****************************************
//*****************************************

//*****************************************************
// operation file's name is mission, everything is happening here
//*****************************************************

void missionFileFunc(string missionFileSS){
    missionFile.open(missionFileSS);
    while (getline(missionFile, getLineString)) {
        splitVector.clear();
        splitINArray(getLineString, '-');
        splitINVector(splitArray[5], ',');

        for (int i = 0; i < cityArr.size(); i++) {  // assign start,mid,target station
            if (splitArray[0] == cityArr[i]->name) {
                start = cityArr[i];
            } else if (splitArray[1] == cityArr[i]->name) {
                mid = cityArr[i];
            } else if (splitArray[2] == cityArr[i]->name) {
                target = cityArr[i];
            }
        }
        // select a truck
        CargoTruck currentTruck;
        start->truck.getFront(currentTruck);
        start->truck.dequeue();

        int sizePop = stoi(splitArray[3]);
        for (int i = 0; i < sizePop; i++) {// take packages at start station
            Packages currentPack;
            start->package.getTop(currentPack);
            start->package.pop();
            currentTruck.add(currentPack);
        }

        sizePop = stoi(splitArray[4]);// take packages at mid station
        for (int i = 0; i < sizePop; i++) {
            Packages currentPack;
            mid->package.getTop(currentPack);
            mid->package.pop();
            currentTruck.add(currentPack);
        }

        for (int i = 0; i < splitVector.size(); i++) { // drop packages at mid station
            Packages zPack;
            currentTruck.removeElem(splitVector[i], zPack);
            mid->package.push(zPack);
            if(i != splitVector.size() - 1) {
                splitVector[i + 1] = to_string(stoi(splitVector[i + 1]) - 1 - i);
            }
        }

        int sizeTarget = currentTruck.sizeFunc();
        for (int i = 0; i < sizeTarget; i++) {//drop packages at target station
            Packages targetPack;
            while (!currentTruck.isEmpty()) {
                currentTruck.pop(targetPack);
                target->package.push(targetPack);
            }
        }
        target->truck.enqueue(currentTruck); //leave the truck at the station
    }
}





















// split functions
void splitINVector(const string& str, char r){
    string word;
    int i = 0;
    for (auto x : str)
    {
        if (x == r){
            splitVector.push_back(word);
            i++;
            word="";
        }
        else
            word += x;
    }
    splitVector.push_back(word);
}

void splitINArray(const string& str, char r){
    string word;
    int i = 0;
    for (auto x : str)
    {
        if (x == r){
            splitArray[i] = word;
            i++;
            word="";
        }
        else
            word += x;
    }
    splitArray[i] = word;

}

//write output method
void display(string outFile){
    output.open(outFile);

    for(int i = 0; i < cityArr.size();i++){
        City* current = cityArr[i];
        output<<current->name<<"\n";//cityName
        output<<"Packages:\n";//all packages
        Stack<Packages> tmpStack;
        while(!current->package.isEmpty()){
            Packages currentPack ;
            current->package.getTop(currentPack);
            tmpStack.push(currentPack);
            current->package.pop();
            output<<currentPack.name<<"\n";
        }
        while(!tmpStack.isEmpty()){
            Packages tmpPack;
            tmpStack.getTop(tmpPack);
            current->package.push(tmpPack);
            tmpStack.pop();
        }

        output<<"Trucks:\n";//all trucks
        int timer = current->truck.size();
        for(int k = 0 ; k<timer;k++){
            CargoTruck currentPack ;
            current->truck.getFront(currentPack);
            current->truck.dequeue();
            current->truck.enqueue(currentPack);
            output<<currentPack.name<<"\n";
        }
        output<<"-------------\n";
    }
    output.close();

}




