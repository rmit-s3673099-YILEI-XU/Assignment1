/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class Children extends Person implements ParentsController{
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
	
	public void displayParents()
	{
		System.out.println("Parents: "+getParents()[0].getName()+" "+getParents()[1].getName());
	}
	
	@Override
	void displayProfile() {
		// TODO Auto-generated method stub
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayParents();
		
	}
}
