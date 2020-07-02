
public class Tests extends Examination {
	Patient patient;

	public Tests(Patient patient) {
		this.patient = patient;
	}


	@Override
	public int getCost() {
			return 7 + patient.getCost();
		}
	

	
}
