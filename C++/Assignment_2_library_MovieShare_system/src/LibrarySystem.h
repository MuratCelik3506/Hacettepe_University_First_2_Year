#include <fstream>
#include "CircDoublyUserList.h"
#include "CircLinkedList.h"


class LibrarySystem {
public:
    std::ofstream writeOut;
    CircDoublyUserList userList;
    CircLinkedList nonCheckList;

    LibrarySystem();
    ~LibrarySystem();

    void addMovie(const int movieId, const std::string movieTitle, const int year);
    void deleteMovie(const int movieId);

    void addUser(const int userId, const std::string userName);
    void deleteUser(const int userId);

    void checkoutMovie(const int movieId, const int userId);
    void returnMovie(const int movieId);

    void showAllMovies();
    void showMovie(const int movieId);
    void showUser(const int userId);

};


