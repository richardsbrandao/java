package riflessione.test.construtore;


public class ConstrutoreNormaleConEccezione {

	public ConstrutoreNormaleConEccezione() throws Exception {
		throw new Exception();
	}

	public static void main(String[] args) {
		try {
			ConstrutoreNormaleConEccezione.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("aaa");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("aaa");
			e.printStackTrace();
		}
	}
	
}
