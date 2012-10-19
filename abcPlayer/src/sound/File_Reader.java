package sound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class File_Reader {
	private String filename;
	
	public File_Reader(String filename) {
		this.filename= filename;
	}
	
	public String readContent() throws IOException {
		StringBuilder result = new StringBuilder();
		FileReader fileReader;
		
		try {
			fileReader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found");
		}
		
		BufferedReader reader = new BufferedReader(fileReader);
		String line = "";
		
		while ((line = reader.readLine()) != null) {
			result.append(line+"\n");
		}
		
		fileReader.close();
		reader.close();
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		String file_name = "C:\\Users\\Deepak\\Documents\\git\\project1\\deepakn-yygu-arjunnar\\abcPlayer\\sample_abc\\invention.abc";
		File_Reader file_reader = new File_Reader(file_name);
		try {
			System.out.println(file_reader.readContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
