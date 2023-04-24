package Pojos;

import java.util.Objects;

public class Feeder extends Employee {

	private static final long serialVersionUID = -6021696531239581430L;

	private enum SpecializedDiet {
		CARNIVOROUS, HERBIVOROUS
	};

	private SpecializedDiet specializedDiet = null;

	public Feeder(String name, String surname, String id, String user, String password, int ssNumber,
			SpecializedDiet specializedDiet) {
		super(name, surname, id, user, password, ssNumber);
		this.specializedDiet = specializedDiet;
	}

	public SpecializedDiet getSpecializedDiet() {
		return specializedDiet;
	}

	public void setSpecializedDiet(SpecializedDiet specializedDiet) {
		this.specializedDiet = specializedDiet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(specializedDiet);
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
		return specializedDiet == other.specializedDiet;
	}

	@Override
	public String toString() {
		return "Feeder [specializedDiet=" + specializedDiet + "]";
	}

}