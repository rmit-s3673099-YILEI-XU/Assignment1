/**
 * 
 */


import java.util.ArrayList;

/**
 * This interface is to implement the actions about friends 
 * @author Yilei Xu
 *
 */
public interface FriendRelation {
	
	/**
	 * This method adds friend to the person's relationship list, and also add the person to the friend's relationship list
	 * @param seletedPerson
	 * @return if the friend has been added, true or false
	 */
	public boolean addFriend(Person seletedPerson);
	
	/**
	 * This method remove the friend from the person's relationship list
	 * @param seletedPerson
	 * @return if the friend has been removed, true or false
	 */
	public boolean removeFriend(Person seletedPerson);
	
	/**
	 * This method check if the selected person is this person's friend
	 * @param seletedPerson
	 * @return true or false
	 */
	public boolean isFriend(Person seletedPerson);
	
	/**
	 * This method get the friend list of the person
	 * return tempPerson is a friend list
	 */
	public ArrayList<Person> getFriendList();

}
