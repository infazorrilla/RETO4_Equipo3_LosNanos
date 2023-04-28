package Pojos.ZooTicket;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Ticket implements Serializable{


	private static final long serialVersionUID = 2537052963906824962L;
	private int idTicket = 0;
	private Date buyDate;
	
	public int getIdTicket() {
		return idTicket;
	}
	public void setId(int idTicket) {
		this.idTicket = idTicket;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(buyDate, idTicket);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(buyDate, other.buyDate) && idTicket == other.idTicket;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + idTicket + ", buyDate=" + buyDate + "]";
	}
}
