package filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class FileSearch extends RecursiveTask<List<String>> {

	private String path;
	private String value;
	private ArrayList<String> filenames;
	
	public FileSearch(String path, String value) {
		this.path = path;
		this.value = value;
		this.filenames = new ArrayList<String>();
	}
	
	@Override
	protected List<String> compute() {
		File folder = new File(path);
		File[] listOfFilesAndFolders = folder.listFiles();
		
		List<FileSearch> tasks = new ArrayList<FileSearch>();
		for(File fileOrFolder : listOfFilesAndFolders) {
			if(fileOrFolder.isDirectory()) {
				FileSearch task = new FileSearch(fileOrFolder.getAbsolutePath(), value);
				task.fork();
				tasks.add(task);
				continue;
			}
			if(fileOrFolder.getName().contains(value)) {
				filenames.add(path + "/" + fileOrFolder.getName());
			}
		}
		
		joinResults(tasks);
		return filenames; 
	}

	private void joinResults(List<FileSearch> tasks) {
		for(FileSearch fileSearchTasks : tasks) {
			filenames.addAll( fileSearchTasks.join() );
		}
	}

}
