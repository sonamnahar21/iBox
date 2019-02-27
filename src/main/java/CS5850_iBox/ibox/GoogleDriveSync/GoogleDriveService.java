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
    

	
	public boolean upload(String filename, Drive service) throws GeneralSecurityException, IOException {

    	System.out.println("in upload File" +filename );
    	File fileMetadata = new File();
    	fileMetadata.setName(filename);
    	
    	System.out.println("1");
    	java.io.File filePath = new java.io.File("./iboxLocalDrive/"+filename);
    	System.out.println("2");
    	FileContent mediaContent = new FileContent("text/txt", filePath);
    	System.out.println("3");
    	File file = service.files().create(fileMetadata, mediaContent)
    	    .setFields("id")
    	    .execute();
    	System.out.println("4");
    	
    	System.out.println("file uploaded");
    	return !file.getId().isEmpty();
    }
    
    public void deleteFile(String filename, Drive service) throws GeneralSecurityException, IOException  {

    	String fileId = getFileId(filename, service);
		if (fileId == null) {
			throw new FileNotFoundException();
		} else {
			service.files().delete(fileId).execute();
		}
	}
    
    public boolean modifyFile(String filename, Drive service) throws IOException, GeneralSecurityException {
        
    	String fileId = getFileId(filename,service);
	
		File body = new File();
		body.setName(filename);
    	java.io.File filePath = new java.io.File("./iboxLocalDrive/"+filename);
    	FileContent mediaContent = new FileContent("text/txt", filePath);
		File file = service.files().update(fileId, body, mediaContent).execute();
		return !file.getId().isEmpty();
		
	}

    private String getFileId(String filename, Drive service) throws GeneralSecurityException, IOException {
    	
    	

        FileList result = service.files().list().execute();
        List<File> files = result.getFiles();
       
        for (File file : files) {
        	if(file.getName().equals(filename))
        	{
        		return file.getId();
        	}
        }
		return null;
    }

    
}