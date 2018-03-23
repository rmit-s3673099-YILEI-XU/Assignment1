/**
 * 
 */


/**
 * This interface is to implement add parent to a person
 * @author Yilei Xu
 *
 */
public interface ParentRelation {
	
	/**
	 * This method adds parents to the object created in the main method, for hard data
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
