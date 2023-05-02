package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public class Dolphin extends Aquatic{


	private static final long serialVersionUID = 2131101451801639958L;
	
	private int durationUnderWater = 0;
	private int id_dolphin = 0;

	public Dolphin(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String animalTipe, int zoneId, int durationUnderWater, int id_dolphin) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, animalTipe, zoneId);
	}
	
	public Dolphin() {
		
	}

	public int getDurationUnderWater() {
		return durationUnderWater;
	}

	public void setDurationUnderWater(int durationUnderWater) {
		this.durationUnderWater = durationUnderWater;
	}


	public int getId_dolphin() {
		return id_dolphin;
	}

	public void setId_dolphin(int id_dolphin) {
		this.id_dolphin = id_dolphin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(durationUnderWater, id_dolphin);
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
		Dolphin other = (Dolphin) obj;
		return durationUnderWater == other.durationUnderWater && id_dolphin == other.id_dolphin;
	}

	@Override
	public String toString() {
		return "Dolphin [durationUnderWater=" + durationUnderWater + ", id_dolphin=" + id_dolphin + "]";
	}




	
	

}
