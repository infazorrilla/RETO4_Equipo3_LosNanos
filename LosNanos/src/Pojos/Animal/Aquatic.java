package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

import Pojos.Zone.Aquarium;

public class Aquatic extends Animal {

	protected static final long serialVersionUID = -8764208574991894358L;

	protected String animalType = null;
	
	/*
	 * Existe una relacion 1:N con Aquarium
	 */
	
	protected Aquarium aquarium = null;

	
	public Aquatic(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String animalType, Aquarium aquarium) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.animalType = animalType;
		this.aquarium = aquarium;
	}
	
	public Aquatic(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String animalType) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.animalType = animalType;
		this.aquarium = aquarium;
	}

	public Aquatic() {
		super();
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public Aquarium getAquarium() {
		return aquarium;
	}

	public void setAquarium(Aquarium aquarium) {
		this.aquarium = aquarium;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(animalType, aquarium);
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
		return Objects.equals(animalType, other.animalType) && Objects.equals(aquarium, other.aquarium);
	}

	@Override
	public String toString() {
		return "Aquatic [animalType=" + animalType + ", aquarium=" + aquarium + ", id=" + id + ", name=" + name
				+ ", scientificName=" + scientificName + ", height=" + height + ", weight=" + weight + ", bornDate="
				+ bornDate + ", vaccinated=" + vaccinated + ", diet=" + diet + "]";
	}

	
	
}
