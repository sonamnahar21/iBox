package CS5850_iBox.ibox.drive;
import static java.nio.file.StandardWatchEventKinds.*;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import CS5850_iBox.ibox.GoogleDriveSync.GoogleDriveService;

/**
 * Hello world!
 *
 */
public class WatcherService 
{
	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private String path = "./tokens/StoredCredential";
	GoogleDriveService googleDriveService;
	static boolean exitFlag;
	
	public WatcherService() throws GeneralSecurityException, IOException {
		googleDriveService = new GoogleDriveService();
	}
	public String watchFiles() throws IOException, GeneralSecurityException
    {
    	
    	WatchService watcher = FileSystems.getDefault().newWatchService(); 

    	Path dir = Paths.get("./iboxLocalDrive/");

    	WatchEvent.Kind<?> kind = null;
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    	try {
    	    WatchKey key = dir.register(watcher,
    	                           ENTRY_CREATE,
    	                           ENTRY_DELETE,
    	                           ENTRY_MODIFY);
    	    
    	} catch (IOException x) {
    	    System.err.println(x);
    	}
    	
    	while(!exitFlag) {
    	    WatchKey key = null;
    	    try {
    	        key = watcher.take();
    	    } catch (InterruptedException x) {
    	        System.out.println(x.getMessage());
    	    }

    	    for (WatchEvent<?> event: key.pollEvents()) {
    	         kind = event.kind();    	        
    	        if (kind == OVERFLOW) {
    	            continue;
    	        }
    	        WatchEvent<Path> ev = (WatchEvent<Path>)event;
    	        Path filename = ev.context();

    	        System.out.println("*** Filename" +  filename);
    	        
    	        
    	        if(kind.toString().equals("ENTRY_CREATE")) {
    	        	if(googleDriveService.upload(filename.toString(),service)){
    	        		System.out.println(filename.toString() +" uploaded!!");
    	        	}
    	        }
    	        else if(kind.toString().equals("ENTRY_DELETE")) {
    	        	googleDriveService.deleteFile(filename.toString(),service);
    	        	System.out.println(filename.toString() +" deleted!!");
    	        }
    	        else if(kind.toString().equals("ENTRY_MODIFY")) {
    	        	if(googleDriveService.modifyFile(filename.toString(),service)) {
    	        		System.out.println(filename.toString() +" modified!!");
    	        	}
    	        }

    	    }
    	    boolean valid = key.reset();
    	    if (!valid) {
    	        break;
    	    }
    	}
		return kind.toString();


    }
    
  public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
      // Load client secrets.
      InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
      GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

      // Build flow and trigger user authorization request.
      GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
              HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
              .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
              .setAccessType("offline")
              .build();
      LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
      return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }
 
}
