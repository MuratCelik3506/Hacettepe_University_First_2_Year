
public class OutPatient extends Patient {
	// patient has a examination type

	public OutPatient() {
		description = "Outpatient\t";
	}
	// patient has a cost of examination type
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 15;
	}
	
}
