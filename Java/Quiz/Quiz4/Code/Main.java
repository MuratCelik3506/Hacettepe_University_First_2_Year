import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static Contact readLine(String line) {
		String[] lineContactArray = line.trim().split(" ");
		Contact contact = new Contact();
		contact.setSocialSecurityNumber(lineContactArray[3]);
		contact.setFirstName(lineContactArray[1]);
		contact.setLastName(lineContactArray[2]);
		contact.setPhoneNumber(lineContactArray[0]);
		return contact;
	}

	public static void writeDocument(Map<String, Contact> collection, String fileName) {

		try {
			FileWriter fileWriter = new FileWriter(fileName + ".txt");
			for (Map.Entry<String, Contact> entry : collection.entrySet()) {
				fileWriter.write(entry.getValue() + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeDocument(Collection<Contact> collection, String fileName) {

		try {
			FileWriter fileWriter = new FileWriter(fileName + ".txt");
			for (Contact contactForLoop : collection) {
				fileWriter.write(contactForLoop + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String contactsDocument = args[0];
		List<Contact> arrayList = new ArrayList<Contact>();
		Set<Contact> hashSet = new HashSet<Contact>();
		Set<Contact> treeSet = new TreeSet<Contact>();
		Set<Contact> treeSetLastName = new TreeSet<Contact>(new LastNameComparator());
		HashMap<String, Contact> hashMap = new HashMap<String, Contact>();
		Contact contact = new Contact();
		File fileContact = new File(contactsDocument);
		try {
			Scanner scanContact = new Scanner(fileContact);
			while (scanContact.hasNextLine()) {
				String line = scanContact.nextLine();
				contact = readLine(line);
				arrayList.add(contact);
				hashSet.add(contact);
				hashMap.put(contact.getPhoneNumber(), contact);
				treeSet.add(contact);
				treeSetLastName.add(contact);

			}
			scanContact.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		writeDocument(arrayList, "contactsArrayList");
		Collections.sort(arrayList, new LastNameComparator());
		writeDocument(arrayList, "contactsArrayListOrderByLastName");
		writeDocument(hashSet, "contactsHashSet");
		writeDocument(treeSet, "contactsTreeSet");
		writeDocument(treeSetLastName, "contactsTreeSetOrderByLastName");
		writeDocument(hashMap, "contactsHashMap");
	}

}
