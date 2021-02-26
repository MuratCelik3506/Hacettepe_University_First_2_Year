/*Function templates are special functions that can operate
with generic types. This allows us to create a function
template whose functionality can be adapted to more than
one type or class without repeating the entire code for each type.*/

/*To use this function template we use the following format for the function call:

function_name <type> (parameters);

*/

template <typename T>
class Stack {
public:
    Stack();
    ~Stack();
    bool isEmpty() const;
    int size() const;

    //Push: Place item on top of the stack
    void push(const T& newItem);
    //Top: Take a look at the topmost item without removing it
    void getTop(T& stackTop) const;
    //Pop: Remove item from the top of the stack
    void pop();
        private:
    struct ListNode {
        T item;
        ListNode* next;
    };
    ListNode* _head;
    int _size;
};


template<typename T>
Stack<T>::Stack() {
    _size = 0;

}

template<typename T>
Stack<T>::~Stack() {

}

template<typename T>
bool Stack<T>::isEmpty() const {
    if(_size == 0)
        return true;
    return false;
}

template<typename T>
int Stack<T>::size() const {
    return _size;
}

template<typename T>
void Stack<T>::push(const T &newItem) {
    ListNode* newNode = new ListNode();
    newNode->item = newItem;
    if(isEmpty()){
        _head = newNode;
    }
    else{
        newNode->next = _head;
        _head = newNode;
    }
    _size++;
}

template<typename T>
void Stack<T>::getTop(T &stackTop) const {
        stackTop = _head->item;
}

template<typename T>
void Stack<T>::pop() {
    if(_head != nullptr){
        _head = _head->next;
        _size--;
    }
}

