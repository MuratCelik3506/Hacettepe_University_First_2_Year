

public class Measurements extends Examination {
	Patient patient;

	public Measurements(Patient patient) {
		this.patient = patient;
	}


	@Override
	public int getCost() {
			return 5 + patient.getCost();
		}
}

