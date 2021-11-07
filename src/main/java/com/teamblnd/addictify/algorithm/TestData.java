package com.teamblnd.addictify.algorithm;

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
		
		plan.setDataImage(dataImage1);
		plan.initiateState(dataImage1.getState());
		
		
		DataImage dataImage2 = plan.getUpdatedData();
		System.out.println(dataImage2.toString() + "\n");
		
		dataImage2.addData(12);
		plan.setDataImage(dataImage2);
		plan.initiateState(dataImage2.getState());
		
		DataImage dataImage3 = plan.getUpdatedData();
		System.out.println(dataImage3.toString() + "\n");
		
		dataImage3.addData(12);
		plan.setDataImage(dataImage3);
		plan.initiateState(dataImage3.getState());
		
		DataImage dataImage4 = plan.getUpdatedData();
		System.out.println(dataImage4.toString() + "\n");
		
		dataImage4.addData(15);
		plan.setDataImage(dataImage4);
		plan.initiateState(dataImage4.getState());
		
		DataImage dataImage5 = plan.getUpdatedData();
		System.out.println(dataImage5.toString() + "\n");
		
		dataImage5.addData(15);
		plan.setDataImage(dataImage5);
		plan.initiateState(dataImage5.getState());
		
		DataImage dataImage6 = plan.getUpdatedData();
		System.out.println(dataImage6.toString() + "\n");
		
		dataImage6.addData(15);
		plan.setDataImage(dataImage6);
		plan.initiateState(dataImage6.getState());
		
		DataImage dataImage7 = plan.getUpdatedData();
		System.out.println(dataImage7.toString() + "\n");
		
		
		dataImage7.addData(15);
		plan.setDataImage(dataImage7);
		plan.initiateState(dataImage7.getState());
		
		DataImage dataImage8 = plan.getUpdatedData();
		System.out.println(dataImage8.toString() + "\n");
		
		
		dataImage8.addData(15);
		plan.setDataImage(dataImage8);
		plan.initiateState(dataImage8.getState());
		
		DataImage dataImage9 = plan.getUpdatedData();
		System.out.println(dataImage9.toString() + "\n");
		
		dataImage9.addData(12);
		plan.setDataImage(dataImage9);
		plan.initiateState(dataImage9.getState());
		
		DataImage dataImage10 = plan.getUpdatedData();
		System.out.println(dataImage10.toString() + "\n");
		
		dataImage10.addData(12);
		plan.setDataImage(dataImage10);
		plan.initiateState(dataImage10.getState());
		
		DataImage dataImage11 = plan.getUpdatedData();
		System.out.println(dataImage11.toString() + "\n");
		

		dataImage11.addData(12);
		plan.setDataImage(dataImage11);
		plan.initiateState(dataImage11.getState());
		
		DataImage dataImage12 = plan.getUpdatedData();
		System.out.println(dataImage12.toString() + "\n");
		

		dataImage12.addData(12);
		plan.setDataImage(dataImage12);
		plan.initiateState(dataImage12.getState());
		
		DataImage dataImage13 = plan.getUpdatedData();
		System.out.println(dataImage13.toString() + "\n");
		
		dataImage13.addData(12);
		plan.setDataImage(dataImage13);
		plan.initiateState(dataImage13.getState());
		
		DataImage dataImage14 = plan.getUpdatedData();
		System.out.println(dataImage14.toString() + "\n");
		
		
		
		
	}
	
	public static void main(String[] args) {
		test1();
	}
	
	
}
