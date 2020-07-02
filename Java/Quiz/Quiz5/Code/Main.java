import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String fileDocument = args[0];
		File file = new File(fileDocument);
		Scanner scan;
		try {
			scan = new Scanner(file);
		while(scan.hasNextLine()) {
			Integer num = scan.nextInt();
			Process.convertOctal(num);
		}
		scan.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully!");
	}


}
