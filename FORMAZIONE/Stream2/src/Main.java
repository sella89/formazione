
public class Main {

	public static void main(String[] args) {
		
		Predicate<String> p1 = s->s.length()<20;
		Predicate<String> p2 = s->s.length()>5;
		
		boolean  b = p1.test("Hello");
		
		Predicate<String> p3= p1.and(p2);
		System.out.println("P3 for yes: "+p3.test("yes"));
		System.out.println("P3 for Good Morning: "+p3.test("good morning"));
		System.out.println("P3 for Good Morning Gentlemen: "+p3.test("good morning gentlemen"));
		
		Predicate<String> p4= p1.or(p2);
		System.out.println("P4 for yes: "+p4.test("yes"));
		System.out.println("P4 for Good Morning: "+p4.test("good morning"));
		System.out.println("P4 for Good Morning Gentlemen: "+p4.test("good morning gentlemen"));
		
		Predicate<String> p5 = Predicate.isEqualsTo("yes");
		System.out.println("P5 for yes: "+p5.test("yes"));
		System.out.println("P5 for No: "+p5.test("No"));
		
	}
}
