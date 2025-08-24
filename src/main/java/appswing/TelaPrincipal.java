package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

public class TelaPrincipal {
    private JFrame frame;
    private JMenu mnTime;
    private JMenu mnJogo;
    private JMenu mnConsulta;
    private JLabel label;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal window = new TelaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPrincipal() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("RolaBola");
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        label = new JLabel("");
        label.setFont(new Font("Tahoma", Font.PLAIN, 26));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        label.setText("");

        try {
            ImageIcon imagem = new ImageIcon(getClass().getResource("/imagens/ImagemRolaBola.png"));
            Image imagemRedimensionada = imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagemRedimensionada));
        } catch (Exception e) {
            label.setText("Erro ao carregar a imagem: " + e.getMessage());
        }

        frame.getContentPane().add(label);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnTime = new JMenu("Times");
        mnTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaTime(frame);
            }
        });
        menuBar.add(mnTime);

        mnJogo = new JMenu("Jogos");
        mnJogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaJogo(frame);
            }
        });
        menuBar.add(mnJogo);

        mnConsulta = new JMenu("Consulta");
        mnConsulta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new TelaConsulta(frame);
            }
        });
        menuBar.add(mnConsulta);
    }
}