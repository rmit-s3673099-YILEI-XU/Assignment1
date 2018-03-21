/**
 * 
 */
package MiniNet;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/**
 * @author Yilei Xu
 *
 */
public class DriverClass {

	private ArrayList<Person> member = new ArrayList();
	private Person selectedPerson;
	private int selectMenuNum = 0;
	private int selectPersonNum = 0;
	private int updateNum = 0;
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
				"5. Exit\n" + 
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

	
	/**
	 * @return the member
	 */
	public ArrayList<Person> getMember() {
		return member;
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
			if (sr.nextLine().contentEquals("Y")) {
				addAdultFriendsProcess(pr);
				
			}
			/*addParents*/
			System.out.println("Do you want to add parents? Y/N");

			if(sr.nextLine().contentEquals("Y")){

				addParentsProcess(pr);


			}
		}
		else if(pr instanceof Children)
		{
			addParentsProcess(pr);
			/*addFriends*/
			System.out.println("Do you want to add friends? Y/N");
			if (sr.nextLine().contentEquals("Y")) {
				addChildrenFriendsProcess(pr);
			
			}
		
		}
			
		else/* it is Baby*/{
				
				addParentsProcess(pr);
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
			member.get(selectPersonNum-1).displayProfile();
		
		
	}
	
	private void addAdultFriendsProcess(Person pr) {
		Scanner sr = new Scanner(System.in);
		String frName;
		Person friend;
		boolean isAddFriend = false;
		System.out.println("Please input friend name: ");
		frName = sr.nextLine();
		
		/* addFriends */
		while (!isAddFriend) {

			while (!isInList(frName)) {
				System.out.println(frName + " is not in system list.Please input again");
				frName = sr.nextLine();
			}
			friend = getPerson(frName);

			if (!frName.equals(pr.getName())) {
				if (!((Adult) pr).isInRelationship(friend)) {
					if (friend instanceof Adult) {
						((Adult) pr).addFriend(friend);
						isAddFriend = true;
						
					} else {
						System.out.println(frName + " is not Adult. They can't make friends. Please input again. ");
						frName = sr.nextLine();
			
					}
				} else {

					System.out.println(frName + " already has relationship with "+ pr.getName()+" . Please input again. ");
					frName = sr.nextLine();
				}

			} else {
				System.out.println(frName + " is the person himself/herself.Please input again");
				frName = sr.nextLine();
			}

		}
		System.out.println("Add Friend successful!");
		
	}
		
	
	private void addChildrenFriendsProcess(Person pr)
	{
		Scanner sr = new Scanner(System.in);
		String frName;
		Person friend;
		boolean isAddFriend = false;
		System.out.println("Please input friend name: ");
		frName = sr.nextLine();
		/* addFriends */
		while(!isAddFriend) {
			
			while (!isInList(frName)) {
				System.out.println(frName + " is not in system list. Please input again. ");
				frName = sr.nextLine();
			}
			friend = getPerson(frName);
			if (friend instanceof Children) {
				if(((Children) pr).addFriend(friend))
					isAddFriend= true;
				else {
					
					frName = sr.nextLine();
					if(frName.equals("back")||frName.equals("BACK"))
						break;
				}
					
			}
			else {
				System.out.println(frName + " is not Children. They can't make friends. Please input again.\nOr you can input 'back' to give up add friends.");
				frName = sr.nextLine();
				if(frName.equals("back")||frName.equals("BACK"))
					break;
			}
		}
		if(isAddFriend) {
			System.out.println("Add Friend successful!");
		}

	}
	
	public void addParentsProcess(Person pr) {
		Scanner sr = new Scanner(System.in);
		String[] parentsName = new String[2];
		Person[] parents = new Person[2];
		//boolean[] isSetParents = {false, false};
		
		System.out.println("Please input parent 1 name: ");
		parentsName[0]= sr.nextLine();
		parents[0]=getLegalParents(parentsName[0], pr);
		//System.out.println(parents[0]+"\n"+getLegalParents(parentsName[0], pr));
		parentsName[0]=parents[0].getName();
		
		System.out.println("Please input parent 2 name: ");
		parentsName[1]= sr.nextLine();
		
		parents[1]=getLegalParents(parentsName[1], pr);
		parentsName[1]=parents[1].getName();
		//Avoid same parents 
		while(parentsName[1].equals(parentsName[0]))
		{
			System.out.println("Same name with parent1. Please input again. ");
			parentsName[1]= sr.nextLine();
			parents[1]=getLegalParents(parentsName[1], pr);
			parentsName[1]=parents[1].getName();
		}
		
	
		((Children)pr).addParent(parents[0]);
		((Children)pr).addParent(parents[1]);
		
		for(int i =0;i<parents[0].getRelationship().size();i++)
		{
			if(parents[0].getRelationship().get(i).getRelevantPerson().equals(parents[1])) {
				parents[0].getRelationship().get(i).setRelationType("Couple");
			}
		}
		for(int i =0;i<parents[1].getRelationship().size();i++)
		{
			if(parents[1].getRelationship().get(i).getRelevantPerson().equals(parents[0])) {
				parents[1].getRelationship().get(i).setRelationType("Couple");
			}
		}
		System.out.println("Add Parents successful!\n");
		
	}
	
	public Person getLegalParents(String parentName, Person pr) {
		Scanner sr = new Scanner(System.in);
		Person parent = new Adult();
		boolean isSetParent = false;

		while (!isSetParent) {
			if (isInList(parentName)) {
				if (getPerson(parentName) instanceof Adult) {

					parent = getPerson(parentName);

					((Adult) getPerson(parentName)).addChildren(pr);
					isSetParent = true;
				}
				else
				{
					System.out.println(parentName + " is not adult. Please check the name and input again.");
					parentName = sr.nextLine();
				}

			} else {
				System.out.println(parentName + " is not in system list. Please check the name and input again.");
				parentName = sr.nextLine();
				//System.out.println("xxxxxxxxxxx");
			}
		}
		return parent;
	}
	

	
	public void displayIsFriends()
	{
		Person tempPerson1,tempPerson2;
		String[] peopleName = new String[2];
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input two names: ");
		peopleName[0]=sr.next();
		peopleName[1]=sr.next();
		sr.nextLine();
		while(!isInList(peopleName[0])||!isInList(peopleName[1]))
		{
			if(!isInList(peopleName[0])){
			System.out.println(peopleName[0]+" is not in the list. Please input again. ");
			peopleName[0]=sr.nextLine();
			}
			else
			{
				System.out.println(peopleName[1]+" is not in the list. Please input again. ");
				peopleName[1]=sr.nextLine();
			}
		}
		tempPerson1=getPerson(peopleName[0]);
		tempPerson2=getPerson(peopleName[1]);
		
		if(tempPerson1 instanceof Adult)
		{
			
			if(((Adult)tempPerson1).isFriend(tempPerson2))
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
				if(deletePerson(selectedPerson))
				{
					System.out.println("Delete successful!\n");
					goBackMainMenu = true;
				}
				else
				{
					System.out.println("Fail to delete!\n");
				}
				
				
				break;
			case 4:
				goBackMainMenu = true;
				break;
			}	
		}
	}
	
    //@Emma
	public void updateProfile(Person selectedPerson) {

        Scanner sr = new Scanner(System.in);

        do {
            System.out.print("select what do you want to update" +
                    "===================================\n" +
                    "1. Name\n"+
                    "2. Age\n" +
                    "3. Pic\n" +
                    "4. Status\n" +
                    "5. Add Friends\n" +
                    "6. Remove Friends\n" +
                    "7. Exit\n" );

            try {

                updateNum = sr.nextInt();
                sr.nextLine();

            } catch (Exception e) {
                System.out.println("Please input a number from menu number.");
                updateNum = 0;
                break;
            }

            if (updateNum < 1 || updateNum > 7)
                System.out.println("Please input a number from menu number.\n");

        } while (updateNum < 1 || updateNum > 6);

        updateSelection(updateNum,selectedPerson);

    }

    public void updateSelection(int updateNum, Person selectedPerson) {

        Scanner sr = new Scanner(System.in);

        switch (updateNum) {

            case 1:
                String prName;
                System.out.println("Please input Person Name:");
                prName = sr.nextLine();
                updateName(prName, selectedPerson);
                break;

            case 2:
                int prAge;
                do{
                    System.out.println("Please input Person Age:");

                    try {
                        prAge = sr.nextInt();
                    } catch (Exception e) {
                        System.out.println("Please input a number.");
                        prAge = sr.nextInt();
                    }

                }while(!updateAge(prAge, selectedPerson));

                break;
            case 3:
                System.out.println("Do you want to change the pic? Y/N");
                if (sr.nextLine().contentEquals("Y")) {
                    String prPic = sr.nextLine();
                    System.out.println("Update successful!");
                }
                break;
            case 4:
                System.out.println("Please input your new status. If there is no status, please input none.");
                String prStatus = sr.nextLine();
                System.out.println("Update successful!");
                break;
            case 5:
                updateAddFriends(selectedPerson);
                break;
            case 6:
                updateRemoveFriends(selectedPerson);
                break;

            default:
                System.out.println("Please input a number from menu number.\n");
                break;

        }
    }

    public boolean updateName(String prName, Person selectedPerson) {
        if(isInList(prName)){
            System.out.println("Name already exist, update fail!");
            return false;
        }else {
            selectedPerson.setName(prName);
            System.out.println("Update successful!");
            return true;
        }

    }
    public boolean updateAge(int prAge, Person selectedPerson) {

        if (selectedPerson instanceof Adult && !(prAge>=16)) {

            System.out.println("Update Fail. Please input the age range in the person type");
            return false;
        } else if (selectedPerson instanceof Children && !(prAge < 16 && prAge > 2)) {
            System.out.println("Update fail. Please input the age range in the person type");
            return false;
        } else if (selectedPerson instanceof Baby && !(prAge <= 2)) {
            System.out.println("Update Fail. Please input the age range in the person type");
            return false;
        } else {
            selectedPerson.setAge(prAge);
            System.out.println("Update successful!");
            return true;
        }

    }

    public void updateAddFriends(Person selectedPerson ) {

        if(selectedPerson instanceof Adult) {
            addAdultFriendsProcess(selectedPerson);
            System.out.println("Update successful!");
        }else if(selectedPerson instanceof Children){
            addChildrenFriendsProcess(selectedPerson);
            System.out.println("Update successful!");
        }else
            System.out.println("Update fail! Baby cannot have friends");
    }

    public void updateRemoveFriends(Person selectedPerson) {
        String friendName;
        System.out.print("Please input the friend you want to remove");
        Scanner sr = new Scanner(System.in);
        friendName = sr.nextLine();
        if(selectedPerson instanceof Adult) {
            ((Adult)selectedPerson).removeFriend(getPerson(friendName));
            System.out.println("Update successful! You removed " + friendName);
        }else if(selectedPerson instanceof Children){
            ((Children)selectedPerson).removeFriend(getPerson(friendName));
            System.out.println("Update successful!You removed " + friendName);
        }else
            System.out.println("Update fail!");

    }


	
	public boolean deletePerson(Person selectedPerson) {
		
		Scanner sr = new Scanner(System.in);
		System.out.println("Are you sure to delete "+selectedPerson.getName() +" Y/N");
		if(sr.nextLine().equals("Y")) {
		
		for(int i = 0 ; i<selectedPerson.getRelationship().size();i++) {
			
			if(selectedPerson.getRelationship().get(i).getRelationType().equals("Child"))
			{
				//deleteRelevantPerson(selectedPerson.getRelationship().get(i).getRelevantPerson());
				System.out.println("This person has children and cannot be deleted.");
				return false;
//				System.out.println("The person has children. If delete person, the children also will be deleted.\n Do you still want to delete this person? Y/N");
//				if(sr.nextLine().equals("N"))
//					break;
			}
		}
			deleteRelevantPerson(selectedPerson);
			member.remove(selectedPerson);
			//System.out.println("Delete successful!");
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void deleteRelevantPerson(Person tempPerson)
	{
		for(int i = 0 ; i<tempPerson.getRelationship().size();i++) {
		
			//int index;
			ArrayList<RelationshipStore> friendsRelation;
			
			System.out.println(tempPerson.getName()+"  "+tempPerson.getRelationship().get(i).getRelationType());
			
//			if(tempPerson.getRelationship().get(i).getRelationType().equals("Child"))
//			{
//				deleteRelevantPerson(tempPerson.getRelationship().get(i).getRelevantPerson());
//				System.out.println("CHILDRENNNNN");
//				
//				//System.out.print(tempPerson.getRelationship().get(i).getRelationType()+"   "+tempPerson.getRelationship().get(i).getRelevantPerson().getName());
//			}
			
			//System.out.println("CHILDRENNNNN__");
			
			friendsRelation = tempPerson.getRelationship().get(i).getRelevantPerson().getRelationship();
//			System.out.println("____"+friendsRelation.get(i).getRelevantPerson().getName());
			for(int j= 0 ;j<friendsRelation.size();j++)
			{
//				System.out.println(friendsRelation.get(j).getRelevantPerson().getName());
				if(friendsRelation.get(j).getRelevantPerson().equals(tempPerson))
					friendsRelation.remove(j);
				
			}	

		}

		
	}
	
	
	
	public void runProfile() {
		
//		testData();
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
		Person child = new Children("child",12,"N","LOL");
		
		
		((Adult)AA).addFriend(BB);
		((Adult)AA).addFriend(DD);
		((Adult)CC).addFriend(DD);
		((Children)child).addParent(AA);
		((Children)child).addParent(BB);
		
		
		member.add(AA);
		member.add(BB);
		member.add(CC);
		member.add(DD);
		
		System.out.println("--------"+AA.getRelationship().get(1).getRelevantPerson().getName());
	}

}
