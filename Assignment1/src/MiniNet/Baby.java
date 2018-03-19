/**
 * 
 */
package MiniNet;

/**
 * @author Yilei Xu
 *
 */
public class Baby extends Person implements ParentsController{

	private String parents[]= new String[2];

	/**
	 * @return the parents
	 */
	public String[] getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(String[] parents) {
		this.parents = parents;
	}

	@Override
	void displayProfile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayParents() {
		// TODO Auto-generated method stub
		
	}
	
	
}
