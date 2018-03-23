/**
 * 
 */


import java.util.ArrayList;

/**
 * @author CIFANG ZHANG
 *
 */
public class Children extends Person implements FriendRelation, ParentRelation {

	/**
	 * This is the constructor is for creating the Children object from the user
	 */
	public Children() {

		super();
	}

	/**
	 * This constructor is for creating the Children object in the main method, to store some people in the system
	 * @param name the name of the person
	 * @param age the age of the person
	 * @param pic the picture of the person
	 * @param status the status of the person
	 */
	public Children(String name, int age, String pic, String status) {

		super(name, age, pic, status);
	}

	/**
	 * This method display basic information and the relationship list of the person, include Friends and Parents
	 */
	@Override
	public void displayProfile() {
		
		// TODO Auto-generated method stub
		super.displayProfile();
		displayRelationship();
		System.out.println("");

	}

	/**
	 * This method display the relationship list of the person, include Friends and Parents
	 */
	private void displayRelationship() {
		// TODO Auto-generated method stub
		System.out.print("Friends:  ");
		for (int i = 0; i < super.getRelationship().size(); i++) {
			if (super.getRelationship().get(i).getRelationType().equals("Friend"))
				System.out.print(super.getRelationship().get(i).getRelevantPerson().getName() + " ");
		}
		System.out.println("");

		System.out.print("Parents:  ");
		for (int i = 0; i < super.getRelationship().size(); i++) {
			if (super.getRelationship().get(i).getRelationType().equals("Parent"))
				System.out.print(super.getRelationship().get(i).getRelevantPerson().getName() + " ");
		}
		System.out.println("");
	}

	/** 
	 * Children from the same family cannot be friends 
	 * Children whose age gap is more than 3 cannot be friends
	 * @see FriendRelation#addFriend(Person)
	 */
	@Override
	public boolean addFriend(Person selectedPerson) {
		// TODO Auto-generated method stub

		RelationshipStore friendRelation1 = new RelationshipStore();// for own relationship
		RelationshipStore friendRelation2 = new RelationshipStore();// for friend' relationship
		Person parent = new Adult();
		boolean isInSameFam = false;

		friendRelation1.setRelationType("Friend");
		friendRelation2.setRelationType("Friend");

		int ageGap = Math.abs(this.getAge() - selectedPerson.getAge());

		for (int i = 0; i < this.getRelationship().size(); i++) {
			if (this.getRelationship().get(i).getRelationType().equals("Parent")) {
				parent = this.getRelationship().get(i).getRelevantPerson();
			}
		}
		for (int i = 0; i < selectedPerson.getRelationship().size(); i++) {
			if (selectedPerson.getRelationship().get(i).getRelationType().equals("Parent")
					&& selectedPerson.getRelationship().get(i).getRelevantPerson().equals(parent)) {
				isInSameFam = true;
				break;

			}
		}
		if (isInSameFam) {
			System.out.println("They are in same family, they cannot be friends.");
			return false;

		} else {

			if (ageGap <= 3) {

				friendRelation1.setRelevantPerson(selectedPerson);
				this.addRelationship(friendRelation1);

				friendRelation2.setRelevantPerson((Person) this);
				selectedPerson.addRelationship(friendRelation2);
				return true;
			} else {
				System.out.println("Gap is too big, they cannot be friends.");
				return false;
			}
		}

	}


	/** 
	 * This method remove person's friends
	 * @see FriendRelation#removeFriend(Person)
	 */
	@Override
	public boolean removeFriend(Person seletedPerson) {
		
		this.getRelationship().remove(this.getRelationByPerson(seletedPerson));
		seletedPerson.getRelationship().remove(seletedPerson.getRelationByPerson(this));
		return true;

	}


	/** 
	 * This method check if the selected person is this person's friend
	 * @see FriendRelation#isFriend(Person)
	 */
	public boolean isFriend(Person seletedPerson) {
		for (int i = 0; i < this.getRelationship().size(); i++) {
			if (this.getRelationship().get(i).getRelevantPerson().equals(seletedPerson)) {
				if (this.getRelationship().get(i).getRelationType().equals("Friend"))
					return true;
			}
		}
		return false;
	}



	/** 
	 * This method adds parent of child
	 * @see ParentRelation#addParent(Person)
	 */
	@Override
	public void addParent(Person seletedPerson) {
		// TODO Auto-generated method stub
		RelationshipStore parentRelation = new RelationshipStore();
		parentRelation.setRelationType("Parent");
		parentRelation.setRelevantPerson(seletedPerson);
		this.addRelationship(parentRelation);
	}

	/** 
	 * This method adds parents by the run time input, for input data
	 * @see ParentRelation#addParent(Person, Person)
	 */
	@Override
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

	/** 
	 * This method get the friend list of the person
	 * @see FriendRelation#getFriendList()
	 */
	@Override
	public ArrayList<Person> getFriendList() {
		// TODO Auto-generated method stub
		
		ArrayList<Person> tempPerson = new ArrayList();
		for(int i=0;i<this.getRelationship().size();i++)
		{
			if(this.getRelationship().get(i).getRelationType().equals("Friend"))
				tempPerson.add(this.getRelationship().get(i).getRelevantPerson());
		}
		return tempPerson;
	}

}
