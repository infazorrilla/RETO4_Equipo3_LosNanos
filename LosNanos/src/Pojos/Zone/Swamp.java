package Pojos.Zone;

import java.util.ArrayList;
import java.util.Objects;

import Pojos.Animal.Reptile;
import Pojos.ZooTicket.Zoo;

public class Swamp extends Zone {

	private static final long serialVersionUID = 7920840047200955197L;
	private float waterSurface = 0;
	
	/*
	 * Existe una relacion N:1 con Reptile
	 */
	
	protected ArrayList<Reptile> reptiles = null;

	
	public Swamp(int id, float extension, int animalsNumber, int speciesNumber, Zoo zoo, float waterSurface,
			ArrayList<Reptile> reptiles) {
		super(id, extension, animalsNumber, speciesNumber, zoo);
		this.waterSurface = waterSurface;
		this.reptiles = reptiles;
	}

	public Swamp() {
		super();
	}

	public Swamp(float extension, int animalsNumber, int speciesNumber, int waterSurface) {
		super(extension, animalsNumber, speciesNumber);
	this.waterSurface = waterSurface;
	}

	public float getWaterSurface() {
		return waterSurface;
	}

	public void setWaterSurface(float waterSurface) {
		this.waterSurface = waterSurface;
	}

	public ArrayList<Reptile> getReptiles() {
		return reptiles;
	}

	public void setReptiles(ArrayList<Reptile> reptiles) {
		this.reptiles = reptiles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(reptiles, waterSurface);
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
		return Objects.equals(reptiles, other.reptiles)
				&& Float.floatToIntBits(waterSurface) == Float.floatToIntBits(other.waterSurface);
	}

	@Override
	public String toString() {
		return "Swamp [waterSurface=" + waterSurface + ", reptiles=" + reptiles + ", id=" + id + ", extension="
				+ extension + ", animalsNumber=" + animalsNumber + ", speciesNumber=" + speciesNumber + ", zoo=" + zoo
				+ "]";
	}
	
}
