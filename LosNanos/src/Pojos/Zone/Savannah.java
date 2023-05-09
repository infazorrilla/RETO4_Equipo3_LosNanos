package Pojos.Zone;

import java.util.ArrayList;
import java.util.Objects;

import Pojos.Animal.TerrestrialMammarian;
import Pojos.ZooTicket.Zoo;

public class Savannah extends Zone {

	private static final long serialVersionUID = 1565125195990152251L;
	private int treeNumber = 0;
	
	/*
	 * Existe una relacion N:1 con TerrestrialMammarian
	 */
	
	protected ArrayList<TerrestrialMammarian> terrestrialMammarians = null;
	

	public Savannah(int id, float extension, int animalsNumber, int speciesNumber, Zoo zoo, int treeNumber,
			ArrayList<TerrestrialMammarian> terrestrialMammarians) {
		super(id, extension, animalsNumber, speciesNumber, zoo);
		this.treeNumber = treeNumber;
		this.terrestrialMammarians = terrestrialMammarians;
	}

	public Savannah() {
		super();
	}

	public Savannah(float extension, int animalsNumber, int speciesNumber, int treeNumber) {
		super(extension, animalsNumber, speciesNumber);
		this.treeNumber = treeNumber;
	}

	public Savannah(int id, float extension, int animalsNumber, int speciesNumber, int treeNumber) {
		super(id, extension, animalsNumber, speciesNumber);
		this.treeNumber = treeNumber;
	}

	public int getTreeNumber() {
		return treeNumber;
	}

	public void setTreeNumber(int treeNumber) {
		this.treeNumber = treeNumber;
	}

	public ArrayList<TerrestrialMammarian> getTerrestrialMammarians() {
		return terrestrialMammarians;
	}

	public void setTerrestrialMammarians(ArrayList<TerrestrialMammarian> terrestrialMammarians) {
		this.terrestrialMammarians = terrestrialMammarians;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(terrestrialMammarians, treeNumber);
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
		Savannah other = (Savannah) obj;
		return Objects.equals(terrestrialMammarians, other.terrestrialMammarians) && treeNumber == other.treeNumber;
	}

	@Override
	public String toString() {
		return "Savannah [treeNumber=" + treeNumber + ", terrestrialMammarians=" + terrestrialMammarians + ", id=" + id
				+ ", extension=" + extension + ", animalsNumber=" + animalsNumber + ", speciesNumber=" + speciesNumber
				+ ", zoo=" + zoo + "]";
	}


}
