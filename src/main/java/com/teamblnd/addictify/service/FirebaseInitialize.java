package com.teamblnd.addictify.service;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


public class FirebaseInitialize 
{
	public void initialize() throws IOException
	{
		FileInputStream serviceAccount =
				  new FileInputStream("./serviceAccountKey.json");

				@SuppressWarnings("deprecation")
				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();

				FirebaseApp.initializeApp(options);
	}
}
