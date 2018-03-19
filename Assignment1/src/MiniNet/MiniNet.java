/**
 * 
 */
package MiniNet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yilei Xu
 *
 */
public class MiniNet {

	private ArrayList<Person> member = new ArrayList();
	private Person selectedPerson;
	private int selectMenuNum = 0;
	private int selectPersonNum = 0;
	private boolean isExit = false;
	//private int memberNum=-1;
	
	private void mainMenu()
	{
		
		Scanner sr = new Scanner(System.in);	
		do {
				System.out.print(
				"MiniNet Menu\n"+
				"===================================\n"+
				"1. List everyone\n" + 
				"2. Select a person\n" + 
				"3. Are these two direct friends?\n" + 
				"4. Add a person\n" + 
				"5. Delete a person\n" + 
				"6. Exit\n" + 
				"Enter an option:\n");
		
			try {
				
				selectMenuNum = sr.nextInt();
				
			} catch (Exception e) {
				System.out.println("Please input a number from menu number.");
				selectMenuNum = 0;
				break;
			}
			
			if(selectMenuNum<1||selectMenuNum>5)
				System.out.println("Please input a number from menu number.\n");
		
		}
		while(selectMenuNum<1 || selectMenuNum>5);
	}

	
	private void addPerson()
	{
		Person pr,friend;
		Adult parent;
		String prName, prPic, prStatus, frName;
		int prAge;
		
		
		
		//more details @Emma
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input Person Name:");
		prName = sr.nextLine();
		System.out.println("Please input Person Age:");
		prAge = sr.nextInt();
		sr.nextLine();		
		System.out.println("Do you want to set a pic? Y/N");
		prPic= sr.nextLine();
		System.out.println("Please input Person status. If there is no status, please input none.");
		prStatus= sr.nextLine();
		
		
		//System.out.println(prName+" "+prAge+" "+ prPic+" "+prStatus+" ");
		
		if(prAge>=16)
			pr = new Adult();
		else if(prAge>2)
			pr = new Children();
		else 
			pr = new Baby();		
		pr.setName(prName);
		pr.setAge(prAge);
		pr.setPic(prPic);
		pr.setStatus(prStatus);
		
		member.add(pr);
		
		System.out.println("Add person successful!");
		
		if (pr instanceof Adult) {
			
			System.out.println("Do you want to add friend? Y/N");
			// more details @Emma
			if (sr.nextLine().contentEquals("y")) {
				System.out.println("Please input friend name: ");
				frName = sr.nextLine();
				for (int i = 0; i < member.size(); i++) {
					if (member.get(i).getName().equals(frName)) {
						friend = member.get(i);
						((Adult) pr).addFriends(friend);
						System.out.println("Add Friend successful!");
						break;
					}

				}
			}
		}
		else if(pr instanceof Children)
		{
			addParents(pr);

		}
		
	}
	
	private void displayPersonDetail(Person p)
	{
		System.out.println("Person Detail\n"+"===================================");
		System.out.println("Name:"+p.getName());
		System.out.println("Age:"+p.getAge());
		System.out.println("Picture: "+p.getPic());
		System.out.println("Stautus: "+p.getStatus());
		if(p instanceof Adult)
		{
			((Adult) p).displayFriendsList();
			((Adult) p).displayChildrenList();
			System.out.println("");
		}
		else if(p instanceof Children)
		{
			((Children)p).displayParents();
		}
		
	}
	
	private void listEveryone()
	{
		for(int i =0;i<member.size();i++)
		{
			if(member.get(i) instanceof Adult)
				System.out.println(i+1+". "+member.get(i).getName() +" Adult");
			else if(member.get(i) instanceof Children)
				System.out.println(i+1+". "+member.get(i).getName() +" Children");
			else if(member.get(i) instanceof Baby)
				System.out.println(i+1+". "+member.get(i).getName() +" Baby");
		}
		System.out.println(member.size()+1+". Back");
		System.out.println("Enter an option:");
		
		Scanner sr = new Scanner(System.in);
		try {
			
			selectPersonNum = sr.nextInt();
			
		} catch (Exception e) {
			System.out.println("Please input a number from menu number.");
			selectPersonNum = 0;
			
		}
		if(selectPersonNum !=member.size()+1)
			displayPersonDetail(member.get(selectPersonNum-1));
		
		
	}
	
	
	public void addParents(Person pr) {
		Scanner sr = new Scanner(System.in);
		String[] parentsName = new String[2];
		Person[] parents = new Person[2];
		boolean[] isSetParents = {false, false};
		
		System.out.println("Please input parents name: ");
		parentsName[0]= sr.next();
		parentsName[1]= sr.next();
		while(parentsName[0].equals(parentsName[1]))
		{
			System.out.println("Same name! Please input again: ");
			parentsName[0]= sr.next();
			parentsName[1]= sr.next();
		}
		sr.nextLine();
		
		while (!isSetParents[0] || !isSetParents[1]) {
			
			for (int i = 0; i < member.size(); i++) {
				if (member.get(i).getName().equals(parentsName[0]) && member.get(i) instanceof Adult) {
					parents[0] = member.get(i);
					((Adult)member.get(i)).addChildren(pr);
					isSetParents[0] = true;
					//break;
				}
				if (member.get(i).getName().equals(parentsName[1]) && member.get(i) instanceof Adult) {
					parents[1] = member.get(i);
					((Adult)member.get(i)).addChildren(pr);
					isSetParents[1] = true;
					//break;
				}

			}
		
			if (!isSetParents[0]) {
				System.out.println(parentsName[0] + " is not in system list. Please check the name and input again.");
				parentsName[0] = sr.nextLine();
				if(parentsName[0].equals(parentsName[1]))
				{
					System.out.println("Same name with another parents, please input again!");
					parentsName[0] = sr.nextLine();
				}
			}
			if (!isSetParents[1]) {
				System.out.println(parentsName[1] + " is not in system list. Please check the name and input again.");
				parentsName[1] = sr.nextLine();
				if(parentsName[1].equals(parentsName[0]))
				{
					System.out.println("Same name with another parents, please input again!");
					parentsName[1] = sr.nextLine();
				}

			}
		}
		((Children)pr).setParents(parents);
		System.out.println("Add Parents successful!\n");
	}
	
	public void displayIsFriends()
	{
		Person tempPerson;
		String[] peopleName = new String[2];
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input two names: ");
		peopleName[0]=sr.next();
		peopleName[1]=sr.next();
		sr.nextLine();
		while(!isInList(peopleName[0]))
		{
			System.out.println(peopleName[0]+" is not in the list. Please input again. ");
			peopleName[0]=sr.next();
		}
		tempPerson=getPerson(peopleName[0]);
		
		if(tempPerson instanceof Adult)
		{
			
			if(((Adult)tempPerson).isFriend(peopleName[1]))
				System.out.println(peopleName[0]+" and "+peopleName[1]+" are Friends\n");
			else
				System.out.println(peopleName[0]+" and "+peopleName[1]+" are not Friends\n");
		}
	
	}
	
	public boolean isInList(String inputName)
	{
		for (int i = 0;i<member.size();i++)
		{
			if(inputName.equals(member.get(i).getName()))
				{
				//memberNum=i;
				return true;
				}
		}
		return false;
	}
	
	
	public Person getPerson(String inputName)
	{
		int index=-1;
		for (int i = 0;i<member.size();i++)
		{
			if(inputName.equals(member.get(i).getName()))
				{
				index=i;
				}
		}
		return member.get(index);

	}
	

	
	public void selectPersonByName()
	{
		String perName;
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input person name: ");
		perName=sr.nextLine();
		while(!isInList(perName))
		{
			System.out.println("The person is not in the list. Please input again. ");
			perName=sr.nextLine();
		}
			displayPersonDetail(getPerson(perName));		
		
	}
	
	
	
	public void runProfile() {
		
		testData();
		while (!isExit) {
			mainMenu();
			switch (selectMenuNum) {
			case 1:
				listEveryone();
				break;
			case 2:
				selectPersonByName();
				break;
			case 3:
				displayIsFriends();
				break;
			case 4:
				addPerson();
				break;
			case 5:
				break;
			default:
				break;			
			}
		}

		System.out.println("good!");
	}
	
	private void testData()
	{
		Person AA = new Adult("AA",20,"Y","PP");
		Person BB = new Adult("BB",28,"N","OO");
		Person CC = new Adult("CC",18,"Y","XX");
		Person DD = new Adult("DD",30,"Y","TT");
		((Adult)AA).addFriends(BB);
		((Adult)AA).addFriends(DD);
		((Adult)CC).addFriends(DD);
		member.add(AA);
		member.add(BB);
		member.add(CC);
		member.add(DD);
	}

}
