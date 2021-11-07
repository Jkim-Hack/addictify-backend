package com.teamblnd.addictify.Firebase;
import com.google.api.core.ApiFuture;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.teamblnd.addictify.algorithm.DataImage;

public class FirebaseWriter 
{
	private Firestore database;
	

	public FirebaseWriter(Firestore database)
	{
		this.database = database;
	}	
	
	public void update(DataImage image, int coins, String currentWeek, String previousWeek)
	{
		DocumentReference docRef = database.collection("users").document("users1");
			
		String state = image.getState().name();
		int currentGoal = image.getPreviousGoal();
		int streakNegative = image.getNegativeStreak();
		int streakPositive = image.getPostiveStreak();
		
		
		docRef.update("state", state);
		docRef.update("currentGoal", currentGoal);
		docRef.update("streakNegative", streakNegative);
		docRef.update("streakPositive", streakPositive);
		docRef.update("currentWeek",currentWeek);
		docRef.update("previousWeek",previousWeek);
		
		
		Iterable<CollectionReference> collections =
			    database.collection("users").document("users1").listCollections();
		
		CollectionReference cigaretteWeeklyCountCollection = null;
		for (CollectionReference collectionReference : collections) 
		{
			System.out.println(collectionReference.getId());
			if(collectionReference.getId().equals("CigaretteWeeklyCount"))
			{
				cigaretteWeeklyCountCollection = collectionReference;
			}
		}
		FirebaseReader reader = new FirebaseReader(database, "user1");
		DocumentReference weekReference = cigaretteWeeklyCountCollection.document(currentWeek);
		weekReference.update("countMap", reader.getCurrentWeekCount(currentWeek));
		
	}
	
	public void writeNewDocument(DataImage image, String currentWeek)
	{
		DocumentReference docRef = database.collection("users").document("users1");
		
		String state = image.getState().name();
		int currentGoal = image.getPreviousGoal();
		int streakNegative = image.getNegativeStreak();
		int streakPositive = image.getPostiveStreak();
		
		String previousWeek = currentWeek;
		String number = currentWeek.substring(4, currentWeek.length());
		Integer newWeek = Integer.parseInt(number) + 1;
		String cur = "week" + String.valueOf(newWeek);
		
		docRef.update("state", state);
		docRef.update("currentGoal", currentGoal);
		docRef.update("streakNegative", streakNegative);
		docRef.update("streakPositive", streakPositive);
		docRef.update("currentWeek",cur);
		docRef.update("previousWeek",previousWeek);
		
		
		Map<String, Integer> docData = new HashMap<String, Integer>();
		docData.put("1", -1);
		docData.put("2", -1);
		docData.put("3", -1);
		docData.put("4", -1);
		docData.put("5", -1);
		docData.put("6", -1);
		docData.put("7", -1);
		
		this.database.collection("users").document("user1").collection("CigaretteWeeklyCount").document(currentWeek).set(docData);
		
	}
	
	
	
}
