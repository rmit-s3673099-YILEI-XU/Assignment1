/**
 * 
 */

/**
 * @author sens_x
 *
 */
public class AdultFriend extends AbstractFriend implements RelationManipulator{


	private Adult friend;
	
	public AdultFriend(Person person, Adult friend)
	{
		super.person = person;
		this.friend=friend;
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		person.addRelationship("Friend", friend);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	

}
