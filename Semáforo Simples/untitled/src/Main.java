import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private final JPanel painelCor = new JPanel();
    private final JLabel lblEstado = new JLabel("Desligado", SwingConstants.CENTER);

    public Main() {
        super("Semáforo Simples");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        painelCor.setPreferredSize(new Dimension(200, 200));
        painelCor.setBackground(Color.GRAY);

        lblEstado.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnVermelho = new JButton("Vermelho");
        JButton btnAmarelo = new JButton("Amarelo");
        JButton btnVerde = new JButton("Verde");
        JButton btnDesligar = new JButton("Desligar");

        JPanel botoes = new JPanel();
        botoes.add(btnVermelho);
        botoes.add(btnAmarelo);
        botoes.add(btnVerde);
        botoes.add(btnDesligar);

        setLayout(new BorderLayout());
        add(painelCor, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.NORTH);
        add(botoes, BorderLayout.SOUTH);

        btnVermelho.addActionListener(e -> setSemaforo(Color.RED, "PARE"));
        btnAmarelo.addActionListener(e -> setSemaforo(Color.YELLOW, "ATENÇÃO"));
        btnVerde.addActionListener(e -> setSemaforo(Color.GREEN, "SIGA"));
        btnDesligar.addActionListener(e -> setSemaforo(Color.GRAY, "Desligado"));
    }

    private void setSemaforo(Color cor, String estado) {
        painelCor.setBackground(cor);
        lblEstado.setText(estado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
