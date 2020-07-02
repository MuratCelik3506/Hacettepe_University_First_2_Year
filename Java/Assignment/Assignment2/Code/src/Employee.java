
public class Employee extends Personnel {

	// use inner class

	public class Parttime extends Employee {
		private Integer hourOfWork = 18;

		@Override
		public void findSalary(Integer[] array, Personnel object) {
			super.findSalary(array, object);
			double salary = object.getSalary();
			for (Integer s : array) {
				if (s <= 20 && s >= 10) {
					salary = salary + (s * hourOfWork);
				} else if (s > 20) {
					salary = salary + (20 * hourOfWork);
				}

			}
			object.setSalary(salary);

		}
	}

	public class Fulltime extends Employee {

		// use inner class again
		public class Worker extends Fulltime {
			private Integer dayOfWork = 105;
			private Integer overWorkSalary = 11;

			@Override
			public void findSalary(Integer[] array, Personnel object) {
				super.findSalary(array, object);
				double salary = object.getSalary();
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
					salary = salary + (5 * dayOfWork);

				}
				object.setSalary(salary);
			}

		}

		public class Chief extends Fulltime {
			private Integer dayOfWork = 125;
			private Integer overWorkSalary = 15;
			private Integer workDayInAWeek = 5;

			@Override
			public void findSalary(Integer[] array, Personnel object) {
				super.findSalary(array, object);
				double salary = object.getSalary();
				for (Integer s : array) {
					if (s < 40) {
						continue;
					}
					int hour = s - 40;
					if (hour >= 8) {
						salary = salary + 8 * overWorkSalary;
					} else if (hour < 8) {
						salary = salary + hour * overWorkSalary;
					}
					salary = salary + (workDayInAWeek * dayOfWork);

				}
				object.setSalary(salary);
			}

		}
	}
}
