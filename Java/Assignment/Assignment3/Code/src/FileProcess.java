
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcess {
	public static PatientDAO patientDao;
	static ArrayList<String> inputCommentLineByLine;

	public static void readINput(String inputFileFromTerminal) throws FileNotFoundException {
		// Read and save Input Comment and execute ordinarly
		File file = new File(inputFileFromTerminal);
		// File file = new File(inputFileFromTerminal);
		try (Scanner inputFileScanner = new Scanner(file)) {
			inputCommentLineByLine = new ArrayList<String>();
			while (inputFileScanner.hasNextLine()) {
				String inputCommentLine = inputFileScanner.nextLine();
				inputCommentLineByLine.add(inputCommentLine);
			}
		}
		// Working Line by Line
		patientDao = new PatientDAOIMp();
		for (String inp : inputCommentLineByLine) {
			processInput(inp, patientDao);
		}
		// No Problem
		System.out.println("Successfully");
	}

	public static void processInput(String s, PatientDAO patientDao) {
		// s is a input line from input.txt
		// I split because first word is important
		String[] arrayInputLineForFirstWord = s.trim().split(" ", 2);
		// I create Object from PatientDaoImp for Data Access Object Pattern

		// Control first word and execute what it want
		if (arrayInputLineForFirstWord[0].matches("AddPatient")) {
			patientDao.addPatient(s);
		} else if (arrayInputLineForFirstWord[0].matches("RemovePatient")) {
			patientDao.deletePatient(s);

		} else if (arrayInputLineForFirstWord[0].matches("CreateAdmission")) {
			patientDao.createAdmission(s);

		} else if (arrayInputLineForFirstWord[0].matches("ListPatients")) {
			patientDao.listPatients();

		}

		else if (arrayInputLineForFirstWord[0].matches("AddExamination")) {
			patientDao.addExamination(s);

		} else if (arrayInputLineForFirstWord[0].matches("TotalCost")) {
			patientDao.totalCost(s);
		}

		else {
			System.out.println("Your request could not be detected.");
		}
	}

}
