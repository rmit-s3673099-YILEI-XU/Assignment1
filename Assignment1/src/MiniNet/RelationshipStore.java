/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * This class stores the relationship type and relevant person
 * @author Yilei Xu
 *
 */
public class RelationshipStore {

	private Person relevantPerson;
	private String relationType;
	
	/**
	 * This method get the relevant person of the person which call the method
	 * @return the relevantPerson
	 */
	public Person getRelevantPerson() {
		return relevantPerson;
	}
	
	/**
	 * This method set the relevant person
	 * @param relevantPerson the relevant person to set
	 */
	public void setRelevantPerson(Person relevantPerson) {
		this.relevantPerson = relevantPerson;
	}
	
	/**
	 * This method get the relationship type of the person which call the method
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}
	
	/**
	 * This method set the relationship type
	 * @param relationType the relationship type to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	
}

