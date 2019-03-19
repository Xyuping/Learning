package com.xyp.Sort;

public class Test {
	public static void main(String[] args) {
//		int[] arr = {2,432,1,35,4,56,10};
//		BubbleSort.sort(arr);
//		SelectionSort.sort(arr);
//		InsertSort.sort(arr);
		int [] arr = new int[10];
		for(int i=0;i<10;i++) {
			arr[i] = (int) (Math.random()*99);
		}
		for(int a:arr) {		
			System.out.print(a+" ");			
		}
		Qsort.qsort(arr, 0, arr.length-1);
		System.out.println();
		
		for(int a:arr) {		
			System.out.print(a+" ");			
		}
		
	}
}
