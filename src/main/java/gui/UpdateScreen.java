package gui;

import dao.AlunoDAO;
import model.Aluno;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateScreen {
    private JPanel contentPane;
    private JTextField nomeField;
    private JLabel cpfField;
    private JTextField pesoField;
    private JFormattedTextField dataField;
    private JTextField alturaField;
    private JButton atualizarButton;
    private Aluno aluno;
    private AlunoDAO alunoDAO = new AlunoDAO();
    private JFrame frame;

    public UpdateScreen(Aluno aluno) {
        this.aluno = aluno;
        nomeField.setText(this.aluno.getNome());
        cpfField.setText(this.aluno.getCpf());
        dataField.setText(this.aluno.getDataNascimento().toString());
        pesoField.setText(String.valueOf(this.aluno.getPeso()));
        alturaField.setText(String.valueOf(this.aluno.getAltura()));
        atualizarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              aluno.setNome(nomeField.getText());
              aluno.setDataNascimento(dataField.getText());
              aluno.setPeso(Float.parseFloat(pesoField.getText()));
              aluno.setAltura(Float.parseFloat(alturaField.getText()));
              alunoDAO.atualizarAluno(aluno);
              JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
              frame.dispose();
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame();
        Image image = Toolkit.getDefaultToolkit().getImage("resources/hawk.png");
        frame.setIconImage(image);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        dataField = new JFormattedTextField();
        try {
            MaskFormatter formatterData = new MaskFormatter("##/##/####");
            dataField.setFormatterFactory(new DefaultFormatterFactory(formatterData));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }
}
