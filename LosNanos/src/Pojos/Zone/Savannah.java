package Pojos.Zone;

import java.util.Objects;

public class Savannah extends Zone {

	private static final long serialVersionUID = 1565125195990152251L;
	private int treeNumber = 0;

	public Savannah(int id, String extension, int animalsNumber, int speciesNumber, int treeNumber) {
		super(id, extension, animalsNumber, speciesNumber);
		this.treeNumber = treeNumber;
	}

	public Savannah() {

	}

	public int getTreeNumber() {
		return treeNumber;
	}

	public void setTreeNumber(int treeNumber) {
		this.treeNumber = treeNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(treeNumber);
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
		return treeNumber == other.treeNumber;
	}

	@Override
	public String toString() {
		return "Savannah [treeNumber=" + treeNumber + "]";
	}

}
