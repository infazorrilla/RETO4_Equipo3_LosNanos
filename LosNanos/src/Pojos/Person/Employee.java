package Pojos.Person;

import java.util.Objects;

public abstract class Employee extends Person {

	private static final long serialVersionUID = 148854585781764229L;
	private int ssNumber;
	private int idZoo;

	public Employee(String name, String surname, String id, String user, String password, int ssNumber, int idZoo) {
		super(name, surname, id, user, password);
		this.ssNumber = ssNumber;
		this.idZoo = idZoo;
	}

	public int getSsNumber() {
		return ssNumber;
	}

	public void setSsNumber(int ssNumber) {
		this.ssNumber = ssNumber;
	}
	

	public int getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(int idZoo) {
		this.idZoo = idZoo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ssNumber);
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
		Employee other = (Employee) obj;
		return ssNumber == other.ssNumber;
	}

	@Override
	public String toString() {
		return "Employee [ssNumber=" + ssNumber + "]";
	}

}
