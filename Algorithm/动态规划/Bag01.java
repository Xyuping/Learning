package com.xyp.dymatic;

public class Bag01 {
	public static int maxVal(int[] v,int[] w,int g) {
		int[][] V = new int[v.length+1][g+1];
		for(int i=0;i<v.length+1;i++)
			V[i][0]=0;
		for(int i=0;i<g+1;i++)
			V[0][i]=0;
		for(int i=1;i<v.length+1;i++) {
			for(int j=1;j<g+1;j++) {
				
			    if(j<w[i-1])
					V[i][j]=V[i-1][j-1];
				else 
					V[i][j]=V[i-1][j]>V[i-1][j-w[i-1]]?V[i-1][j]:V[i-1][j-w[i-1]]+v[i-1];
			}
		}
		return V[V.length-1][V[0].length-1];
	}

}
