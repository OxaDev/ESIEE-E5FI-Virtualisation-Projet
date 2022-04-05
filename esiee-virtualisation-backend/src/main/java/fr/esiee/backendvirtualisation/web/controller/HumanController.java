package fr.esiee.backendvirtualisation.web.controller;

import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import fr.esiee.backendvirtualisation.model.*;

@RestController

public class HumanController {
	
	String urlDB = "jdbc:mysql://127.0.0.1:60247/db_project";
	String myDriver = "org.gjt.mm.mysql.Driver";
    String idDB = "admin2";
    String MdPDB = "admin2";
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/AllHumans")
   public ArrayList<Human> listeProduits() {
		ArrayList<Human> listReturn = new ArrayList<Human>();
		try
	    {
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
	      String query = "SELECT * FROM HUMANS; ";

	      Statement st = conn.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        int tid = rs.getInt("id");
	        String tfirstName = rs.getString("FirstName");
	        String tlastName = rs.getString("LastName");
	        String temail = rs.getString("Email");
	        
	        // print the results
	        System.out.format("%s, %s, %s, %s\n", tid, tfirstName, tlastName, temail);
	        listReturn.add( new Human(tid, tlastName, tfirstName, temail) );
	        
	      }
	      st.close();
	      return listReturn;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return listReturn;
   }
	
	@GetMapping("/InitDB")
	public void initDB() {
		try
	    {    
		  Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
	      
	      String query = "CREATE TABLE IF NOT EXISTS HUMANS ("
	      		+ "id INT AUTO_INCREMENT PRIMARY KEY,"
	      		+ "FirstName VARCHAR(100),"
	      		+ "LastName VARCHAR(100),"
	      		+ "Email VARCHAR(100)"
	      		+ ")";

	      Statement st = conn.createStatement();
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/FillDB")
	public void fillDB() {
		try
	    {	    
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
		  Statement st = conn.createStatement();
		  
	      String query = "INSERT INTO HUMANS (FirstName, LastName, Email)"
	      		+ "VALUES "
	      		+ "('John', 'Doe', 'John.Doe@gmail.com'),"
	      		+ "('Alice', 'Herbert', 'Alice.Herbert@gmail.com'),"
	      		+ "('Oxa', 'Rin', 'Oxa.Rin@gmail.com');";
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/ClearDB")
	public void clearDB() {
		try
	    {
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
	      
	      String query = "TRUNCATE TABLE HUMANS;";

	      Statement st = conn.createStatement();
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/DeleteDB")
	public void deleteDB() {
		try
	    {
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
	      
	      String query = "DROP TABLE HUMANS;";

	      Statement st = conn.createStatement();
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/Human/{id}")
	public Human afficherUnHumain(@PathVariable int id) {
		System.out.println("fzkengrfb");
		Human temp_Human = new Human(0,"error", "error","JeTeDisQueCestUneErreurDUKON@gmail.com");
		try
	    {
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
	      String query = "SELECT * FROM HUMANS "
	      		+ "WHERE HUMANS.id="+id+";";

	      Statement st = conn.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        int tid = rs.getInt("id");
	        String tfirstName = rs.getString("FirstName");
	        String tlastName = rs.getString("LastName");
	        String temail = rs.getString("Email");
	        
	        // print the results
	        System.out.format("%s, %s, %s, %s\n", tid, tfirstName, tlastName, temail);
	        temp_Human = new Human(tid, tlastName, tfirstName, temail);
	      }
	      st.close();
	      return temp_Human;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return temp_Human;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/Human/{id}")
	public void supprimerUnHumain(@PathVariable int id) {
		try
	    {
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
	      String query = "DELETE FROM HUMANS "
	      		+ "WHERE HUMANS.id="+id+";";

	      Statement st = conn.createStatement();
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/AddHuman")
	public void ajouterHumain(@RequestBody Human pHuman) {
		try
	    {	    
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
		  Statement st = conn.createStatement();
		  
	      String query = "INSERT INTO HUMANS (FirstName, LastName, Email)"
	      		+ "VALUES "
	      		+ "('"+pHuman.getFirstName()+"', '"+pHuman.getLastName()+"', '"+pHuman.getEmail()+"')";
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/ModifyHuman")
	public void modifierHumain(@RequestBody Human pHuman) {
		try
	    {	    
		  Class.forName(myDriver);
		  Connection conn = DriverManager.getConnection(urlDB, idDB, MdPDB);
		  Statement st = conn.createStatement();
		  
	      String query = "UPDATE HUMANS "
	      		+ "SET "
	      		+ "firstName= '"+pHuman.getFirstName()+"',"
	      		+ "lastName= '"+pHuman.getLastName()+"',"
	      		+ "email= '"+pHuman.getEmail()+"'"
	      		+ "WHERE id="+pHuman.getID()+";";
	      st.executeUpdate(query);
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
}
