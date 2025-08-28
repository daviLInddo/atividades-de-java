import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Main extends JFrame {
            private final JTextField campoValor = new JTextField();
            private final JComboBox<String> comboDirecao = new JComboBox<>(
                    new String[]{"Celsius → Fahrenheit", "Fahrenheit → Celsius"}
            );
            private final JLabel lblResultado = new JLabel("Digite um valor e escolha a conversão.");

            private final DecimalFormat fmt;

            public Main () {
                super("Main");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(400, 200);
                setLocationRelativeTo(null);

                DecimalFormatSymbols sym = new DecimalFormatSymbols(new Locale("pt", "BR"));
                fmt = new DecimalFormat("#,##0.00", sym);

                JButton btnConverter = new JButton("Converter");

                setLayout(new BorderLayout(10, 10));
                JPanel painel = new JPanel(new GridLayout(3, 2, 5, 5));
                painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                painel.add(new JLabel("Valor:"));
                painel.add(campoValor);

                painel.add(new JLabel("Conversão:"));
                painel.add(comboDirecao);

                painel.add(new JLabel(""));
                painel.add(btnConverter);

                add(painel, BorderLayout.NORTH);
                add(lblResultado, BorderLayout.CENTER);

                // Eventos
                btnConverter.addActionListener(e -> converter());
                comboDirecao.addActionListener(e -> converter());
                campoValor.getDocument().addDocumentListener(new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) { converter(); }
                    public void removeUpdate(DocumentEvent e) { converter(); }
                    public void changedUpdate(DocumentEvent e) { converter(); }
                });
            }

            private void converter() {
                String texto = campoValor.getText().trim();
                if (texto.isEmpty()) {
                    lblResultado.setText("Digite um valor e escolha a conversão.");
                    return;
                }
                texto = texto.replace(',', '.');
                try {
                    double valor = Double.parseDouble(texto);
                    boolean c2f = comboDirecao.getSelectedIndex() == 0;
                    double calculo = c2f ? (valor * 9.0 / 5.0 + 32.0) : ((valor - 32.0) * 5.0 / 9.0);
                    String unidade = c2f ? "°F" : "°C";
                    lblResultado.setText("Resultado: " + fmt.format(calculo) + " " + unidade);
                } catch (NumberFormatException ex) {
                    lblResultado.setText("Valor inválido!");
                }
            }

            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new Main ().setVisible(true));
            }
        }













