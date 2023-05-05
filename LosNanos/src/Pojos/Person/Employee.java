package Pojos.Person;

import java.util.Objects;

import Pojos.ZooTicket.Zoo;

public abstract class Employee extends Person {

	protected static final long serialVersionUID = 148854585781764229L;
	protected int ssNumber = 0;
	
	/*
	 * Existe una relacion 1:N con Zoo
	 */
	
	protected Zoo zoo = null;

	public Employee(String name, String surname, String id, String user, String password, int ssNumber, Zoo zoo) {
		super(name, surname, id, user, password);
		this.ssNumber = ssNumber;
		this.zoo = zoo;
	}

	public Employee() {
		super();
	}

	public Employee(String name, String surname, String id, String user, String password, int ssNumber) {
		super(name, surname, id, user, password);
		this.ssNumber = ssNumber;
	}

	public int getSsNumber() {
		return ssNumber;
	}

	public void setSsNumber(int ssNumber) {
		this.ssNumber = ssNumber;
	}

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ssNumber, zoo);
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
		return ssNumber == other.ssNumber && Objects.equals(zoo, other.zoo);
	}
	
	

}
