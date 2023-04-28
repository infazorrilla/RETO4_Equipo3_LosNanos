package Pojos.ZooTicket;

import java.util.Objects;

public class Zoo{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String location;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, location, name);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zoo other = (Zoo) obj;
		return id == other.id && Objects.equals(location, other.location) && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "Zoo [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
	
	
	
	
	}
