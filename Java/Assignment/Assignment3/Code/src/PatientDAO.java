
public interface PatientDAO {

	public void addPatient(String addPatientString);

	public void deletePatient(String deletePatientString);

	public void createAdmission(String createAdmissionString);

	public void listPatients();

	public void addExamination(String addExaminationString);

	public void totalCost(String totalCostString);
}
