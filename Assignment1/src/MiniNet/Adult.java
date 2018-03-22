/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * This class is the subclass of Person, it creates Adult type Person 
 * @author Yilei Xu
 *
 */
public class Adult extends Person implements FriendRelation{

	/**
	 * This is the constructor is for creating the Adult object from the user
	 */
	public Adult()
	{
		super();
	}
	
	/**
	 * This constructor is for creating the Adult object in the main method, to store some people in the system
	 * @param name the name of the person
	 * @param age the age of the person
	 * @param pic the picture of the person
	 * @param status the status of the person
	 */
	public Adult(String name, int age, String pic, String status)
	{
		super(name,age,pic,status);
	}
	
	
	/**
	 *  This method display the person's basic profile and the relationship 
	 */
	public void displayProfile() {
		 
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayRelationship();
		System.out.println("");
	}

	/**
	 * This method adds friend to the person's relationship list, and also add the person to the friend's relationship list
	 */
	public boolean addFriend(Person selectedPerson) {
		
		RelationshipStore friendRelation1 = new RelationshipStore();//for own relationship
		RelationshipStore friendRelation2 = new RelationshipStore();//for friend's relationship
		
		friendRelation1.setRelationType("Friend");
		friendRelation2.setRelationType("Friend");
		
		friendRelation1.setRelevantPerson(selectedPerson);
		this.addRelationship(friendRelation1);
		
		friendRelation2.setRelevantPerson((Person)this);
		selectedPerson.addRelationship(friendRelation2);
		
		return true;
	}

	/**
	 * This method remove the friend from the person's relationship list
	 */
	public boolean removeFriend(Person seletedPerson) {
		
		this.getRelationship().remove(this.getRelationByPerson(seletedPerson));
		seletedPerson.getRelationship().remove(seletedPerson.getRelationByPerson(this));
		return true;
	}

	/**
	 * This method check if the selected person is this person's friend
	 */
	public boolean isFriend(Person seletedPerson) {
		
		for(int i=0; i < this.getRelationship().size();i++ ) {
			if(this.getRelationship().get(i).getRelevantPerson().equals(seletedPerson)) {
				if(this.getRelationship().get(i).getRelationType().equals("Friend"))
					return true;
			}
		}
		return false;
	}

	/**
	 * This method add child to the parents
	 * @param seletedPerson
	 */
	public void addChildren(Person seletedPerson)
	{
		RelationshipStore ChildRelation = new RelationshipStore();
		ChildRelation.setRelationType("Child");
		ChildRelation.setRelevantPerson(seletedPerson);
		this.addRelationship(ChildRelation);
	}
	
	/**
	 * This method display the relationship list of the person, include Friend, couple and children
	 */
	private void displayRelationship()
	{
		System.out.print("Friends: ");
		for(int i =0;i<this.getRelationship().size();i++) {
			if(this.getRelationship().get(i).getRelationType().equals("Friend"))
			System.out.print(this.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");	
		
		System.out.print("Couple: ");
		
		for(int i =0;i<this.getRelationship().size();i++) {
			if(this.getRelationship().get(i).getRelationType().equals("Couple"))
			System.out.print(this.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
		System.out.print("Children: ");
		
		for(int i =0;i<this.getRelationship().size();i++) {
			if(this.getRelationship().get(i).getRelationType().equals("Child"))
			System.out.print(this.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
	}

	/**
	 * This method get the friend list of the person
	 * return tempPerson is a friend list
	 */
	public ArrayList<Person> getFriendList() 
	{
		ArrayList<Person> tempPerson = new ArrayList();
		for(int i=0;i<this.getRelationship().size();i++)
		{
			if(this.getRelationship().get(i).getRelationType().equals("Friend"))
				tempPerson.add(this.getRelationship().get(i).getRelevantPerson());
		}
		return tempPerson;
	}
	
}
