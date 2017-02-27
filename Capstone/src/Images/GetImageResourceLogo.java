package Images;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;

public class GetImageResourceLogo {
	//GetImage Method
    public String GetImage(String photos, String destination, String addorupdate) throws IOException{
    	String currentDirectory;
		File file = new File("src\\img\\" + destination);
		currentDirectory = file.getAbsolutePath();
		if(!file.exists()){
			Files.createDirectories(Paths.get(currentDirectory));
		}
		if(addorupdate.equals("add"))
//		JOptionPane.showMessageDialog(null, "Image saved to " + currentDirectory);
		copyAndOverwriteFile(photos, currentDirectory);
		return file.toString();
    }//end of GetImage Method
    
    //copyAndOverwriteFile Method
	public void copyAndOverwriteFile(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        //overwrite the destination file if it exists, and copy
        // the file attributes, including the rwx permissions
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }//end of copyAndOverwriteFile Method
}
