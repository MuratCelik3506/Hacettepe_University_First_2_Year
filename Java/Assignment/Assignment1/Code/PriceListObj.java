
import java.util.Date;

public class PriceListObj {
	private String productName;
	private String membershipList;
	private Date dateBefore;
	private Date dateaLast;
	private Double price;

	public PriceListObj() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMembershipList() {
		return membershipList;
	}

	public void setMembershipList(String membershipList) {
		this.membershipList = membershipList;
	}

	public Date getDateBefore() {
		return dateBefore;
	}

	public void setDateBefore(Date dateBefore) {
		this.dateBefore = dateBefore;
	}

	public Date getDateaLast() {
		return dateaLast;
	}

	public void setDateaLast(Date dateaLast) {
		this.dateaLast = dateaLast;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
