import javax.swing.*;
import java.sql.*;

public class MhsDAO {
    private int jmlData;
    private Connection koneksi;
    private Statement statement;

    public MhsDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url  = "jdbc:mysql://localhost/mahasiswa?serverTimezone=UTC";
            koneksi     = DriverManager.getConnection(url, "root", "");
            statement   = koneksi.createStatement();
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Not Found : " + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception : " + ex);
        }
    }

    public void Insert(MhsModel Model) {
        try {
            String query = "INSERT INTO data_mhs VALUES ('" + Model.getNim() + "', '" + Model.getNama() + "', '" + Model.getAlamat() + "')";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data disimpan");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void Update(MhsModel Model) {
        try {
            String query = "UPDATE data_mhs SET NIM = '" + Model.getNim() + "', Nama = '" + Model.getNama() + "', Alamat = '" + Model.getAlamat() + "' WHERE NIM = '" + Model.getNim() + "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil diupdate");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void Delete(MhsModel Model) {
        try {
            String query = "DELETE FROM data_mhs WHERE NIM = '" + Model.getNim() + "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil dihapus");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    //untuk mengambil data dari database dan mengatur ke dalam tabel
    public String[][] readMahasiswa() {
        try {
            int jmlData = 0; //menampung jumlah data
            //baris sejumlah data, kolom nya ada 3
            String data[][] = new String[getJmlData()][3];
            //pengambilan data dalam java dari database
            String query    = "SELECT * FROM data_mhs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut ke data selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("NIM");
                data[jmlData][1] = resultSet.getString("Nama");
                data[jmlData][2] = resultSet.getString("Alamat");
                jmlData++; //barisnya selalu berpindah
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return null;
        }
    }

    public int getJmlData() {
        int jmlData = 0;
        try {
            //pengambilan data ke dalam java dari database
            String query = "SELECT * FROM data_mhs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }
            return jmlData;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
}