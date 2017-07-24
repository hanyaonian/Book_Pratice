
public class Object_test {

	public static void main(String[] args) {
		//1
		Pet pet1 = new Pet("pet", 10);
		Pet pet2 = pet1;
		pet1.setAge(20);
		System.out.println(pet2.getAge());
		//2
		pet2.setAge(20);
		System.out.println(pet1.getAge());
		//3 str1一开始指向test1，后面指向test2，因此不影响str2.这些变量名其实都可以当作是“指针”
		String str1, str2;
		str1 = "test1";
		str2 = str1;
		str1 = "shit";
		System.out.println(str1);
		System.out.println(str2);
		//4 浅复制
		Owner owner1 = new Owner("zhangsan", 20);
		Owner owner2 = (Owner)owner1.clone();
		owner1.setName("Lisi");
		System.out.println(owner1.getName());
		System.out.println(owner2.getName());
		//紧接4 浅复制的对象问题, 可见对象未复制，依然是同一个引用
		owner1.pet = new Pet("dog", 10);
		owner2 = (Owner)owner1.clone();
		owner1.pet.setName("cat");
		System.out.println(owner1.pet.getName());
		System.out.println(owner2.pet.getName());
		//5 深复制，需要将Pet此类也写一个clone，将对象内的对象也复制，大概就熟悉一下对象之间的关系吧
	}

}

class Pet {
	String name;
	int age;
	public Pet(String _name, int _age) {
		age = _age;
		name = _name;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int _age) {
		age = _age;
	}
	public void setName(String _name) {
		name = _name;
	}
}
class Owner implements Cloneable {
	private String name;
	public Pet pet;
	private int age;
	
	public Owner(String _name, int _age) {
		name = _name;
		age = _age;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		name = _name;
	}
	
	@Override
	public Object clone() {
		//initialize as null
		Owner owner = null;
		try {
			owner = (Owner)super.clone();
		} catch(Exception e) {
			
		}
		return owner;
	}
}