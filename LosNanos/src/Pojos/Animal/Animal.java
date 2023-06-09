package Pojos.Animal;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Animal implements Serializable {

	protected static final long serialVersionUID = -710553172728332503L;
	protected int id = 0;
	protected String name = null;
	protected String scientificName = null;
	protected float height = 0;
	protected float weight = 0;
	protected Date bornDate;
	protected int vaccinated = 0;;
	protected String diet = null;

	public Animal(int id, String name, String scientificName, float height, float weight, Date bornDate, int vaccinated,
			String diet) {
		super();
		this.id = id;
		this.name = name;
		this.scientificName = scientificName;
		this.height = height;
		this.weight = weight;
		this.bornDate = bornDate;
		this.vaccinated = vaccinated;
		this.diet = diet;
	}
	
	public Animal() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public int getVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(int vaccinated) {
		this.vaccinated = vaccinated;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bornDate, diet, height, id, name, scientificName, vaccinated, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(bornDate, other.bornDate) && Objects.equals(diet, other.diet)
				&& Float.floatToIntBits(height) == Float.floatToIntBits(other.height) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(scientificName, other.scientificName)
				&& vaccinated == other.vaccinated && Float.floatToIntBits(weight) == Float.floatToIntBits(other.weight);
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", scientificName=" + scientificName + ", height=" + height
				+ ", weight=" + weight + ", bornDate=" + bornDate + ", vaccinated=" + vaccinated + ", diet=" + diet
				+ "]";
	}

}
