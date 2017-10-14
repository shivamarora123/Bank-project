import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Signup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                  response.setContentType("text/html");
                  PrintWriter out=response.getWriter();
                  String name=request.getParameter("signup-name");
                  String username=request.getParameter("signup-username");
                  String password=request.getParameter("signup-password");
                  String repassword=request.getParameter("signup-repassword");
                  String gender=request.getParameter("sex");
                  String account=request.getParameter("Account Type");
                  String url="jdbc:oracle:thin:@localhost:1521:xe";
                  String id="system";
                  String pass="system";
                  String amountinit="1000";
                  
           if(request.getParameter("signupbtn")!=null){
               
                  
                  try{
                
            if(password.equals(repassword)){
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con=DriverManager.getConnection(url,id,pass);
                  PreparedStatement ps=con.prepareStatement("insert into bankmodule values(?,?,?,?,?,?,?)");
                          ps.setString(1,name);
                          ps.setString(2,username);
                          ps.setString(3,password);
                          ps.setString(4,repassword);
                          
                          ps.setString(5,gender);
                          ps.setString(6,account);
                          ps.setString(7,amountinit);
                         // ps.executeQuery();
                          int z=ps.executeUpdate();
                          if(z>0){
                              out.println("Sign-up successful");
                          }
            }
            else {
                      out.println("Password Doesnot Match");
                      RequestDispatcher rd=request.getRequestDispatcher("signup-form.html");
                      rd.include(request, response);
                  }
            
            }
            catch(Exception e){
                out.println(e);
            }
           out.println("<a href='index.html'>click here to login</a>");
           }
           
           
           if(request.getParameter("backsignup")!=null){
               response.sendRedirect("index.html");
           }
           
                  
                        
                  
    
                  
}
}