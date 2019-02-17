package sample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class FabricaConexao {

    private static Connection[] pool;
    public static SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd");
    //public static DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/uuuu");

   private static String CONNECTION_STR =  "jdbc:mysql://localhost/Barber_Time?useTimezone=true&serverTimezone=UTC&useSSL=false";


    /*private static String CONNECTION_STR = "jdbc:mysql:"+
            "//infoprojetos.com.br:3132/tads17_efigenio" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=GMT";
*/

    private static int MAX_CONNECTIONS=6;

    private static  String login="root";
   // private static  String senha="JUninho123@";


  //  private static  String login="tads17_efigenio";
    private static  String senha="JUninho123@";


    static {
        pool = new Connection[MAX_CONNECTIONS];
    }

    public static Connection getConnection() throws SQLException {

           for(int i=0;i<pool.length;i++){
            if((pool[i]==null) || (pool[i].isClosed())){

                pool[i] = DriverManager.getConnection(CONNECTION_STR,login,senha);
                return pool[i];
            }
        }
        throw new SQLException("Muitas conexões abertas!!!");
    }

}
