import java.io.IOException;
import java.io.PrintWriter;
//import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class validate extends HttpServlet {
static String username=CheckValidation.username1;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            int currentamount = 0;
            username=CheckValidation.username1;
            
            String amountaction=request.getParameter("amountact");
            int finalamount;
            String amountentered=request.getParameter("amount");
                        String url="jdbc:oracle:thin:@localhost:1521:xe";
                        String id="system";
                        String pass="system";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection(url,id,pass);
            Statement s=con.createStatement();
            
            
            if(request.getParameter("amountdeposit")!=null){
                 PreparedStatement ps1=con.prepareStatement("select*from bankmodule where username=?");
                    ps1.setString(1,username);
                ResultSet rs=ps1.executeQuery();
                while(rs.next()){
                   currentamount=rs.getInt(7); 
                    username=rs.getString(2);
                 }
                finalamount = (currentamount)+Integer.parseInt(amountaction);
                
               
              
               PreparedStatement ps=con.prepareStatement("update bankmodule set amount=? where username=?");
               ps.setInt(1,finalamount);
               ps.setString(2,username);
               
               int qw=ps.executeUpdate();
                 if(qw>0){
                   out.println("Deposit Successful");
                   out.println("Your New Balance is:"+finalamount);
                   RequestDispatcher rd=request.getRequestDispatcher("HomePage");
                           rd.include(request,response);
                 }
                
            }
             if(request.getParameter("amountwithdrawl")!=null){
                 PreparedStatement ps2=con.prepareStatement("select*from bankmodule where username=?");
                    ps2.setString(1,username);
                   ResultSet rs=ps2.executeQuery();
                    while(rs.next()){
                   currentamount=rs.getInt(7); 
                    username=rs.getString(2);
                 }
                finalamount = (currentamount)-Integer.parseInt(amountaction); 
                if(finalamount<1000){
                    out.println("Error!You cannot have Balance Below Rs.1000");
                    RequestDispatcher rd=request.getRequestDispatcher("HomePage");
                           rd.include(request,response);
                }
                else{
                PreparedStatement ps=con.prepareStatement("update bankmodule set amount=? where username=?");
               ps.setInt(1,finalamount);
               ps.setString(2,username);
               
               int qw=ps.executeUpdate();
                 if(qw>0){
                   out.println("Withdrawl Successful");
                   out.println("Your New Balance is:"+finalamount);
                   RequestDispatcher rd=request.getRequestDispatcher("HomePage");
                           rd.include(request,response);
                 }
                }
             }
             if(request.getParameter("backbtn")!=null){
                 RequestDispatcher rd=request.getRequestDispatcher("index.html");
                 rd.forward(request, response);
             }
           
        } catch (Exception e) {
              out.println(e);
        }
    }
}