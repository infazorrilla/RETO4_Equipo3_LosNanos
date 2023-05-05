package Pojos.Person;

import java.util.Objects;

import javax.swing.JTextField;

import Pojos.ZooTicket.Zoo;

public class Feeder extends Employee {

	private static final long serialVersionUID = -6021696531239581430L;

	private String SpecializedDiet = null;

	public Feeder(String name, String surname, String id, String user, String password, int ssNumber, Zoo zoo,
			String specializedDiet) {
		super(name, surname, id, user, password, ssNumber, zoo);
		SpecializedDiet = specializedDiet;
	}

	public Feeder() {
		super();
	}

	public Feeder(String name, String surname, String id, String user, String password, int ssNumber,
			String specializedDiet) {
		super(name, surname, id, user, password, ssNumber);
		SpecializedDiet = specializedDiet;
	}

	public String getSpecializedDiet() {
		return SpecializedDiet;
	}

	public void setSpecializedDiet(String specializedDiet) {
		SpecializedDiet = specializedDiet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(SpecializedDiet);
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
		Feeder other = (Feeder) obj;
		return Objects.equals(SpecializedDiet, other.SpecializedDiet);
	}

	@Override
	public String toString() {
		return "Feeder [SpecializedDiet=" + SpecializedDiet + ", ssNumber=" + ssNumber + ", zoo=" + zoo + ", name="
				+ name + ", surname=" + surname + ", id=" + id + ", user=" + user + ", password=" + password + "]";
	}

}