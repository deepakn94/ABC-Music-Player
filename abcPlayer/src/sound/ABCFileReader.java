package sound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sound.Token.TokenType;

public class ABCFileReader {
	private String filename;
	
	public ABCFileReader(String filename) {
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
		//String file_name = "C:\\Users\\Deepak\\Documents\\git\\project1\\deepakn-yygu-arjunnar\\abcPlayer\\sample_abc\\fur_elise.abc";
	    String file_name = "sample_abc/piece1.abc";
		ABCFileReader file_reader = new ABCFileReader(file_name);
		try {
			String content = file_reader.readContent();
			//System.out.println(content);
			Lexer newLexer = new Lexer(content);
			Token currentToken = newLexer.next();
			while (currentToken.getTokenType()!=TokenType.END_OF_PIECE) {
				System.out.println(currentToken.getTokenName() + ":" + currentToken.getTokenType());
				currentToken = newLexer.next();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
