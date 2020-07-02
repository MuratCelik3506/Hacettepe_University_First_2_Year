import java.io.FileWriter;
import java.io.IOException;

public class ReadFile {

	// command for Stack
	public static void commandLineS(Stack stack, String lineCommand) {

		String[] lineSplitArray = lineCommand.trim().split(" ");
		if (lineSplitArray[1].matches("removeGreater")) {
			stack.removeGreater(Integer.valueOf(lineSplitArray[2]));
			ReadFile.writeOutput("stackOut.txt",
					"After removeGreater " + lineSplitArray[2] + ":\n" + stack.toString() + "\n");

		} else if (lineSplitArray[1].matches("calculateDistance")) {
			int sum = stack.calculateDistance();
			ReadFile.writeOutput("stackOut.txt", "After calculateDistance: \n" + "Total distance=" + sum + "\n");

		} else if (lineSplitArray[1].matches("addOrRemove")) {
			if (Integer.valueOf(lineSplitArray[2]) < 0) {
				stack.addOrRemoveThatRemove(Integer.valueOf(lineSplitArray[2]));
				ReadFile.writeOutput("stackOut.txt",
						"After addOrRemove  " + lineSplitArray[2] + ":\n" + stack.toString() + "\n");

			} else if (Integer.valueOf(lineSplitArray[2]) > 0) {
				stack.addOrRemoveThatAdd(Integer.valueOf(lineSplitArray[2]));
				ReadFile.writeOutput("stackOut.txt",
						"After addOrRemove " + lineSplitArray[2] + ":\n" + stack.toString() + "\n");

			}
		} else if (lineSplitArray[1].matches("reverse")) {
			stack.reverse(Integer.valueOf(lineSplitArray[2]));
			ReadFile.writeOutput("stackOut.txt",
					"After reverse " + lineSplitArray[2] + ":\n" + stack.toString() + "\n");

		} else if (lineSplitArray[1].matches("sortElements")) {
			stack.sort();
			ReadFile.writeOutput("stackOut.txt", "After sortElements:\n" + stack.toString() + "\n");

		} else if (lineSplitArray[1].matches("distinctElements")) {
			int sum = stack.distinct();
			ReadFile.writeOutput("stackOut.txt", "After distinctElements:\n" + "Total distinct element=" + sum + "\n");

		}

	}

	// command for Queue
	public static void commandLineQ(Queue queue, String lineCommand) {

		String[] lineSplitArray = lineCommand.trim().split(" ");
		if (lineSplitArray[1].matches("removeGreater")) {
			queue.removeGreater(Integer.valueOf(lineSplitArray[2]));
			ReadFile.writeOutput("queueOut.txt",
					"After removeGreater " + lineSplitArray[2] + ":\n" + queue.toString() + "\n");

		} else if (lineSplitArray[1].matches("calculateDistance")) {
			int sum = queue.calculateDistance();
			ReadFile.writeOutput("queueOut.txt", "After calculateDistance: \n" + "Total distance=" + sum + "\n");

		} else if (lineSplitArray[1].matches("addOrRemove")) {
			if (Integer.valueOf(lineSplitArray[2]) < 0) {
				queue.addOrRemoveThatRemove(Integer.valueOf(lineSplitArray[2]));
				ReadFile.writeOutput("queueOut.txt",
						"After addOrRemove  " + lineSplitArray[2] + ":\n" + queue.toString() + "\n");

			} else if (Integer.valueOf(lineSplitArray[2]) > 0) {
				queue.addOrRemoveThatAdd(Integer.valueOf(lineSplitArray[2]));
				ReadFile.writeOutput("queueOut.txt",
						"After addOrRemove " + lineSplitArray[2] + ":\n" + queue.toString() + "\n");

			}
		} else if (lineSplitArray[1].matches("reverse")) {
			queue.reverse(Integer.valueOf(lineSplitArray[2]));
			ReadFile.writeOutput("queueOut.txt",
					"After reverse " + lineSplitArray[2] + ":\n" + queue.toString() + "\n");

		} else if (lineSplitArray[1].matches("sortElements")) {
			queue.sort();
			ReadFile.writeOutput("queueOut.txt", "After sortElements:\n" + queue.toString() + "\n");

		} else if (lineSplitArray[1].matches("distinctElements")) {
			int sum = queue.distinct();
			ReadFile.writeOutput("queueOut.txt", "After distinctElements:\n" + "Total distinct element=" + sum + "\n");

		}

	}

	// same method for each operation
	public static void writeOutput(String file, String line) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write(line);
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("ReadFile 98. line");
		}

	}

}
