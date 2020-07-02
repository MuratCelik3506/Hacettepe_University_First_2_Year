import java.io.FileWriter;
import java.io.IOException;

public class Process {
	static Stack stack;
	public static void convertOctal(Integer num) {
		stack = new Stack();
		while(!num.equals(0)) {
			Integer mod = num % 8;
			stack.push(mod);
		num = (num-mod) / 8;
		}
		
		
		
		try {
			FileWriter fileWriter = new FileWriter("octal.txt",true);
			while(!stack.isempty()) {
				fileWriter.write(String.valueOf(stack.pop()));
			}
			fileWriter.write("\n");
			
			
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("When output is written, make a mistake");
			e.printStackTrace();
		}
		
		System.out.println();
	}

}
