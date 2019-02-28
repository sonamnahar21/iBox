package CS5850_iBox.ibox.drive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.spi.FileSystemProvider;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class WatcherServiceTest {
	
	private WatcherService watcherService  ;
	private String path = "./tokens/StoredCredential";
	private String wrongPath = "./tokens/StoredCredential1";
	
	@Before
	public void initialization() throws IOException, GeneralSecurityException  {
	//	watcherService = new WatcherService();
	}

}
