import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;

public class MhsView extends JFrame {
    JLabel lnim, lnama, lalamat;
    JTextField tfnim, tfnama, tfalamat;
    JButton simpan, delete;
    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"NIM", "Nama", "Alamat"};
    Statement statement;

    public MhsView() {
        setTitle("Form Pegisian Mahasiswa");
        //atur tabel
        tableModel  = new DefaultTableModel(namaKolom, 0); //0 baris
        tabel       = new JTable(tableModel);
        scrollPane  = new JScrollPane(tabel);
        //atur label
        lnim    = new JLabel("NIM");
        lnama   = new JLabel("Nama");
        lalamat = new JLabel("Alamat");
        //atur textfield
        tfnim       = new JTextField("");
        tfnama      = new JTextField("");
        tfalamat    = new JTextField("");
        //atur tombol
        simpan  = new JButton("Cetak");
        delete  = new JButton("Delete");
        //atur tabel

        setLayout(null);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(tfnim);
        add(tfnama);
        add(tfalamat);
        add(simpan);
        add(delete);
        add(scrollPane);

        lnim.setBounds(75, 25, 30, 20);
        lnama.setBounds(75, 50, 50, 20);
        lalamat.setBounds(75, 75, 50, 20);
        tfnim.setBounds(150, 25, 300, 20);
        tfnama.setBounds(150, 50, 300, 20);
        tfalamat.setBounds(150, 75, 300, 100);
        simpan.setBounds(230, 190, 75, 20);
        delete.setBounds(310, 190, 75, 20);
        scrollPane.setBounds(75, 225, 480, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        delete.setEnabled(false);
        setSize(600, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getNim() {
        return tfnim.getText();
    }

    public String getNama() {
        return tfnama.getText();
    }

    public String getAlamat() {
        return tfalamat.getText();
    }
}
