package com.teamblnd.addictify;

import java.util.Arrays;

public class DataImage 
{
	private int[] data;
	private int previousGoal;
	private int postiveStreak;
	private int negativeStreak;
	private String previousState;
	
	public DataImage(int[] data, int previousGoal, int positiveStreak, int negativeStreak, String previousState) 
	{
		// TODO Auto-generated constructor stub
		this.data = data;
		this.previousGoal = previousGoal;
		this.postiveStreak = positiveStreak;
		this.negativeStreak = negativeStreak;
		this.previousState = previousState;
		
	}
	
	public int getLatestDataPoint()
	{
		return data[data.length - 1];
	}
	
	public STATE getState()
	{
		STATE state;
		String stateAsString = previousState.toUpperCase();
		
		if(stateAsString.equals("PROGRESS"))
		{
			state = STATE.PROGRESS;
		}
		else if(stateAsString.equals("RECOVERY"))
		{
			state = STATE.RECOVERY;
		}
		else
		{
			state = STATE.RELAPSE;
		}
		
		return state;
	}

	public int getLeastGoalValue()
	{
		int min = data[0];
		
		for(int i = 1; i < data.length; i++)
		{
			if(data[i] < min)
			{
				min = data[i];
			}
		}
		System.out.println(min);
		return min;
		
	}
	
	@Override
	public String toString() {
		return "DataImage [data=" + Arrays.toString(data) + ", previousGoal=" + previousGoal + ", postiveStreak="
				+ postiveStreak + ", negativeStreak=" + negativeStreak + ", previousState=" + previousState + "]";
	}

	public int[] getData() 
	{
		return data;
	}

	public int getPreviousGoal() 
	{
		return previousGoal;
	}

	public int getPostiveStreak() 
	{
		return postiveStreak;
	}

	public int getNegativeStreak() 
	{
		return negativeStreak;
	}
	
	public void addData(int value)
	{
		int[] newArray = new int[this.data.length + 1];
		for(int i = 0; i < this.data.length; i++)
		{
			newArray[i] = data[i];
		}
		newArray[this.data.length] = value;
		this.data = newArray;
	}
	
}
