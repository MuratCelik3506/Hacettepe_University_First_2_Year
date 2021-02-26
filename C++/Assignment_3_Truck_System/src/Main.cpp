// Murat Celik 21827263
//Assignment 3 BBM203

#include <string>
#include "ReadFile.h"
int main(int argc, char *argv[]) {
    // fifth argument for write output in file
    std::string destFileArgument = argv[1];
    std::string packageFileArgument = argv[2];
    std::string trucktFileArgument = argv[3];
    std::string missionFileArgument = argv[4];
    std::string outputFileArgument = argv[5];



    destFileFunc(destFileArgument);
    packagesFileFunc(packageFileArgument);
    truckFileFunc(trucktFileArgument);
    missionFileFunc(missionFileArgument);
    display(outputFileArgument); // write output by display method




    return 0;
}



