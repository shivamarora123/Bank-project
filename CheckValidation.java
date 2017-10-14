
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

public class CheckValidation extends HttpServlet {
static String username1;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
                  response.setContentType("text/html");
                  PrintWriter out=response.getWriter();
                    username1=request.getParameter("userN");
                  String password=request.getParameter("userP");
                  String validateid = null;
                  String validatepas=null;
                try{ 
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  String url="jdbc:oracle:thin:@localhost:1521:xe";
                  String id="system";
                  String pass="system";
                  Connection con=DriverManager.getConnection(url,id,pass);
                //  Statement s=con.createStatement();
                  
                  if(request.getParameter("loginsubmit")!=null){
                  //////
                  if(username1.equals("Admin") && password.equals("Admin")){
                      RequestDispatcher rd=request.getRequestDispatcher("AdminHome");
                              rd.forward(request, response);
                  }
                  
                  
                  
                  /////
                   PreparedStatement ps=con.prepareStatement("select*from bankmodule where username=?");
                    ps.setString(1,username1);
                      ResultSet rs=ps.executeQuery();
                       while (rs.next()){
                       validateid=rs.getString(2);
                       
                      validatepas=rs.getString(3);
                       }
                      if(username1.equals(validateid)&& password.equals(validatepas)){
                     RequestDispatcher rd=request.getRequestDispatcher("HomePage");
			rd.forward(request,response);
                 }
                 else{
                          
                  //   out.println("Wrong username and Password");
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.forward(request,response);
                 }
                      
                      
                      
                  }
                  else if(request.getParameter("signupsubmit")!=null){
                      response.sendRedirect("signup-form.html");
                    
                      
                  }
                }
                catch(Exception e){out.println(e);}
                }
}

    