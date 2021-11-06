package com.teamblnd.addictify;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.teamblnd.regression.LinearRegression;

public class DataImage 
{

	private double[][] data;
	private RegressionResults regressionResults;
	private boolean regressionResultsReady;
	private float coefficient;
	private int currentGoal;
	private int actualVal;
	
	public DataImage(double[][] data, int currentGoal, int actualVal) 
	{
		// TODO Auto-generated constructor stub
		this.data = data;
		this.regressionResultsReady = false;
		coefficient = .2f;
	}
	
	/*
	 * requires: regressionResultsReady == TRUE
	 * 
	 * ensures: 
	 * 		getParameters is a double array s.t.
	 * 			0th Index - Intercept
	 * 			1st Index - Slope
	 */
	public double[] getParameters()
	{
		double slope = regressionResults.getParameterEstimate(1);
		double intercept = regressionResults.getParameterEstimate(0);
		
		double[] parameters = {intercept, slope};
		
		return parameters;
	}
	
	public int getNewGoal()
	{
		double[] parameters = getParameters();
		int newGoal;
		
		double slope = parameters[1];
		
		int goalDifference = currentGoal - actualVal;
		
		if( -.2 < slope && slope < .2 )
		{
			
			if(goalDifference ==  0)
			{
				newGoal = currentGoal - 1;
			}
			else if(goalDifference > 0)
			{
				newGoal = (int)( currentGoal - (goalDifference * coefficient));
			}
			else
			{
				newGoal = (int)( (goalDifference * coefficient) + currentGoal);
			}
		}
		else if( slope < -.2)
		{
			if(goalDifference ==  0)
			{
				newGoal = currentGoal - 1;
			}
			else if(goalDifference > 0)
			{
				newGoal = (int)( currentGoal - (goalDifference * Math.abs(slope)));
			}
			else
			{
				newGoal = (int)( currentGoal + (goalDifference/2));
			}
		}
		else
		{
			if(goalDifference == 0)
			{
				newGoal = currentGoal;
			}
			else if(goalDifference > 0)
			{
				newGoal = currentGoal - 1;
			}
			else
			{
				newGoal = currentGoal;
			}
		}
		
		return newGoal;
		
		
	}

	
	/*
	 * Runs the regression on the Data
	 * 
	 * ensures: regressionResultsReady
	 * 
	 */
	public void runRegression()
	{
		this.regressionResults = LinearRegression.generateLeaseSquareLine(this.data);
		this.regressionResultsReady = true;
	}
	

	
	
	
	
}
