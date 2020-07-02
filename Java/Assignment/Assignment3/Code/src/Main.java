import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		String inputDocument = args[0];
		try {
			FileProcess.readINput(inputDocument);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
