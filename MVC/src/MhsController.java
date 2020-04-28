import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MhsController {
    MhsModel mhsModel;  //untuk import class Model
    MhsView mhsView;    //untuk import class View
    MhsDAO mhsDAO;      //untuk import class DAO
    static String nim;

    public MhsController(MhsModel mhsModel, MhsView mhsView, MhsDAO mhsDAO) {
        this.mhsModel   = mhsModel;
        this.mhsView    = mhsView;
        this.mhsDAO     = mhsDAO;

        if (mhsDAO.getJmlData() != 0) { //di sini untuk megecek apakah ada data atau tidak
            String dataMahasiswa[][] = mhsDAO.readMahasiswa();
            mhsView.tabel.setModel((new JTable(dataMahasiswa, mhsView.namaKolom)).getModel());
        } else {
            //kalau tidak ada maka akan muncul pop up
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        mhsView.simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim      = mhsView.getNim();
                String nama     = mhsView.getNama();
                String alamat   = mhsView.getAlamat();

                if (nim.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap Isi Semua Field");
                } else {
                    //memasukkan data ke dalam Model
                    mhsModel.setMhsModel(nim, nama, alamat);
                    //menjalankan perintah insert di DAO
                    mhsDAO.Insert(mhsModel);

                    String dataMahasiswa[][] = mhsDAO.readMahasiswa();
                    mhsView.tabel.setModel((new JTable(dataMahasiswa, mhsView.namaKolom)).getModel());
                }
            }
        });

        mhsView.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelMouseClicked(e);
                mhsView.delete.setEnabled(true);
            }
        });

        mhsView.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //memasukkan data ke dalam Model
                mhsModel.setNim(nim);
                //menjalankan perintah insert di DAO
                mhsDAO.Delete(mhsModel);

                String dataMahasiswa[][] = mhsDAO.readMahasiswa();
                mhsView.tabel.setModel((new JTable(dataMahasiswa, mhsView.namaKolom)).getModel());
            }
        });
    }

    private void tabelMouseClicked(MouseEvent e) {
        int baris = mhsView.tabel.rowAtPoint(e.getPoint());
        nim = mhsView.tabel.getValueAt(baris,0).toString();
    }
}
