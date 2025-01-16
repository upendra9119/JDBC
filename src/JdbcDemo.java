import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/*1.import
  2.load and register
  3.create connection
  4.create statement
  5.execute statement
  6.process the results
  7.close the connection
 */
public class JdbcDemo {

    public static Connection getConnection() {
//     The Properties class is part of the java.util package.
//     It is a specialized subclass of Hashtable that is designed to handle key-value pairs where both the key and value are Strings.
//     It is commonly used for configuration and property files in Java applications.
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/config.properties")) {
            // Load properties from the file
            props.load(fis);

            // Get credentials from properties
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.user");
            String password = props.getProperty("db.pass");

            // Establish the connection
            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
        }
        return null; // Return null if the connection fails
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

    public static void Read(){
        String sql ="select * from products";
        try(Connection connection = getConnection();
            PreparedStatement psmt = connection.prepareStatement(sql);
        ResultSet resultset=psmt.executeQuery()){
            System.out.println("The list of products are : ");
            while(resultset.next()){
                System.out.print(resultset.getInt("productid"));
                System.out.print(" ");
                System.out.println(resultset.getString("productname"));


            }

        }
        catch(SQLException e){
            e.printStackTrace();

        }
    }

    }

