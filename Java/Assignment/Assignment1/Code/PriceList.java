import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PriceList {
	public static double selectLine(int deger, String product, ShopListObj shopList, String priceList)
			throws IOException, ParseException {
		// control priceList.txt
		String line = Files.readAllLines(Paths.get(priceList)).get(deger);
		String[] priceListArray = line.split("\t");
		PriceListObj myPrice = new PriceListObj();
		myPrice.setProductName(priceListArray[0]);
		myPrice.setMembershipList(priceListArray[1]);
		// convert string to date
		Date dateBefore = new SimpleDateFormat("dd.MM.yyyy").parse(priceListArray[2]);
		myPrice.setDateBefore(dateBefore);
		Date dateLast = new SimpleDateFormat("dd.MM.yyyy").parse(priceListArray[3]);
		myPrice.setDateaLast(dateLast);
		double price = Double.parseDouble(priceListArray[4]);
		myPrice.setPrice(price);
		// return a product of price
		return Control.controlPrice(deger, product, shopList, myPrice, priceList);
	}

}
