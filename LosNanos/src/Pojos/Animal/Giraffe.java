package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

import Pojos.Zone.Savannah;

public class Giraffe extends TerrestrialMammarian {

	private static final long serialVersionUID = -880259951743115792L;

	private int neckLength = 0;

	public Giraffe(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String hairColor, Savannah savannah, int neckLength) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, hairColor, savannah);
		this.neckLength = neckLength;
	}

	public Giraffe() {
		super();
	}

	public int getNeckLength() {
		return neckLength;
	}

	public void setNeckLength(int neckLength) {
		this.neckLength = neckLength;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(neckLength);
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
		Giraffe other = (Giraffe) obj;
		return neckLength == other.neckLength;
	}

	@Override
	public String toString() {
		return "Giraffe [neckLength=" + neckLength + ", hairColor=" + hairColor + ", savannah=" + savannah + ", id="
				+ id + ", name=" + name + ", scientificName=" + scientificName + ", height=" + height + ", weight="
				+ weight + ", bornDate=" + bornDate + ", vaccinated=" + vaccinated + ", diet=" + diet + "]";
	}

	

}
