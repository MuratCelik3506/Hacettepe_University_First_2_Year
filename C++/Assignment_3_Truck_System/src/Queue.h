/*Function templates are special functions that can operate
with generic types. This allows us to create a function
template whose functionality can be adapted to more than
one type or class without repeating the entire code for each type.*/

/*To use this function template we use the following format for the function call:

function_name <type> (parameters);
*/


template<typename T>
class Queue {
public:
    Queue();
    ~Queue();
    bool isEmpty() const;
    int size() const;
    //Enqueue: Items are added to the back of the queue
    void enqueue(const T& newItem);
    //Dequeue: Items are removed from the front of the queue
    void dequeue();
    //Get Front: Take a look at the first item
    void getFront(T& queueTop) const;
private:
    struct QueueNode {
        T item;
        QueueNode *next;
    };
    int _size;
    /* to avoid the traversal to the last node,
     an additional pointer to the last node is defined*/
    QueueNode *_lastNode;
};



template<typename T>
Queue<T>::Queue() {
_size =0;
}

template<typename T>
Queue<T>::~Queue() {

}

template<typename T>
bool Queue<T>::isEmpty() const {
    if(_size == 0)
        return true;
    return false;
}

template<typename T>
int Queue<T>::size() const {
    return _size;
}

template<typename T>
void Queue<T>::enqueue(const T &newItem) {
    QueueNode* newNode = new QueueNode();
    newNode->item = newItem;
    if(_lastNode == nullptr){
        _lastNode = newNode;
    }
    else{
        newNode->next = _lastNode;
        _lastNode = newNode;
    }
    _size++;
}

template<typename T>
void Queue<T>::dequeue() {
    QueueNode* initial = _lastNode;
    int i = 2;
    while(i < _size){
        initial = initial->next;
        i++;
    }
    initial->next = nullptr;
    _size--;
}

template<typename T>
void Queue<T>::getFront(T& queueTop) const{
    if(!isEmpty()){
        QueueNode* initial = _lastNode;
        for(int i = 1;i<_size;i++ ){
            initial = initial->next;
        }
        queueTop = initial->item ;
    }

}
