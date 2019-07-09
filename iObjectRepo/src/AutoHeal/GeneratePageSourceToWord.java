package AutoHeal;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class GeneratePageSourceToWord {
	public static void storeSourceCodeToWord(String html) {
		try {
			XWPFDocument document = new XWPFDocument();
			FileOutputStream out = new FileOutputStream(new File("D:\\automation tool developement\\autoSpy\\src\\pageSource\\abc.docx"));
			XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
			run.setText(html);
			document.write(out);
			out.close();
			
		}catch(Exception e) {
			e.getMessage();
		}
		
	}
}
