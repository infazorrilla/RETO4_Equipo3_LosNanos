package Pojos.Person;

import java.util.Objects;

import Pojos.ZooTicket.Zoo;

public class Client extends Person {

	private static final long serialVersionUID = 6499057011243982254L;
	
	private int clientId = 0;

	/*
	 * Existe una relacion 1:N con Zoo
	 */
	
	private Zoo zoo = null;

	public Client(String name, String surname, String id, String user, String password, int clientId, Zoo zoo) {
		super(name, surname, id, user, password);
		this.clientId = clientId;
		this.zoo = zoo;
	}

	public Client() {
		super();
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(clientId, zoo);
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
		Client other = (Client) obj;
		return clientId == other.clientId && Objects.equals(zoo, other.zoo);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", zoo=" + zoo + ", name=" + name + ", surname=" + surname + ", id="
				+ id + ", user=" + user + ", password=" + password + "]";
	}
	
}
