/**
 * 
 */


/**
 * This interface is to implement add parent to a person
 * @author CIFANG ZHANG
 *
 */
public interface ParentRelation {
	
	/**
	 * This method adds parents to the object for initialization data, for hard data
	 * @param seletedPerson
	 */
	public void addParent(Person seletedPerson);
	
	/**
	 * This method adds parents by the run time input, for input data
	 * @param parent1  input parent1
	 * @param parent2  input parent2
	 */
	public void addParent(Person parent1,Person parent2);

}
