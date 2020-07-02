
public class Officer extends Personnel {
	private Integer baseSalary = 2600;
	private Integer ssBenefits = baseSalary * 65 / 100;
	private Integer overWorkSalary = 20;

	@Override
	public void findSalary(Integer[] array, Personnel object) {
		super.findSalary(array, object);
		double salary = object.getSalary() + baseSalary;
		salary = salary + ssBenefits;
		for (Integer s : array) {
			if (s < 40) {
				continue;
			}
			int hour = s - 40;
			if (hour >= 10) {
				salary = salary + 10 * overWorkSalary;
			} else if (hour < 10) {
				salary = salary + hour * overWorkSalary;
			}

		}

		object.setSalary(salary);
	}

}
