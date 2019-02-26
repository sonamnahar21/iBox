package CS5850_iBox.ibox.GoogleDriveSync;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleDriveManager {

	public GoogleDriveManager()
	{

	  File file = new File("./tokens/StoredCredential");
	  if(file.delete()) 
	  { 
	      System.out.println("File deleted successfully"); 
	  } 
	  else
	  { 
	      System.out.println("Failed to delete the file"); 
	  } 
	}
	public void createFile(String filename) throws GeneralSecurityException, IOException {
		GoogleDriveService driveQuickstart  = new GoogleDriveService();
		driveQuickstart.upload(filename);
	}
	public void deleteFile(String filename) throws GeneralSecurityException, IOException {

		GoogleDriveService driveQuickstart  = new GoogleDriveService();
		driveQuickstart.deleteFile(filename);
	}
	public void modifyFile(String filename) throws GeneralSecurityException, IOException {

		GoogleDriveService driveQuickstart  = new GoogleDriveService();
		driveQuickstart.modifyFile(filename);
	}
}
