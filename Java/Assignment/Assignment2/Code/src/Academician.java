
public class Academician extends Personnel {
	// same attribute for Academician personnel
	private Integer baseSalary = 2600;

	// use inner class in a Academician
	public class Facultymembers extends Academician {
		private Integer ssBenefits = baseSalary * 135 / 100;
		private Integer addCourseFee = 20;

		// override method
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
				if (hour >= 8) {
					salary = salary + (addCourseFee * 8);
				} else if (hour > 0) {
					salary = salary + (addCourseFee * hour);
				}

			}
			object.setSalary(salary);
		}

	}

	public class Researchassistants extends Academician {
		private Integer ssBenefits = baseSalary * 105 / 100;

		// override method
		@Override
		public void findSalary(Integer[] array, Personnel object) {
			super.findSalary(array, object);
			double salary = object.getSalary() + baseSalary;
			salary = salary + ssBenefits;
			object.setSalary(salary);
		}

	}
}
