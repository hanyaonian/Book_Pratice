
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
		//3 str1һ��ʼָ��test1������ָ��test2����˲�Ӱ��str2.��Щ��������ʵ�����Ե����ǡ�ָ�롱
		String str1, str2;
		str1 = "test1";
		str2 = str1;
		str1 = "shit";
		System.out.println(str1);
		System.out.println(str2);
		//4 ǳ����
		Owner owner1 = new Owner("zhangsan", 20);
		Owner owner2 = (Owner)owner1.clone();
		owner1.setName("Lisi");
		System.out.println(owner1.getName());
		System.out.println(owner2.getName());
		//����4 ǳ���ƵĶ�������, �ɼ�����δ���ƣ���Ȼ��ͬһ������
		owner1.pet = new Pet("dog", 10);
		owner2 = (Owner)owner1.clone();
		owner1.pet.setName("cat");
		System.out.println(owner1.pet.getName());
		System.out.println(owner2.pet.getName());
		//5 ��ƣ���Ҫ��Pet����Ҳдһ��clone���������ڵĶ���Ҳ���ƣ���ž���Ϥһ�¶���֮��Ĺ�ϵ��
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