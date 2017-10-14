
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomePage extends HttpServlet {

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
         String username=CheckValidation.username1;
         
                  int amountdb=0;
       try{
          Class.forName("oracle.jdbc.driver.OracleDriver");
                  String url="jdbc:oracle:thin:@localhost:1521:xe";
                  String id="system";
                  String pass="system";
                 
                  
                  Connection con=DriverManager.getConnection(url,id,pass);
                //  Statement s=con.createStatement();
                  //s.executeQuery("select*from bankmodule");
                  
     PreparedStatement ps=con.prepareStatement("select*from bankmodule where username=?");
                    ps.setString(1,username);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next()){
                 username=rs.getString(2);
                 amountdb=rs.getInt(7);
                         }
                  
                  
       }
        
        
        catch(Exception e){
            out.println("Enter any data"+e);
        }
        out.println("<html>\n" +
"<head>\n" +
"    \n" +
"    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\n" +
"    <style>\n" +
"        th {\n" +
"    background-color: #5bc0de;\n" +
"    color: white;\n" +
"            text-align: center;\n" +
"            margin: auto;\n" +
"            \n" +
"                \n" +
"            }\n" +
"        tr{\n" +
"            text-align: center;\n" +
"        }\n" +
"        \n" +
"    </style>\n" +
"    </head>\n" +
"<body>\n" +
"    <h1><center>Java Bank Of India</center></h1><br><br>\n" +
"    <h2 align=\"center\"><font color=\"red\">Home Page </font></h2><br>\n" +
"    <form action=\"validate\"  method=\"Post\">\n" +
"    <table align=\"center\">\n" +
"        <tr>\n" +
"        <th>Username</th>\n" +
"            <th>Account Status</th>\n" +
"            <th>Amount</th>\n" +
"        </tr>\n" +
"        <tr>\n" +
"       <td>"+username+"</td>\n" +
"            <td><font color=\"green>\">Active</font></td>\n" +
"            <td>"+amountdb+"</td>\n" +
"        </tr>\n" +
"        \n" +
"        <tr>\n" +
"        <td colspan=\"3\"><input type=\"text\"name=\"amountact\"placeholder=\"Enter The Amount To Perform Following Operations\"size=\"100%\"> </td>\n" +
"        \n" +
"        </tr>\n" +
"        <tr>\n" +
"        <td> <input type=\"submit\"name=\"amountdeposit\"value=\"Deposit\" class=\"btn btn-info\"></td>\n" +
"        <td> <input type=\"submit\"name=\"amountwithdrawl\"value=\"Withdrawl\"class=\"btn btn-info\"> </td>  \n" +
"         <td><input type=\"submit\" name=\"backbtn\"value=\"Signout\"class=\"btn btn-info\"></td>      "+
"</tr>\n" +
        
"  </table>    \n" +
"    </form>\n" +
"   </body>\n" +
"</html>");
   
    }
}




