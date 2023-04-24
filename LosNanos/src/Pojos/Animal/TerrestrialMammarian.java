package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public abstract class TerrestrialMammarian extends Animal {


	private static final long serialVersionUID = -2801285273629005682L;

	private String hairColor = null;

	public TerrestrialMammarian(int id, String name, String scientificName, float height, float weight, Date bornDate,
			boolean vaccinated, Diet diet, String hairColor) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.hairColor = hairColor;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hairColor);
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
		return Objects.equals(hairColor, other.hairColor);
	}

	@Override
	public String toString() {
		return "TerrestrialMammarian [hairColor=" + hairColor + "]";
	}
	
	

}
