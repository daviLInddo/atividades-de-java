//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.regex.Pattern;

public class Main extends JFrame {
    private final JTextField tfNome = new JTextField();
    private final JTextField tfIdade = new JTextField();
    private final JTextField tfEmail = new JTextField();

    private final Border defaultBorder = new JTextField().getBorder();
    private final Border erroBorder = BorderFactory.createLineBorder(Color.RED, 2);

    private static final Pattern EMAIL_OK = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public Main() {
        super("Formulário de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2, 10, 10));
        setBorder();

        add(new JLabel("Nome:"));
        add(tfNome);

        add(new JLabel("Idade:"));
        add(tfIdade);

        add(new JLabel("Email:"));
        add(tfEmail);

        JButton btnEnviar = new JButton("Enviar");
        JButton btnLimpar = new JButton("Limpar");

        add(btnEnviar);
        add(btnLimpar);

        btnEnviar.addActionListener(e -> enviar());
        btnLimpar.addActionListener(e -> limpar());
    }

    private void setBorder() {
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void enviar() {
        tfNome.setBorder(defaultBorder);
        tfIdade.setBorder(defaultBorder);
        tfEmail.setBorder(defaultBorder);

        String nome = tfNome.getText().trim();
        String idadeTxt = tfIdade.getText().trim();
        String email = tfEmail.getText().trim();

        StringBuilder erros = new StringBuilder();

        if (nome.length() < 2) {
            erros.append("- Nome inválido (mínimo 2 letras)\n");
            tfNome.setBorder(erroBorder);
        }

        int idade = -1;
        try {
            idade = Integer.parseInt(idadeTxt);
            if (idade < 0 || idade > 120) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            erros.append("- Idade inválida (0-120)\n");
            tfIdade.setBorder(erroBorder);
        }

        if (!EMAIL_OK.matcher(email).matches()) {
            erros.append("- Email inválido\n");
            tfEmail.setBorder(erroBorder);
        }

        if (erros.length() > 0) {
            JOptionPane.showMessageDialog(this, erros.toString(), "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Cadastro realizado!\nNome: " + nome + "\nIdade: " + idade + "\nEmail: " + email,
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limpar();
        }
    }

    private void limpar() {
        tfNome.setText("");
        tfIdade.setText("");
        tfEmail.setText("");
        tfNome.setBorder(defaultBorder);
        tfIdade.setBorder(defaultBorder);
        tfEmail.setBorder(defaultBorder);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
