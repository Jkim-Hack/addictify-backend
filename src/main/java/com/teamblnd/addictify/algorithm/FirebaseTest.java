package com.teamblnd.addictify.algorithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.teamblnd.addictify.Firebase.FirebaseRead;
import com.teamblnd.addictify.service.FirebaseInitialize;

public class FirebaseTest 
{
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException 
	{
		FirebaseInitialize initialize = new FirebaseInitialize();
	    initialize.initialize();
	    
	    Firestore db = FirestoreClient.getFirestore();

		
		
		FirebaseRead fireBaseRead = new FirebaseRead(db, "user1");
	   
	    
	    Map<String, Long> map =  fireBaseRead.getCurrentWeekCount("week1");
	    
	    int val = map.get("Monday").intValue();
	    System.out.println(val);

	}
}
