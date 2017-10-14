
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminHome extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setContentType("text/html");
           PrintWriter out=response.getWriter();
           out.println("Welcome Admin \n");
          String url="jdbc:oracle:thin:@localhost:1521:xe";
                  String id="system";
                  String pass="system";
                  
                    try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con=DriverManager.getConnection(url,id,pass);
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select*from bankmodule");
                    while(rs.next()){
                        out.println("");
                    }
                    
                    
                    }
                  
                  catch(Exception e){}
                  
    }

}
