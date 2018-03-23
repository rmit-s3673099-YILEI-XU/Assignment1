/**
 * 
 */


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/**
 * This class implements features in MiniNet System. It uses other classes to manage whole social network;
 * 
 * @author Yilei Xu
 *
 */
public class DriverClass {

	/**
	 * member: store person in member list.
	 * isExist: check if exit the system.
	 */
	private ArrayList<Person> member = new ArrayList();
	private int selectMenuNum = 0;
	private int updateNum = 0;
	private boolean isExit = false;	
	
	/**
	 * for hard coded data
	 * @return the member
	 */
	private ArrayList<Person> getMember() {
		return member;
	}
	
	/**
	 * This method displays the main menu and gets the input number from user.
	 */
	private void mainMenu() {

		Scanner sr = new Scanner(System.in);
		do {
			
			System.out.print("MiniNet Menu\n" 
					+ "===================================\n" 
					+ "1. List everyone.\n"
					+ "2. Select a person by name.\n" 
					+ "3. Are these two direct friends?\n" 
					+ "4. Add a person.\n"
					+ "5. Exit.\n" + "Enter an option:\n");
			try {

				selectMenuNum = sr.nextInt();

			} catch (Exception e) {
				
				System.out.println("Please input a number from menu number.\n");
				selectMenuNum = 0;
				break;
			}

			if (selectMenuNum < 1 || selectMenuNum > 5) {
				System.out.println("Please input a number from menu number.\n");
			}

		} while (selectMenuNum < 1 || selectMenuNum > 5);
	}

	/**
	 * This method adds person into system according to type of person.
	 * Children and Baby have to add their parents before they are added in system.
	 * Adult and Children can add friends after they are added in system list.
	 * @param pr the person will be added in system
	 */
	private void addPerson(Person pr) {

		Scanner sr = new Scanner(System.in);
		Person tempPerson;
		String flag;
		if(pr!=null) {
		if (pr instanceof Adult) {
			member.add(pr);
			System.out.println("Add person successful!\n");
			do {/* addFriends */
				System.out.println("Do you want to add friend? Y/N");
				flag = sr.nextLine().toUpperCase();
				if (flag.equals("Y")) {
					tempPerson = getRelationPerson(getPersonListByType(pr, "Friend"));	//get person from available member list.
					if (tempPerson != null) {
						((Adult) pr).addFriend(tempPerson);								//add friends (optional)
						System.out.println("Add Friend successful!\n");
					}
				}
			} while (!(flag.equals("Y") || flag.equals("N")));
		} else if (pr instanceof Children) {							
			System.out.println("Please select parents from the list.");
			if (addParentsProcess(pr)) {										//add parents (compulsory)
				System.out.println("Add person successful!\n");
				do {															//add friends (optional)
					System.out.println("Do you want to add friends? Y/N");
					flag = sr.nextLine().toUpperCase();
					if (flag.equals("Y")) {
						tempPerson = getRelationPerson(getPersonListByType(pr, "Friend"));
						if (tempPerson != null) {
							if (((Children) pr).addFriend(tempPerson))
								System.out.println("Add Friend successful!\n");
							else {
								tempPerson = getRelationPerson(getPersonListByType(pr, "Friend"));
							}
						}
					}
				} while (!(flag.equals("Y") || flag.equals("N")));

			} else {
				System.out.println("Fail add person!");
			}
		}
		else/* it is Baby */ {
			System.out.println("Please select parents from the list.");
			if (addParentsProcess(pr)) {								//add parents (compulsory)
				System.out.println("Add person successful!\n");
			} else {
				System.out.println("Fail add person!\n");
			}
		}
		}
		else
			System.out.println("Fail add person!\n");
	}
	
	/**
	 * This method instance a person with different type according to the age.(Adult age>=16, Child age: 3~15, Baby age: 0~2)
	 * @return pr the the person will be added in system with all basic information (name, age, pic, status)
	 */
	private Person inputBasicProfile() {
		
		Person pr;
		String prName, prPic, prStatus;
		int prAge=-1;
		Scanner sr = new Scanner(System.in);

		prName=inputName();
		if(prName==null)
			return null;
		prAge=inputAge();
		prPic=inputPic();

		System.out.println("Please input Person status.(Can be empty.)");
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
	
	/**
	 * This method gets name from input.
	 * @return the valid name
	 */
	private String inputName() {

		String prName;
		Scanner sr = new Scanner(System.in);

		System.out.println("Please input Person Name:");
		do {
			prName = sr.nextLine().trim();
			if (prName.isEmpty())
				return null;
			if (isInList(prName)) {
				System.out.println(
						"The person is already in system.\nPlease input Person Name:");
			}

		} while (isInList(prName));

		return prName;
	}
	
	/**
	 * This method gets valid age from input.
	 * @return the valid age
	 */
	private int inputAge() {
		
		int prAge = -1;
		Scanner sr = new Scanner(System.in);

		System.out.println("Please input Person Age:");
		do {
			try {
				prAge = sr.nextInt();

			} catch (Exception e) {
		
				sr.nextLine();
			}
			if (prAge < 0 || prAge > 200) {
				System.out.println("Please in put a valid number (0~200).");
			}
		} while (prAge < 0 || prAge > 200);

		return prAge;
	}
	
	/**
	 * This method gets valid value for picture from input. Value for picture is only Yes or No.
	 * @return the valid value for picture
	 */
	private String inputPic() {
		String prPic;
		Scanner sr = new Scanner(System.in);
		do {
			System.out.println("Do you want to set a pic? Y/N");
			prPic = sr.nextLine();
		} while (!(prPic.toUpperCase().equals("Y") || prPic.toUpperCase().equals("N")));

		return prPic;
	}
	
	/**
	 * This method lists member in system.
	 */
	private void listEveryone() {

		System.out.println("Member List\n===================================");
		for (int i = 0; i < member.size(); i++) {
			if (member.get(i) instanceof Adult)
				System.out.printf("%d. %-10sAdult\n", i + 1, member.get(i).getName());
			else if (member.get(i) instanceof Children)
				System.out.printf("%d. %-10sChild\n", i + 1, member.get(i).getName());
			else if (member.get(i) instanceof Baby)
				System.out.printf("%d. %-10sBaby\n", i + 1, member.get(i).getName());
		}
		System.out.println("");

	}
	
	/**
	 * This method lists the available people from system member lists according action.
	 * @param tempPerson the person who will add friends or parents
	 * @param action "Friend" for add friend / "Parent" for add parents
	 * @return available people list
	 */
	private ArrayList<Person> getPersonListByType(Person tempPerson, String action) {
		
		ArrayList<Person> tempPersonList = new ArrayList();
		if (tempPerson instanceof Adult) {
			for (int i = 0; i < member.size(); i++) {
				if (member.get(i) instanceof Adult) {
					if (!member.get(i).equals(tempPerson) && !tempPerson.isInRelationship(member.get(i)))
						tempPersonList.add(member.get(i));	//select adults who can make friends with person who is selected.
				}
			}
		} else {
			for (int i = 0; i < member.size(); i++) {
				if (action.equals("Parent")) {
					if (member.get(i) instanceof Adult) {
						tempPersonList.add(member.get(i));	//select adults who can be parents with person who is selected.
					}
				} else if (action.equals("Friend")) {
					if (member.get(i) instanceof Children && !tempPerson.isInRelationship(member.get(i))) {
						if (!member.get(i).equals(tempPerson))
							tempPersonList.add(member.get(i));	//select children who can make friends with person who is selected.
					}
				}
			}
		}
		return tempPersonList;
	}
	
	/**
	 * This method is for selecting person from the available people list and return this person.
	 * @param tempPersonList available people list to select
	 * @return person is be selected
	 */
	private Person getRelationPerson(ArrayList<Person> tempPersonList) {
		
		Person tempPerson;
		int selectedPersonNum = 0;
		
		do {
			System.out.println("Select Person\n===================================");
			for (int i = 0; i < tempPersonList.size(); i++) {

				System.out.println(i + 1 + ". " + tempPersonList.get(i).getName());
			}
			System.out.println(tempPersonList.size() + 1 + ". Back");
			System.out.println("Enter an option:");

			Scanner sr = new Scanner(System.in);
			try {
				selectedPersonNum = sr.nextInt();

			} catch (Exception e) {
//				System.out.println("Please input a number from menu number.");
				selectedPersonNum = 0;

			}
			if(selectedPersonNum < 1 || selectedPersonNum > tempPersonList.size() + 1)
				System.out.println("Please input a number from menu number.");
			if (selectedPersonNum == tempPersonList.size() + 1)			//for go back to last menu
				break;
		} while (selectedPersonNum < 1 || selectedPersonNum > tempPersonList.size() + 1);

		if (selectedPersonNum == tempPersonList.size() + 1)
			tempPerson = null;
		else {
			tempPerson = tempPersonList.get(selectedPersonNum - 1);
		}

		return tempPerson;
	}
	
	/**
	 * This method adds parents for child and baby. If the selected person(adult) has a relation with other, the parents would be selected automatically.
	 * @param pr Child or Baby who have to add parents
	 * @return add successful or not
	 */
	private boolean addParentsProcess(Person pr) {
		
		Person parent1 = new Adult();
		Person parent2 = new Adult();

		parent1 = getRelationPerson(getPersonListByType(pr, "Parent"));
		if (parent1 != null) {
			// select couple automatically
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
									System.out.print(parent2.getName()+" already has a relationship.");
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

			if (pr instanceof Children) {
				((Children) pr).addParent(parent1, parent2);
			} else {
				((Baby) pr).addParent(parent1, parent2);
			}
			member.add(pr);
			return true;
		} else
			return false;
	}
	
	/**
	 * This method displays whether two people are friends or not
	 */
	private void displayIsFriends() {

		Person tempPerson1, tempPerson2;
		String[] peopleName = new String[2];
		listEveryone();
		System.out.println("If you want to go back last menu. Please return/enter nothing");
		Scanner sr = new Scanner(System.in);
		System.out.println("Please input 1st Person's name: ");
		peopleName[0] = sr.nextLine();
		if (!peopleName[0].isEmpty()) {
			while (!isInList(peopleName[0])) {
				System.out.println(peopleName[0] + " is not in the list. Please input again. ");
				System.out.println("If you want to go back last menu. Please return/enter nothing");
				peopleName[0] = sr.nextLine().trim();
				if (peopleName[0].isEmpty())
					break;
			}
			System.out.println("If you want to go back last menu. Please return/enter nothing");
			System.out.println("Please input 2nd person's name: ");
			peopleName[1] = sr.nextLine();
			if (!peopleName[1].isEmpty()) {
				while (!isInList(peopleName[1]) || peopleName[0].equals(peopleName[1])) {
					System.out.println(peopleName[1] + " is not in the list. Please input again. ");
					System.out.println("If you want to go back last menu. Please return/enter nothing");
					peopleName[1] = sr.nextLine().trim();
					if (peopleName[1].isEmpty())
						break;
				}
			}
			if (!peopleName[0].isEmpty() && !peopleName[1].isEmpty()) {
				tempPerson1 = getPerson(peopleName[0]);
				tempPerson2 = getPerson(peopleName[1]);

				if (tempPerson1.isInRelationship(tempPerson2)) {
					if (tempPerson1 instanceof Adult) {
						if (((Adult) tempPerson1).isFriend(tempPerson2))
							System.out.println(peopleName[0] + " and " + peopleName[1] + " are Friends.\n");
						else
							System.out.println(peopleName[0] + " and " + peopleName[1] + " are not Friends.\n");

					} else if (tempPerson2 instanceof Children) {
						if (((Children) tempPerson1).isFriend(tempPerson2))
							System.out.println(peopleName[0] + " and " + peopleName[1] + " are Friends.\n");
						else
							System.out.println(peopleName[0] + " and " + peopleName[1] + " are not Friends.\n");
					} else
						System.out.println(peopleName[0] + " and " + peopleName[1] + " are not Friends.\n");
				}
			}
		}

	}
	
	/**
	 * This method checks if the person in the system member list
	 * @param inputName the name of person from input
	 * @return
	 */
	private boolean isInList(String inputName) {
		
		for (int i = 0; i < member.size(); i++) {
			if (inputName.equals(member.get(i).getName())) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * This method find the person from system member list according inputing name
	 * @param inputName the name of person from input
	 * @return person object
	 */
	private Person getPerson(String inputName) {
		
		int index = -1;
		
		for (int i = 0; i < member.size(); i++) {
			if (inputName.equals(member.get(i).getName())) {
				index = i;
			}
		}
		return member.get(index);

	}
	
	/**
	 * This method selects person by name.
	 */
	private void selectPersonByName() {

		listEveryone();
		String perName;
		Person selectedPerson;

		Scanner sr = new Scanner(System.in);
		System.out.println("If you want to go back last menu. Please return/enter nothing. ");
		System.out.println("Please input person name: ");
		perName = sr.nextLine().trim();
		if (!perName.isEmpty()) {
			while (!isInList(perName)) {

				System.out.println("The person is not in the list.");
				System.out.println("If you want to go back last menu. Please return/enter nothing. ");
				System.out.println("Please input person name: ");
				perName = sr.nextLine().trim();
				if (perName.isEmpty())
					break;

			}
			if (!perName.isEmpty()) {
				selectedPerson = getPerson(perName);
				System.out.println("Select " + selectedPerson.getName() + " is successful!\n");
				selectOptions(selectedPerson);
			}
		}

	}
	
	/**
	 * This method displays select option menu after a person is selected
	 */
	private void displaySelectOption()
	{
		System.out.println("Selection Option\n"+"===================================");
		System.out.println("1. Display Profile.");
		System.out.println("2. Update Profile.");
		System.out.println("3. Delete person.");
		System.out.println("4. Back.");
		System.out.println("Enter an option: ");
	}
	
	/**
	 * This method implements all features after a person is selected
	 * @param selectedPerson
	 */
	private void selectOptions(Person selectedPerson) {
		
		int selectOptionNum = 0;
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
					sr.nextLine();
					selectOptionNum = 0;
					break;
				}
				if(selectOptionNum < 1 || selectOptionNum > 4)
				System.out.println("Please input a number from menu number.");
				
			} while (selectOptionNum < 1 || selectOptionNum > 4);

			switch (selectOptionNum) {
			case 1:
				selectedPerson.displayProfile();
				break;
			case 2:
				updateProfile(selectedPerson);
				break;
			case 3:
				if (deletePerson(selectedPerson)) {
					System.out.println("Delete successful!\n");
					goBackMainMenu = true;
				} else {
					System.out.println("Fail to delete!\n");
				}
				break;
			case 4:
				goBackMainMenu = true;
				break;
			}
		}
	}
	
   
	/**
	 * This method displays the update menu, gets selected option number and use 'updateSelection' method to implement the update feature.
	 * @param selectedPerson
	 */
	private void updateProfile(Person selectedPerson) {

		Scanner sr = new Scanner(System.in);
		boolean isBackToMenu = false;
		
		while (!isBackToMenu) {
			do {
				System.out.print("select what do you want to update\n" 
						+ "===================================\n"
						+ "1. Name\n" 
						+ "2. Age\n" 
						+ "3. Picture\n" 
						+ "4. Status\n" 
						+ "5. Add Friends\n"
						+ "6. Remove Friends\n" 
						+ "7. Back\n");
				try {

					updateNum = sr.nextInt();
					sr.nextLine();

				} catch (Exception e) {

					sr.nextLine();
					updateNum = 0;
					break;
				}

				if (updateNum < 1 || updateNum > 7)
					System.out.println("Please input a number from menu number.\n");
				if (updateNum == 7) {
					
					isBackToMenu = true;
				}

			} while (updateNum < 1 || updateNum > 7);

			updateSelection(updateNum, selectedPerson);
		}
	}

	/**
	 * This method updates profile information of the selected person and adds/removes friends for adults and children.
	 * @param updateNum number of selected option of menu
	 * @param selectedPerson 
	 */
	private void updateSelection(int updateNum, Person selectedPerson) {

		Scanner sr = new Scanner(System.in);

		switch (updateNum) {

		case 1:
			if(inputName()!=null) {
			selectedPerson.setName(inputName());
			System.out.println("Update successful!");
			}else
				System.out.println("Fail to update! The name cannot be empty!");
			break;
		case 2:
			updateAge(inputAge(), selectedPerson);
			break;
		case 3:
			System.out.println("Please choose the pic condition again");
			selectedPerson.setPic(inputPic());
			System.out.println("Update successful!");
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

	/**
	 * This method updates age and returns if it is successful.
	 * @param prAge target age
	 * @param selectedPerson
	 * @return
	 */
	private boolean updateAge(int prAge, Person selectedPerson) {

		if (selectedPerson instanceof Adult && !(prAge >= 16)) {
			System.out.println("Update Fail. Adult age range: 16~200.");
			return false;
		} else if (selectedPerson instanceof Children && !(prAge < 16 && prAge > 2)) {
			System.out.println("Update fail. Child age range: 3~15.");
			return false;
		} else if (selectedPerson instanceof Baby && !(prAge <= 2)) {
			System.out.println("Update Fail. Baby age range: 0~2.");
			return false;
		} else {
			selectedPerson.setAge(prAge);
			System.out.println("Update successful!");
			return true;
		}
	}

	/**
	 * This method adds friend in Update features after select a person.
	 * @param selectedPerson
	 */
	private void updateAddFriends(Person selectedPerson) {

		Person tempPerson;
		boolean isGoBack = false;

		if (selectedPerson instanceof Baby) {
			
			System.out.println("Update fail! Baby cannot have friends");
		} else {
			while (!isGoBack) {
				
				tempPerson = getRelationPerson(getPersonListByType(selectedPerson, "Friend"));

				if (tempPerson != null) {
						if (selectedPerson instanceof Adult) {
							((Adult) selectedPerson).addFriend(tempPerson);
							System.out.println("Add Friend successful!");
							System.out.println("Update successful!");

						} else {
							if (((Children) selectedPerson).addFriend(tempPerson)) {
								System.out.println("Add Friend successful!");
								System.out.println("Update successful!");
							} else {
								System.out.println("Fail to add friend!");
								System.out.println("Fail to update!");
							}
						}
						isGoBack = true;
				} else {
					isGoBack = true;
				}
			}
		}
	}

	/**
	 * This method removes friends in update feature after select a person.
	 * @param selectedPerson
	 */
	private void updateRemoveFriends(Person selectedPerson) {

		Person tempPerson;
		String inputYN = "";
		Scanner sr = new Scanner(System.in);

		if (selectedPerson instanceof Baby) {
			System.out.println("Update fail! Baby cannot have friends");
		} else if (selectedPerson instanceof Adult) {

			tempPerson = getRelationPerson(((Adult) selectedPerson).getFriendList());
			if (tempPerson != null) {
				do {
					System.out.println("Are you sure to delete " + tempPerson.getName() + " from "
							+ selectedPerson.getName() + "'s friend list? Y/N");
					inputYN = sr.nextLine();
				} while (!(inputYN.toUpperCase().equals("Y") || inputYN.toUpperCase().equals("N")));
				if (inputYN.toUpperCase().equals("Y")) {
					((Adult) selectedPerson).removeFriend(tempPerson);
					System.out.println("Delete Friend successful!");
					System.out.println("Update successful!");
				} else {
					System.out.println("Fail to Delete friend.");
				}
			}

		} else {
			tempPerson = getRelationPerson(((Children) selectedPerson).getFriendList());
			if (tempPerson != null) {
				do {
					System.out.println("Are you sure to delete " + tempPerson.getName() + " from "
							+ selectedPerson.getName() + "'s friend list? Y/N");
					inputYN = sr.nextLine();
				} while (!(inputYN.toUpperCase().equals("Y") || inputYN.toUpperCase().equals("N")));
				if (inputYN.toUpperCase().equals("Y")) {
					((Children) selectedPerson).removeFriend(tempPerson);
					System.out.println("Delete Friend successful!");
					System.out.println("Update successful!");
				} else {
					System.out.println("Fail to Delete friend.");
				}
			}
		}
	}
	
	/**
	 * This method deletes the selected person.
	 * If the selected person has children, the children will be deleted after the selected person is deleted. 
	 * Therefore, the system would double check if the user wants to delete the selected person who has children. 
	 * @param selectedPerson
	 * @return
	 */
	private boolean deletePerson(Person selectedPerson) {

		Scanner sr = new Scanner(System.in);
		RelationshipStore tempRelation;
		String inputYN;
		ArrayList<Person> childrenList = new ArrayList();

		do {
			System.out.println("Are you sure to delete " + selectedPerson.getName() + " ？ Y/N");
			inputYN = sr.nextLine();
		} while (!(inputYN.toUpperCase().equals("Y") || inputYN.toUpperCase().equals("N")));

		if (inputYN.toUpperCase().equals("Y")) {

			inputYN = "";
			for (int i = 0; i < selectedPerson.getRelationship().size(); i++) {
				tempRelation = selectedPerson.getRelationship().get(i);
				if (tempRelation.getRelationType().equals("Child")) {
					if (inputYN.isEmpty()) {
						do {
							System.out.println(
									"The person has children. If delete person, the children also will be deleted.\n"
											+ "Do you still want to delete this person? Y/N");
							inputYN = sr.next();
						} while (!(inputYN.toUpperCase().equals("Y") || inputYN.toUpperCase().equals("N")));
					}
					if (inputYN.toUpperCase().equals("N")) {

						return false;
					} else {
						childrenList.add(tempRelation.getRelevantPerson());
					}
				}
			}
			if (!childrenList.isEmpty()) {
				for (int i = 0; i < childrenList.size(); i++) {
					deleteRelevantPerson(childrenList.get(i));
					member.remove(childrenList.get(i));
				}
			}
			deleteRelevantPerson(selectedPerson);
			member.remove(selectedPerson);
			return true;
		} else {
			return false;
		}

	}
	
	
	/**
	 * This method deletes people who have any relationship with the selected person, before deletes the selected person.
	 * @param tempPerson selected person
	 */
	private void deleteRelevantPerson(Person tempPerson) {
		for (int i = 0; i < tempPerson.getRelationship().size(); i++) {

			ArrayList<RelationshipStore> friendsRelation = new ArrayList();

			friendsRelation = tempPerson.getRelationship().get(i).getRelevantPerson().getRelationship();

			for (int j = 0; j < friendsRelation.size(); j++) {
				if (friendsRelation.get(j).getRelevantPerson().equals(tempPerson)) {
					friendsRelation.remove(j);
					break;
				}

			}
		}

	}
	
	/**
	 * This method initialize social network.
	 * Alice and Bob are couple and they have two children(Emily and Frank).
	 * Alice and Don are friends.
	 * Cathy and Don are friends.
	 * Cathy and Henry are couple and they have a child(Jammy) and a baby(Ivy).
	 * Jammy and Frank are friends.
	 * 
	 */
	private void initialData() {
		Person alice = new Adult("Alice",32,"Y","Working at KFC");
		Person bob = new Adult("Bob",36,"N","Freelance");
		Person cathy = new Adult("Cathy",28,"Y","Student at RMIT");
		Person don = new Adult("Don",30,"Y","Looking for jobs");
		Person emily = new Children("Emily",12,"N","Student at school");
		Person frank = new Children("Frank",6,"N","");
		Person henry = new Adult("Henry",30,"N","Freelance");
		Person ivy = new Baby("Ivy",1,"N","");
		Person jammy = new Children("Jammy",8,"N","");
		
		((Adult)alice).addFriend(bob);
		((Adult)alice).addFriend(don);
		((Adult)cathy).addFriend(don);
		((Children)emily).addParent(alice,bob);
		((Children)frank).addParent(alice,bob);
		((Children)frank).addFriend(jammy);
		((Children)jammy).addParent(cathy,henry);
		((Baby)ivy).addParent(cathy, henry);
		
		getMember().add(alice);
		getMember().add(bob);
		getMember().add(cathy);
		getMember().add(don);
		getMember().add(emily);
		getMember().add(frank);
		getMember().add(henry);
		getMember().add(ivy);
		getMember().add(jammy);
	}
	
	/**
	 * This method supports the main menu and connect all features in main menu.
	 */
	public void runProject() {

		initialData();
		Scanner sr = new Scanner(System.in);
		String inputYN;

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
					inputYN = sr.nextLine().toUpperCase();
					if (inputYN.equals("Y"))
						addPerson(inputBasicProfile());
				} while (!(inputYN.equals("Y") || inputYN.equals("N")));
				break;
			case 5:
				isExit = true;
				break;
			default:
				break;
			}
		}

		System.out.println("Thank you for using MiniNet System!");
	}

}
