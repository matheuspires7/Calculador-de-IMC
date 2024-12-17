package gui;

import dao.AlunoDAO;
import model.Aluno;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class Screen {
    private JPanel contentPane;
    private JTextField nomeField;
    private JFormattedTextField cpfField;
    private JFormattedTextField dataField;
    private JTextField pesoField;
    private JTextField alturaField;
    private JButton salvarButton;
    private JButton consultarButton;
    private JButton deletarButton;
    private JButton gerarIMCButton;
    private JButton editarButton;
    private AlunoDAO alunoDAO = new AlunoDAO();

    public Screen() {
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno aluno = new Aluno(cpfField.getText(), nomeField.getText(), dataField.getText(), Float.parseFloat(pesoField.getText()), Float.parseFloat(alturaField.getText()));
                alunoDAO.inserirAluno(aluno);
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog(contentPane, "Digite o CPF");
                alunoDAO.consultarAluno(cpf);
                Aluno aluno = alunoDAO.consultarAluno(cpf);
                JOptionPane.showMessageDialog(contentPane, aluno.toString());
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog(contentPane, "Digite o CPF");
                alunoDAO.excluirAluno(cpf);
            }
        });

        gerarIMCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog(contentPane, "Digite o CPF");
                alunoDAO.consultarAluno(cpf);
                Aluno aluno = alunoDAO.consultarAluno(cpf);
                File file = new File("src/Alunos_IMC/" + aluno.getNome() + ".txt");
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                    List<String> text = aluno.calcularIMC();

                    for (String s : text) {
                        bw.write(s);
                        bw.newLine();
                    }
                    bw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = JOptionPane.showInputDialog(contentPane, "Digite o CPF");
                alunoDAO.consultarAluno(cpf);
                Aluno aluno = alunoDAO.consultarAluno(cpf);
                UpdateScreen updateScreen = new UpdateScreen(aluno);
                updateScreen.createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("finge que tem algo");
        Image image = Toolkit.getDefaultToolkit().getImage("resources/hawk.png");
        frame.setIconImage(image);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.createAndShowGUI();
    }

    private void createUIComponents() {
        cpfField = new JFormattedTextField();
        dataField = new JFormattedTextField();
        try {
            MaskFormatter formatterCPF = new MaskFormatter("###.###.###-##");
            cpfField.setFormatterFactory(new DefaultFormatterFactory(formatterCPF));
            MaskFormatter formatterData = new MaskFormatter("##/##/####");
            dataField.setFormatterFactory(new DefaultFormatterFactory(formatterData));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

}
