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
	public boolean removeFriend(Person seletedPerson) {
		
		
		this.getRelationship().remove(this.getRelationByPerson(seletedPerson));
		seletedPerson.getRelationship().remove(seletedPerson.getRelationByPerson(this));
		return true;
		  
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
	
	
	
	public void addChildren(Person seletedPerson)
	{
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Child");
		friendRelation.setRelevantPerson(seletedPerson);
		this.addRelationship(friendRelation);
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

	@Override
	public ArrayList<Person> getFriendList() {
		// TODO Auto-generated method stub
		ArrayList<Person> tempPerson = new ArrayList();
		for(int i=0;i<this.getRelationship().size();i++)
		{
			if(this.getRelationship().get(i).getRelationType().equals("Friend"))
				tempPerson.add(this.getRelationship().get(i).getRelevantPerson());
		}
		return tempPerson;
	}
	

	
}
