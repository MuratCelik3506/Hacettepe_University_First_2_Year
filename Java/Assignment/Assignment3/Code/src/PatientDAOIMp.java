import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PatientDAOIMp implements PatientDAO {
	static ArrayList<Patient> patientFileArrayListPatientDaoImpObject;
	

	// Constructors for save information in object and objectArrayList
	public PatientDAOIMp() {
		ArrayList<String> admisionFileArrayListConstructor = new ArrayList<String>();
		patientFileArrayListPatientDaoImpObject = new ArrayList<Patient>();
		try {
			File patientFileConstructor = new File("patient.txt");
			Scanner patientFileConstructorScanner = new Scanner(patientFileConstructor);
			while (patientFileConstructorScanner.hasNextLine()) {
				String patientFileConstructorLine = patientFileConstructorScanner.nextLine();
				Patient patient = (Patient) createObject(patientFileConstructorLine);
				patientFileArrayListPatientDaoImpObject.add(patient);
			}
			patientFileConstructorScanner.close();
		} catch (Exception e) {
			System.out.println("Exception founded in PatientDaoImp Constructor for patient.txt");

		}
		try {
			File admissionFileConstructor = new File("admission.txt");
			Scanner admisionFileConstructorScanner = new Scanner(admissionFileConstructor);
			while (admisionFileConstructorScanner.hasNextLine()) {
				String admisionFileConstructorLine = admisionFileConstructorScanner.nextLine();
				admisionFileArrayListConstructor.add(admisionFileConstructorLine);
			}
			admisionFileConstructorScanner.close();
		} catch (Exception e) {
			System.out.println("Exception founded in PatientDaoImp Constructor for admission.txt");

		}
		for (int i = 0; i < admisionFileArrayListConstructor.size(); i++) {
			String[] lineSplitArrayConstructor = admisionFileArrayListConstructor.get(i).split("\t", 2);
			if (!(lineSplitArrayConstructor[0].matches("Inpatient")
					|| lineSplitArrayConstructor[0].matches("Outpatient"))) {
				for (Patient patientSaveAdmission : patientFileArrayListPatientDaoImpObject) {
					lineSplitArrayConstructor = admisionFileArrayListConstructor.get(i).split("\t", 2);

					if (patientSaveAdmission.getId().equals(Integer.valueOf(lineSplitArrayConstructor[1]))) {
						patientSaveAdmission.setAdmissionId(Integer.valueOf(lineSplitArrayConstructor[0]));
						if (Integer.valueOf(i).equals(admisionFileArrayListConstructor.size() - 1)) {
							break;
						}
						if (Integer.valueOf(i) < (admisionFileArrayListConstructor.size() - 1)) {
							lineSplitArrayConstructor = admisionFileArrayListConstructor.get(i + 1).split("\t", 2);

							if ((lineSplitArrayConstructor[0].matches("Inpatient")
									|| lineSplitArrayConstructor[0].matches("Outpatient"))) {
								patientSaveAdmission.addExamType(admisionFileArrayListConstructor.get(i + 1));

							}
						}
						if (Integer.valueOf(i) < (admisionFileArrayListConstructor.size() - 2)) {
							lineSplitArrayConstructor = admisionFileArrayListConstructor.get(i + 2).split("\t", 2);
							if ((lineSplitArrayConstructor[0].matches("Inpatient")
									|| lineSplitArrayConstructor[0].matches("Outpatient"))) {
								patientSaveAdmission.addExamType(admisionFileArrayListConstructor.get(i + 2));
							}

						}
						if (Integer.valueOf(i) < (admisionFileArrayListConstructor.size() - 3)) {
							lineSplitArrayConstructor = admisionFileArrayListConstructor.get(i + 3).split("\t", 2);
							if ((lineSplitArrayConstructor[0].matches("Inpatient")
									|| lineSplitArrayConstructor[0].matches("Outpatient"))) {
								patientSaveAdmission.addExamType(admisionFileArrayListConstructor.get(i + 3));
								break;
							}

						}

					}
				}
			}

		}

	}

	// Constructors for save information in object and objectArrayList

	// add Patient which input file want

	@Override
	public void addPatient(String addPatientString) {
		String[] fixString = addPatientString.split(" ", 6);
		String newObject = fixString[1] + "\t" + fixString[2] + " " + fixString[3] + "\t" + fixString[4] + "\t"
				+ fixString[5];
		Patient patient = (Patient) createObject(newObject);
		patient.setAddress("Address: " + patient.getAddress());
		int controlBoolean = 1;
		for (Patient patientLineforControl : patientFileArrayListPatientDaoImpObject) {
			if (patientLineforControl.getId().equals(patient.getId())) {
				controlBoolean = -1;
			}
		}
		if (controlBoolean == 1) {
			patientFileArrayListPatientDaoImpObject.add(patient);
			getSortedArrayListById(patientFileArrayListPatientDaoImpObject);
			writePatientFile("patient.txt", false,"\t");
			// write output what it want or do
			try {
				FileWriter fileOutWriteraddPatient = new FileWriter("output.txt", true);
				fileOutWriteraddPatient.write("Patient " + patient.getId() + " " + patient.getName() + " added\n");
				fileOutWriteraddPatient.close();
			} catch (IOException e) {
				System.out.println("Exception founded in addPatient method for out.txt");

			}
		} else if (controlBoolean == -1) {
			try {
				FileWriter fileOutWriteraddPatient = new FileWriter("output.txt", true);

				fileOutWriteraddPatient.write(patient.getId() + " is already declared\n");
				fileOutWriteraddPatient.close();
			} catch (IOException e) {
				System.out.println("Exception founded in addPatient method for out.txt");

			}

		}

	}

	// add Patient which input file want
	// delete Patient which input file want

	@Override
	public void deletePatient(String deletePatientString) {
		int removeControl = 0;
		String[] removeArray = deletePatientString.trim().split(" ");
		Integer removePatientId = Integer.valueOf(removeArray[1]);
		Patient removePatientObject = new Patient() {

			@Override
			public int getCost() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		for (Patient patientLineforControl : patientFileArrayListPatientDaoImpObject) {
			if (patientLineforControl.getId().equals(removePatientId)) {
				patientFileArrayListPatientDaoImpObject.remove(patientLineforControl);
				removePatientObject = patientLineforControl;
				removeControl = 1;
				break;
			}
		}
		if (removeControl == 1) {
			getSortedArrayListById(patientFileArrayListPatientDaoImpObject);
			writePatientFile("patient.txt", false,"\t");
			try {
				FileWriter filedeletePatientOutWriter = new FileWriter("output.txt", true);
				filedeletePatientOutWriter.write(
						"Patient " + removePatientObject.getId() + " " + removePatientObject.getName() + " removed\n");
				filedeletePatientOutWriter.close();
			} catch (IOException e) {
				System.out.println("Exception founded in deletePatient method for out.txt");

			}
		} else {
			try {
				FileWriter filedeletePatientOutWriter = new FileWriter("output.txt", true);
				filedeletePatientOutWriter.write("Patient " + removeArray[1] + " is not founded!\n");
				filedeletePatientOutWriter.close();
			} catch (IOException e) {
				System.out.println("Exception founded in deletePatient method for out.txt");

			}
		}

	}
	// delete Patient which input file want

	// create and save Admission which input file want

	@Override
	public void createAdmission(String createAdmissionString) {
		String[] admissionIDAndPatientIDArray = createAdmissionString.split(" ");
		for (Patient patientForAdmission : patientFileArrayListPatientDaoImpObject) {
			if (Integer.valueOf(admissionIDAndPatientIDArray[2]).equals(patientForAdmission.getId())) {
				patientForAdmission.setAdmissionId(Integer.valueOf(admissionIDAndPatientIDArray[1]));
				break;
			}
		}
		writeAdmissionFile();
		try {
			FileWriter filecreateAdmissionOutWriter = new FileWriter("output.txt", true);
			filecreateAdmissionOutWriter.write("Admission " + admissionIDAndPatientIDArray[1] + " created\n");
			filecreateAdmissionOutWriter.close();
		} catch (IOException e) {
			System.out.println("Exception founded in createAdmission method for out.txt");

		}

	}
	// create and save Admission which input file want
	// add Examination Type and save Admission which input file want

	@Override
	public void addExamination(String addExaminationString) {
		String[] splitForAddExam = addExaminationString.trim().split(" ", 3);
		String[] examTypeSplitString = splitForAddExam[2].trim().split(" ", 2);
		String examTypeString = examTypeSplitString[0] + "\t" + examTypeSplitString[1];
		for (Patient patientAddExamObject : patientFileArrayListPatientDaoImpObject) {
			if (patientAddExamObject.getAdmissionId().equals(Integer.valueOf(splitForAddExam[1]))) {
				patientAddExamObject.addExamType(examTypeString);
				break;
			}
		}
		getSortedArrayListByAdmissionId(patientFileArrayListPatientDaoImpObject);
		writeAdmissionFile();

		try {
			FileWriter fileOutAddExamAdmission = new FileWriter("output.txt", true);

			fileOutAddExamAdmission
					.write(examTypeSplitString[0] + " examination added to admission " + splitForAddExam[1] + "\n");
			fileOutAddExamAdmission.close();
		} catch (IOException e) {
			System.out.println("Exception founded in listPatients method for out.txt");

		}
	}

	// add Examination Type and save Admission which input file want

	// calculate cost and write output
	@Override
	public void totalCost(String totalCostString) {
		String[] whichID = totalCostString.split(" ");
		Patient patientTotalCost = new Patient() {
			@Override
			public int getCost() {
				return 0;
			}
		};
		for (Patient totalCostObject : patientFileArrayListPatientDaoImpObject) {
			if (totalCostObject.getAdmissionId().equals(Integer.valueOf(whichID[1]))) {
				patientTotalCost = totalCostObject;
			}
		}
		FileWriter fileWriterTotalCostTitle;
		try {
			fileWriterTotalCostTitle = new FileWriter("output.txt", true);
			fileWriterTotalCostTitle.write("TotalCost for admission " + patientTotalCost.getAdmissionId() + " \n");
			fileWriterTotalCostTitle.close();
		} catch (IOException e1) {
			System.out.println("Exception founded in totalCost method for title output");
		}
		int totalCost = 0;
		int currentCost = 0;
		for (String lineInOROutArrayListString : patientTotalCost.getExamType()) {
			String[] lineInOROutStringArray = lineInOROutArrayListString.trim().split("\t");
			Patient examination = null;
			if (lineInOROutStringArray[0].matches("Outpatient")) {
				examination = new OutPatient();
				examination = (Patient) addOperation(examination, lineInOROutStringArray[1]);
				currentCost = examination.getCost();
			} else if (lineInOROutStringArray[0].matches("Inpatient")) {
				examination = new InPatient();
				examination = (Patient) addOperation(examination, lineInOROutStringArray[1]);
				currentCost = examination.getCost();
			}

			totalCost += currentCost;

			FileWriter fileWriterOutputTotalCost;
			try {
				fileWriterOutputTotalCost = new FileWriter("output.txt", true);
				fileWriterOutputTotalCost.write(
						"\t" + lineInOROutStringArray[0] + " " + lineInOROutStringArray[1] + " " + currentCost + "$\n");
				fileWriterOutputTotalCost.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		FileWriter fileWriterOutputTotalCost;
		try {
			fileWriterOutputTotalCost = new FileWriter("output.txt", true);
			fileWriterOutputTotalCost.write("\tTotal: " + totalCost + "$\n");
			fileWriterOutputTotalCost.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// calculate cost and write output
// Decorator Method
	public static Patient addOperation(Patient examination, String addOperationType) {
		if (addOperationType.contains("tests")) {
			examination = new Tests(examination);
		}
		if (addOperationType.contains("doctorvisit")) {
			examination = new DoctorVisit(examination);
		}
		if (addOperationType.contains("measurements")) {
			examination = new Measurements(examination);
		}
		if (addOperationType.contains("imaging")) {
			examination = new Imaging(examination);
		}
		return examination;
	}

	// Decorator Method
// same method for create Object
	public static Patient createObject(String line) {
		String[] generalAttributePatient = line.trim().split("\t");
		String[] nameGeneralAttributePatient = generalAttributePatient[1].trim().split(" ");
		Patient patient = new Patient() {
			@Override
			public int getCost() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		patient.setId(Integer.valueOf(generalAttributePatient[0]));
		patient.setName(nameGeneralAttributePatient[0]);
		patient.setSurname(nameGeneralAttributePatient[1]);
		patient.setPhoneNumber(generalAttributePatient[2]);
		patient.setAddress(generalAttributePatient[3]);
		return patient;

	}

	// same method for create Object
	// list patients
	@Override
	public void listPatients() {
		try {
			FileWriter fileWriterOutTitle = new FileWriter("output.txt",true);
			fileWriterOutTitle.write("Patient List:\n");
			fileWriterOutTitle.close();
		}
		catch (IOException e) {
			System.out.println("Exception founded in addPatient method for patient.txt");

		}
		getSortedArrayListByName(patientFileArrayListPatientDaoImpObject);
		writePatientFile("output.txt", true," ");

	}

	// list patients
// same method for list Patients
	public static void writePatientFile(String fileName, boolean trueOrFalse, String space) {
		try {
			FileWriter fileAddPatientWriter = new FileWriter(fileName, trueOrFalse);
			for (Patient patientInArrayList : patientFileArrayListPatientDaoImpObject) {
				fileAddPatientWriter.write(patientInArrayList.getId() + space + patientInArrayList.getName() + " "
						+ patientInArrayList.getSurname() + space + patientInArrayList.getPhoneNumber() + space
						+ patientInArrayList.getAddress() + "\n");
			}
			fileAddPatientWriter.close();
		} catch (IOException e) {
			System.out.println("Exception founded in addPatient method for patient.txt");

		}

	}
	// same method for list Patients

	public static void writeAdmissionFile() {
		getSortedArrayListByAdmissionId(patientFileArrayListPatientDaoImpObject);
		try {
			FileWriter filecreateAdmissionWriter = new FileWriter("admission.txt");
			for (Patient createExamPatientLoop : patientFileArrayListPatientDaoImpObject) {
				if (createExamPatientLoop.getAdmissionId().equals(1000000)) {
					continue;
				} else {
					filecreateAdmissionWriter.write(
							createExamPatientLoop.getAdmissionId() + "\t" + createExamPatientLoop.getId() + "\n");
					if (createExamPatientLoop.getExamType().isEmpty()) {
						continue;
					}
					for (String lineExamTypeOrAdmission : createExamPatientLoop.getExamType()) {
						filecreateAdmissionWriter.write(lineExamTypeOrAdmission + "\n");
					}
				}

			}
			filecreateAdmissionWriter.close();

		} catch (IOException e) {
			System.out.println("Exception founded in createAdmission method for out.txt");

		}
	}
// sorted according to id, admission id , name

	public static Comparator<Patient> idComparator = new Comparator<Patient>() {
		@Override
		public int compare(Patient patientOne, Patient patientTwo) {
			return -(patientTwo.getId() < patientOne.getId() ? -1 : (patientTwo.getId() == patientOne.getId() ? 0 : 1));
		}
	};
	public static Comparator<Patient> admissionIdComparator = new Comparator<Patient>() {
		@Override
		public int compare(Patient patientOne, Patient patientTwo) {
			return -(patientTwo.getAdmissionId() < patientOne.getAdmissionId() ? -1
					: (patientTwo.getAdmissionId() == patientOne.getAdmissionId() ? 0 : 1));
		}
	};
	public static Comparator<Patient> nameComparator = new Comparator<Patient>() {
		@Override
		public int compare(Patient patientOne, Patient patientTwo) {
			return (int) (patientOne.getName().compareTo(patientTwo.getName()));
		}
	};

	public static ArrayList<Patient> getSortedArrayListByName(ArrayList<Patient> arrayList) {
		Collections.sort(arrayList, nameComparator);
		return arrayList;
	}

	public static ArrayList<Patient> getSortedArrayListById(ArrayList<Patient> arrayList) {
		Collections.sort(arrayList, idComparator);
		return arrayList;
	}

	public static ArrayList<Patient> getSortedArrayListByAdmissionId(ArrayList<Patient> arrayList) {
		Collections.sort(arrayList, admissionIdComparator);
		return arrayList;
	}

	// sorted according to id, admission id , name

}
