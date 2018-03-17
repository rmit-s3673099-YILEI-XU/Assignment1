/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Children extends Person{
	private ArrayList friends = new ArrayList();
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
}
