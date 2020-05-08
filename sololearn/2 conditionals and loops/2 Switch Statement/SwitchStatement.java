class MyClass {
	public static void main (String[] args) {
		int day = 6;
		
		switch (day) {
			case 6:
				System.out.println("Saturday");
				day = 8;
			case 7:
				System.out.println("Sunday");
				break;
			default:
				System.out.println("Another day");
		}
	}
}
