package Pojos.ZooTicket;

import java.util.ArrayList;
import java.util.Objects;

import Pojos.Person.Employee;
import Pojos.Zone.Zone;

public class Zoo{

	private static final long serialVersionUID = 1L;
	private int Id;
	private String name;
	private String location;
	
	protected Zone zone = null;
	protected ArrayList<Ticket> ticket = null;
	protected Employee employee = null;
	
	public Zoo(int id, String name, String location) {
		this.Id = id;
		this.name = name;
		this.location = location;
	}
	
	public Zoo() {
		
	}
	
	public Zoo(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
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
		return Objects.hash(Id, location, name);
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
		return Id == other.Id && Objects.equals(location, other.location) && Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return "Zoo [id=" + Id + ", name=" + name + ", location=" + location + "]";
	}
}