/**
 * 
 */
package MiniNet;

/**
 * This class is the subclass of Person, it creates the Baby type person
 * @author CIFANG ZHANG
 *
 */
public class Baby extends Person implements ParentRelation{

	/**
	 * This is the constructor is for creating the Adult object from the user
	 */
	public Baby() {

        super();
        
        }

	/**
	 * This constructor is for creating the Baby object in the main method, to store some people in the system
	 * @param name the name of the person
	 * @param age the age of the person
	 * @param pic the picture of the person
	 * @param status the status of the person
	 */
	public Baby(String name, int age, String pic, String status)
        {
        super(name,age,pic,status);
        }

	/**
	 *  This method display the person's basic profile and the relationship 
	 */
	public void displayProfile() {
		
		super.displayProfile();
		displayRelationship();
		System.out.println("");
		
	}

	/**
	 * This method display the relationship list of the person, only the parents
	 */
	private void displayRelationship() {
			
		System.out.print("Parents: ");
		for(int i =0;i<super.getRelationship().size();i++) {
			if(super.getRelationship().get(i).getRelationType().equals("Parent"))
			System.out.print(super.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
	}
	
	/**
	 * This method adds parents to the object created in the main method, for hard data. 
	 * @param seletedPerson this is the parent who is going to be added
	 */
	public void addParent(Person seletedPerson) {
		
		RelationshipStore parentRelation = new RelationshipStore();
		parentRelation.setRelationType("Parent");
		parentRelation.setRelevantPerson(seletedPerson);
		super.addRelationship(parentRelation);
	}
	
	/**
	 * This method adds parents by the run time input, for input data. It add parents to the person
	 * and then set those two people to couple relationship
	 * and then add this child to those two people
	 * @param parent1  this is input parent1
	 * @param parent2  this is input parent2
	 */
	public void addParent(Person parent1, Person parent2) {
		
		RelationshipStore parentRelation1 = new RelationshipStore();
		RelationshipStore parentRelation2 = new RelationshipStore();

		RelationshipStore childRelation1 = new RelationshipStore();
		RelationshipStore childRelation2 = new RelationshipStore();

		parentRelation1.setRelationType("Parent");
		parentRelation1.setRelevantPerson(parent1);
		this.addRelationship(parentRelation1);

		parentRelation2.setRelationType("Parent");
		parentRelation2.setRelevantPerson(parent2);
		this.addRelationship(parentRelation2);

		for (int i = 0; i < parent1.getRelationship().size(); i++) {
			if (parent1.getRelationship().get(i).getRelevantPerson().equals(parent2)) {
				parent1.getRelationship().get(i).setRelationType("Couple");
			}
		}
		for (int i = 0; i < parent2.getRelationship().size(); i++) {
			if (parent2.getRelationship().get(i).getRelevantPerson().equals(parent1)) {
				parent2.getRelationship().get(i).setRelationType("Couple");
			}
		}

		childRelation1.setRelationType("Child");
		childRelation1.setRelevantPerson(this);
		parent1.addRelationship(childRelation1);

		childRelation2.setRelationType("Child");
		childRelation2.setRelevantPerson(this);
		parent2.addRelationship(childRelation2);
		
	}


}
