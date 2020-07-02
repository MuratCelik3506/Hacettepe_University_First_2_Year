

public class Imaging extends Examination{
	Patient patient;

	public Imaging(Patient patient) {
		this.patient = patient;
	}


	@Override
	public int getCost() {
			return 10 + patient.getCost();
		
	}

	
}
