package Manager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManagerInterface<T> {
	
	/**
	 * 
	 * @return ArrayList
	 * @throws SQLException
	 * @throws Exception
	 * 
	 * Selects all the items in the table and returns an ArrayList of them
	 */
	
	public ArrayList<T> selectAll () throws SQLException, Exception;
	
	/**
	 * 
	 * @throws SQLException
	 * @throws Exception
	 * 
	 * Inserts the item passed from parameter into the database
	 */
	
	public void insert (T t) throws SQLException, Exception;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * 
	 * Updates the item passed from parameter into the database
	 */
	
	public void update (T t) throws SQLException, Exception;
	
	/**
	 * 
	 * @throws SQLException
	 * @throws Exception
	 * 
	 * Deletes the item passed from parameter from the database
	 */
	
	public void delete (T t) throws SQLException, Exception;

}