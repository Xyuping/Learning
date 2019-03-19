package com.xyp.dymatic;

public class MaxSubString {
	public static int maxSub(char[]a,char[]b) {
		int[][]c = new int[a.length+1][b.length+1];
		for(int i=0;i<a.length+1;i++) {
			for(int j=0;j<b.length+1;j++) {
				if(i==0||j==0) {
					c[i][j]=0;
				}
				else if(a[i-1]==b[j-1]) {
					c[i][j]=c[i-1][j-1]+1;
				}
				else {
					c[i][j]=c[i-1][j]<c[i][j-1]?c[i][j-1]:c[i-1][j];
				}
			}
		}
		return c[c.length-1][c[0].length-1];
	}

}
