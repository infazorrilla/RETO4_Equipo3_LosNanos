package Pojos;

import java.util.Objects;

public class Vet extends Employee {

	private static final long serialVersionUID = -479101372287991715L;
	private String specializedAnimalType;

	public Vet(String name, String surname, String id, String user, String password, int ssNumber,
			String specializedAnimalType) {
		super(name, surname, id, user, password, ssNumber);
		this.specializedAnimalType = specializedAnimalType;
	}

	public String getSpecializedAnimalType() {
		return specializedAnimalType;
	}

	public void setSpecializedAnimalType(String specializedAnimalType) {
		this.specializedAnimalType = specializedAnimalType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(specializedAnimalType);
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
		Vet other = (Vet) obj;
		return Objects.equals(specializedAnimalType, other.specializedAnimalType);
	}

	@Override
	public String toString() {
		return "Vet [specializedAnimalType=" + specializedAnimalType + "]";
	}

}
