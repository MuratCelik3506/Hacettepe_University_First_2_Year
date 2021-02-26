//***************************************************************
//              Murat Celik 21827263
//              BBM203 Assignment 4
//***************************************************************
//              g++ -std=c++11 -o Main *.cpp
//              ./Main -i encodeFile.txt -encode        encoding file and display on terminal
//              ./Main -l                               display tree map
//              ./Main -s <char>                        finding char and display char's binary code on terminal
//              ./Main -i decodeFile.txt -decode        decoding file and display on terminal
//***************************************************************


#include "ControlObject.h"


int main(int argc, char** argv){

    ControlObject controlPermission;   // all command decisions are made here

    if(argc == 4){
        string third = argv[3];
        if(third == "-encode")
            controlPermission.encodeMethod(argc,argv); // ./Main -i encodeFile.txt -encode
        else if(third == "-decode")
            controlPermission.decodeMethod(argc,argv); // ./Main -i decodeFile.txt -decode
    }
    else if(argc == 2)
        controlPermission.listMethod(argc,argv); // ./Main -l
    else if(argc == 3)
        controlPermission.findCharMethod(argc,argv);// ./Main -s <char>


}