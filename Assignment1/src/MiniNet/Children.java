/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Children extends Person implements FriendRelation,ParentRelation{
//	private ArrayList<Person> friends = new ArrayList();
//	private Person parents[]= new Person[2];
	
	
	public Children() {

        super();
        
        }

	public Children(String name, int age, String pic, String status)
        {
		
        super(name,age,pic,status);
        
        }
	/**
	 * @return the parents
	 */
//	public Person[] getParents() {
//		return parents;
//	}
//	/**
//	 * @param parents the parents to set
//	 */
//	public void setParents(Person[] parents) {
//		this.parents = parents;
//	}
//	
//	public void displayParents()
//	{
//		System.out.println("Parents: "+getParents()[0].getName()+" "+getParents()[1].getName());
//	}
//	
	@Override
	public void displayProfile() {
		// TODO Auto-generated method stub
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayRelationship();
		System.out.println("");
		
	}

	private void displayRelationship() {
		// TODO Auto-generated method stub
		System.out.print("Frinds: ");
		for(int i =0;i<super.getRelationship().size();i++) {
			if(super.getRelationship().get(i).getRelationType().equals("Friend"))
			System.out.print(super.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
		
		System.out.print("Parents: ");
		for(int i =0;i<super.getRelationship().size();i++) {
			if(super.getRelationship().get(i).getRelationType().equals("Parent"))
			System.out.print(super.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
	}

	@Override
	public boolean addFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Friend");
		
		int ageGap = Math.abs(this.getAge() - seletedPerson.getAge());

		if (super.getRelationship().get(super.getRelationship().indexOf("Parent")).getRelevantPerson().equals(
				seletedPerson.getRelationship().get(seletedPerson.getRelationship().indexOf("Parent")).getRelevantPerson())) {
			System.out.println("They are in same family, they cannot be friends.");
			return false;

		} else {

			if (ageGap <= 3) {
				friendRelation.setRelevantPerson(seletedPerson);
				super.addRelationship(friendRelation);
				friendRelation.setRelevantPerson(this);
				((Children) seletedPerson).addRelationship(friendRelation);
				return true;
			} else {
				System.out.println("Gap is too big, they cannot be friends.");
				return false;
			}
		}
	
	}

	@Override
	public void removeFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFriend(Person seletedPerson) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addParent(Person seletedPerson) {
		// TODO Auto-generated method stub
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Parent");
		friendRelation.setRelevantPerson(seletedPerson);
		super.addRelationship(friendRelation);
	}
	
//	@Override
//	public boolean addFriend(Person pr) {
//		int ageGap = Math.abs(this.getAge() - pr.getAge());
//
//		if (parents[0].equals(((Children) pr).getParents()[0]) || parents[0].equals(((Children) pr).getParents()[1])) {
//			System.out.println("They are in same family, they cannot be friends.");
//			return false;
//		
//		} else {
//			
//			if (ageGap <= 3) {
//				friends.add(pr);
//				((Children) pr).friends.add(this);
//				return true;
//			} else {
//				System.out.println("Gap is too big, they cannot be friends.");
//				return false;
//			}
//		}
//	}
//
//     public ArrayList getFriendsList() {
//
//            return friends;
//
//        }
//     
//     public void displayFriendsList()
// 	{
// 		System.out.print("Frinds: ");
// 		for(int i =0;i<friends.size();i++)
// 			System.out.print(friends.get(i).getName()+" ");
// 		System.out.println("");		
// 	}
//
//	@Override
//	public void removeFriend(Person seletedPerson) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isFriend(Person seletedPerson) {
//		// TODO Auto-generated method stub
//		return false;
//	}



}
