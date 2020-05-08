class MyClass {
	public static void main (String[] args) {
		for (int x = 10; x <= 40; x += 10) {
			if (x == 30) {
				continue;
			}
			System.out.println(x);
		}
	}
}

/*
  10
  20
  40
*/
