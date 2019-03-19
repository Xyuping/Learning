package com.xyp.Sort;

public class Qsort {
	public static void qsort(int[] array,int left, int right) {
		if((right-left)<=0)
			return ;
		int partition = partition(array,0,right,right);
		qsort(array,left,partition-1);
		qsort(array,partition+1,right);
	}
	public static int partition(int[] array,int left,int right,int k_index) {
		int key = array[k_index];
		int leftPtr = left-1;
		int rightPtr = right;
		while(true) {
			while(leftPtr<rightPtr&&array[++leftPtr]<key);
			while(leftPtr<rightPtr&&array[--rightPtr]>key);			
			if(leftPtr>=rightPtr)
				break;
			else
				swap(array,leftPtr,rightPtr);
		}
		swap(array,rightPtr,right);
		return rightPtr;
	}
	public static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
