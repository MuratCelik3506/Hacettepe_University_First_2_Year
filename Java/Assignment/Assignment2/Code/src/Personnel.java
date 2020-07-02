
public class Personnel {
	// superclass for all classes --- inheritance
	// same attribute for all classes
	private String name;
	private String surname;
	private String registerNumber;
	private String position;
	private Integer yearOfStart;
	private Double salary;
	
	

	public Personnel() {
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
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

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Integer getYearOfStart() {
		return yearOfStart;
	}

	public void setYearOfStart(Integer yearOfStart) {
		this.yearOfStart = yearOfStart;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	// same method for all classes
	public void findSalary(Integer[] array, Personnel object) {
		int currentYear = 2020;
		double salary = 0.0;
		// calculate severance Pay
		double severancePay = (double) ((currentYear - object.getYearOfStart()) * 20 * 8 / 10);
		salary = salary + severancePay;
		object.setSalary(salary);
	}

}
