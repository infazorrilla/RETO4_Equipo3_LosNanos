package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

import Pojos.Zone.Savannah;

public abstract class TerrestrialMammarian extends Animal {


	protected static final long serialVersionUID = -2801285273629005682L;

	protected String hairColor = null;

	/*
	 * Existe una relacion 1:N con Savannah
	 */
	
	protected Savannah savannah = null;

	
	public TerrestrialMammarian(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String hairColor, Savannah savannah) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.hairColor = hairColor;
		this.savannah = savannah;
	}
	
	public TerrestrialMammarian() {
		super();
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public Savannah getSavannah() {
		return savannah;
	}

	public void setSavannah(Savannah savannah) {
		this.savannah = savannah;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hairColor, savannah);
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
		TerrestrialMammarian other = (TerrestrialMammarian) obj;
		return Objects.equals(hairColor, other.hairColor) && Objects.equals(savannah, other.savannah);
	}

	@Override
	public String toString() {
		return "TerrestrialMammarian [hairColor=" + hairColor + ", savannah=" + savannah + ", id=" + id + ", name="
				+ name + ", scientificName=" + scientificName + ", height=" + height + ", weight=" + weight
				+ ", bornDate=" + bornDate + ", vaccinated=" + vaccinated + ", diet=" + diet + "]";
	}
	
}
