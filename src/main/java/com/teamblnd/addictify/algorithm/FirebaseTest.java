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
import com.teamblnd.addictify.Firebase.FirebaseHandRequest;
import com.teamblnd.addictify.Firebase.FirebaseReader;
import com.teamblnd.addictify.service.FirebaseInitialize;

public class FirebaseTest 
{
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException 
	{
		FirebaseInitialize initialize = new FirebaseInitialize();
	    initialize.initialize();
	    
	    Firestore db = FirestoreClient.getFirestore();

		
		
		FirebaseReader fireBaseRead = new FirebaseReader(db, "user1");
	   
		
	    DataImage image = fireBaseRead.getDataImage("week2");
	    
	    System.out.println(image.toString() + "\n");
	    
	    
	    FirebaseHandRequest handler = new FirebaseHandRequest(db, "user1");
	    DataImage newImage = handler.readData();
	    newImage = handler.updateData();
	    
	    System.out.println(newImage.toString() + "\n");
			
	}
}
