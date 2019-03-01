package CS5850_iBox.ibox.drive;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

import org.junit.Test;


public class WatcherServiceTest {
	
//	@Test
//	public void testRegister() throws GeneralSecurityException {
//		boolean thrown = true;
//
//	  try {
//		  	WatcherService watcherService = new WatcherService();
//		  	watcherService.watchFiles();
//		  } 
//	  catch (IOException e) {
//		    thrown = false;
//		    e.printStackTrace();
//		  }
//		 assertTrue(thrown);
//	}
	
	@Test
	public void testWatchFiles() throws GeneralSecurityException {
		boolean flag = false;
		try {
			TimerThreadUtility tt = new TimerThreadUtility();
			tt.start();
			WatcherService watcherService = new WatcherService();
		  	watcherService.watchFiles();
			java.io.File file = new java.io.File("iboxLocalDrive/infinite_test.txt");
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
			file.setLastModified(new Date().getTime());
			file.delete();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(flag);
	}

}
