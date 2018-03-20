/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Adult extends Person implements FriendRelation{
	
//	private ArrayList<Person> friendList = new ArrayList(); 
//	private ArrayList<Person> childrenList = new ArrayList(); 
//	
//	private Person[] parents = new Person[2];
	
	public Adult()
	{
		super();
	}
	
	public Adult(String name, int age, String pic, String status)
	{
		super(name,age,pic,status);
	}
	
	@Override
	public void displayProfile() {
		 //TODO Auto-generated method stub
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayRelationship();
		System.out.println("");
	}

	@Override
	public boolean addFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Friend");
		friendRelation.setRelevantPerson(seletedPerson);
		super.addRelationship(friendRelation);
		friendRelation.setRelevantPerson(this);
		seletedPerson.addRelationship(friendRelation);
		return true;

		
	}

	@Override
	public void removeFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		super.getRelationship().remove(seletedPerson);
	}
	
	public void addChildren(Person seletedPerson)
	{
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Child");
		friendRelation.setRelevantPerson(seletedPerson);
		super.addRelationship(friendRelation);
	}


	@Override
	public boolean isFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		for(int i=0; i < super.getRelationship().size();i++ ) {
			if(super.getRelationship().get(i).getRelevantPerson().equals(seletedPerson)) {
				if(super.getRelationship().get(i).getRelationType().equals("Friend"))
					return true;
			}
		}
		return false;
	}
	
	private void displayRelationship()
	{
		System.out.print("Frinds: ");
		for(int i =0;i<super.getRelationship().size();i++) {
			if(super.getRelationship().get(i).getRelationType().equals("Friend"))
			System.out.print(super.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");	
		System.out.print("Children: ");
		
		for(int i =0;i<super.getRelationship().size();i++) {
			if(super.getRelationship().get(i).getRelationType().equals("Child"))
			System.out.print(super.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
	}
	
//	public ArrayList<Person> getFriendsList() {
//
//		return friendList;
//		
//	}
	
//	public boolean addFriends(Person pr) {
//		
//		friendList.add(pr);	
//		((Adult)pr).friendList.add(this);	
//		return true;		
//		
//	}
//	
//	public void displayFriendsList()
//	{
//		System.out.print("Frinds: ");
//		for(int i =0;i<friendList.size();i++)
//			System.out.print(friendList.get(i).getName()+" ");
//		System.out.println("");		
//	}
//	
//	
//	

//	
//	
//	

//	
//	public void displayChildrenList()
//	{
//		System.out.print("Children: ");
//		for(int i = 0; i<childrenList.size();i++)
//		{
//			System.out.print(childrenList.get(i).getName()+" ");
//		}
//		System.out.println("");
//	}
//	
//	
//	public void setParents(Person[] parents) {
//		this.parents = parents;
//	}
//
//	public Person[] getParents() {
//
//		return parents;
//	}
//
//	public void displayParents() {
//		System.out.println("Parents: " + getParents()[0].getName() + " " + getParents()[1].getName());
//	}

	
}
