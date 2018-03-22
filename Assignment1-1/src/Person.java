import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author sens_x
 *
 */
public abstract class Person {
	private String name;
	private int age;
	private boolean pic;
	private String status;
	private HashMap<String, ArrayList<Person>> relationships;
	private ArrayList<RelationManipulator> relationManipulators;
	
	public void add() {
		
	}
	
	public void remove() {
		
	}
	
	public void addRelationship(String relation,Person ps)
	{
		
	}
	public void removeRelation(Person ps)
	{
		
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the pic
	 */
	public boolean isPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(boolean pic) {
		this.pic = pic;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
