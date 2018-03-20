/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Children extends Person implements ParentsController{
	private ArrayList<Person> friends = new ArrayList();
	private Person parents[]= new Person[2];
	
	/**
	 * @return the parents
	 */
	public Person[] getParents() {
		return parents;
	}
	/**
	 * @param parents the parents to set
	 */
	public void setParents(Person[] parents) {
		this.parents = parents;
	}
	
	public void displayParents()
	{
		System.out.println("Parents: "+getParents()[0].getName()+" "+getParents()[1].getName());
	}
	
	@Override
	void displayProfile() {
		// TODO Auto-generated method stub
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayParents();
		displayFriendsList();
		
	}
	
	public boolean addFriends(Person pr) {
		int ageGap = Math.abs(this.getAge() - pr.getAge());

		if (parents[0].equals(((Children) pr).getParents()[0]) || parents[0].equals(((Children) pr).getParents()[1])) {
			System.out.println("They are in same family, they cannot be friends.");
			return false;
		
		} else {
			
			if (ageGap <= 3) {
				friends.add(pr);
				((Children) pr).friends.add(this);
				return true;
			} else {
				System.out.println("Gap is too big, they cannot be friends.");
				return false;
			}
		}
	}

     public ArrayList getFriendsList() {

            return friends;

        }
     
     public void displayFriendsList()
 	{
 		System.out.print("Frinds: ");
 		for(int i =0;i<friends.size();i++)
 			System.out.print(friends.get(i).getName()+" ");
 		System.out.println("");		
 	}

}
