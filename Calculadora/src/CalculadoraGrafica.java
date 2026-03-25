import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraGrafica extends JFrame {

    JTextField num1, num2;
    JButton btnSumar, btnRestar, btnMultiplicar, btnDividir;
    JLabel resultado;
    Font fuente = new Font("Arial", 1, 60);

    public CalculadoraGrafica() {
        setTitle("Casio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        num1 = new JTextField(10);
        num1.setFont(fuente);
        num2 = new JTextField(10);
        num2.setFont(fuente);

        btnSumar = new JButton("+");
        btnRestar = new JButton("-");
        btnMultiplicar = new JButton("x");
        btnDividir = new JButton("/");

        JPanel pOpciones = new JPanel();
        pOpciones.add(btnSumar);
        pOpciones.add(btnRestar);
        pOpciones.add(btnMultiplicar);
        pOpciones.add(btnDividir);

        resultado = new JLabel("", JLabel.CENTER);
        resultado.setFont(fuente);

        add(num1);
        add(num2);
        add(pOpciones);
        add(resultado);

        // --- Uso de Lambdas ---
        btnSumar.addActionListener(e -> calcular("+"));
        btnRestar.addActionListener(e -> calcular("-"));
        btnMultiplicar.addActionListener(e -> calcular("*"));
        btnDividir.addActionListener(e -> calcular("/"));
    }

    public void calcular(String operador) {
        try {
            double n1 = Double.parseDouble(num1.getText());
            double n2 = Double.parseDouble(num2.getText());
            double res = 0;

            switch (operador) {
                case "+": res = n1 + n2; break;
                case "-": res = n1 - n2; break;
                case "*": res = n1 * n2; break;
                case "/":
                    if (n2 != 0) res = n1 / n2;
                    else {
                        resultado.setText("Error");
                        return;
                    }
                    break;
            }
            // Formateo simple para no mostrar .0 si es entero
            resultado.setText(String.valueOf(res));

        } catch (NumberFormatException ex) {
            resultado.setText("Datos invalidos");
        }
    }

    public static void main(String[] args) {
        new CalculadoraGrafica().setVisible(true);
    }
}