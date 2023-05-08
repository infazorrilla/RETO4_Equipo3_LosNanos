package Pojos.Zone;

import java.util.ArrayList;
import java.util.Objects;

import Pojos.Animal.Aquatic;
import Pojos.ZooTicket.Zoo;

public class Aquarium extends Zone {

	private static final long serialVersionUID = 491335310290568936L;
	private float waterTemp = 0;
	
	/*
	 * Existe una relacion N:1 con Aquatic
	 */
	
	protected ArrayList<Aquatic> acuatics = null;


	public Aquarium(int id, float extension, int animalsNumber, int speciesNumber, Zoo zoo, float waterTemp,
			ArrayList<Aquatic> acuatics) {
		super(id, extension, animalsNumber, speciesNumber, zoo);
		this.waterTemp = waterTemp;
		this.acuatics = acuatics;
	}
	
	public Aquarium(float extension, int animalsNumber, int speciesNumber,float waterTemp) {
		super(extension, animalsNumber, speciesNumber);
		this.waterTemp = waterTemp;
	}

	public Aquarium() {
		super();
	}

	public float getWaterTemp() {
		return waterTemp;
	}

	public void setWaterTemp(float waterTemp) {
		this.waterTemp = waterTemp;
	}

	public ArrayList<Aquatic> getAcuatics() {
		return acuatics;
	}

	public void setAcuatics(ArrayList<Aquatic> acuatics) {
		this.acuatics = acuatics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(acuatics, waterTemp);
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
		return Objects.equals(acuatics, other.acuatics)
				&& Float.floatToIntBits(waterTemp) == Float.floatToIntBits(other.waterTemp);
	}

	@Override
	public String toString() {
		return "Aquarium [waterTemp=" + waterTemp + ", acuatics=" + acuatics + ", id=" + id + ", extension=" + extension
				+ ", animalsNumber=" + animalsNumber + ", speciesNumber=" + speciesNumber + ", zoo=" + zoo + "]";
	}

}
