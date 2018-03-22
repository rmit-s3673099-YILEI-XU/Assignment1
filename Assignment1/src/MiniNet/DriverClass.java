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
				"1. List everyone.\n" + 
				"2. Select a person by name.\n" + 
				"3. Are these two direct friends?\n" + 
				"4. Add a person.\n" + 
				"5. Exit.\n" + 
				"Enter an option:\n");
		
			try {
				
				selectMenuNum = sr.nextInt();
				
			} catch (Exception e) {
				System.out.println("Please input a number from menu number.\n");
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


	private void addPerson(Person pr) {
		
		Scanner sr = new Scanner(System.in);
		Person tempPerson;
		String flag;

		if (pr instanceof Adult) {
			member.add(pr);
			System.out.println("Add person successful!\n");
			/* addFriends */
			do {
			System.out.println("Do you want to add friend? Y/N");
			flag=sr.nextLine().toUpperCase();
			if (flag.equals("Y")) {
				System.out.println("Select Person\n===================================");
				tempPerson = getRelationPerson(getPersonListByType(pr, "Friend"));
				if (tempPerson != null) {
					((Adult) pr).addFriend(tempPerson);
					System.out.println("Add Friend successful\n!");
				}
			}
			}while(!(flag.equals("Y")||flag.equals("N")));
			

		} else if (pr instanceof Children) {

			System.out.println("Please select parents from the list.");
			if (addParentsProcess(pr)) {

				System.out.println("Add person successful!\n");
				do {
				/* addFriends */
				System.out.println("Do you want to add friends? Y/N");
				flag=sr.nextLine().toUpperCase();
				if (flag.equals("Y")) {
					System.out.println("Select Person\n===================================");
					tempPerson = getRelationPerson(getPersonListByType(pr, "Friend"));
					if (tempPerson != null) {
						if(((Children) pr).addFriend(tempPerson))
						System.out.println("Add Friend successful!\n");
						else {
							tempPerson = getRelationPerson(getPersonListByType(pr, "Friend"));
						}
					}

				}
				}while(!(flag.equals("Y")||flag.equals("N")));

			} else {
				System.out.println("Fail add person!");
			}

		}

		else/* it is Baby */ {
			
			System.out.println("Please select parents from the list.");
			if (addParentsProcess(pr)) {
				
				System.out.println("Add person successful!\n");

			} else {
				System.out.println("Fail add person!\n");
			}

		}

	}
	
	private Person inputBasicProfile() {
		// Adult parent;
		Person pr;
		String prName, prPic, prStatus;
		int prAge;

		// more details @Emma
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input Person Name:");
		do {
		prName = sr.nextLine();
		if(isInList(prName)||prName.isEmpty())
		{
			System.out.println("The person is already in system./The name cannot be empty.\nPlease input Person Name:");
		}
		
		}while(isInList(prName)||prName.isEmpty());		
		System.out.println("Please input Person Age:");
		do {
			try {
				prAge = sr.nextInt();
				sr.nextLine();
			} catch (Exception e) {
				System.out.println("Please in put a number!");
				prAge = sr.nextInt();
				sr.nextLine();
			}
			if(prAge<0||prAge>200)
			{
				System.out.println("Please in put a legal number.");
			}
		}while(prAge<0||prAge>200);
		
		do {
			System.out.println("Do you want to set a pic? Y/N");
			prPic = sr.nextLine();
		}while(!(prPic.toUpperCase().equals("Y")||prPic.toUpperCase().equals("N")));
		System.out.println("Please input Person status. If there is no status, please input none.");
		prStatus = sr.nextLine();

		if (prAge >= 16)
			pr = new Adult();
		else if (prAge > 2)
			pr = new Children();
		else
			pr = new Baby();

		pr.setName(prName);
		pr.setAge(prAge);
		pr.setPic(prPic);
		pr.setStatus(prStatus);
		
		return pr;
	}
	
	private void listEveryone() {
//		do {
//			
			System.out.println("Member List\n===================================");
			for (int i = 0; i < member.size(); i++) {
				if (member.get(i) instanceof Adult)
					System.out.println(i + 1 + ". " + member.get(i).getName() + " Adult");
				else if (member.get(i) instanceof Children)
					System.out.println(i + 1 + ". " + member.get(i).getName() + " Children");
				else if (member.get(i) instanceof Baby)
					System.out.println(i + 1 + ". " + member.get(i).getName() + " Baby");
			}
			System.out.println("");
//			System.out.println(member.size() + 1 + ". Back");
//			System.out.println("Enter an option:");
//
//			Scanner sr = new Scanner(System.in);
//			try {
//
//				selectPersonNum = sr.nextInt();
//
//			} catch (Exception e) {
//				
//				selectPersonNum = 0;
//
//			}
//			if (selectPersonNum < 1 || selectPersonNum > member.size() + 1) {
//				System.out.println("Please input a number from menu number.\n");
//			}
//		} while (selectPersonNum < 1 || selectPersonNum > member.size() + 1);
//		if (selectPersonNum != member.size() + 1)
//			member.get(selectPersonNum - 1).displayProfile();

	}
	
	private ArrayList<Person> getPersonListByType(Person tempPerson, String action)
	{
		ArrayList<Person> tempPersonList = new ArrayList();
		if(tempPerson instanceof Adult)
		{
			for(int i =0;i<member.size();i++)
			{
				if(member.get(i) instanceof Adult) {
					if(!member.get(i).equals(tempPerson))
						tempPersonList.add(member.get(i));
				}
			}
		}
		else 
		{
			for(int i =0;i<member.size();i++)
			{
				if(action.equals("Parent")) {
					if(member.get(i) instanceof Adult) {
						tempPersonList.add(member.get(i));
						}
					}
				else if(action.equals("Friend"))
				{
					if(member.get(i) instanceof Children) {
						if(!member.get(i).equals(tempPerson))
							tempPersonList.add(member.get(i));
						}
				}
			}
		}
		return tempPersonList;
	}
	
	private Person getRelationPerson(ArrayList<Person> tempPersonList)
	{
		Person tempPerson;
		int selectedPersonNum=0;
		do {
		for(int i =0;i<tempPersonList.size();i++)
		{
			
			System.out.println(i+1+". "+tempPersonList.get(i).getName());
		}
		System.out.println(tempPersonList.size()+1+". Back");
		System.out.println("Enter an option:");
		
		Scanner sr = new Scanner(System.in);
		try {
			
			selectedPersonNum = sr.nextInt();
			
		} catch (Exception e) {
			System.out.println("Please input a number from menu number.");
			selectPersonNum = 0;
			
		}
		if(selectedPersonNum==tempPersonList.size()+1)
			break;
		}while(selectedPersonNum<1||selectedPersonNum>tempPersonList.size()+1);
		
		if(selectedPersonNum==tempPersonList.size()+1)
			tempPerson=null;
		else {
		tempPerson=tempPersonList.get(selectedPersonNum-1);
		}
		
		return tempPerson;
	}
	
	private boolean addParentsProcess(Person pr) {
		Person parent1 = new Adult();
		Person parent2 = new Adult();
		System.out.println("Select Person\n===================================");
		parent1 = getRelationPerson(getPersonListByType(pr, "Parent"));
		if (parent1 != null) {
			//select couple automatically
			for (int i = 0; i < parent1.getRelationship().size(); i++) {
				if (parent1.getRelationship().get(i).getRelationType().equals("Couple")) {
					parent2 = parent1.getRelationship().get(i).getRelevantPerson();
					System.out.println(parent1.getName() + " " + parent2.getName() + " are couple in system.");
					break;
				} else {
					parent2 = null;
				}
			}
			if (parent2 == null) {
				boolean getLegalParent = false;
				while (!getLegalParent) {
					parent2 = getRelationPerson(getPersonListByType(pr, "Parent"));
					if (parent2 != null) {
						if (!parent2.equals(parent1)) {
							getLegalParent = true;
							for (int i = 0; i < parent2.getRelationship().size(); i++) {
								if (parent2.getRelationship().get(i).getRelationType().equals("Couple")) {
									getLegalParent = false;
									break;
								}
							}
						}
					} else {
						getLegalParent = true;
					}
				}
			}
		}
		if (parent1 != null && parent2 != null) {

			if(pr instanceof Children) {
				((Children) pr).addParent(parent1, parent2);
			}
			else
			{
				((Baby) pr).addParent(parent1, parent2);
			}
			member.add(pr);
			return true;
		} else
			return false;
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
		listEveryone();
		String perName;	
		Person selectedPerson;
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input person name: ");
		perName=sr.nextLine();
		while(!isInList(perName))
		{
			System.out.println("The person is not in the list. Please input again. \n");
			listEveryone();
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
				 try {

					 selectOptionNum = sr.nextInt();
		                sr.nextLine();

		            } catch (Exception e) {
		                System.out.println("Please input a number from menu number.");
		                selectOptionNum = 0;
		                break;
		            }
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
            System.out.print("select what do you want to update\n" +
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

    		Person tempPerson;
    		
    			if(selectedPerson instanceof Baby)
    			{
    				 System.out.println("Update fail! Baby cannot have friends");
    			}
    			else
    			{
    				tempPerson = getRelationPerson(getPersonListByType(selectedPerson, "Friend"));
    				if (tempPerson != null) {
    					if (selectedPerson.isInRelationship(tempPerson)) {
    						System.out.println("Fail to add friend, they already have relationship!");
    						tempPerson = getRelationPerson(getPersonListByType(selectedPerson, "Friend"));
    					} else {
    						if(selectedPerson instanceof Adult) {
    						((Adult) selectedPerson).addFriend(tempPerson);
    						}
    						else
    						{
    							((Children) selectedPerson).addFriend(tempPerson);
    						}
    						System.out.println("Add Friend successful!");
    						System.out.println("Update successful!");
    					}
    				}
    			}

    }

    public void updateRemoveFriends(Person selectedPerson) {
    	
    		Person tempPerson;
    		if(selectedPerson instanceof Baby)
    		{
    			 System.out.println("Update fail! Baby cannot have friends");
    		}
    		else if(selectedPerson instanceof Adult)	{
    		
    			tempPerson=getRelationPerson(((Adult)selectedPerson).getFriendList());
        		if(tempPerson!=null) {
        			((Adult) selectedPerson).removeFriend(tempPerson);
        			System.out.println("Delete Friend successful!");
    	    			System.out.println("Update successful!");
    	    			}  		

    		}else
    		{
    			tempPerson=getRelationPerson(((Children)selectedPerson).getFriendList());
        		if(tempPerson!=null) {
        			((Children) selectedPerson).removeFriend(tempPerson);
        			System.out.println("Delete Friend successful!");
    	    			System.out.println("Update successful!");
    	    			}  		

    		}
    
    }
	
	public boolean deletePerson(Person selectedPerson) {
		
		Scanner sr = new Scanner(System.in);
		RelationshipStore tempRelation;
		
		System.out.println("Are you sure to delete "+selectedPerson.getName() +" Y/N");
		if(sr.nextLine().equals("Y")) {
		
		for(int i = 0 ; i<selectedPerson.getRelationship().size();i++) {
			
			tempRelation=selectedPerson.getRelationship().get(i);
			if(tempRelation.getRelationType().equals("Child"))
			{

				
				System.out.println("The person has children. If delete person, the children also will be deleted.\n Do you still want to delete this person? Y/N");
				if(sr.nextLine().equals("N"))

					return false;
				else {
					
					deleteRelevantPerson(tempRelation.getRelevantPerson());
					System.out.println("----- "+tempRelation.getRelevantPerson().getName());
					member.remove(tempRelation.getRelevantPerson());
				}

			}
		}
			deleteRelevantPerson(selectedPerson);
			member.remove(selectedPerson);
		
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
			ArrayList<RelationshipStore> friendsRelation = new ArrayList();
			
			System.out.println(tempPerson.getName()+"  "+tempPerson.getRelationship().get(i).getRelationType());

			friendsRelation=tempPerson.getRelationship().get(i).getRelevantPerson().getRelationship();

			for(int j= 0 ;j<friendsRelation.size();j++)
			{
				if(friendsRelation.get(j).getRelevantPerson().equals(tempPerson)) {
					friendsRelation.remove(j);
					break;
				}

			}	
		}

	}
	
	
	
	public void runProfile() {
		
//		testData();
		Scanner sr = new Scanner(System.in);
		String input;
		
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
				do {
				System.out.println("Do you want to add a person? Y/N");
				input=sr.nextLine().toUpperCase();
				if(input.equals("Y"))
					addPerson(inputBasicProfile());
				}while(!(input.equals("Y")||input.equals("N")));
				break;
			case 5:
				isExit = true;
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
