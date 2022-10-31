package javaGenericEnumTest;

public class Homework {
	public enum Planet {
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);

		private int r;

		Planet(int data) {
			this.r = data;
		}

		public int getRadius() {
			return r;
		}

	}

	public static void main(String[] args) {

		for (Planet p : Planet.values()) {
			System.out.println(p.name() + " : " + (4 * Math.PI * Math.pow(p.getRadius(), 2)));
		}
	}
}
