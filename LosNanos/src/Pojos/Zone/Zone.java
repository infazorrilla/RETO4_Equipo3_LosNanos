package Pojos.Zone;

import java.io.Serializable;
import java.util.Objects;

public class Zone implements Serializable{

	private static final long serialVersionUID = -9022103785689291546L;
	private int id = 0;
	private String extension;
	private int animalsNumber = 0;
	private int speciesNumber = 0;
	
	
	public Zone(int id, String extension, int animalsNumber, int speciesNumber) {
		super();
		this.id = id;
		this.extension = extension;
		this.animalsNumber = animalsNumber;
		this.speciesNumber = speciesNumber;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}


	public int getAnimalsNumber() {
		return animalsNumber;
	}


	public void setAnimalsNumber(int animalsNumber) {
		this.animalsNumber = animalsNumber;
	}


	public int getSpeciesNumber() {
		return speciesNumber;
	}


	public void setSpeciesNumber(int speciesNumber) {
		this.speciesNumber = speciesNumber;
	}


	@Override
	public int hashCode() {
		return Objects.hash(animalsNumber, extension, id, speciesNumber);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zone other = (Zone) obj;
		return animalsNumber == other.animalsNumber && Objects.equals(extension, other.extension) && id == other.id
				&& speciesNumber == other.speciesNumber;
	}


	@Override
	public String toString() {
		return "Zone [id=" + id + ", extension=" + extension + ", animalsNumber=" + animalsNumber + ", speciesNumber="
				+ speciesNumber + "]";
	}
	
	
	
	
	

}
