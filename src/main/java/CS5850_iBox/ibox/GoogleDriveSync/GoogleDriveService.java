package CS5850_iBox.ibox.GoogleDriveSync;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;



public class GoogleDriveService {
    

	
	public boolean upload(String filename, Drive service){
    	try {
		File fileMetadata = new File();
    	fileMetadata.setName(filename);
    	
    	java.io.File filePath = new java.io.File("./iboxLocalDrive/"+filename);
    	FileContent mediaContent = new FileContent("text/txt", filePath);
    	File file = service.files().create(fileMetadata, mediaContent)
    	    .setFields("id")
    	    .execute();
    	
    	return true; //!file.getId().isEmpty();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return false;
    	}
		
    }
    
    public boolean deleteFile(String filename, Drive service)  {

    	try {
    		String fileId = getFileId(filename, service);

	    	if (fileId == null) {
				//throw new FileNotFoundException();
				return false;
			} else {
				service.files().delete(fileId).execute();
		    	return true;
			}
	    	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
    
    public boolean modifyFile(String filename, Drive service){
	
    	try {
    		
    		String fileId = getFileId(filename,service);
	    	if (fileId == null) {
	    		return false;
	    	}
	    	else
	    	{
				File body = new File();
				body.setName(filename);
		    	java.io.File filePath = new java.io.File("./iboxLocalDrive/"+filename);
		    	FileContent mediaContent = new FileContent("text/txt", filePath);
				File file = service.files().update(fileId, body, mediaContent).execute();
				return true;
				//return !file.getId().isEmpty();
	    	}
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    		return false;
    	}

	}

    public String getFileId(String filename, Drive service){ 	
    	String fileID= null;

    	try {
	        FileList result = service.files().list().execute();
	        List<File> files = result.getFiles();
	       
	        for (File file : files) {
	        	if(file.getName().equals(filename))
	        	{
	        		fileID =  file.getId();
	        	}
	        }
    	}
    	catch(Exception e)
    	{
    		fileID= null;
    		System.out.println(e.getMessage());
    	}
    	return fileID;
		
    }

    
}