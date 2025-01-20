public class Main {

    public static void main(String[] args) {
       JdbcDemo.Create(6,"milk");
        JdbcDemo.Read();
        JdbcDemo.Update(3,"sunscreen");
        JdbcDemo.Delete(3);
    }
}