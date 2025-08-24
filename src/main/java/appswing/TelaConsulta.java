package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Jogo;
import modelo.Time;
import requisito.Fachada;

public class TelaConsulta extends JDialog {
    private JDialog frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton button;
    private JLabel label;
    private JLabel label_4;
    private JComboBox<String> comboBox;

    public TelaConsulta(JFrame owner) {
        super(owner);
        initialize();
    }

    private void initialize() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setResizable(false);
        frame.setTitle("Consultas JPQL");
        frame.setBounds(100, 100, 729, 385);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Fachada.inicializar();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.finalizar();
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 43, 674, 148);
        frame.getContentPane().add(scrollPane);

        table = new JTable() {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };

        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.LIGHT_GRAY);
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
        label.setBounds(21, 321, 688, 14);
        frame.getContentPane().add(label);

        label_4 = new JLabel("resultados:");
        label_4.setBounds(21, 190, 431, 14);
        frame.getContentPane().add(label_4);

        button = new JButton("Consultar");
        button.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = comboBox.getSelectedIndex();
                if(index<0) {
                    label_4.setText("consulta nao selecionada");
                } else {
                    label_4.setText("");
                    switch(index) {
                        case 0:
                            String data = JOptionPane.showInputDialog("digite a data (dd-MM-yyyy HH:mm)");
                            List<Jogo> resultado1 = Fachada.jogosPorData(data);
                            listagemJogo(resultado1);
                            break;
                        case 1:
                            String nomeTime = JOptionPane.showInputDialog("digite o nome do time");
                            List<Jogo> resultado2 = Fachada.jogosTimeCasa(nomeTime);
                            listagemJogo(resultado2);
                            break;
                        case 2:
                            String n = JOptionPane.showInputDialog("digite N");
                            int numero = Integer.parseInt(n);
                            List<Time> resultado3 = Fachada.timesComMaisDeNJogos(numero);
                            listagemTime(resultado3);
                            break;
                    }
                }
            }
        });
        button.setBounds(606, 10, 89, 23);
        frame.getContentPane().add(button);

        comboBox = new JComboBox<String>();
        comboBox.setToolTipText("selecione a consulta");
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"jogos em uma data", "jogos de um time em casa", "times com N jogos"}));
        comboBox.setBounds(21, 10, 513, 22);
        frame.getContentPane().add(comboBox);

        frame.setVisible(true);
    }

    public void listagemJogo(List<Jogo> lista) {
        try {
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);
            model.addColumn("ID");
            model.addColumn("Data");
            model.addColumn("Local");
            model.addColumn("Time Casa");
            model.addColumn("Gols");
            model.addColumn("Time Visita");
            model.addColumn("Gols");

            for (Jogo j : lista) {
                model.addRow(new Object[]{j.getId(), j.getDataHora(), j.getLocal(),
                        j.getTimeCasa().getNome(), j.getGols1(),
                        j.getTimeVisita().getNome(), j.getGols2()});
            }
            label_4.setText("resultados: " + lista.size() + " jogos encontrados.");
        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }

    public void listagemTime(List<Time> lista) {
        try {
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);
            model.addColumn("Nome");
            model.addColumn("Pontuacao");
            model.addColumn("Total de Jogos");

            for (Time t : lista) {
                model.addRow(new Object[]{t.getNome(), t.getPontuacao(), t.getTodosOsJogos().size()});
            }
            label_4.setText("resultados: " + lista.size() + " times encontrados.");
        } catch (Exception erro) {
            label.setText(erro.getMessage());
        }
    }
}