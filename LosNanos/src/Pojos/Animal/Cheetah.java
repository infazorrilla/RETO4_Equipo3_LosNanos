package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

import Pojos.Zone.Savannah;

public class Cheetah extends TerrestrialMammarian {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4763177983261767278L;

	private int maxSpeed = 0;

	public Cheetah(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String hairColor, Savannah savannah, int maxSpeed) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, hairColor, savannah);
		this.maxSpeed = maxSpeed;
	}
	
	public Cheetah(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String hairColor,  int maxSpeed) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, hairColor);
		this.maxSpeed = maxSpeed;
	}

	public Cheetah() {
		super();
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxSpeed);
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
		Cheetah other = (Cheetah) obj;
		return maxSpeed == other.maxSpeed;
	}

	@Override
	public String toString() {
		return "Cheetah [maxSpeed=" + maxSpeed + ", hairColor=" + hairColor + ", savannah=" + savannah + ", id=" + id
				+ ", name=" + name + ", scientificName=" + scientificName + ", height=" + height + ", weight=" + weight
				+ ", bornDate=" + bornDate + ", vaccinated=" + vaccinated + ", diet=" + diet + "]";
	}

	
}
