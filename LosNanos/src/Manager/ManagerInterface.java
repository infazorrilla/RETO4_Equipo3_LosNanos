package Manager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManagerInterface<T> {
	
	public ArrayList<T> selectAll () throws SQLException, Exception;
	
	public void insert (T t) throws SQLException, Exception;

	public void update (T t) throws SQLException, Exception;
	
	public void delete (T t) throws SQLException, Exception;

}
