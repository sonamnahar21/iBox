package CS5850_iBox.ibox.GoogleDriveSync;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleDriveManager {
	
	GoogleDriveService googleDriveService;


	public GoogleDriveManager() throws GeneralSecurityException, IOException
	{
	
		File file = new File("./tokens/StoredCredential");
	  if(file.delete()) 
	  { 
	      System.out.println("File deleted successfully"); 
	      googleDriveService  = new GoogleDriveService();

	  } 
	  else
	  { 
	      System.out.println("Failed to delete the file"); 
	  } 
	}
	public boolean createFile(String filename) throws GeneralSecurityException, IOException {
		if(googleDriveService.upload(filename)) {
			return true; 
		}
		else {
			return false; 
		}
	}
	public void deleteFile(String filename) throws GeneralSecurityException, IOException {
		googleDriveService.deleteFile(filename);
	}
	public boolean modifyFile(String filename) throws GeneralSecurityException, IOException {
		if(googleDriveService.modifyFile(filename)) {
			return true; 
		}
		else {
			return false;
		}
	}
}
