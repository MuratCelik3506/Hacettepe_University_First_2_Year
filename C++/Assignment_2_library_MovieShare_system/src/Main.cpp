//******************************************
//******** Murat Celik 21827263 ************
//******************************************
//*** dos2unix required  *******


#include "LibrarySystem.h"
#include <sstream>
using namespace std;


std::ifstream commandsText;
string splitArray[4];
string previousComnd ;

LibrarySystem librarySytem;

int getCommand(string commandLine);


//******************************************
//******** main function ************
//******************************************
int main(int argc, char** argv) {

    std::string fileCommand = argv[1];// first argument
    std::string fileOut = argv[2];// second argument

    librarySytem.writeOut.open(fileOut); // create output
    commandsText.open(fileCommand); // open command file
    librarySytem.writeOut<<"===Movie Library System===\n";

    std::string commandString;
    while (getline (commandsText, commandString)) 
        getCommand(commandString);


    commandsText.close();
    librarySytem.writeOut.close();
    return 0;
}



void chooseCommandWord(string str);  // read command line word by word

int getCommand(string commandLine){
    chooseCommandWord(commandLine);
    if(splitArray[0] =="addMovie" ){
        if(previousComnd != "addMovie")
            librarySytem.writeOut<<"\n===addMovie() method test===\n";
        librarySytem.addMovie(stoi(splitArray[1]),splitArray[2],stoi(splitArray[3]));

    }
    else if(splitArray[0] =="deleteMovie" ){
        if(previousComnd != "deleteMovie")
            librarySytem.writeOut<<"\n===deleteMovie() method test===\n";
        librarySytem.deleteMovie(stoi(splitArray[1]));
    }
    else if(splitArray[0] =="addUser" ){
        if(previousComnd != "addUser")
            librarySytem.writeOut<<"\n===addUser() method test===\n";
        librarySytem.addUser(stoi(splitArray[1]),splitArray[2]);
    }
    else if(splitArray[0] =="deleteUser" ){
        if(previousComnd != "deleteUser")
            librarySytem.writeOut<<"\n===deleteUser() method test===\n";
        librarySytem.deleteUser(stoi(splitArray[1]));
    }
    else if(splitArray[0] =="checkoutMovie" ){
        if(previousComnd != "checkoutMovie")
            librarySytem.writeOut<<"\n===checkoutMovie() method test===\n";
        librarySytem.checkoutMovie(stoi(splitArray[1]),stoi(splitArray[2]));
    }
    else if(splitArray[0] =="returnMovie" ){
        if(previousComnd != "returnMovie")
            librarySytem.writeOut<<"\n===returnMovie() method test===\n";
        librarySytem.returnMovie(stoi(splitArray[1]));
    }
    else if(splitArray[0] =="showAllMovie" ){
        if(previousComnd != "showAllMovie")
            librarySytem.writeOut<<"\n===showAllMovie() method test===\n";
        librarySytem.showAllMovies();
    }
    else if(splitArray[0] =="showMovie" ){
        if(previousComnd != "showMovie")
            librarySytem.writeOut<<"\n===showMovie() method test===\n";
        librarySytem.showMovie(stoi(splitArray[1]));
    }
    else if(splitArray[0] =="showUser" ){
        if(previousComnd != "showUser")
            librarySytem.writeOut<<"\n===showUser() method test===\n";
        librarySytem.showUser(stoi(splitArray[1]));
    }
    previousComnd = splitArray[0];
    return 0;
}

void chooseCommandWord(string line){
    istringstream streamStr(line);
    string spltWord;
    int i = 0;
    while(getline(streamStr, spltWord, '\t'))
    {
        splitArray[i] = spltWord;
        i++;
    }
}