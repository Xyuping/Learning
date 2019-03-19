package com.xyp.dymatic;

public class ArrayMul {
	public static int minMul(int []r) {	//r为矩阵规模
		int[][] c = new int[r.length-1][r.length-1];
		for(int i=0;i<c.length;i++) {
			c[i][i]=0;
		}
		for(int d=1;d<c.length;d++) {
			for(int i=0;i<c.length-d;i++) {
				int j=d+i;
				c[i][j]=Integer.MAX_VALUE;
				for(int k=i+1;k<=j;k++) {
					int temp = c[i][k-1]+c[k][j]+r[i]*r[k]*r[j+1];
					if(temp<c[i][j])
						c[i][j]=temp;
				}
			}
		}
		return c[0][c.length-1];
	}

}
