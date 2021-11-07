package com.teamblnd.addictify;

import org.apache.commons.math3.stat.regression.RegressionResults;



public class TestData 
{

	public static void test1()
	{
		int[] data = {15,14,16,15,14,13,12,11};
		int currentGoal = 14;
		DataImage image = new DataImage(data, currentGoal, 0, 0, "PROGRESS");
		
		TreatmentPlan plan = new TreatmentPlan(image);
		DataImage dataImage1 = plan.getUpdatedData();
		System.out.println(dataImage1.toString() + "\n");
		
		dataImage1.addData(13);
		
		System.out.println(dataImage1.toString() + "\n ");
		plan.setDataImage(dataImage1);
		plan.initiateState(dataImage1.getState());
		
		
		DataImage dataImage2 = plan.getUpdatedData();
		System.out.println(dataImage2.toString() + "\n");
		
		dataIm
		
		
	}
	
	public static void main(String[] args) {
		test1();
	}
	
	
}
