/**
 * 
 */
package MiniNet;

/**
 * @author Yilei Xu
 *
 */
public interface FriendRelation {
	
	public boolean addFriend(Person seletedPerson);
	public void removeFriend(Person seletedPerson);
	public boolean isFriend(Person seletedPerson);

}
