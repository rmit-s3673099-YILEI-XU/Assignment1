/**
 * 
 */


import java.util.ArrayList;

/**
 * This class is an abstract class which contains a general person's information and function
 * @author Yilei Xu
 *
 */
public abstract class Person {
	
	private String name;
	private int age;
	private String pic;
	private String status;
	private ArrayList<RelationshipStore> relationship = new ArrayList<RelationshipStore>();
	
	/**
	 * This is the constructor for the subclass to create the object from the user
	 */
	public Person() {};
	
	/**
	 * This constructor is for subclass to create the object in the main method, to store some people in the system
	 * @param name the name of the person
	 * @param age the age of the person
	 * @param pic the picture of the person
	 * @param status the status of the person
	 */
	public Person(String name, int age, String pic, String status) {
		this.name = name;
		this.age = age;
		this.pic = pic;
		this.status = status;
	}
	
	/**
	 *  This method display the person's basic profile 
	 */
	public void displayProfile() {
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name:     "+this.getName());
		System.out.println("Age:      "+this.getAge());
		System.out.println("Picture:  "+this.getPic());
		System.out.println("Status:  "+this.getStatus());
	}
	
	
	/**
	 * This method is getting the relationship list of the person
	 * @return the relationship
	 */
	public ArrayList<RelationshipStore> getRelationship() {
		return relationship;
	}
	
	/**
	 * This method is getting the relation by a Person object, the relation includes relevant person and relation type,
	 * @param ps this is the person which is going to be get with the relationship type
	 * @return return relationship type and the person
	 */
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
	
	/**
	 * This is the method to check if the person is in the relationship list
	 * @param seletedPerson this is the person to be checked
	 * @return true or false
	 */
	public boolean isInRelationship(Person seletedPerson) {
		
		for(int i=0; i < this.getRelationship().size();i++ ) {
			if(this.getRelationship().get(i).getRelevantPerson().equals(seletedPerson)) {
				
					return true;
			}
		}
		return false;
	}

	/**
	 * This is the method to set the person's name
	 * @param name  the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This is the method to get the person's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This is the method to set the age of the person
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * this is the method to get the age of the person
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * This is the method to set the picture 
	 * @param pic the picture to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	/**
	 * This is the method to get if the person has a picture 
	 * @return the picture
	 */
	public String getPic() {
		return pic;
	}
	
	/**
	 * This is the method to set the person's status
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * This is the method to get the status of the person
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * This method is adding relationship to the relationship ArrayList
	 * @param relationship the relationship need to be added
	 */
	public void addRelationship(RelationshipStore relationship) {
		this.relationship.add(relationship);
	}

}
