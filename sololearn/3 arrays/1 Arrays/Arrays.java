class MyClass {
	public static void main (String[] args) {
		int[] arr = new int[5];
		arr[2] = 42;
		String[] myNames = {"A", "B", "C", "D"};
		System.out.println(myNames[2]);		//C
		System.out.println(arr[2]);			//42
		System.out.println(arr.length);		//5
		
		int [] myArr = {6, 42, 3, 7};
		int sum = 0;
		for(int x = 0; x < myArr.length; x++) {
			sum += myArr[x];
		}
		System.out.println(sum);			//58
	}
}
