package Pojos.ZooTicket;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Ticket implements Serializable{


	private static final long serialVersionUID = 2537052963906824962L;
	private int idTicket = 0;
	private int id_Zoo = 0;
	private int id_Client= 0;
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
	public int getId_Zoo() {
		return id_Zoo;
	}
	public void setId_Zoo(int id_Zoo) {
		this.id_Zoo = id_Zoo;
	}
	public int getId_Client() {
		return id_Client;
	}
	public void setId_Client(int id_Client) {
		this.id_Client = id_Client;
	}
}