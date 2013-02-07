package edu.research.p2ptestapp.structures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import android.util.Log;

public class Folder {

	public String name;
	public LinkedHashMap<String, Folder> subfolders;
	public List<CustomFile> files;
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public LinkedHashMap<String, Folder> getSubfolders() { return subfolders; }
	
	public void setSubfolders(LinkedHashMap<String, Folder> subfolders) { this.subfolders = subfolders; }
	
	public List<CustomFile> getFiles() { return files; }
	
	public void setFiles(ArrayList<CustomFile> files) { this.files = files; }
	
	public void addFile( CustomFile newFile ){
		files.add(newFile);
		Log.d("File added to " + name + ":", newFile.getName() );
	}
	
	public void addFileSet( ArrayList<CustomFile> newFiles){
		files.addAll(newFiles);
		for( CustomFile c : newFiles ){
			Log.d("Added:", c.toString() );
		}
	}
	
	public void addFolder( String folderName, Folder subfolder ){
		subfolders.put(folderName, subfolder);
		Log.d("Folder added to " + name + ":", folderName);
	}
	
	public void addFolderSet( ArrayList<Folder> newFolders ){
		for( Folder f : newFolders ){
			subfolders.put(f.getName(), f);
			Log.d(f.getName() + "was added to ", name);
		}
	}
}
