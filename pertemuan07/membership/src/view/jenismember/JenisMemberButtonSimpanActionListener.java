package view.jenismember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();

        if (nama == null || nama.trim().isEmpty()) {
            // Tambahkan logika untuk menangani input kosong jika diperlukan
            System.err.println("Nama tidak boleh kosong.");
            return;
        }

        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);

        this.jenisMemberFrame.addJenisMember(jenisMember);

        this.jenisMemberDao.insert(jenisMember);
    }
}
