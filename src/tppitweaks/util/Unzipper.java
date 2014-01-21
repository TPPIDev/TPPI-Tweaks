package tppitweaks.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Provides two utility methods which can be used for zip deployment
 */
public class Unzipper 
{
    public static final int BUFFER_SIZE = 2048;
    
    /**
     * Deletes the given file or folder. If the folder contains things, it recursively deletes those too
     */
	public static void delete(File file) throws IOException
	{
		if(file.isDirectory())
		{
			if(file.list().length==0)
			{
				file.delete();
			}
			else
			{
				String files[] = file.list();
				for (String filename : files) 
				{
					File fileDelete = new File(file, filename);
					delete(fileDelete);
				}
				if(file.list().length==0)
				{
					file.delete();
				}
			}
		}
		else
		{
			file.delete();
		}
	}
	
	/**
	 * Takes an InputStream pointing to a zip file, and extracts it to the directory at
	 * destDirectory, creating it if necessary
	 */
    public static boolean unzipFiles(InputStream archive, String destDirectory)
    {
        try
        {
            
            File destinationDirectory = new File(destDirectory);
            destinationDirectory.mkdirs();

            //now start with unzip process
            BufferedOutputStream dest = null;

            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(archive));

            ZipEntry entry = null;

            while((entry = zis.getNextEntry()) != null)
            {
                String outputFilename = destDirectory + File.separator + entry.getName();

                createDirIfNeeded(destDirectory, entry);

                int count;

                byte data[] = new byte[BUFFER_SIZE];
                
                if (!entry.isDirectory())
                {
                	//write the file to the disk
                	File destFile = new File(outputFilename);
                	if (destFile.exists()) destFile.delete();
                	FileOutputStream fos = new FileOutputStream(outputFilename);
                	dest = new BufferedOutputStream(fos, BUFFER_SIZE);
                	
                	while((count = zis.read(data, 0, BUFFER_SIZE)) != -1)
                	{
                		dest.write(data, 0, count);
                	}

                	//close the output streams
                	dest.flush();
                	dest.close();
                }
            }

            //we are done with all the files
            //close the zip file
            zis.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void createDirIfNeeded(String destDirectory, ZipEntry entry)
    {
        String name = entry.getName();

        if(name.contains("/"))
        {

            int index = name.lastIndexOf("/");
            String dirSequence = name.substring(0, index);

            File newDirs = new File(destDirectory + File.separator + dirSequence);

            //create the directory
            newDirs.mkdirs();
        }
    }

}