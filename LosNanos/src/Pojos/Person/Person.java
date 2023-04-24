package Pojos.Person;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 3183063123012135015L;
	private String name;
	private String surname;
	private String id;
	private String user;
	private String password;

	public Person(String name, String surname, String id, String user, String password) {
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.user = user;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, password, surname, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", id=" + id + ", user=" + user + ", password="
				+ password + "]";
	}

}
