package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

import Pojos.Zone.Swamp;

public abstract class Reptile extends Animal {

	protected static final long serialVersionUID = 7761481468131552098L;

	protected String shedSkin;
	
//	Relaciones
	
	/*
	 * Existe una relacion 1:N con Swamp
	 */
	
	protected Swamp swamp = null;
	
	
	public Reptile(int id, String name, String scientificName, float height, float weight, Date bornDate,
			int vaccinated, String diet, String shedSkin, Swamp swamp) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.shedSkin = shedSkin;
		this.swamp = swamp;
	}

	public Reptile() {

	}

	public String getShedSkin() {
		return shedSkin;
	}

	public void setShedSkin(String shedSkin) {
		this.shedSkin = shedSkin;
	}

	public Swamp getSwamp() {
		return swamp;
	}

	public void setSwamp(Swamp swamp) {
		this.swamp = swamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(shedSkin, swamp);
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
		Reptile other = (Reptile) obj;
		return Objects.equals(shedSkin, other.shedSkin) && Objects.equals(swamp, other.swamp);
	}

	@Override
	public String toString() {
		return "Reptile [shedSkin=" + shedSkin + ", swamp=" + swamp + ", id=" + id + ", name=" + name
				+ ", scientificName=" + scientificName + ", height=" + height + ", weight=" + weight + ", bornDate="
				+ bornDate + ", vaccinated=" + vaccinated + ", diet=" + diet + "]";
	}

	

}
