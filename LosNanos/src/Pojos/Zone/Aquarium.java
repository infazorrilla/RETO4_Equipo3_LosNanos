package Pojos.Zone;

import java.util.Objects;

public class Aquarium extends Zone {



	private static final long serialVersionUID = 491335310290568936L;
	private float waterTemp = 0;

	public Aquarium(int id, String extension, int animalsNumber, int speciesNumber, float waterTemp) {
		super(id, extension, animalsNumber, speciesNumber);
		this.waterTemp = waterTemp;
		}

	public float getWaterTemp() {
		return waterTemp;
	}

	public void setWaterTemp(float waterTemp) {
		this.waterTemp = waterTemp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(waterTemp);
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
		Aquarium other = (Aquarium) obj;
		return Float.floatToIntBits(waterTemp) == Float.floatToIntBits(other.waterTemp);
	}

	@Override
	public String toString() {
		return "Aquarium [waterTemp=" + waterTemp + "]";
	}

	
	
}
