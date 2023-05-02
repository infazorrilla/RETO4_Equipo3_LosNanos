package Pojos.Person;

import java.util.Objects;

public class Client extends Person {

	private int clientId;
	private static final long serialVersionUID = 6499057011243982254L;

	public Client(String name, String surname, String id, String user, String password, int clientId) {
		super(name, surname, id, user, password);

		this.clientId = clientId;
	}
	
	public Client() {
		
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(clientId);
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
		return clientId == other.clientId;
	}

	@Override
	public String toString() {
		return "Cliente [clientId=" + clientId + "]";
	}

}
