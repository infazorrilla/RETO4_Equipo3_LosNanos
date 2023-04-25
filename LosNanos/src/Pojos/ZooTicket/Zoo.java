package Pojos.ZooTicket;

import java.util.Date;
import java.util.Objects;

public class Zoo{

private static final long serialVersionUID = 1L;
private int id;
private String name;
private String location;


public int setId (int id) {
return id;
}

public String getName (String name) {
return name;
}

public String getLocation (String location) {
return location;
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

public Zoo(int id, String name, String location) {
this.id = id;
this.name = name;
this.location = location;
}

public int getId() {
return id;
}

@Override
public String toString() {
return "Zoo [id=" + id + ", name=" + name + ", location=" + location + ", getClass()=" + getClass()
+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}

}
