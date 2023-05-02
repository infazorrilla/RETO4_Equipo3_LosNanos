package Pojos.Person;

import java.util.Objects;

public class Feeder extends Employee {

	private static final long serialVersionUID = -6021696531239581430L;

	private String SpecializedDiet;

	public Feeder(String name, String surname, String id, String user, String password, int ssNumber,
			String specializedDiet) {
		super(name, surname, id, user, password, ssNumber, ssNumber);
		this.SpecializedDiet = specializedDiet;
	}

	public Feeder() {

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
		return SpecializedDiet == other.SpecializedDiet;
	}

	@Override
	public String toString() {
		return "Feeder [specializedDiet=" + SpecializedDiet + "]";
	}

}