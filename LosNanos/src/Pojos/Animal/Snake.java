package Pojos.Animal;

import java.util.Date;
import java.util.Objects;

public class Snake extends Reptile{


	private static final long serialVersionUID = -58749026852993665L;

	private boolean poisonus = false;
	private int zoneId = 0;
	


	public Snake(int id, String name, String scientificName, float height, float weight, String bornDate,
			int i, String diet, String shedSkin, boolean poisonus, int zoneId) {
		super(id, name, scientificName, height, weight, bornDate, i, diet, shedSkin);
		this.poisonus = poisonus;
		this.zoneId = zoneId;
	}



	public boolean isPoisonus() {
		return poisonus;
	}



	public void setPoisonus(boolean poisonus) {
		this.poisonus = poisonus;
	}



	public int getZoneId() {
		return zoneId;
	}



	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(poisonus, zoneId);
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
		return poisonus == other.poisonus && zoneId == other.zoneId;
	}



	@Override
	public String toString() {
		return "Snake [poisonus=" + poisonus + ", zoneId=" + zoneId + "]";
	}
	
	


	
	

}
