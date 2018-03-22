/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public interface FriendRelation {
	
	public boolean addFriend(Person seletedPerson);
	public boolean removeFriend(Person seletedPerson);
	public boolean isFriend(Person seletedPerson);
	public ArrayList<Person> getFriendList();

}
