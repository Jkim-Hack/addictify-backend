package com.teamblnd.addictify;

public class TreatmentPlan 
{
	private DataImage dataImage;
	private TreatmentState currentState;
	
	public TreatmentPlan(DataImage dataImage)
	{
		STATE state = dataImage.getState();
		this.dataImage = dataImage;
		initiateState(state);
		
	}
	
	public void initiateState(STATE state)
	{
		if(state == STATE.PROGRESS)
		{
			currentState = new ProgressState(dataImage);
		}
		else if(state == STATE.RECOVERY)
		{
			currentState = new RecoveryState(dataImage);
		}
		else
		{
			currentState = new RelapseState(dataImage);
		}
		
	}
	
	
	public DataImage getUpdatedData()
	{
		return currentState.UpdateData();
	}

	public void setCurrentState(TreatmentState currentState) {
		this.currentState = currentState;
	}

	public void setDataImage(DataImage dataImage) {
		this.dataImage = dataImage;
	}
	
	

	
}
