/**
 * 
 */
package MiniNet;

/**
 * This class is the main method class to run the program
 * @author CIFANG ZHANG
 *
 */
public class MiniNet {
	
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args) {

		DriverClass pc = new DriverClass();
		
		Person AA = new Adult("AA",20,"Y","PP");
		Person BB = new Adult("BB",28,"N","OO");
		Person CC = new Adult("CC",18,"Y","XX");
		Person DD = new Adult("DD",30,"Y","TT");
		Person child = new Children("child",12,"N","LOL");
		
		
		((Adult)AA).addFriend(BB);
		((Adult)AA).addFriend(DD);
		((Adult)CC).addFriend(DD);
		((Children)child).addParent(AA,BB);
		
		
		pc.getMember().add(AA);
		pc.getMember().add(BB);
		pc.getMember().add(CC);
		pc.getMember().add(DD);
		pc.getMember().add(child);
		
		pc.runProfile();
	}
	


}
