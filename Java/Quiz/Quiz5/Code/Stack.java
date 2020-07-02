
public class Stack {
	Integer[] array;
	int size = 0;
	public Stack() {
		array = new Integer[0];

	}

	public Integer push(Integer numberPush) {
		if (this.isfull()) {
			return 0;
		}
		Integer[] newArray = new Integer[size + 1];
		newArray[0] = numberPush;
		for (int i = 0; i < array.length; i++) {
			newArray[i + 1] = array[i];
		}
		size++;
		array = newArray;
		return numberPush;
	}

	public Integer pop() {
		Integer num = array[0];
		Integer[] newArray = new Integer[size - 1];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i + 1];
		}
		size--;
		array = newArray;
		return num;
	}

	public Integer top() {
		return array[0];
	}

	public boolean isempty() {
		return !(size > 0);
	}

	public boolean isfull() {
		return (size == 10);
	}

	public Integer size() {
		return size;
	}
}
