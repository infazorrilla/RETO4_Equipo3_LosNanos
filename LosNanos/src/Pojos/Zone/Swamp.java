package Pojos.Zone;

import java.util.Objects;

public class Swamp extends Zone {

	private static final long serialVersionUID = 7920840047200955197L;
	private float waterSurface = 0;

	public Swamp(int id, String extension, int animalsNumber, int speciesNumber, float waterSurface) {
		super(id, extension, animalsNumber, speciesNumber);
		this.waterSurface = waterSurface;
	}

	public Swamp() {

	}

	public float getWaterSurface() {
		return waterSurface;
	}

	public void setWaterSurface(float waterSurface) {
		this.waterSurface = waterSurface;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(waterSurface);
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
		Swamp other = (Swamp) obj;
		return Float.floatToIntBits(waterSurface) == Float.floatToIntBits(other.waterSurface);
	}

	@Override
	public String toString() {
		return "Swamp [waterSurface=" + waterSurface + "]";
	}

}
