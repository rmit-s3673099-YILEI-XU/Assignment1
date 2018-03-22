/**
 * 
 */
package MiniNet;

/**
 * @author Yilei Xu
 *
 */
public class Baby extends Person implements ParentRelation{

	//private Person parents[]= new Person[2];
	
	public Baby() {

        super();
        }

	public Baby(String name, int age, String pic, String status)
        {
        super(name,age,pic,status);
        }



	@Override
	public void displayProfile() {
		
		System.out.println("\nPerson Profile\n"+"===================================");
		System.out.println("Name: "+this.getName());
		System.out.println("Age: "+this.getAge());
		System.out.println("Picture: "+this.getPic());
		System.out.println("Stautus: "+this.getStatus());
		displayRelationship();
		System.out.println("");
		
	}


	private void displayRelationship() {
		// TODO Auto-generated method stub
			
		System.out.print("Parents: ");
		for(int i =0;i<super.getRelationship().size();i++) {
			if(super.getRelationship().get(i).getRelationType().equals("Parent"))
			System.out.print(super.getRelationship().get(i).getRelevantPerson().getName()+" ");
		}
		System.out.println("");
	}
	
	@Override
	public void addParent(Person seletedPerson) {
		// TODO Auto-generated method stub
		RelationshipStore friendRelation = new RelationshipStore();
		friendRelation.setRelationType("Parent");
		friendRelation.setRelevantPerson(seletedPerson);
		super.addRelationship(friendRelation);
	}
	
	@Override
	public void addParent(Person parent1, Person parent2) {
		// TODO Auto-generated method stub
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
		// System.out.println(childRelation1.getRelevantPerson());
		parent1.addRelationship(childRelation1);

		childRelation2.setRelationType("Child");
		childRelation2.setRelevantPerson(this);
		parent2.addRelationship(childRelation2);
		//
	}

	
	
}
