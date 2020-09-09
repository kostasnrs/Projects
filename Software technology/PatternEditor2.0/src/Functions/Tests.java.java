package Functions;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import Functions.CollectFields;
import Functions.SaveToFile;
import Functions.ReadFromFile;
class Tests {
	
	private class c extends ReadFromFile{

		public c(File file) {
			super();
		}

		
	}
	
	private class q extends SaveToFile{

		public q(File file) {
			super();
		}

		
	}


	@Test
	void test() {
		File file = new File("foo.txt","");
		ReadFromFile c = new ReadFromFile();
		c.readFromTxt(file);
		
		assertEquals(file.getPath(),"foo.txt");
		
		file = new File("foo.tex","");
		c = new ReadFromFile();
		c.readFromLatex(file);
		
		assertEquals(file.getPath(),"foo.tex");
		
		file = new File("foo.txt","");
		SaveToFile q = new SaveToFile();
		q.saveAsTxt(file);
		
		assertEquals(file.getPath(),"foo.txt");
		
		file = new File("foo.tex","");
		q = new SaveToFile();
		q.saveAsTxt(file);
		
		assertEquals(file.getPath(),"foo.tex");
	}

}
