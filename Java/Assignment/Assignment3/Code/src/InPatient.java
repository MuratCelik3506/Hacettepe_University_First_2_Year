
public class InPatient extends Patient {
	// patient has a examination type
	public InPatient() {
		description = "Inpatient\t";
	}
	// patient has a cost of examination type
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 10;
	}
	
}
