package CS5850_iBox.ibox.GoogleDriveSync;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;


import CS5850_iBox.ibox.drive.WatcherService;
public class GoogleDriveServiceIntegTest {
	static GoogleDriveService googleDriveService;
	static Drive service;
	@Before
	public void setup() throws GeneralSecurityException, IOException {
		googleDriveService = new GoogleDriveService();
    	String APPLICATION_NAME = "Google Drive API Java Quickstart";
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, WatcherService.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        
        File fileForDelete = new File("./iboxLocalDrive/test_Delete.txt");
        fileForDelete.createNewFile();
		googleDriveService.upload(fileForDelete.getName(), service);
		File fileForModify = new File("./iboxLocalDrive/test_Modify.txt");
		fileForModify.createNewFile();
		googleDriveService.upload(fileForModify.getName(), service);
	}
	@Test
	public void testUploadIntegration() throws IOException, GeneralSecurityException {
		assertTrue(googleDriveService.upload("test27.txt", service));
	}
	@Test
	public void uploadIntegrationTestForFileNotFound() throws IOException, GeneralSecurityException {
		assertFalse(googleDriveService.upload("dummy.txt", service));
	}
	@Test
	public void testModifyFileIntegrationTest() throws IOException, GeneralSecurityException {	
			BufferedWriter bufwriter = new BufferedWriter(new FileWriter("./iboxLocalDrive/"+"test_Modify.txt"));
	        bufwriter.write("habsfhbsghbgk");//writes the edited string buffer to the new file
	        bufwriter.close();//closes the file			

	        assertTrue(googleDriveService.modifyFile("test_Modify.txt", service));
	}
	@Test
	public void testModifyFileIntegrationTestForFileNotFound() throws IOException, GeneralSecurityException {
		assertFalse(googleDriveService.modifyFile("dummyfile.txt", service));
	}
	@Test
	public void testDeleteFileIntegrationTest() throws IOException, GeneralSecurityException {
		assertTrue(googleDriveService.deleteFile("test_Delete.txt", service));
	}
	@Test
	public void testDeleteFileIntegrationTestForNotFileFound() throws IOException, GeneralSecurityException {
		assertFalse(googleDriveService.deleteFile("dummyfile.txt", service));
	}
	@Test
	public void testGetFileIdIntegrationTest()  {						
		assertNull(googleDriveService.getFileId("a.txt", service));	
	}
	@AfterClass
	public static void cleanUp()
	{
		boolean isDeletedFromDrive = googleDriveService.deleteFile("test_Modify.txt", service);
		File fileModified = new File("./iboxLocalDrive/test_Modify.txt"); 
		File fileDeleted = new File("./iboxLocalDrive/test_Delete.txt"); 
        
        if(fileModified.delete() && fileDeleted.delete() && isDeletedFromDrive) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
	}
}

