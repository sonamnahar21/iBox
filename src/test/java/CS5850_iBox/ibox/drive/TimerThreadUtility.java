package CS5850_iBox.ibox.drive;

import java.util.Date;


public class TimerThreadUtility extends Thread {

	@Override
	public void run() {
		try {
			System.out.println(1);
			Thread.sleep(10*1000);
			System.out.println(2);
			WatcherService.exitFlag = true;
			System.out.println(3);
			String absoluteFilePath = "iboxLocalDrive/dummy_upload.txt";
			System.out.println(4);
			java.io.File file = new java.io.File(absoluteFilePath);
			System.out.println(4);
			file.createNewFile();
			System.out.println(""+file.getAbsolutePath());
			file.setLastModified(new Date().getTime());
			System.out.println(5);
			file.delete();
			System.out.println(6);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}