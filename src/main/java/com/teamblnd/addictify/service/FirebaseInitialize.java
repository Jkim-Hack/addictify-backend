package com.teamblnd.addictify.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


public class FirebaseInitialize 
{
	public void initialize() throws IOException
	{
		 InputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
		    GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		    @SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
		        .setCredentials(credentials)
		        .build();
		    FirebaseApp.initializeApp(options);

	}
}
