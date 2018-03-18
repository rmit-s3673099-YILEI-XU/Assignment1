/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Adult extends Person implements AddFriends{
	
	private ArrayList<Person> friendList = new ArrayList(); 
	private ArrayList<Person> childrenList = new ArrayList(); 
	
	private String[] parents = new String[2];
	
	public Adult()
	{
		super();
	}
	public Adult(String name, int age, String pic, String status)
	{
		super(name,age,pic,status);
	}
	
	
	public boolean addFriends(Person pr) {
				
		//String[] prFriendList =((Adult)pr).returnFriendsList();
		
		friendList.add(pr);	
		((Adult)pr).friendList.add(this);
		/*
		if(prFriendList.length==0)
		{
			((Adult)pr).friendList.add(this);
			//System.out.println(this.getName());
		}
		else {
		//if(pr instanceof Adult) {
		
			for(int i =0;i<prFriendList.length;i++)
			{
				if(this.getName().equals(prFriendList[i]))
				{
					break;
				}
				else
				{
					((Adult)pr).friendList.add(this);
				}
			}
		//}
		
		}*/
		return true;
		
	}
	

	
	public String[] returnFriendsList() {
		
		String showFriendList[]= new String[friendList.size()];
		for(int i=0; i < friendList.size();i++ )
		{
			showFriendList[i]=friendList.get(i).getName();
		}
		
		return showFriendList;
		
	}
	
	public void displayFriendsList()
	{
		System.out.print("Frinds: ");
		for(int i =0;i<returnFriendsList().length;i++)
			System.out.print(returnFriendsList()[i]+" ");
		System.out.println("");
			
	}
	
	public boolean addChildren(Person pr)
	{
		childrenList.add(pr);
		return true;
	}
	
	public void displayChildrenList()
	{
		System.out.print("Children: ");
		for(int i = 0; i<friendList.size();i++)
		{
			System.out.print(childrenList.get(i).getName()+" ");
		}
	}
}
