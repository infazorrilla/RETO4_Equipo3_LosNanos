package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public class Aquatic extends Animal {

	private static final long serialVersionUID = -8764208574991894358L;

	private String animalTipe = null;
	private int zoneId = 0;

	public Aquatic(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String animalTipe, int zoneId) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.animalTipe = animalTipe;
		this.zoneId = zoneId;
	}
	
	public Aquatic() {
		
	}

	public String getAnimalTipe() {
		return animalTipe;
	}

	public void setAnimalTipe(String animalTipe) {
		this.animalTipe = animalTipe;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(animalTipe);
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
		Aquatic other = (Aquatic) obj;
		return Objects.equals(animalTipe, other.animalTipe);
	}

	@Override
	public String toString() {
		return "Aquatic [animalTipe=" + animalTipe + "]";
	}

}
