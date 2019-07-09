package AutoHeal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.util.StringUtils;

/**
 * @author info2890
 *
 */

public class XpathCapture {
	
	

    private static int getElementPosition(final Element element) {
        final Elements chlds = element.parent().children();
        
        int count = 0;
        for (Element e : chlds) {
            if (e.nodeName().equals(element.nodeName())) {
                count++;
            }
        }
        int position = 0;
        for (Element e : chlds) {
            if (e.nodeName().equals(element.nodeName())) {
                position++;
                if (element.siblingIndex() == e.siblingIndex() && count > 1) {
                    break;
                }
            }
        }
        return count > 1 ? position : 0;
    }

    public void xpath(final String html, WebDriver driver, boolean b) {
    	try {
    	Document doc;
    	BufferedWriter out1 = new BufferedWriter(new FileWriter("C:\\newWorkSpace\\iObjectRepo\\Xpath Repository\\Test1.txt"));
    	if(b) {
    		GeneratePageSourceToWord.storeSourceCodeToWord(html);
    		doc = Jsoup.parse(html);
    	}else {
    		final String html1 = PageSourceFromWord.fetchContent();
    		doc = Jsoup.parse(html1);
    	}
    	
        Elements elements = doc.body().getAllElements();
      //System.out.println(elements.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\newWorkSpace\\iObjectRepo\\Xpath Repository\\Test.txt"));
        out.write(elements.toString());
        out.close();
        int count = 0;
        ArrayList<String> xpathList = new ArrayList<String>();
        ArrayList<String> xpathNameList = new ArrayList<String>();
        Map<String, String> xpathDetails = new HashMap<String, String>();
        //System.out.println(elements);
        
        for (Element element : elements) {
            StringBuilder path;           
            
            if(element.id().equalsIgnoreCase("TabBar:DesktopTab")) {
            System.out.println(element.text());	
            
            }
            /*if(element.id().equalsIgnoreCase("tab-services")) {
            	System.out.println();
            	element.getElementsByAttribute("span");
            	element.getElementsByTag("span");
            	element.childNodes();
            	element.child(0).text();
            }*/
            
            
            
            if (StringUtils.hasLength(element.id()) && XpathCapture.makeDynamicId(element.id().toString()).equals("true")) {
            	
                path = new StringBuilder("//*[@id=\"" + element.id() + "\"]");
            } else {
                path = new StringBuilder(element.nodeName());
                int position = getElementPosition(element);
                if (position > 0) {
                    path.append("[").append(position).append("]");
                }
                for (Element el : element.parents()) {
                    if (StringUtils.hasLength(el.id()) && XpathCapture.makeDynamicId(el.id().toString()).equals("true")) {
                        path.insert(0, "//*[@id=\"" + el.id() + "\"]/");
                    } else {
                        position = getElementPosition(el);
                        if (position > 0) {
                            path.insert(0, el.nodeName() + "[" + position + "]/");
                        } else {
                            path.insert(0, el.nodeName() + '/');
                        }
                    }
                }
                int index = path.lastIndexOf("*");
                if (index > 3) {
                    path.delete(0, index - 2);
                }
            }
            System.out.println("Name : "+element.text()+" Path : "+path + " : " + element.attributes());
            //out1 = new BufferedWriter(new FileWriter("C:\\newWorkSpace\\iObjectRepo\\Xpath Repository\\Test1.txt"));
           // out1.write(path + " = " + element.attributes());
            out1.append("Name : "+element.text()+" Path : "+path + " : " + element.attributes()+"\r\n");
            
           
            
            String attributesNames="";
            String tempPath=""; 
            String elementname="";
            String elementOwnText = "";
            String elementGeneratedName = "";
            String nodeType = "";
            String node = "";
            String nodeName = element.nodeName();
            StringBuffer elementNameBuffer = new StringBuffer("");
            Properties prop = new Properties();
            String propFileName = "nodeDetails.properties";
            InputStream inputStreamValue = getClass().getClassLoader().getResourceAsStream(propFileName);
			
            
            int elAttributesCount = element.attributes().size();
            if(!(elAttributesCount==0)) {
            	List<Attribute> attributeList = element.attributes().asList();
            	
            	String[] xpath = path.toString().split("/");
            	if(xpath[xpath.length-1].contains("[")) {
            		node = xpath[xpath.length-1].substring(0, xpath[xpath.length-1].indexOf("["));
            	}else {
            		node = xpath[xpath.length-1];
            	}
            	
            	for(int i=0; i<attributeList.size(); i++) {
            		String[] attributeDetails = attributeList.get(i).toString().split("=");
            		switch(attributeDetails[0]) {
            		case "href":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			
            			break;
            		case "name":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			break;
            		case "alt":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			break;
            		case "title":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			break;
            		case "placeholder":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			break;
            		case "src":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			break;
            		case "value":
            			elementNameBuffer.append(formattingOwnText(element, attributeDetails, prop, inputStreamValue, node));
            			break;
            		default:
            			tempPath = "";
            			break;
            		}
            	}
            	
            }
            
            String[] elementNameArr = elementNameBuffer.toString().split("_");
            if(elementNameArr[0].equalsIgnoreCase("*")) {
            	prop.load(inputStreamValue);
            	String eleNodeName = prop.getProperty(nodeName);
            	if(eleNodeName==null) {
            		elementNameBuffer.replace(0, 1, nodeName);
            	}
            	else elementNameBuffer.replace(0, 1, eleNodeName);
            }
            
            if(!elementNameBuffer.toString().equals("") && elementNameArr.length>1) {
            	if(elementNameBuffer.substring(elementNameBuffer.length() - 1).equalsIgnoreCase("_")) {
            		tempPath = elementNameBuffer.toString().substring(0,(elementNameBuffer.length() - 1));
            	}
            	else tempPath=elementNameBuffer.toString();            	
            	//System.out.println(path.toString() +" elementname="+tempPath);
            	xpathList.add(path.toString());
            	
            	//String tmpPath1 = tempPath;
            	xpathNameList.add(tempPath);
            }
            
            elementNameBuffer = new StringBuffer("");
            
//            System.out.println(xpathList.size());
            
//            ExcelGenerate.createInnerExcel(path.toString(), element);
        }
        
        xpathDetails = makeUniqueName(xpathList, xpathNameList);
        ExcelGenerate.excelWrite(xpathDetails, driver.getTitle());
        out1.close();
//        ExcelGenerate.createInnerExcel(xpathAttributes, xpathOwnText);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    private String formattingOwnText(Element element, String[] attributeDetails, Properties prop, InputStream inputStreamValue, String node) throws Exception{
    	
    	String elementOwnText = "";
    	String nodeType = "";
    	String elementGeneratedName = "";
    	String elementname = "";
    	
    	if(!element.ownText().isEmpty()) {
			elementOwnText = element.ownText().substring(0).replaceAll(" ", "_");
    		//elementname = element.ownText().substring(0).replaceAll(" ", "_");
	}
    	
    	
    	if(attributeDetails[0].equals("src")) {
    		String[] srcLink = attributeDetails[1].split("/");
    		if(srcLink[srcLink.length-1].contains(".")) {
    			elementname = srcLink[srcLink.length-1].substring(0, srcLink[srcLink.length-1].indexOf("."));
    		}else {
    			elementname = srcLink[srcLink.length-1];
    		}
    	}else {
    		elementname = attributeDetails[1].substring(1, attributeDetails[1].length()-1).replaceAll(" ", "_").replaceAll("/", "_");
    	}
    	
    	
    	
    	
    	prop.load(inputStreamValue);
		nodeType = prop.getProperty(node);
		//System.out.println(nodeType);
		if(nodeType==null) {
			nodeType = node;
//			System.out.println("true");
		}
		
		
		elementGeneratedName = nodeType+"_"+elementOwnText+"_"+elementname;
		elementGeneratedName = elementGeneratedName.replace(".", "_");
		//elementGeneratedName = nodeType+"_"+elementname;
		
    	
    	return elementGeneratedName;
    }
    
    
private Map<String, String> makeUniqueName(ArrayList<String> xpathList, ArrayList<String> xpathNameList) {
    	
    	Map<String, String> xpathDetails = new HashMap<String, String>();
    	Map<String, Integer> tempDetails = new HashMap<String, Integer>();
    	for(int i=0; i<xpathNameList.size(); i++) {
    		//int count = 1;
    		int count = 0;
    		if(!xpathDetails.containsKey(xpathNameList.get(i))) {
    			//xpathDetails.put(xpathNameList.get(i)+"_1", xpathList.get(i));
    			xpathDetails.put(xpathNameList.get(i), xpathList.get(i));
    			tempDetails.put(xpathNameList.get(i), count);
    		}else {
    			int lastNumber = tempDetails.get(xpathNameList.get(i));
    			xpathDetails.put(xpathNameList.get(i)+"_"+(lastNumber+2), xpathList.get(i));
    			tempDetails.replace(xpathNameList.get(i), lastNumber+1);
    			//count = 1;    			
    			count = 0;
    		}
    	}    	
    	
    	for(Map.Entry<String, String> pair : xpathDetails.entrySet()) {
    		System.out.println(pair.getValue()+"=>>>>>"+pair.getKey());
    	}    	
    	
    	
    	return xpathDetails;
    }

public static String makeDynamicId(String id) {	
	
	if(id.equals("postsbc")) {
		System.out.println();
	}
	String status = "false";
	if(!id.equals("")) {
		char[] chars = id.toCharArray();
		   /* StringBuilder sb = new StringBuilder();
		    for(char c : chars){
		       if((Character.isDigit(c))){
		          count=count+1;
		       }
		       else sb.append(c);
		    }*/
		    
		    if(Character.isDigit(chars[chars.length-1])) {
		    	status="false";
		    } else status = "true";
	}
    
       
	return status;
	
}
    
    
    
}