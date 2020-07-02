import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Stack {
	// I created my own Stack class with using array
	// Stack method between 8-60
	// Other methods 60 - ...
	Integer[] array;
	int size = 0;
	Integer lastElement;

	// Constructors

	public Stack() {
		array = new Integer[0];

	}

	// add element
	public Integer push(Integer lastNum) {
		Integer[] newArray = new Integer[size + 1];
		newArray[0] = lastNum;
		for (int i = 0; i < size; i++) {
			newArray[i + 1] = array[i];
		}
		size++;
		array = newArray;
		lastElement = lastNum;
		return lastNum;
	}

	// delete element
	public Integer pop() {
		if (size == 0)
			return null;
		Integer num = array[0];
		Integer[] newArray = new Integer[size - 1];
		for (int i = 0; i < size - 1; i++) {
			newArray[i] = array[i + 1];
		}
		size--;
		array = newArray;
		return num;
	}

	// control stack size
	public boolean empty() {
		return !(size > 0);
	}

	// return last element
	public Integer peek() {
		if (size == 0)
			return null;
		return lastElement;
	}

	// Start Operation

	// remove element which greater than input number
	public void removeGreater(Integer num) {
		Stack newStack = new Stack();
		int timer = size;
		int sayac = 0;
		for (int i = 0; i < timer; i++) {
			Integer numPop = this.pop();
			if (num >= numPop) {
				newStack.push(numPop);
				sayac++;
			}
		}
		for (int i = 0; i < sayac; i++) {
			Integer numNew = newStack.pop();
			this.push(numNew);

		}

	}

	// calculate distance of numbers
	public int calculateDistance() {
		int sum = 0;
		Stack oldStack = new Stack();
		for (int i = size; i > 0; i--) {
			oldStack.push(array[i - 1]);
		}

		int timer = size;
		Stack newStack = new Stack();
		for (int i = 0; i < timer - 1; i++) {
			for (int m = 0; m < i; m++) {
				Integer popNum = oldStack.pop();
				newStack.push(popNum);
			}
			int numberOne = oldStack.pop();
			newStack.push(numberOne);
			for (int j = i + 1; j < timer; j++) {
				Integer numberTwo = oldStack.pop();
				newStack.push(numberTwo);
				sum += Math.abs(numberOne - numberTwo);
			}
			while (!newStack.empty()) {
				oldStack.push(newStack.pop());
			}
		}

		return sum;
	}

	// remove elements as the number of times
	public void addOrRemoveThatRemove(Integer numRemove) {
		for (int i = 0; i < Math.abs(numRemove); i++) {
			this.pop();
		}

	}

	// add elements as the number of times
	public void addOrRemoveThatAdd(Integer numAdd) {
		for (int i = 0; i < numAdd; i++) {
			Random randomBetweenFifty = new Random();
			Integer randomNumAdd = randomBetweenFifty.nextInt(50);
			this.push(randomNumAdd);

		}

	}

	// reverses the first k elements of stack
	public void reverse(Integer numReverse) {
		Queue reverseQueue = new Queue();
		for (int i = 0; i < numReverse; i++) {
			Integer num = this.pop();
			reverseQueue.add(num);
		}
		for (int i = 0; i < numReverse; i++) {
			Integer num = reverseQueue.poll();
			this.push(num);
		}

	}

	// how many distinct elements there are in stack
	public int distinct() {
		Stack newQueue = new Stack();
		Stack oldQueue = new Stack();
		int timer = size;
		for (int i = 0; i < timer; i++) {
			Integer num = this.pop();
			oldQueue.push(num);
		}
		for (int i = 0; i < timer; i++) {
			Integer num = oldQueue.pop();
			newQueue.push(num);
			this.push(num);
		}
		newQueue.sort();
		int sum = 0;
		Integer beforeNumber = -1;
		for (int i = 0; i < timer; i++) {
			Integer number = newQueue.pop();
			if (!(beforeNumber.equals(number))) {
				sum++;
			}
			beforeNumber = number;
		}
		return sum;
	}

	// sort Stack
	public void sort() {
		Stack newStackk = new Stack();
		while (!this.empty()) {
			Integer popElement = this.pop();

			while (!newStackk.empty() && newStackk.peek() > popElement) {

				this.push(newStackk.pop());
			}

			newStackk.push(popElement);
		}
		while (!newStackk.empty()) {
			this.push(newStackk.pop());
		}

	}

	@Override
	public String toString() {
		Stack tmpStack = new Stack();
		int timer = size;
		String line = "";
		for (int i = 0; i < timer; i++) {
			Integer num = this.pop();
			tmpStack.push(num);
			line += String.valueOf(num) + " ";
		}

		for (int i = 0; i < timer; i++) {
			this.push(tmpStack.pop());
		}
		return line;
	}

	// rewrite stack.txt
	public void endOfPRocess() {
		FileWriter stackDocument;
		try {
			stackDocument = new FileWriter("stack.txt");
			stackDocument.write(this.toString());
			stackDocument.close();
		} catch (IOException e) {
			System.out.println("Stack 208. line");
			e.printStackTrace();
		}

	}
}
