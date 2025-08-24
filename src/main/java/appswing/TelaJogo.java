package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Jogo;
import modelo.Time;
import requisito.Fachada;

public class TelaJogo extends JDialog {
    private JDialog frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton button;
    private JButton button_1;
    private JLabel label;
    private JTextField textFieldData;
    private JTextField textFieldLocal;
    private JLabel label_1;
    private JLabel label_2;
    private JTextField textFieldGolsCasa;
    private JLabel label_3;
    private JTextField textFieldGolsVisita;
    private JButton button_2;
    private JLabel label_4;
    private JComboBox<String> comboTimeCasa;
    private JComboBox<String> comboTimeVisita;
    private JLabel label_5;
    private JLabel label_6;
    private JButton button_3;

    public TelaJogo(JFrame owner) {
        super(owner);
        initialize();
    }

    private void initialize() {
        frame = new JDialog();
        frame.setResizable(false);
        frame.setModal(true);
        frame.setTitle("Gerenciar Jogos");
        frame.setBounds(100, 100, 800, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent arg0) {
                Fachada.inicializar();
                preencherCombos();
                listagem();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.finalizar();
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 44, 735, 152);
        frame.getContentPane().add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() >= 0) {
                    label.setText("");
                }
            }
        });
        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        label = new JLabel("");
        label.setForeground(Color.BLACK);
        label.setBounds(26, 303, 735, 14);
        frame.getContentPane().add(label);

        label_4 = new JLabel("selecione um jogo para editar/apagar");
        label_4.setBounds(26, 194, 735, 14);
        frame.getContentPane().add(label_4);

        label_1 = new JLabel("Data:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_1.setBounds(26, 217, 71, 14);
        frame.getContentPane().add(label_1);

        textFieldData = new JTextField();
        textFieldData.setColumns(10);
        textFieldData.setBounds(78, 215, 86, 20);
        frame.getContentPane().add(textFieldData);

        label_2 = new JLabel("Local:");
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_2.setBounds(172, 217, 71, 14);
        frame.getContentPane().add(label_2);

        textFieldLocal = new JTextField();
        textFieldLocal.setColumns(10);
        textFieldLocal.setBounds(224, 215, 150, 20);
        frame.getContentPane().add(textFieldLocal);

        label_5 = new JLabel("Time Casa:");
        label_5.setHorizontalAlignment(SwingConstants.LEFT);
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_5.setBounds(26, 242, 71, 14);
        frame.getContentPane().add(label_5);

        comboTimeCasa = new JComboBox<>();
        comboTimeCasa.setBounds(100, 240, 150, 20);
        frame.getContentPane().add(comboTimeCasa);

        label_6 = new JLabel("Time Visita:");
        label_6.setHorizontalAlignment(SwingConstants.LEFT);
        label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_6.setBounds(260, 242, 71, 14);
        frame.getContentPane().add(label_6);

        comboTimeVisita = new JComboBox<>();
        comboTimeVisita.setBounds(334, 240, 150, 20);
        frame.getContentPane().add(comboTimeVisita);

        label_3 = new JLabel("Gols Casa:");
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_3.setBounds(26, 267, 71, 14);
        frame.getContentPane().add(label_3);

        textFieldGolsCasa = new JTextField();
        textFieldGolsCasa.setColumns(10);
        textFieldGolsCasa.setBounds(100, 265, 50, 20);
        frame.getContentPane().add(textFieldGolsCasa);

        label_3 = new JLabel("Gols Visita:");
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_3.setBounds(172, 267, 71, 14);
        frame.getContentPane().add(label_3);

        textFieldGolsVisita = new JTextField();
        textFieldGolsVisita.setColumns(10);
        textFieldGolsVisita.setBounds(246, 265, 50, 20);
        frame.getContentPane().add(textFieldGolsVisita);

        button_2 = new JButton("Criar");
        button_2.setToolTipText("adicionar novo jogo");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textFieldData.getText().isEmpty() || textFieldLocal.getText().isEmpty() || textFieldGolsCasa.getText().isEmpty() || textFieldGolsVisita.getText().isEmpty()) {
                    label.setText("Todos os campos devem ser preenchidos.");
                    return;
                }

                try {
                    Fachada.cadastrarJogo(textFieldData.getText(), textFieldLocal.getText(),
                            (String)comboTimeCasa.getSelectedItem(),
                            (String)comboTimeVisita.getSelectedItem(),
                            Integer.parseInt(textFieldGolsCasa.getText()),
                            Integer.parseInt(textFieldGolsVisita.getText()));
                    label.setText("jogo criado");
                    listagem();
                }
                catch(Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_2.setBounds(26, 320, 77, 23);
        frame.getContentPane().add(button_2);

        button_1 = new JButton("Apagar");
        button_1.setToolTipText("remover o jogo");
        button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(table.getSelectedRow() >= 0) {
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                        Object[] options = { "Confirmar", "Cancelar" };
                        int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão do jogo "+id, "Alerta",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                        if(escolha == 0) {
                            Fachada.excluirJogo(id);
                            label.setText("exclusão realizada");
                            listagem();
                        }
                    } else {
                        label.setText("selecione uma linha para apagar");
                    }
                }
                catch(Exception erro) {
                    label.setText(erro.getMessage());
                }
            }
        });
        button_1.setBounds(113, 320, 95, 23);
        frame.getContentPane().add(button_1);

        button_3 = new JButton("Trocar Times");
        button_3.setToolTipText("trocar times do jogo selecionado");
        button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(table.getSelectedRow() >= 0) {
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                        Fachada.alterarJogoTrocarTimes(id);
                        label.setText("troca de times realizada");
                        listagem();
                    } else {
                        label.setText("selecione uma linha para alterar");
                    }
                } catch(Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button_3.setBounds(220, 320, 110, 23);
        frame.getContentPane().add(button_3);

        frame.setVisible(true);
    }

    private void preencherCombos() {
        comboTimeCasa.removeAllItems();
        comboTimeVisita.removeAllItems();
        List<Time> times = Fachada.listarTimes();
        for(Time t : times) {
            comboTimeCasa.addItem(t.getNome());
            comboTimeVisita.addItem(t.getNome());
        }
    }

    public void listagem () {
        try{
            List<Jogo> lista = Fachada.listarJogos();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Data");
            model.addColumn("Local");
            model.addColumn("Time Casa");
            model.addColumn("Gols");
            model.addColumn("Time Visita");
            model.addColumn("Gols");

            for(Jogo j : lista) {
                model.addRow(new Object[]{j.getId(), j.getDataHora(), j.getLocal(),
                        j.getTimeCasa().getNome(), j.getGols1(),
                        j.getTimeVisita().getNome(), j.getGols2()});
            }
            table.setModel(model);
            label_4.setText("resultados: "+lista.size()+ " jogos");
        }
        catch(Exception erro){
            label.setText(erro.getMessage());
        }
    }
}