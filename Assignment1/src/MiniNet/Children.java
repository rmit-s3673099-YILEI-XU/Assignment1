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
	private Person parents[]= new Person[2];
	/**
	 * @return the parents
	 */
	public Person[] getParents() {
		return parents;
	}
	/**
	 * @param parents the parents to set
	 */
	public void setParents(Person[] parents) {
		this.parents = parents;
	}
}
