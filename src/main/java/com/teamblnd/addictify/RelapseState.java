package com.teamblnd.addictify;

public class RelapseState implements TreatmentState
{
	private DataImage dataImage;
	
	public RelapseState(DataImage dataImage)
	{
		this.dataImage = dataImage;
	}
	
	@Override
	public DataImage UpdateData() {
		
		int latestDataPoint = dataImage.getLatestDataPoint();
		int differenceWithGoal = latestDataPoint - dataImage.getPreviousGoal();
		
		int positiveStreak = dataImage.getPostiveStreak();
		int negativeStreak = dataImage.getNegativeStreak();
		int newGoal = dataImage.getPreviousGoal();
		
		STATE treatmentState = STATE.RELAPSE;
		
		if(differenceWithGoal <= 0 )
		{
			positiveStreak++;
			negativeStreak = 0;
			treatmentState = STATE.RECOVERY;
		}
		
		else
		{
			if(negativeStreak <= 2)
			{
				negativeStreak++;
				newGoal++;
			}
		}
		
		return new DataImage(dataImage.getData(), newGoal, positiveStreak, negativeStreak,treatmentState.name());
		
	}

}
