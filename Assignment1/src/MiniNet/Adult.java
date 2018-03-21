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
	public boolean addFriend(Person selectedPerson) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void removeFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		this.getRelationship().remove(seletedPerson);
	}
	
	public void addChildren(Person seletedPerson)
	{
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Child");
		friendRelation.setRelevantPerson(seletedPerson);
		this.addRelationship(friendRelation);
	}


	@Override
	public boolean isFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		for(int i=0; i < this.getRelationship().size();i++ ) {
			if(this.getRelationship().get(i).getRelevantPerson().equals(seletedPerson)) {
				if(this.getRelationship().get(i).getRelationType().equals("Friend"))
					return true;
			}
		}
		return false;
	}
	
	public boolean isInRelationship(Person seletedPerson) {
		// TODO Auto-generated method stub
		for(int i=0; i < this.getRelationship().size();i++ ) {
			if(this.getRelationship().get(i).getRelevantPerson().equals(seletedPerson)) {
				
					return true;
			}
		}
		return false;
	}
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
	

	
}
