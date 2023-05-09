package Pojos.ZooTicket;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Ticket implements Serializable{


	private static final long serialVersionUID = 2537052963906824962L;
	private int idTicket = 0;
	private Date buyDate = null;
	public int getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
	
}
