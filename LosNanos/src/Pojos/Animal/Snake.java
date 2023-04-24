package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public class Snake extends Reptile{


	private static final long serialVersionUID = -58749026852993665L;

	private boolean poisonus = false;
	


	public Snake(int id, String name, String scientificName, float height, float weight, Date bornDate,
			boolean vaccinated, Diet diet, Date shedSkin, boolean poisonus) {
		super(id, name, scientificName, height, weight, bornDate, vaccinated, diet, shedSkin);
		this.poisonus = poisonus;
	}

	public boolean isPoisonus() {
		return poisonus;
	}

	public void setPoisonus(boolean poisonus) {
		this.poisonus = poisonus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(poisonus);
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
		Snake other = (Snake) obj;
		return poisonus == other.poisonus;
	}

	@Override
	public String toString() {
		return "Snake [poisonus=" + poisonus + "]";
	}
	
	

}
