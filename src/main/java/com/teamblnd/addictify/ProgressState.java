package com.teamblnd.addictify;

public class ProgressState implements TreatmentState{
	
	DataImage dataImage;
	
	public ProgressState(DataImage dataImage)
	{
		this.dataImage = dataImage;
	}

	public void ChangeState() 
	{
		
		
	}
	

	public STATE CheckChangeState() 
	{
		int latestDataPoint = dataImage.getLatestDataPoint();
		int differenceWithGoal = dataImage.getPreviousGoal() - latestDataPoint;
		
		
		if(differenceWithGoal <= 0 )
		{
			
		}
		
		return null;
	}
	
	private int getNewGoal()
	{
		int latestDataPoint = dataImage.getLatestDataPoint();
		int postiveStreak = dataImage.getPostiveStreak();
		
		return 0;
	}
	
	@Override
	public DataImage UpdateData() {
		
		int latestDataPoint = dataImage.getLatestDataPoint();
		int differenceWithGoal = latestDataPoint - dataImage.getPreviousGoal() ;
		
		int positiveStreak = dataImage.getPostiveStreak();
		int negativeStreak = dataImage.getNegativeStreak();
		int newGoal = dataImage.getPreviousGoal();
		
		STATE treatmentState = STATE.PROGRESS;
		
		if(differenceWithGoal <= 0 )
		{
			positiveStreak ++;
			if(positiveStreak % 3 == 0)
			{
				newGoal = dataImage.getPreviousGoal() + 1;
			}
		}
		else
		{
			positiveStreak = 0;
			if(differenceWithGoal >= 2)
			{
				negativeStreak++;
				if(negativeStreak >= 3)
				{
					treatmentState = STATE.RELAPSE;
					negativeStreak = 1;
					newGoal++;
				}
			}
		}
		
		return new DataImage(dataImage.getData(), newGoal, positiveStreak, negativeStreak,treatmentState.name());
		
	}
}
