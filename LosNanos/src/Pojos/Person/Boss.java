package Pojos.Person;

import java.util.ArrayList;
import java.util.Objects;

import Pojos.ZooTicket.Zoo;

public class Boss extends Employee {

	private static final long serialVersionUID = -3650945065686945377L;
	private int employeeNumCharge = 0;
	
	/*
	 * Existe una relacion 1:N con Employee
	 */
	
	private ArrayList<Employee> employees = null;

	public Boss(String name, String surname, String id, String user, String password, int ssNumber, Zoo zoo,
			int employeeNumCharge, ArrayList<Employee> employees) {
		super(name, surname, id, user, password, ssNumber, zoo);
		this.employeeNumCharge = employeeNumCharge;
		this.employees = employees;
	}

	public Boss() {
		super();
	}

	public Boss(String name, String surname, String id, String user, String password, int ssNumber,
			int employeeNumCharge) {
		super(name, surname, id, user, password, ssNumber);
		this.employeeNumCharge = employeeNumCharge;
	}

	public int getEmployeeNumCharge() {
		return employeeNumCharge;
	}

	public void setEmployeeNumCharge(int employeeNumCharge) {
		this.employeeNumCharge = employeeNumCharge;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(employeeNumCharge, employees);
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
		return employeeNumCharge == other.employeeNumCharge && Objects.equals(employees, other.employees);
	}

	@Override
	public String toString() {
		return "Boss [employeeNumCharge=" + employeeNumCharge + ", employees=" + employees + ", ssNumber=" + ssNumber
				+ ", zoo=" + zoo + ", name=" + name + ", surname=" + surname + ", id=" + id + ", user=" + user
				+ ", password=" + password + "]";
	}
	
}
