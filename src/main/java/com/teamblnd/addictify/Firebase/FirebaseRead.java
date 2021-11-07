package com.teamblnd.addictify.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.teamblnd.addictify.algorithm.DataImage;

public class FirebaseRead 
{
	
	private Firestore database;
	private String user;
	private DocumentSnapshot document;
	
	public FirebaseRead(Firestore database, String user)
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
		streaks[0] = (int) document.get("streakPositive");
		streaks[1] = (int) document.get("streakNegative");
		
		return streaks;
	}
	 
	public int getCoins()
	{
		return (int) document.get("coins");
	}
	
	public int getCurrentGoal()
	{
		return (int) document.get("currentGoal");
	}
	
	public Map<String, Long> getCurrentWeekCount(String weekId)
	{
		Map<String, Long> map = new HashMap<String, Long>();
			
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
		
		map = (HashMap<String, Long>)weekSnapShot.get("countMap");
		
		return map;
		
	}
	
	
}
