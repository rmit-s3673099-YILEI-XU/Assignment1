/**
 * 
 */
package MiniNet;

import java.util.ArrayList;

/**
 * @author Yilei Xu
 *
 */
public class RelationshipStore {

	private Person relevantPerson;
	private String relationType;
	/**
	 * @return the relevantPerson
	 */
	public Person getRelevantPerson() {
		return relevantPerson;
	}
	/**
	 * @param relevantPerson the relevantPerson to set
	 */
	public void setRelevantPerson(Person relevantPerson) {
		this.relevantPerson = relevantPerson;
	}
	/**
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}
	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	
}

