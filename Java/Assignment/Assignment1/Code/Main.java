import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// Get a input commons-line
		String fileShop = args[0]; 
		String filePrice = args[1];
		// Start a program
		ShoppingFile.inputFile(fileShop, filePrice);

	}

}