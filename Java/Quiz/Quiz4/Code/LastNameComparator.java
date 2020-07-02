import java.util.Comparator;

public class LastNameComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact contactOne, Contact contactTwo) {
		return contactOne.getLastName().compareTo(contactTwo.getLastName());
	}

}
