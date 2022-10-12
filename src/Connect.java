
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Connect {
    
    private String kullanici_adi = "efee";
    private String parola = "efe245";
    
    private String db_ismi = "debris";
    
    private String host =  "localhost";
    
    private int port = 3306;
    
    private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public void preparedBring(int id) {
       
       String sorgu = "Select * From calisanlar where id > ? and ad like ? ";
       
       
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,"M%");
            
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ad  = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email =  rs.getString("email");
                
                System.out.println("Ad : " + ad + " Soyad : " + soyad + " Email : " + email);
                
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
       
        
        
    }
    public void Add() {
        
        
        
        try {
            statement = con.createStatement();
            String ad = "Semih";
            String soyad = "Aktaş";
            String email =  "semihaktas@gmail.com";
            // Insert Into calisanlar (ad,soyad,email) VALUES('Yusuf','Çetinkaya','mucahit@gmail.com')
            String sorgu = "Insert Into calisanlar (ad,soyad,email) VALUES(" + "'" + ad + "'," + "'" + soyad + "'," + "'" + email + "')";
            
            statement.executeUpdate(sorgu);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void Delete() {
        
        try {
            statement = con.createStatement();
            
            String sorgu = "Delete from calisanlar where id > 3";
            
            int deger = statement.executeUpdate(sorgu);
            System.out.println(deger + " kadar veri etkilendi...");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void Update() {
        
        
        try {
            statement = con.createStatement();
            
            String sorgu = "Update calisanlar Set email = 'example@gmail.com' where id > 3";
            
            statement.executeUpdate(sorgu);
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void Bring() {
        
        String sorgu = "Select * From calisanlar";
      
        try {
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(sorgu);
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String email = rs.getString("email");
                
                System.out.println("Id : " + id + "Ad: " + ad + "Soyad : " + soyad + " Email : " + email);
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public Connect() {
        
        // "jbdc:mysql://localhost:3306/demo" 
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
        
        
        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Bağlantı Başarılı...");
            
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        Connect baglanti = new Connect();
        baglanti.preparedBring(1);
        
       System.out.println("Silinmeden Önce........");
        baglanti.Bring();
        System.out.println("********************************************");
        System.out.println("Silindikten Sonra........");
        baglanti.Update();
        baglanti.Delete();
        
        baglanti.Bring();
        
        baglanti.Add();
        baglanti.Bring();

        
        
    }
    
    
}
