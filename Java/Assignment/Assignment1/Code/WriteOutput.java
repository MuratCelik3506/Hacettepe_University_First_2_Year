import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class WriteOutput {
	public static void writeText(ShopListObj shopList, String priceList) throws NumberFormatException, ParseException {

		FileWriter writer = null;

		try {
			// create file or open exists file
			writer = new FileWriter("output.txt", true);
			// write a title
			System.out.println("---" + shopList.getName() + " " + shopList.getSurname() + "---");
			writer.write("---" + shopList.getName() + " " + shopList.getSurname() + "---\n");
			// initial a total price value
			double totalPrice = 0.0;
			for (int i = 0; i < (shopList.getProduct().length); i += 2) {
				// write a output
				writer.write(
						shopList.getProduct()[i] + "\t"
								+ PriceList.selectLine(0, shopList.getProduct()[i], shopList, priceList) + "\t"
								+ Integer.parseInt(shopList.getProduct()[i + 1]) + "\t"
								+ (Integer.parseInt(shopList.getProduct()[i + 1])
										* PriceList.selectLine(0, shopList.getProduct()[i], shopList, priceList))
								+ "\n");

				System.out.println(shopList.getProduct()[i] + "\t"
						+ PriceList.selectLine(0, shopList.getProduct()[i], shopList, priceList) + "\t"
						+ Integer.parseInt(shopList.getProduct()[i + 1]) + "\t"
						+ (Integer.parseInt(shopList.getProduct()[i + 1])
								* PriceList.selectLine(0, shopList.getProduct()[i], shopList, priceList)));
				totalPrice = totalPrice + (Integer.parseInt(shopList.getProduct()[i + 1])
						* PriceList.selectLine(0, shopList.getProduct()[i], shopList, priceList));
			}
			// write a total price
			System.out.println("Total\t" + totalPrice);
			writer.write("Total\t" + totalPrice + "\n");

		} catch (IOException ex) {
			System.out.println("Oh No ! IOException in WriteOutput class");
		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					System.out.println("Oh No ! IOException in WriteOutput class --- close mistake");
				}

			}
		}

	}

}
