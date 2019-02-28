package CS5850_iBox.ibox.GoogleDriveSync;

//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//import static org.mockito.Matchers.*;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.GeneralSecurityException;
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.FileContent;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.model.File;
//import com.google.api.services.drive.model.FileList;
//
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.powermock.api.easymock.PowerMock;

//@RunWith(PowerMockRunner.class)
//@PowerMockIgnore({ "javax.net.ssl.*", "javax.security.*" })

public class GoogleDriveServiceTest {
//	
//	GoogleDriveService googleDriveService = new GoogleDriveService();
//	Drive servicemock = mock(Drive.class, RETURNS_DEEP_STUBS);
//	@Before
//	public void setup() throws IOException {
//		String filename = "a.txt";
//		File fileMetadata = new File();
//    	fileMetadata.setName(filename);
//    	java.io.File filePath = new java.io.File("./iboxLocalDrive/"+filename);
//    	FileContent mediaContent = new FileContent("text/txt", filePath);
//    	
//		Drive.Files mock2 = mock(Drive.Files.class);
//		Drive.Files.Create mock3 = mock(Drive.Files.Create.class);
//		Drive.Files.Create mock4 = mock(Drive.Files.Create.class);		
//		File mock5 = mock(File.class);
//		
//		
//		when(servicemock.files()).thenReturn(mock2);
//		when(mock2.create(any(File.class),any(FileContent.class))).thenReturn(mock3);
//		when(mock3.setFields(eq("id"))).thenReturn(mock4);
//		when(mock4.execute()).thenReturn(mock5);
//		
//		when(servicemock.files()).thenReturn(mock2);
//		when(mock2.create(any(File.class),any(FileContent.class))).thenReturn(mock3);
//		when(mock3.setFields(eq("id"))).thenReturn(mock4);
//		PowerMockito.when(mock4.execute()).thenReturn(mock5);
//	}
	
	
//	@PrepareForTest(File.class)
//	@Test
//	public void testUpload() throws IOException, GeneralSecurityException {
//		assertTrue(googleDriveService.upload("a.txt", servicemock));
//		verify(servicemock, times(1)).files();		
//	}
//	@PrepareForTest(File.class)
//	@Test
//	public void testDeleteFile() throws IOException, GeneralSecurityException {
//				
//		assertFalse(googleDriveService.deleteFile("a.txt", servicemock));
//		verify(servicemock, times(1)).files();		
//		
//	}
//	@PrepareForTest(File.class)
//	@Test
//	public void testModifyFile() throws IOException, GeneralSecurityException {
//				
//		assertFalse(googleDriveService.modifyFile("a.txt", servicemock));
//		verify(servicemock, times(1)).files();		
//	}

}
