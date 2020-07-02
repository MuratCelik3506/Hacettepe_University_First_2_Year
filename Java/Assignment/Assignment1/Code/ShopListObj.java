import java.util.Date;

public class ShopListObj {
	private String name;
	private String surname;
	private String membership;
	private Date date;
	private String[] Product;

	public ShopListObj() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String[] getProduct() {
		return Product;
	}

	public void setProduct(String[] product) {
		Product = product;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
