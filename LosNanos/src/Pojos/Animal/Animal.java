package Pojos.Animal;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Animal implements Serializable {


	private static final long serialVersionUID = -710553172728332503L;
	private int id = 0;
	private String name = null;
	private String scientificName = null;
	private float height = 0;
	private float weight = 0;
	private Date bornDate;
	private boolean vaccinated = false;
	private String diet = null;

	

	public Animal(int id, String name, String scientificName, float height, float weight, Date bornDate,
			boolean vaccinated, String diet) {
		this.id = id;
		this.name = name;
		this.scientificName = scientificName;
		this.height = height;
		this.weight = weight;
		this.bornDate = bornDate;
		this.vaccinated = vaccinated;
		this.diet = diet;
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



	public boolean isVaccinated() {
		return vaccinated;
	}



	public void setVaccinated(boolean vaccinated) {
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
