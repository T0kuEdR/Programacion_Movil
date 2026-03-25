import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer; // Usaremos Timer para que sea más sencillo que hilos manuales

public class Fred extends JFrame {

    JButton[] casillas = new JButton[4];
    // Definimos los colores correspondientes a cada índice
    Color[] colores = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    int[] secuencia = new int[5];
    Random random = new Random();

    public Fred() {
        setTitle("Fred20");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(2, 2));

        for (int i = 0; i < 4; i++) {
            final int indice = i; // Necesario para usar dentro de la lambda
            casillas[i] = new JButton();
            casillas[i].setBackground(Color.LIGHT_GRAY);

            // Funcionalidad: Al presionar, se prende y se apaga
            casillas[i].addActionListener(e -> iluminarBoton(indice));

            add(casillas[i]);
        }

        generarSecuencia();
        // Esperamos un segundo antes de mostrar la secuencia inicial
        new Timer(1000, e -> mostrarSecuencia()).start();
    }

    // Método para prender un botón y apagarlo tras 300ms
    public void iluminarBoton(int indice) {
        casillas[indice].setBackground(colores[indice]);

        // Timer de Swing: ejecuta el código después de 300 milisegundos
        Timer temporizador = new Timer(300, e -> {
            casillas[indice].setBackground(Color.LIGHT_GRAY);
        });
        temporizador.setRepeats(false); // Para que solo ocurra una vez
        temporizador.start();
    }

    public void generarSecuencia() {
        for (int i = 0; i < secuencia.length; i++) {
            secuencia[i] = random.nextInt(4);
        }
    }

    public void mostrarSecuencia() {
        Thread hilo = new Thread(() -> {
            try {
                for (int indice : secuencia) {
                    casillas[indice].setBackground(colores[indice]);
                    Thread.sleep(800);
                    casillas[indice].setBackground(Color.LIGHT_GRAY);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        hilo.start();
    }

    public static void main(String[] args) {
        new Fred().setVisible(true);
    }
}
