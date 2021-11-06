package com.teamblnd.regression;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class LinearRegression 
{
	public static RegressionResults generateLeaseSquareLine(double[][] data)
	{
		SimpleRegression regression = new SimpleRegression(true);
		regression.addData(data); 
		
		return regression.regress();
	}
	
	
	

}
