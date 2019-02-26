package CS5850_iBox.ibox.drive;
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.GeneralSecurityException;

import CS5850_iBox.ibox.GoogleDriveSync.GoogleDriveManager;

/**
 * Hello world!
 *
 */
public class WatcherService 
{
	GoogleDriveManager driveManager = new GoogleDriveManager();
	public void watchFiles() throws IOException, GeneralSecurityException
    {
    	
    	WatchService watcher = FileSystems.getDefault().newWatchService(); 
    	//Path dir = ;
    	// 
    	Path dir = Paths.get("./iboxLocalDrive/");

    	try {
    	    WatchKey key = dir.register(watcher,
    	                           ENTRY_CREATE,
    	                           ENTRY_DELETE,
    	                           ENTRY_MODIFY);
    	} catch (IOException x) {
    	    System.err.println(x);
    	}
    	
    	for (;;) {
    	    WatchKey key;
    	    try {
    	        key = watcher.take();
    	    } catch (InterruptedException x) {
    	        return;
    	    }

    	    for (WatchEvent<?> event: key.pollEvents()) {
    	        WatchEvent.Kind<?> kind = event.kind();    	        
    	        if (kind == OVERFLOW) {
    	            continue;
    	        }
    	        WatchEvent<Path> ev = (WatchEvent<Path>)event;
    	        Path filename = ev.context();

    	        System.out.println("*** Filename" +  filename);
    	        
    	        if(kind.toString().equals("ENTRY_CREATE")) {
    	        	driveManager.createFile(filename.toString());
    	        }
    	        else if(kind.toString().equals("ENTRY_DELETE")) {
    	        	driveManager.deleteFile(filename.toString());
    	        }
    	        else if(kind.toString().equals("ENTRY_MODIFY")) {
    	        	driveManager.modifyFile(filename.toString());
    	        }

    	    }
    	    boolean valid = key.reset();
    	    if (!valid) {
    	        break;
    	    }
    	}

    }
}
