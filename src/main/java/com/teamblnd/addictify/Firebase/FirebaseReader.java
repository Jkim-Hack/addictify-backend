package com.teamblnd.addictify.Firebase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.teamblnd.addictify.algorithm.DataImage;

public class FirebaseReader 
{
	
	private Firestore database;
	private String user;
	private DocumentSnapshot document;
	
	public FirebaseReader(Firestore database, String user)
	{
		this.database = database;
		this.user = user;
		getUserData();
	}
	
	public void getUserData()
	{
		DocumentReference docRef = database.collection("users").document(user);
		ApiFuture<DocumentSnapshot> future = docRef.get();
		
		try {
			DocumentSnapshot document = future.get();
			this.document = document;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public DataImage getDataImage(String weekId)
	{
		int[] streaks = getStreaks();
		int currentGoal = getCurrentGoal();
		Map<Long, Long> map = getCurrentWeekCount(weekId);
		
		int positiveStreak = streaks[0];
		int negativeStreak = streaks[1];
		
		int[] data = parseMap(map.values());
		
		String state = getState();
		
		return new DataImage(data, currentGoal, positiveStreak, negativeStreak, state);
	}
	
	/* 
	 * Ensures:
	 * 	getStreaks[0] = positiveStreak
	 * 	getStreaks[1] = negativeStreak
 	 * 
	 */
	public int[] getStreaks()
	{
		int[] streaks = new int[2];
		
		Long tempVal1 = (Long)document.get("streakPositive");
		Long tempVal2 = (Long)document.get("streakNegative");
		streaks[0] = tempVal1.intValue();
		streaks[1] = tempVal2.intValue();
		
		return streaks;
	}
	 
	public int getCoins()
	{
		Long longCoins = (Long) document.get("coins");
		return longCoins.intValue();
	}
	
	public int getCurrentGoal()
	{
		Long tempGoal = (Long)document.get("currentGoal");
		return tempGoal.intValue() ;
	}
	
	public String getState()
	{
		return (String)document.getString("State");
	}
	
	public String currentWeekId()
	{
		String currentWeekId = document.getString("currentWeek");
		return currentWeekId;
	}
	
	public String previousWeekId()
	{
		String previousWeekId = document.getString("previousWeek");
		return previousWeekId;
	}
	
	public Map<Long, Long> getCurrentWeekCount(String weekId)
	{
		Map<Long, Long> map = new HashMap<Long, Long>();
			
		Iterable<CollectionReference> collections =
			    database.collection("users").document(user).listCollections();
		
		CollectionReference cigaretteWeeklyCountCollection = null;
		for (CollectionReference collectionReference : collections) 
		{
			System.out.println(collectionReference.getId());
			if(collectionReference.getId().equals("CigaretteWeeklyCount"))
			{
				cigaretteWeeklyCountCollection = collectionReference;
			}
		}
		
		DocumentReference weekReference = cigaretteWeeklyCountCollection.document(weekId);
		
		
		ApiFuture<DocumentSnapshot> future = weekReference.get();
		
		DocumentSnapshot weekSnapShot = null;
		try {
			weekSnapShot = future.get();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map = (HashMap<Long, Long>)weekSnapShot.get("countMap");
		
		return map;
		
	}
	
	public int[] filterValues(int[] data)
	{
		int numOfNA = 0;
		int[] filteredArray;
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] == -1)
			{
				numOfNA++;
			}
		}
		
		filteredArray = new int[data.length - numOfNA];
		
		for(int i = 0; i < filteredArray.length; i++)
		{
			filteredArray[i] = data[i];
		}
		return filteredArray;
		
	}
	
	public int[] parseMap(Collection<Long> collection)
	{
		int[] counts = new int[collection.size()];
		
		int index = 0;
		for(Long temp: collection)
		{
			int castedVal = temp.intValue();
			counts[index] = castedVal;
			index++;
		}
		
		return counts;
	}
	
}
