import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Create Stack and Queue Objects
		Stack myStack = new Stack();
		Queue myQueue = new Queue();

		// get a input(stack.txt) and push Stack
		String dataStack;
		try {
			dataStack = Files.readAllLines(Paths.get("stack.txt")).get(0);
			String[] stackArrayStack = dataStack.trim().split(" ");
			for (int i = stackArrayStack.length; i > 0; i--) {
				myStack.push(Integer.valueOf(stackArrayStack[i - 1]));
			}
		} catch (IOException e1) {
			System.out.println("Main 23 stack.txt");
		}

		// get a input(queue.txt) and push Queue

		String dataqueue;
		try {
			dataqueue = Files.readAllLines(Paths.get("queue.txt")).get(0);
			String[] queueArrayqueue = dataqueue.trim().split(" ");
			for (int i = 0; i < queueArrayqueue.length; i++) {
				myQueue.add(Integer.valueOf(queueArrayqueue[i]));
			}
		} catch (IOException e1) {
			System.out.println("Main 36 queue.txt");
		}

		// get a input(command.txt) and write a loop
		String commandDoc = args[0];
		File fileCommand = new File(commandDoc);
		Scanner scan;
		try {
			scan = new Scanner(fileCommand);
			while (scan.hasNextLine()) {

				String line = scan.nextLine();
				String[] lineSplitArray = line.trim().split(" ");
				if (lineSplitArray[0].matches("S")) {
					ReadFile.commandLineS(myStack, line);

				}

				else if (lineSplitArray[0].matches("Q")) {
					ReadFile.commandLineQ(myQueue, line);

				}

			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Main 62 command.txt");
		}

		myStack.endOfPRocess();
		myQueue.endOfPRocess();
		System.out.println("Succesfully!");

	}

}
