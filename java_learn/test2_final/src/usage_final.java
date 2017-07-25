
public class usage_final {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aclass test = new Aclass();
		//1 hello world: final做参数，会改变main内的对象
		StringBuffer buffer = new StringBuffer("hello");
		test.testChangeFinalBuffer(buffer);
		System.out.println(buffer);
		//2
		StringBuffer buffer1 = new StringBuffer("hello");
		test.testChangeBuffer(buffer1);
		System.out.println(buffer1);
		//3
		StringBuffer buffer2 = new StringBuffer("hello");
		test.testChangeBufferPoint(buffer2);
		System.out.println(buffer2);
		//5: true, false, 原因：final变量在编译过程中被当作成了常量                         e                           
        String a = "hello" + 2; 
        final String b = "hello";
        String d = "hello";
        String c = b + 2; 
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
	}
}
final class Aclass {
	public void testChangeFinalBuffer(final StringBuffer buffer) {
		buffer.append(" world");
	}
	public void testChangeBuffer(StringBuffer buffer) {
		buffer.append(" world");
	}
	public void testChangeBufferPoint(StringBuffer buffer) {
		buffer = new StringBuffer("shit");
	}
	public void testPoint(Bclass test) {
		test.cool = "no";
	}
}
//4 cant extends final class
class Bclass {
	public String cool;
}