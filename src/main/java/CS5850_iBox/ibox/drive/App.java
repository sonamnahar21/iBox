package CS5850_iBox.ibox.drive;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, GeneralSecurityException
    {
    	
    	WatcherService watcherService = new WatcherService();
    	watcherService.watchFiles();
   	
    	
    }
    
}
