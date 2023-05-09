package Pojos.Zone;

import java.io.Serializable;
import java.util.Objects;

import Pojos.ZooTicket.Zoo;

public abstract class Zone implements Serializable {

	protected static final long serialVersionUID = -9022103785689291546L;
	protected int id = 0;
	protected float extension;
	protected int animalsNumber = 0;
	protected int speciesNumber = 0;

	
	/*
	 * Existe una relacion 1:N con Zoo
	 */

	protected Zoo zoo = null;


	public Zone(int id, float extension, int animalsNumber, int speciesNumber, Zoo zoo) {
		this.id = id;
		this.extension = extension;
		this.animalsNumber = animalsNumber;
		this.speciesNumber = speciesNumber;
		this.zoo = zoo;
	}
	
	public Zone(float extension, int animalsNumber, int speciesNumber) {
		this.extension = extension;
		this.animalsNumber = animalsNumber;
		this.speciesNumber = speciesNumber;
	}

	public Zone() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getExtension() {
		return extension;
	}


	public void setExtension(float extension) {
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


	public Zoo getZoo() {
		return zoo;
	}


	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animalsNumber, extension, id, speciesNumber, zoo);
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
				&& speciesNumber == other.speciesNumber && Objects.equals(zoo, other.zoo);
	}

	@Override
	public String toString() {
		return "Zone [id=" + id + ", extension=" + extension + ", animalsNumber=" + animalsNumber + ", speciesNumber="
				+ speciesNumber + ", zoo=" + zoo + "]";
	} 
		
}
