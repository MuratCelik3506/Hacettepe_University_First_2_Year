import java.io.IOException;
import java.text.ParseException;

public class Control {
	// control every attribute
	public static double controlPrice(int order, String productControl, ShopListObj shopList, PriceListObj priceList,
			String priceText) throws IOException, ParseException {

		if (controlMembership(shopList, priceList) && controlDate(shopList, priceList)
				&& controlProduct(productControl, priceList)) {
			double price = priceList.getPrice();
			return price;
		} else {

			double price = PriceList.selectLine(order + 1, productControl, shopList, priceText);
			return price;
		}

	}

	// control gold-silver-bronze
	public static boolean controlMembership(ShopListObj shopList, PriceListObj priceList) {

		if (shopList.getMembership().equals(priceList.getMembershipList())) {

			return true;
		}
		return false;

	}

	// control Date
	public static boolean controlDate(ShopListObj shopList, PriceListObj priceList) {
		if (shopList.getDate().after(priceList.getDateBefore()) && shopList.getDate().before(priceList.getDateaLast())
				|| shopList.getDate().equals(priceList.getDateBefore())
				|| shopList.getDate().equals(priceList.getDateaLast())) {
			return true;
		}
		return false;
	}

	// control product
	public static boolean controlProduct(String product, PriceListObj priceList) {
		if (product.equals(priceList.getProductName())) {
			return true;
		}

		return false;

	}

}
