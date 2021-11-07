package com.teamblnd.addictify;

public class RecoveryState implements TreatmentState 
{
	private DataImage dataImage;
	
	public RecoveryState(DataImage dataImage)
	{
		this.dataImage = dataImage;
	}
	
	@Override
	public DataImage UpdateData() {
		
		int latestDataPoint = dataImage.getLatestDataPoint();
		int differenceWithGoal = dataImage.getPreviousGoal() - latestDataPoint;
		
		int positiveStreak = dataImage.getPostiveStreak();
		int negativeStreak = dataImage.getNegativeStreak();
		int newGoal = dataImage.getPreviousGoal();
		
		STATE treatmentState = STATE.RECOVERY;
		
		if(differenceWithGoal <= 0 )
		{
			positiveStreak++;
			negativeStreak = 0;
			if(positiveStreak % 2 == 0)
			{
				newGoal --;
			}
			if(newGoal == dataImage.getLeastGoalValue())
			{
				treatmentState = STATE.PROGRESS;
				positiveStreak = 1;
			}
			
		}
		
		else
		{
			positiveStreak = 0;
			if(negativeStreak >= 4)
			{
				negativeStreak = 0;
				newGoal++;
				treatmentState = STATE.RELAPSE;
			}
		}
		
		return new DataImage(dataImage.getData(), newGoal, positiveStreak, negativeStreak,treatmentState.name());
		
	}

}
