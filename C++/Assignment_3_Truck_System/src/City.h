
#include <string>
#include "Packages.h"
#include "CargoTruck.h"

class City{
public:
    std::string name;
    Stack<Packages> package;
    Queue<CargoTruck> truck;
    void display();

};

