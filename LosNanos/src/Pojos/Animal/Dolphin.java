package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public class Dolphin extends Aquatic{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2131101451801639958L;
	
	private int durationUnderWater = 0;


	public Dolphin(int id, String name, String scientificName, float height, float weight, Date bornDate,
			boolean vaccinated, Diet diet, AnimalTipe animalTipe, int durationUnderWater) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, animalTipe);
		this.durationUnderWater = durationUnderWater;

	}

	public int getDurationUnderWater() {
		return durationUnderWater;
	}

	public void setDurationUnderWater(int durationUnderWater) {
		this.durationUnderWater = durationUnderWater;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(durationUnderWater);
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
		Dolphin other = (Dolphin) obj;
		return durationUnderWater == other.durationUnderWater;
	}

	@Override
	public String toString() {
		return "Dolphin [durationUnderWater=" + durationUnderWater + "]";
	}
	
	

}
