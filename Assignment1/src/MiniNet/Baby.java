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
	
	
}
