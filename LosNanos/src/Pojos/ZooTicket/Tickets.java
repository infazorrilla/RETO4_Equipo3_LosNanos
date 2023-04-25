package Pojos.ZooTicket;

import java.util.Date;
import java.util.Objects;

public class Tickets extends Zoo{


private static final long serialVersionUID = 1L;
private int id;
private Date buyDate;

public Tickets(int id, String name, String location) {
	super(id, name, location);
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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
	return Objects.hash(buyDate, id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Tickets other = (Tickets) obj;
	return Objects.equals(buyDate, other.buyDate) && id == other.id;
}



}
