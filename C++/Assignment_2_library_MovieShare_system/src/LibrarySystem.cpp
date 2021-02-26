#include "LibrarySystem.h"
using namespace std;

User* findUserbyMovieID(int movieID, CircDoublyUserList &userList){  // Which user have the movie?
    if(userList.isEmpty())
        return nullptr;
    CircDoublyUserList* currentLinkUser = userList.next;
    do{
        if((*currentLinkUser).data.haveMovies.existMovie(movieID))
            return &(*currentLinkUser).data;
        currentLinkUser = currentLinkUser->next;
    }while (currentLinkUser != userList.next);

    return nullptr;
}

bool existMovieUserHand(int movieId, CircDoublyUserList &list){  // Any user have the movie?
    if(findUserbyMovieID(movieId, list) == nullptr)
        return false;
    return true;
}
//**************************************
//**********  Constructor  *************
//**************************************
LibrarySystem::LibrarySystem(){}
LibrarySystem::~LibrarySystem(){}
//**************************************
//********** Movie Function ************
//**************************************
void LibrarySystem::addMovie(const int movieId, const std::string movieTitle, const int year){//İN USER HAND
    if(nonCheckList.existMovie(movieId) || existMovieUserHand(movieId, userList))
        writeOut<<"Movie "<<movieId<<" already exists\n";
	
    else{
        Movie* newMovie = new Movie();
        newMovie->id = movieId;
        newMovie->name = movieTitle;
        newMovie->year = year;
        nonCheckList.push(*newMovie);
        writeOut<<"Movie "<<newMovie->id<<" has been added\n";
    }
}
void LibrarySystem::deleteMovie(const int movieId){
    if(existMovieUserHand(movieId, userList)){
        (*findUserbyMovieID(movieId,userList)).haveMovies.pop(movieId);
        writeOut<<"Movie "<<movieId<<" has been checked out\nMovie "<<movieId<<" has been deleted\n";
    }
    else if(nonCheckList.existMovie(movieId)){
        nonCheckList.pop(movieId);
        writeOut<<"Movie "<<movieId<<" has not been checked out\nMovie "<<movieId<<" has been deleted\n";
    }
    else
        writeOut<<"Movie "<<movieId<<" does not exist\n";
}

//**************************************
//********** User Function ************
//**************************************
void LibrarySystem::addUser(const int userId, const std::string userName) {
    if (userList.existUser(userId))
        writeOut << "User " << userId << " already exists\n";
    else {
        User *newUser = new User();
        newUser->title = userName;
        newUser->id = userId;
        userList.push(*newUser);
        writeOut << "User " << userId << " has been added\n";
    }
}
void LibrarySystem::deleteUser(const int userId) {
    if (!userList.existUser(userId))
        writeOut << "User " << userId << " does not exist\n";
    else {
        userList.pop(userId);
                writeOut << "User " << userId << " has been deleted\n";
    }
}
//**************************************
//********** Show Function ************
//**************************************
void LibrarySystem::showAllMovies(){
    writeOut<<"Movie id - Movie name - Year - Status\n";
    if( !(nonCheckList.ptr == nullptr)){    //firstly show noncheck movies
        CircLinkedList* initial = nonCheckList.ptr;
        do{
            writeOut<<(*initial).data.id<<" "<<(*initial).data.name<<" "<<(*initial).data.year
                    <<" Not checked out"<<"\n";
            initial = (*initial).ptr;
        }while(initial != nonCheckList.ptr);
    }
    if(!(userList.next == nullptr)){       //secondly show noncheck movies
        CircDoublyUserList * initialize = userList.next;
        do{
            if( !((*initialize).data.haveMovies.ptr == nullptr)){
                CircLinkedList* initial = (*initialize).data.haveMovies.ptr;
                do{
                    writeOut<<(*initial).data.id<<" "<<(*initial).data.name<<" "<<(*initial).data.year<<
                            " Checked out by User "<<(*initialize).data.id<<"\n";
                    initial = (*initial).ptr;

                }while(initial != (*initialize).data.haveMovies.ptr);
            }
            initialize = initialize->next;
        }while(userList.next != initialize);
    }
}

void LibrarySystem::showMovie(const int movieId) {
    if(nonCheckList.existMovie(movieId)){
        Movie *movie = nonCheckList.getElement(movieId);
        writeOut << movie->id << " " << movie->name << " " << movie->year << " Not checked out\n";
    }
    else if(existMovieUserHand(movieId,userList)){
        User * currentUser = findUserbyMovieID(movieId,userList);
        Movie *currentMovie = currentUser->haveMovies.getElement(movieId);
        writeOut << currentMovie->id << " " << currentMovie->name << " "
                 << currentMovie->year << " Checked out by User "
                 << currentUser->id<< "\n";
    }
    else
        writeOut<<"Movie with the id "<<movieId<<" does not exist\n";
}

void LibrarySystem::showUser(const int userId){
	 if (userList.existUser(userId)){
    User *currentUSer = &userList.getElement(userId)->data;
    writeOut<<"User id: "<<(*currentUSer).id<<" User name: "<<(*currentUSer).title<<"\n";
    writeOut<<"User "<<(*currentUSer).id<<" checked out the following Movies:\n";
    if(!(*currentUSer).haveMovies.isEmpty()){
        writeOut<<"Movie id - Movie name - Year\n";
        CircLinkedList* initial = (*currentUSer).haveMovies.ptr;
        do{
            writeOut<<(*initial).data.id<<" "<<(*initial).data.name<<" "<<(*initial).data.year<<"\n";
            initial = (*initial).ptr;
        }while(initial != (*currentUSer).haveMovies.ptr);
    }}
	else{
		writeOut<< "User with the id " << userId << " does not exist" << "\n";

}
}
//**************************************
//********** Other Function ************
//**************************************
void LibrarySystem::checkoutMovie(const int movieId, const int userId) {
    if (!nonCheckList.existMovie(movieId))
        writeOut << "Movie " << movieId << " does not exist for checkout\n";
    else if(!userList.existUser(userId))
        writeOut << "User " << userId << " does not exist for checkout\n";
    else {
        Movie *currentmovie = nonCheckList.getElement(movieId);
        User *currentUser =  &userList.getElement(userId)->data;
        (*currentUser).haveMovies.push(*currentmovie);
        nonCheckList.pop(movieId);
        writeOut << "Movie " << movieId << " has been checked out by User " << userId << "\n";
    }
}

void LibrarySystem::returnMovie(const int movieId){
    if(nonCheckList.existMovie(movieId))
        writeOut<<"Movie "<<movieId <<" has not been checked out\n";
    else if(existMovieUserHand(movieId,userList)){
        writeOut<<"Movie "<<movieId <<" has been returned\n";
        User *currentUser = findUserbyMovieID(movieId,userList);
        nonCheckList.push( *currentUser->haveMovies.getElement(movieId));
       currentUser->haveMovies.pop(movieId);
    }
    else
        writeOut<<"Movie "<<movieId <<" not exist in the library\n";
}