/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public abstract class Person {
	
	private String name;
	private int age;
	private String pic;
	private String status;
	private ArrayList<RelationshipStore> relationship = new ArrayList<RelationshipStore>();
	
	
	public abstract void displayProfile();
	
	/**
	 * @param name
	 * @param age
	 * @param pic
	 * @param status
	 */
	
	public Person(String name, int age, String pic, String status) {
		this.name = name;
		this.age = age;
		this.pic = pic;
		this.status = status;
	}
	
	/**
	 * @return the relationship
	 */
	public ArrayList<RelationshipStore> getRelationship() {
		return relationship;
	}
	
	public RelationshipStore getRelationByPerson(Person ps)
	 {
	  RelationshipStore tempRelation = new RelationshipStore();
	  for(int i=0;i<relationship.size();i++)
	  {
	   if(relationship.get(i).getRelevantPerson().equals(ps))
	    tempRelation=relationship.get(i);
	  }
	  return tempRelation;
	 }

	public Person() {};
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
	public String getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
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
	
	public void addRelationship(RelationshipStore relationship) {
		this.relationship.add(relationship);
	}

}
