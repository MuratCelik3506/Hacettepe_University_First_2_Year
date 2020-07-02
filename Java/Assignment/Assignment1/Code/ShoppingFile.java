import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ShoppingFile {
	public static void inputFile(String fileShop, String filePrice) throws ParseException {

		try {
			// Create a object for Shopping List
			File shoppingListFile = new File(fileShop);
			Scanner myText = new Scanner(shoppingListFile);
			// Loop until end of text
			while (myText.hasNextLine()) {
				String[] shopListArrayName = myText.nextLine().split("\t");
				ShopListObj person1 = new ShopListObj();
				String[] nameSurname = shopListArrayName[0].split(" ");
				person1.setName(nameSurname[0]);
				person1.setSurname(nameSurname[1]);
				person1.setMembership(shopListArrayName[1]);
				Date dateNow = new SimpleDateFormat("dd.MM.yyyy").parse(shopListArrayName[2]);
				person1.setDate(dateNow);
				String[] productsAndQuantity = new String[(shopListArrayName.length - 3)];
				for (int i = 3; i < shopListArrayName.length; i++) {
					productsAndQuantity[(i - 3)] = shopListArrayName[i];
				}
				person1.setProduct(productsAndQuantity);
				// go to write a output
				WriteOutput.writeText(person1, filePrice);
			}
			// }

			myText.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}
