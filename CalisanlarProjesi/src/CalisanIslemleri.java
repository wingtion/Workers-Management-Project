import java.sql.*;
import java.util.ArrayList;

public class CalisanIslemleri {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public CalisanIslemleri(){
        //driverimiz mevcut mu kontrol ediyoruz sonradan sıkıntı çıkmaması adina
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            System.out.println("Driver could not FOUND.");
        }

        try {
            connection = DriverManager.getConnection(Database.url,Database.username,Database.password);
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            System.out.println("Connection failed..");
        }
    }
    public boolean girisYap(String kullanici_adi , String parola){

        try {
            String sql = "Select * from adminler WHERE username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,kullanici_adi);
            preparedStatement.setString(2,parola);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Calisan> calisanlariGetir() {
        ArrayList<Calisan> cikti = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM calisanlar";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String ad = resultSet.getString("ad");
                String soyad= resultSet.getString("soyad");
                String dept = resultSet.getString("departman");
                String maas = resultSet.getString("maas");

                Calisan calisan = new Calisan(id,ad,soyad,dept,maas);
                cikti.add(calisan);
            }
            return cikti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CalisanIslemleri calisanIslemleri = new CalisanIslemleri();
    }
    public void calisanEkle(String ad, String soyad, String departman, String maas) {
        String sql = "INSERT INTO calisanlar(ad,soyad,departman,maas) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ad);
            preparedStatement.setString(2,soyad);
            preparedStatement.setString(3,departman);
            preparedStatement.setString(4,maas);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void calisanGuncelle(int id, String ad, String soyad, String departman, String maas) {
        String sql = "UPDATE calisanlar SET ad=? , soyad=? , departman=? , maas=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ad);
            preparedStatement.setString(2,soyad);
            preparedStatement.setString(3,departman);
            preparedStatement.setString(4,maas);
            preparedStatement.setInt(5,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void calisanSil(int id) {
        String sql = "DELETE FROM calisanlar WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
