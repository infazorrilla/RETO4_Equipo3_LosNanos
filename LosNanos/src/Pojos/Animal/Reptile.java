package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public abstract class Reptile extends Animal{


	private static final long serialVersionUID = 7761481468131552098L;
	
	private Date shedSkin;


	public Reptile(int id, String name, String scientificName, float height, float weight, Date bornDate,
			boolean vaccinated, String diet, Date shedSkin) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet);
		this.shedSkin = shedSkin;
	}

	public Date getShedSkin() {
		return shedSkin;
	}

	public void setShedSkin(Date shedSkin) {
		this.shedSkin = shedSkin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(shedSkin);
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
		return Objects.equals(shedSkin, other.shedSkin);
	}

	@Override
	public String toString() {
		return "Reptile [shedSkin=" + shedSkin + "]";
	}

	
}
