import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlCheck {
	public static void readInput(String personnel, String monitoring) {
		try {
			File filepersonnel = new File(personnel);
			Scanner fileperson = new Scanner(filepersonnel);
			File filemonitoring = new File(monitoring);
			Scanner filemonitor = new Scanner(filemonitoring);
			// I save document lines in arrayList
			ArrayList<String> linespersonnel = new ArrayList<String>();
			ArrayList<String> linesmonitoring = new ArrayList<String>();

			while (filemonitor.hasNextLine()) {
				String liness = filemonitor.nextLine();
				linesmonitoring.add(liness);
			}
			while (fileperson.hasNextLine()) {
				String liness = fileperson.nextLine();
				linespersonnel.add(liness);
			}
			// And I close document
			fileperson.close();
			filemonitor.close();
			try {
				for (String lines : linespersonnel) {
					// creating object
					Personnel object = selectRegister(lines);
					for (String s : linesmonitoring) {
						// match a registration number and find to gain salary how much
						String[] array = s.split("\t");
						if (object.getRegisterNumber().matches(array[0])) {
							Integer[] arrayWeek = { Integer.valueOf(array[1]), Integer.valueOf(array[2]),
									Integer.valueOf(array[3]), Integer.valueOf(array[4]) };
							// same method for each position
							object.findSalary(arrayWeek, object);
							break;
						}
					}
					// create a new file for personnel information and salary
					writeOutput(object);

				}

			} catch (Exception e) {
				System.out.println("Exception in ControlCheck 30");
			}

		} catch (

		Exception e) {
			System.out.println("Exception in ControlCheck 24");
		}

	}

	public static void setAttiribute(Personnel object, String[] array) throws ParseException {
		// object attribute is setting
		String[] arrayName = array[0].split(" ");
		object.setName(arrayName[0]);
		object.setSurname(arrayName[1]);
		object.setRegisterNumber(array[1]);
		object.setPosition(array[2]);
		object.setYearOfStart(Integer.valueOf(array[3]));

	}

	public static Personnel selectRegister(String line) throws ParseException {
		// Created a object for each personnel orderly
		String[] array = line.split("\t");
		if ("FACULTY_MEMBER".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("F")) {
			Academician.Facultymembers facultMember = new Academician().new Facultymembers();
			setAttiribute(facultMember, array);
			return facultMember;
		} else if ("RESEARCH_ASSISTANT".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("R")) {
			Academician.Researchassistants researchassistants = new Academician().new Researchassistants();
			setAttiribute(researchassistants, array);
			return researchassistants;

		} else if ("WORKER".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("W")) {
			Employee.Fulltime.Worker worker = new Employee().new Fulltime().new Worker();
			setAttiribute(worker, array);
			return worker;

		} else if ("SECURITY".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("S")) {
			Security security = new Security();
			setAttiribute(security, array);
			return security;

		} else if ("OFFICER".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("O")) {
			Officer officer = new Officer();
			setAttiribute(officer, array);
			return officer;

		} else if ("CHIEF".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("C")) {
			Employee.Fulltime.Chief chief = new Employee().new Fulltime().new Chief();
			setAttiribute(chief, array);
			return chief;

		} else if ("PARTTIME_EMPLOYEE".matches(array[2]) || String.valueOf(array[1].charAt(0)).matches("P")) {
			Employee.Parttime parttime = new Employee().new Parttime();
			setAttiribute(parttime, array);
			return parttime;

		}
		return null;

	}

	public static void writeOutput(Personnel object) {
		// create and write a file and display personnel information
		try {
			FileWriter myWriter = new FileWriter(object.getRegisterNumber() + ".txt");
			myWriter.write("Name : " + object.getName() + "\n" + "Surname : " + object.getSurname() + "\n"
					+ "Registiration Number : " + object.getRegisterNumber() + "\n" + "Position : "
					+ object.getPosition() + "\n" + "Year of Start : " + String.valueOf(object.getYearOfStart()) + "\n"
					+ "Total Salary : " + String.format("%.2f", object.getSalary()) + " TL");
			System.out.println("Name : " + object.getName() + "\n" + "Surname : " + object.getSurname() + "\n"
					+ "Registration Number : " + object.getRegisterNumber() + "\n" + "Position : "
					+ object.getPosition() + "\n" + "Year of Start : " + String.valueOf(object.getYearOfStart()) + "\n"
					+ "Total Salary : " + String.format("%.2f", object.getSalary()) + " TL\n\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
