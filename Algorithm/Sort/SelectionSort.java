package com.xyp.Sort;

public class SelectionSort {
	public static void sort(int[] arr) {
		int k=0;
		int temp;
		for(int i=0;i<arr.length-1;i++) {
			k=i;
			for(int j=i;j<arr.length;j++) {
				if(arr[k]>arr[j]) 
					k=j;
			}
		temp = arr[k];
		arr[k]=arr[i];
		arr[i]=temp;
		}
	}

}
