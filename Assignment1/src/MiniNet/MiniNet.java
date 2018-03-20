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
			
			/*addFriends*/
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
			/*addParents*/
			System.out.println("Do you want to add parents? Y/N");

			if(sr.nextLine().contentEquals("Y")){

				addParents(pr);


			}
		}
		else if(pr instanceof Children)
		{
			addParents(pr);
			/*addFriends*/
			System.out.println("Do you want to add friends? Y/N");
			if (sr.nextLine().contentEquals("Y")) {
				System.out.println("Please input friend name: ");
				frName = sr.nextLine();
				for (int i = 0; i < member.size(); i++) {
					if (member.get(i).getName().equals(frName)) {
						friend = member.get(i);
						((Children) pr).addFriends(friend);
						System.out.println("Add Friend successful!");

						break;
		}
		
	}
			}
			}else/* it is Baby*/{
				
				addParents(pr);
			}
		}
	
/*	private void displayPersonDetail(Person p)
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
	*/
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
			member.get(selectPersonNum-1).displayProfile();
		
		
	}
	
	
	public void addParents(Person pr) {
		Scanner sr = new Scanner(System.in);
		String[] parentsName = new String[2];
		Person[] parents = new Person[2];
		//boolean[] isSetParents = {false, false};
		
		System.out.println("Please input parent 1 name: ");
		parentsName[0]= sr.nextLine();
		parents[0]=setParents(parentsName[0], pr);
		parentsName[0]=parents[0].getName();
		System.out.println("Please input parent 2 name: ");
		parentsName[1]= sr.nextLine();
		
		System.out.println("xxxxxx"+parentsName[0]+" "+parentsName[1]);
		while(parentsName[1].equals(parentsName[0]))
		{
			System.out.println("Same name with parent1. Please input again. ");
			parentsName[1]= sr.nextLine();
		}
		parents[1]=setParents(parentsName[1], pr);
		
		//parentsName[1]= sr.next();
		/*
		while(parentsName[0].equals(parentsName[1]))
		{
			System.out.println("Same name! Please input again: ");
			parentsName[0]= sr.next();
			parentsName[1]= sr.next();
		}*/
		
		
		/*
		while(!isInList(parentsName[0])) {
			System.out.println(parentsName[0] + " is not in system list. Please check the name and input again.");
			parentsName[0] = sr.nextLine();
			if(parentsName[0].equals(parentsName[1]))
			{
				System.out.println("Same name with another parents, please input again!");
				parentsName[0] = sr.nextLine();
			}
		}
		while(!isInList(parentsName[1])) {
			System.out.println(parentsName[1] + " is not in system list. Please check the name and input again.");
			parentsName[1] = sr.nextLine();
			if(parentsName[1].equals(parentsName[0]))
			{
				System.out.println("Same name with another parents, please input again!");
				parentsName[1] = sr.nextLine();
			}
		}
		*/
		
		
		/*
		while (!isSetParents[0]) {
			if(isInList(parentsName[0])) {
				if(getPerson(parentsName[0]) instanceof Adult) {
					
					parents[0] = getPerson(parentsName[0]);
					((Adult)getPerson(parentsName[0])).addChildren(pr);
					isSetParents[0]=true;
					//((Adult)getPerson(parentsName[1])).addChildren(pr);
				}
			}else {
				System.out.println(parentsName[0] + " is not in system list. Please check the name and input again.");
				parentsName[0] = sr.nextLine();
			}
			
			while(!isSetParents[1]) {
				
			}*/
//				for (int i = 0; i < member.size(); i++) {
//				if (member.get(i).getName().equals(parentsName[0]) && member.get(i) instanceof Adult) {
//					parents[0] = member.get(i);
//					((Adult)member.get(i)).addChildren(pr);
//					isSetParents[0] = true;
//					//break;
//				}
//				if (member.get(i).getName().equals(parentsName[1]) && member.get(i) instanceof Adult) {
//					parents[1] = member.get(i);
//					((Adult)member.get(i)).addChildren(pr);
//					isSetParents[1] = true;
//					//break;
//				}
//
//			}
		
//			if (!isSetParents[0]) {
//				System.out.println(parentsName[0] + " is not in system list. Please check the name and input again.");
//				parentsName[0] = sr.nextLine();
//				if(parentsName[0].equals(parentsName[1]))
//				{
//					System.out.println("Same name with another parents, please input again!");
//					parentsName[0] = sr.nextLine();
//				}
//			}
//			if (!isSetParents[1]) {
//				System.out.println(parentsName[1] + " is not in system list. Please check the name and input again.");
//				parentsName[1] = sr.nextLine();
//				if(parentsName[1].equals(parentsName[0]))
//				{
//					System.out.println("Same name with another parents, please input again!");
//					parentsName[1] = sr.nextLine();
//				}
//
//			}
////		}
		((Children)pr).setParents(parents);
		System.out.println("Add Parents successful!\n");
	}
	
	public Person setParents(String parentName, Person pr) {
		Scanner sr= new Scanner(System.in);
		Person parent = new Adult();
		boolean isSetParent = false;
		while(!isSetParent) {
		if(isInList(parentName)) {
			if(getPerson(parentName) instanceof Adult) {
				
				parent= getPerson(parentName);
			
				((Adult)getPerson(parentName)).addChildren(pr);
				isSetParent= true;
				//isSetParents[0]=true;
				//((Adult)getPerson(parentsName[1])).addChildren(pr);
			}
		}else {
			System.out.println(parentName + " is not in system list. Please check the name and input again.");
			parentName = sr.nextLine();
		}
		}
		return parent;
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
		Person selectedPerson;
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input person name: ");
		perName=sr.nextLine();
		while(!isInList(perName))
		{
			System.out.println("The person is not in the list. Please input again. ");
			perName=sr.nextLine();
		}
		selectedPerson = getPerson(perName);
		System.out.println("Select "+ selectedPerson.getName()+" is successful!\n");
		selectOptions(selectedPerson);
		
		
	}
	
	public void displaySelectOption()
	{
		System.out.println("Selection Option\n"+"===================================");
		System.out.println("1. Display Profile.");
		System.out.println("2. Update Profile.");
		System.out.println("3. Delete person.");
		System.out.println("4. Back.");
		System.out.println("Enter an option: ");
	}
	
	public void selectOptions(Person selectedPerson)
	{
		int selectOptionNum=0;
		boolean goBackMainMenu = false;
		
		Scanner sr = new Scanner(System.in);
		while (!goBackMainMenu) {
			do {
				displaySelectOption();
				selectOptionNum = sr.nextInt();
			} while (selectOptionNum < 1 || selectOptionNum > 4);

			switch (selectOptionNum) {
			case 1:
				selectedPerson.displayProfile();
				break;
			case 2:
				updateProfile(selectedPerson);
				break;
			case 3:
				deletePerson(selectedPerson);
				goBackMainMenu = true;
				break;
			case 4:
				goBackMainMenu = true;
				break;
			}	
		}
	}
	
	//@Emma
	public void updateProfile(Person selectedPerson)
	{
		
	}
	
	public void deletePerson(Person selectedPerson) {
		
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
