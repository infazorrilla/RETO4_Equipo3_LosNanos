package Pojos.Person;

import java.util.Objects;

public class Boss extends Employee {

	private static final long serialVersionUID = -3650945065686945377L;
	private int employeeNumCharge;

	public Boss(String name, String surname, String id, String user, String password, int ssNumber,
			int employeeNumCharge) {
		super(name, surname, id, user, password, ssNumber, employeeNumCharge);
		this.employeeNumCharge = employeeNumCharge;
	}

	public Boss() {
		
	}
	public int getEmployeeNumCharge() {
		return employeeNumCharge;
	}

	public void setEmployeeNumCharge(int employeeNumCharge) {
		this.employeeNumCharge = employeeNumCharge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(employeeNumCharge);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boss other = (Boss) obj;
		return employeeNumCharge == other.employeeNumCharge;
	}

	@Override
	public String toString() {
		return "Boss [employeeNumCharge=" + employeeNumCharge + "]";
	}

}
