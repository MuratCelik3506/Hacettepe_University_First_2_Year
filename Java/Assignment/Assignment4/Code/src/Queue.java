import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Queue {
	// I created my own Queue class with using array
	// Queue method between 8-50
	// Other methods 50 - ...
	Integer[] array;
	int size = 0;
	Integer lastElement;

	// Constructors
	public Queue() {
		array = new Integer[0];

	}

	// add element
	public void add(Integer lastNum) {
		Integer[] newArray = new Integer[size + 1];
		for (int i = 0; i < size; i++)
			newArray[i] = array[i];
		newArray[size] = lastNum;
		array = newArray;
		size++;
	}

	// delete element
	public Integer poll() {
		if (this.size == 0)
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

	// return size
	public int size() {
		return size;
	}

	// return last element in Queue
	public Integer peek() {
		if (this.size == 0)
			return null;
		lastElement = array[size-1];
		return lastElement;

	}
	// Start Operation

	// remove element which greater than input number
	public void removeGreater(Integer valueOF) {
		Queue newQueue = new Queue();
		int sayac = this.size();
		for (int i = 0; i < sayac; i++) {
			Integer sayi = this.poll();
			if (valueOF >= sayi) {
				newQueue.add(sayi);
			}
			
		}
		while (!(newQueue.size() == 0)) {
			this.add(newQueue.poll());
		}

	}

	// calculate distance of numbers
	public int calculateDistance() {
		Queue reverseQueue = new Queue();
		int timer = this.size();
		for (int i = 0; i < timer; i++) {
			int num = this.poll();
			this.add(num);
			reverseQueue.add(num);
		}
		int sum = 0;
		for (int i = 0; i < timer - 1; i++) {
			Integer numberOne = reverseQueue.poll();
			for (int j = i; j < timer-1; j++) {
				Integer numberTwo = reverseQueue.poll();
				reverseQueue.add(numberTwo);
				sum += Math.abs(numberOne - numberTwo);
			}

		}

		return sum;

	}

	// remove elements as the number of times
	public void addOrRemoveThatRemove(Integer numRemove) {
		for (int i = 0; i < Math.abs(numRemove); i++) {
			this.poll();
		}

	}

	// add elements as the number of times
	public void addOrRemoveThatAdd(Integer numAdd) {
		for (int i = 0; i < numAdd; i++) {
			Random randomBetweenFifty = new Random();
			Integer randomNumAdd = randomBetweenFifty.nextInt(50);
			this.add(randomNumAdd);

		}

	}

	// reverses the first k elements of queue
	public void reverse(Integer valueOf) {
		Queue reverseQueue = new Queue();
		Stack reverseStack = new Stack();
		int timer = this.size();
		for (int i = 0; i < valueOf; i++) {
			Integer num = this.poll();
			reverseStack.push(num);
		}
		for (int i = valueOf; i < timer; i++) {
			int num = this.poll();
			reverseQueue.add(num);
		}

		for (int i = 0; i < valueOf; i++) {
			Integer num = reverseStack.pop();
			this.add(num);
		}
		for (int i = valueOf; i < timer; i++) {
			int num = reverseQueue.poll();
			this.add(num);
		}

	}

	// how many distinct elements there are in queue
	public int distinct() {
		Queue newQueue = new Queue();
		int timer = this.size();
		for (int i = 0; i < timer; i++) {
			Integer num = this.poll();
			this.add(num);
			newQueue.add(num);
		}
		newQueue.sort();
		int sum = 0;
		Integer beforeNumber = -1;
		for (int i = 0; i < timer; i++) {
			Integer number = newQueue.poll();
			if (!(beforeNumber.equals(number))) {
				sum++;
			}
			beforeNumber = number;
		}

		return sum;
	}

	// sort queue
	public void sort() {
		int timer = this.size();
		while (this.sortedVerify()) {
			if (timer % 2 == 0) {
				for (int i = 0; i < timer / 2; i++) {
					Integer numOne = this.poll();
					Integer numTwo = this.poll();
					if (numOne < numTwo) {
						this.add(numOne);
						this.add(numTwo);
					} else {
						this.add(numTwo);
						this.add(numOne);
					}
				}
				Integer num = this.poll();
				this.add(num);
				for (int j = 0; j < timer / 2 - 1; j++) {

					Integer numOne = this.poll();
					Integer numTwo = this.poll();
					if (numOne < numTwo) {
						this.add(numOne);
						this.add(numTwo);
					} else {
						this.add(numTwo);
						this.add(numOne);
					}
				}
				Integer numX = this.poll();
				this.add(numX);

			} else {
				Integer num = this.poll();
				this.add(num);
				for (int i = 0; i < (timer - 1) / 2; i++) {

					Integer numOne = this.poll();
					Integer numTwo = this.poll();
					if (numOne < numTwo) {
						this.add(numOne);
						this.add(numTwo);
					} else {
						this.add(numTwo);
						this.add(numOne);
					}
				}
				for (int j = 0; j < (timer - 1) / 2; j++) {

					Integer numOne = this.poll();
					Integer numTwo = this.poll();
					if (numOne < numTwo) {
						this.add(numOne);
						this.add(numTwo);
					} else {
						this.add(numTwo);
						this.add(numOne);
					}
				}
				Integer numX = this.poll();
				this.add(numX);

			}
		}
		for (int i = 0; i < timer; i++) {
			Integer num = this.poll();
			this.add(num);
		}

	}
	// sort verify queue

	public boolean sortedVerify() {
		int timer = this.size();
		int sayac = 0;
		Integer beforeNumber = -1;
		for (int i = 0; i < timer; i++) {
			Integer number = this.poll();
			this.add(number);
			if (beforeNumber > number) {
				sayac++;
			}
			beforeNumber = number;

		}
		if (sayac != 0) {
			return true;
		}
		return false;

	}

	@Override
	public String toString() {
		String line = "";
		int timer = this.size();
		for (int i = timer; i > 0; i--) {
			Integer num = this.poll();
			line += String.valueOf(num) + " ";
			this.add(num);
		}

		return line;
	}
	// rewrite queue.txt

	public void endOfPRocess() {
		FileWriter queueDocument;
		try {
			queueDocument = new FileWriter("queue.txt");
			queueDocument.write(this.toString());
			queueDocument.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
