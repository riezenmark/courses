class MyClass {
	public static void main (String[] args) {
		int[][] sample = {{1, 2, 3}, {4, 5, 6}};
		int x = sample[1][0];
		System.out.println(x);				//4
		sample[0][2] = 42;
	}
}
