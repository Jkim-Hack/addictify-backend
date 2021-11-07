package com.teamblnd.addictify.Firebase;

import java.util.Map;

import javax.swing.text.AbstractDocument.LeafElement;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.teamblnd.addictify.algorithm.DataImage;
import com.teamblnd.addictify.algorithm.TreatmentPlan;

public class FirebaseHandRequest 
{

	private Firestore database;	
	private FirebaseReader reader;
	
	
	public FirebaseHandRequest(Firestore database, String user)
	{
		this.database = database;
		reader = new FirebaseReader(database, user);
	}
	 
	
	
	public DataImage updateData()
	{
		String previousWeedId = reader.previousWeekId();
		Map<Long, Long> map = reader.getCurrentWeekCount(previousWeedId);
		int[] previousWeekValues = reader.parseMap(map.values());
		
		DataImage image = readData();
		int[] data = reader.filterValues(joinArray(previousWeekValues, image.getData()));
		
		image.setData(data);
		
		TreatmentPlan plan = new TreatmentPlan(image);
		
		return plan.getUpdatedData();
		
	}
	
	public DataImage readData()
	{
		String currentWeekId = reader.currentWeekId();
		
		return reader.getDataImage(currentWeekId);
		
	}
	
	public void writeData()
	{
		DataImage image = updateData();
		int positiveStreak = reader.getStreaks()[0];
		int newCoinValue = reader.getCoins() * 10 * positiveStreak;
		
		int[] data = getLastSevenPoints(image.getData());
		image.setData(data);
		
	}
	
	
	  private int[] joinArray(int[] firstArray, int[] secondArray)
	  {
		  int[] newArray = new int[firstArray.length + secondArray.length];
		  
		  for(int i = 0; i < firstArray.length; i++)
		  {
			  if(i < firstArray.length)
			  {
				  newArray[i] = firstArray[i];
			  }
		  }
		  
		  for(int i = 0; i < secondArray.length; i++)
		  {
			  newArray[i + firstArray.length] = secondArray[i];
		  }
		  return newArray;
	  }
	  
	  private int[] getLastSevenPoints(int[] array)
	  {
		  int[] newArray = new int[7];
		  int length = array.length - newArray.length;
		  
		  for(int i = length; i < length + 7; i++)
		  {
			  newArray[i] = array[i];
		  }
		  return newArray;
	  }
	
	
}
