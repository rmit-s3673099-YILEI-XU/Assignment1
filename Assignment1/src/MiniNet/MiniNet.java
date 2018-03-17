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
		
		//trouble
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
		Person pr;
		String prName, prPic, prStatus;
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
		
		
		System.out.println(prName+" "+prAge+" "+ prPic+" "+prStatus+" ");
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
		
		
	}
	
	private void displayPersonDetail(Person p)
	{
		System.out.println("Person Detail\n"+"===================================");
		System.out.println("Name:"+p.getName());
		System.out.println("Age:"+p.getAge());
		System.out.println("Picture: "+p.getPic());
		System.out.println("Stautus: "+p.getStatus()+"\n");
		
	}
	
	private void listEveryone()
	{
		for(int i =0;i<member.size();i++)
		{
			if(member.get(i) instanceof Adult)
				System.out.println(i+1+". "+member.get(i).getName() +" Audult");
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
	
	public void runProfile() {
		while (!isExit) {
			mainMenu();
			switch (selectMenuNum) {
			case 1:
				listEveryone();
				break;
			case 2:
				break;
			case 3:
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
}
