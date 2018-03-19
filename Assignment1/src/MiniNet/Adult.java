/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Adult extends Person implements FriendsController, ParentsController{
	
	private ArrayList<Person> friendList = new ArrayList(); 
	private ArrayList<Person> childrenList = new ArrayList(); 
	
	private Person[] parents = new Person[2];
	
	public Adult()
	{
		super();
	}
	
	public Adult(String name, int age, String pic, String status)
	{
		super(name,age,pic,status);
	}
	
	@Override
	void displayProfile() {
		// TODO Auto-generated method stub
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayFriendsList();
		displayChildrenList();
		System.out.println("");
	}
	
	public ArrayList getFriendsList() {

		return friendList;
		
	}
	
	public boolean addFriends(Person pr) {
		
		friendList.add(pr);	
		((Adult)pr).friendList.add(this);	
		return true;		
		
	}
	
	public void displayFriendsList()
	{
		System.out.print("Frinds: ");
		for(int i =0;i<friendList.size();i++)
			System.out.print(friendList.get(i).getName()+" ");
		System.out.println("");		
	}
	
	
	
	public boolean isFriend(String tempPersonName)
	{
		for(int i=0; i < friendList.size();i++ )
		{
			if(tempPersonName.equals(friendList.get(i).getName()))
				return true;
		}
		return false;
	}
	
	
	
	public boolean addChildren(Person pr)
	{
		childrenList.add(pr);
		return true;
	}
	
	public void displayChildrenList()
	{
		System.out.print("Children: ");
		for(int i = 0; i<childrenList.size();i++)
		{
			System.out.print(childrenList.get(i).getName()+" ");
		}
		System.out.println("");
	}
	
	
	public void setParents(Person[] parents) {
		this.parents = parents;
	}

	public Person[] getParents() {

		return parents;
	}

	public void displayParents() {
		System.out.println("Parents: " + getParents()[0].getName() + " " + getParents()[1].getName());
	}

	
}
