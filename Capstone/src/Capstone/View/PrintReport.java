package Capstone.View;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Capstone.Database.Connect;



public class PrintReport {
	
	static Connect c;
  //private static String FILE = "table.pdf";
  private static Font HeaderName = new Font(Font.FontFamily.TIMES_ROMAN, 25,
      Font.BOLD);
  private static Font SubHeader = new Font(Font.FontFamily.TIMES_ROMAN, 13,
      Font.NORMAL);
  private static Font dateissue = new Font(Font.FontFamily.TIMES_ROMAN, 8,
      Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.BOLD);
  
  private static Font TotalAmount = new Font(Font.FontFamily.TIMES_ROMAN, 15,
	      Font.NORMAL, BaseColor.RED);

  public void Caller(Document doc, String filename) {
    try {
      
      PdfWriter.getInstance(doc, new FileOutputStream(filename));
      doc.open();
      addMetaData(doc);
      addTitlePage(doc);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // iText allows to add metadata to the PDF which can be viewed in your Adobe
  // Reader
  // under File -> Properties
  private static void addMetaData(Document document) {
    document.addTitle("Sales Report");
    document.addAuthor("CapstoneTeam");
    document.addCreator("CapstoneTeam");
  }

  public static void addTitlePage(Document document)
      throws DocumentException {
	  Paragraph preface = new Paragraph();
	    
	    // We add one empty line
	    addEmptyLine(preface, 1);
	    // Lets write a big header
	    
	    //------------------------------------------------------------------------------------ GET HEADER FROM DATABASE
	    Chunk c1;
	    Chunk c2;
	    Chunk c3;
	    
	    c1 = new Chunk("Restauant Reservation System \n", HeaderName);   
	     c2 = new Chunk("64 Calderon St., Calumpang, \n Marikina City \n (02)246-90-69 \n", SubHeader);
	     c3 = new Chunk("Date Issued: " + new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss a").format(new Date()),dateissue);
	    
	    try {
			c = new Connect();
			String query = "SELECT * FROM utilities WHERE utility_id = '1';";
			c.pst = c.con.prepareStatement(query);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				 c1 = new Chunk(c.rs.getString("restaurant_name")+" \n", HeaderName);   
			     c2 = new Chunk(c.rs.getString("restaurant_address")+" \n "+c.rs.getString("restaurant_contact_no")+" \n", SubHeader);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    Phrase head = new Phrase();
	    head.add(c1);
	    head.add(c2);
	    head.add(c3);
	    
	    Paragraph p1 = new Paragraph();
	    p1.add(head);
	    p1.setAlignment(Element.ALIGN_CENTER);
	    
	    addEmptyLine(preface, 1);  
	    addEmptyLine(preface, 1);
	    document.add(p1);
	    document.add(preface);
	    // Start a new page            
	    //document.newPage();
  }

private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
} 