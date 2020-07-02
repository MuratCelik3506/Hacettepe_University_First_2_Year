


public class DoctorVisit extends Examination{
	Patient patient;

	public DoctorVisit(Patient patient) {
		this.patient = patient;
	}


	@Override
	public int getCost() {
		return 15 + patient.getCost();
	}
	

	
}
