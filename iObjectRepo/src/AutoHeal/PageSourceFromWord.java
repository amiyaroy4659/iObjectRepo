package AutoHeal;

import java.io.FileInputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class PageSourceFromWord {
	public static String fetchContent() {
		try {
		XWPFDocument docx = new XWPFDocument(new FileInputStream("D:\\automation tool developement\\autoSpy\\src\\pageSource\\abc.docx"));
      //using XWPFWordExtractor Class
		XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
		
		final String html = extractor.getText();
		
		return html;
		
		}catch(Exception e) {
			e.getMessage();
		}      
	      
		
		return "@";
	}
}
