
import java.util.ArrayList;

public abstract class Patient {

	private Integer id;
	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	private Integer admissionId = 1000000;
	private ArrayList<String> examType;
	private Integer totalcost = 0;
	public String description;


	public Patient() {
		examType = new ArrayList<String>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}
	public ArrayList<String> getExamType() {
		return examType;
	}
	public void addExamType(String examType) {
		this.examType.add(examType);
	}
		public String getDescription() {
		return description;
	}
		// for Decorator Pattern
    public abstract int getCost();
    
	public Integer getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(Integer totalcost) {
		this.totalcost = totalcost;
		
	} 

	
}
