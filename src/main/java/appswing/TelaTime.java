package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import modelo.Time;
import requisito.Fachada;

public class TelaTime extends JDialog {
    private JDialog frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton button_3;
    private JLabel label;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JButton button_1;
    private JButton button_2;
    private JButton button_4;
    private JLabel label_8;
    private JPanel panel;
    private JLabel label_1;
    private JButton button_5;
    private JButton button_6;
    private JTextField textField_1;
    private BufferedImage buffer;

    public TelaTime(JFrame owner) {
        super(owner);
        initialize();
    }

    private void initialize() {
        frame = new JDialog();
        frame.setResizable(false);
        frame.setModal(true);
        frame.setTitle("Gerenciar Times");
        frame.setBounds(100, 100, 813, 438);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent arg0) {
                Fachada.inicializar();
                listagem();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.finalizar();
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 39, 751, 147);
        frame.getContentPane().add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    label.setText("");
                    if (table.getSelectedRow() >= 0) {
                        String nome = (String) table.getValueAt(table.getSelectedRow(), 0);
                        Time t = Fachada.localizarTime(nome);s
                        textField_1.setText(nome);

                        // carregar foto
                        if (t.getFoto() != null) {
                            InputStream in = new ByteArrayInputStream(t.getFoto());
                            buffer = ImageIO.read(in);
                            ImageIcon icon = new ImageIcon(
                                    buffer.getScaledInstance(buffer.getWidth(), buffer.getHeight(), Image.SCALE_DEFAULT));
                            icon.setImage(icon.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1));
                            label_1.setIcon(icon);
                        } else {
                            buffer = null;
                            label_1.setText("sem foto");
                            label_1.setIcon(null);
                        }
                    }
                } catch (Exception erro) {
                    label.setText(erro.getMessage());
                }
            }
        });

        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        label = new JLabel("");
        label.setForeground(Color.BLACK);
        label.setBounds(21, 374, 735, 14);
        frame.getContentPane().add(label);

        label_2 = new JLabel("selecione um time para editar");
        label_2.setBounds(21, 187, 394, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("nome:");
        label_3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        label_3.setBounds(21, 216, 62, 14);
        frame.getContentPane().add(label_3);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(
                new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Foto",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(555, 197, 102, 105);
        frame.getContentPane().add(panel);

        label_1 = new JLabel("sem foto");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setBounds(10, 21, 78, 73);
        panel.add(label_1);

        button_1 = new JButton("Criar");
        button_1.setToolTipText("cadastrar novo time");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField_1.getText().isEmpty())
                    label.setText("nome vazio");
                else
                    criarTime();
            }
        });
        button_1.setBounds(548, 327, 95, 23);
        frame.getContentPane().add(button_1);

        button_2 = new JButton("Atualizar");
        button_2.setToolTipText("atualizar time");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField_1.getText().isEmpty())
                    label.setText("nome vazio");
                else
                    atualizarTimeSelecionado();
            }
        });
        button_2.setBounds(284, 327, 95, 23);
        frame.getContentPane().add(button_2);

        button_3 = new JButton("Apagar");
        button_3.setToolTipText("apagar time");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField_1.getText().isEmpty())
                    label.setText("nome vazio");
                else
                    apagarTimeSelecionado();
            }
        });
        button_3.setBounds(415, 327, 95, 23);
        frame.getContentPane().add(button_3);

        button_4 = new JButton("Limpar");
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText("");
                button_6.doClick(); // limpa a foto
            }
        });
        button_4.setBounds(147, 327, 95, 23);
        frame.getContentPane().add(button_4);

        button_5 = new JButton("Buscar foto");
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = selecionarArquivoFoto();
                if (file == null)
                    return;

                try {
                    buffer = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(buffer.getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
                    label_1.setIcon(icon);
                    label.setText("Precisa atualizar/criar time para salvar a foto");
                } catch (IOException ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button_5.setBounds(664, 213, 108, 23);
        frame.getContentPane().add(button_5);

        button_6 = new JButton("Limpar foto");
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buffer = null;
                label_1.setIcon(null);
                label_1.setText("sem foto");
                label.setText("Precisa atualizar/criar time para salvar a foto");
            }
        });
        button_6.setBounds(667, 247, 105, 23);
        frame.getContentPane().add(button_6);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        textField_1.setColumns(10);
        textField_1.setBackground(Color.WHITE);
        textField_1.setBounds(93, 213, 253, 20);
        frame.getContentPane().add(textField_1);

        frame.setVisible(true);
    }

    public void listagem() {
        try {
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);

            model.addColumn("Nome");
            model.addColumn("Pontuacao");
            model.addColumn("Total de Jogos");

            List<Time> lista = Fachada.listarTimes();
            for (Time t : lista)
                model.addRow(new Object[] { t.getNome(), t.getPontuacao(), t.getTodosOsJogos().size() });

            label_2.setText("resultados: " + lista.size() + " times - selecione uma linha para editar");
        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }

    public void apagarTimeSelecionado() {
        try {
            label.setText("");
            String nome = textField_1.getText();

            Object[] options = { "Confirmar", "Cancelar" };
            int escolha = JOptionPane.showOptionDialog(null,
                    "Esta operação apagará o time " + nome, "Alerta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
            if (escolha == 0) {
                Fachada.excluirTime(nome);
                label.setText("time excluído");
                listagem();
            }
            else
                label.setText("exclusão cancelada");

        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }

    public void criarTime() {
        try {
            label.setText("");
            String nome = textField_1.getText().trim();

            byte[] bytesfoto = null;
            if (buffer != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(buffer, "png", baos);
                bytesfoto = baos.toByteArray();
                baos.close();
            }

            Fachada.cadastrarTime(nome, bytesfoto);

            label.setText("time criado");
            listagem();

        } catch (Exception ex) {
            label.setText(ex.getMessage());
        }
    }

    public void atualizarTimeSelecionado() {
        try {
            label.setText("");
            String nome = textField_1.getText();

            byte[] bytesfoto = null;
            if (buffer != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(buffer, "png", baos);
                bytesfoto = baos.toByteArray();
                baos.close();
            }

            Fachada.alterarFotoTime(nome, bytesfoto);

            label.setText("time atualizado");
            listagem();
        } catch (Exception ex) {
            label.setText(ex.getMessage());
        }
    }

    public File selecionarArquivoFoto() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "png", "jpg", "gif");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile();
    }
}