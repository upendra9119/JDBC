import java.sql.*;

/*1.import
  2.load and register
  3.create connection
  4.create statement
  5.execute statement
  6.process the results
  7.close the connection
 */
public class JdbcDemo {
    //public static void main(String[] args) {
    public static Connection getConnection() {

       try {
           String url = "jdbc:mysql://localhost:3306/upendra";
           String username = "root";
           String password = "root";

           return DriverManager.getConnection(url, username, password);
       }

            catch(SQLException e){
           System.out.println("check url or databasename or username, password" +e);
               e.printStackTrace();
                return null;


            }

    }
        public static void Create(int productid,String productname){
        String Sql ="insert into products values(?,?)";

        try (Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(Sql))
    {

            psmt.setString(1,productname);
            psmt.setInt(2,productid);
            int rowsAffected = psmt.executeUpdate();
            System.out.println(rowsAffected + " Rows got inserted");

        }catch (SQLException e){
            e.printStackTrace();
        }
        





        }

    }

