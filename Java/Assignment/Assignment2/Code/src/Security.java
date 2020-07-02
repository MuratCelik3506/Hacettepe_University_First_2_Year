
public class Security extends Personnel {
	private Integer hourOfWork = 10;
	private Integer transMoney = 5;
	private Integer foodMoney = 10;

	@Override
	public void findSalary(Integer[] array, Personnel object) {
		super.findSalary(array, object);
		double salary = object.getSalary();
		for (Integer s : array) {
			if (s >= 30) {
				if (54 <= s) {
					s = 54;
				}
				salary = salary + hourOfWork * s;
				salary = salary + transMoney * 6;
				salary = salary + foodMoney * 6;
			}

		}
		object.setSalary(salary);
	}

}
