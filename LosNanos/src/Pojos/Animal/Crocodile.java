package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public class Crocodile extends Reptile {

	private static final long serialVersionUID = 3493391510682934125L;

	private int teethNumber = 0;

	public Crocodile(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String shedSkin, int teethNumber) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, shedSkin);
		this.teethNumber = teethNumber;

	}

	public Crocodile() {

	}

	public int getTeethNumber() {
		return teethNumber;
	}

	public void setTeethNumber(int teethNumber) {
		this.teethNumber = teethNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(teethNumber);
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
		Crocodile other = (Crocodile) obj;
		return teethNumber == other.teethNumber;
	}

	@Override
	public String toString() {
		return "Crocodile [teethNumber=" + teethNumber + "]";
	}

}
