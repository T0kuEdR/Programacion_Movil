import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gato01 extends JFrame implements ActionListener{
    JButton botones [] = new JButton[9];
    boolean turnoX;
    Font fuente  = new Font("Arial", 1, 50);
    JButton btnReiniciar;
    JPanel panelJuego, panelOpciones;

    public Gato01(){
        setSize(400,400);
        setTitle("Juego Gato");
        setDefaultCloseOperation(3);
        //setLayout(new GridLayout(2, 0));

        panelJuego = new JPanel(new GridLayout(3, 3));
        panelOpciones = new JPanel();
        for(int i=0; i<botones.length;i++){
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            panelJuego.add(botones[i]);
        }
        btnReiniciar = new JButton("Reiniciar Juego");

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<botones.length;i++){
                    botones[i].setText("");
                    botones[i].setEnabled(true);
                    turnoX = true;
                }
            }
        });

        panelOpciones.add(btnReiniciar);
        add(panelJuego, BorderLayout.CENTER);
        add(panelOpciones, BorderLayout.SOUTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonPulsado = (JButton) e.getSource();

        // Solo actuar si el botón está vacío
        if (botonPulsado.getText().equals("")) {
            String letra = turnoX ? "X" : "O";
            botonPulsado.setText(letra);
            botonPulsado.setEnabled(false);

            verificarGanador();

            turnoX = !turnoX;
        }
    }
    public void verificarGanador() {

        int[][] combinaciones = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] c : combinaciones) {
            String b1 = botones[c[0]].getText();
            String b2 = botones[c[1]].getText();
            String b3 = botones[c[2]].getText();

            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                javax.swing.JOptionPane.showMessageDialog(this, "¡Ha ganado: " + b1 + "!");
                bloquearTablero();
                return;
            }
        }
        verificarEmpate();
    }

    private void bloquearTablero() {
        for (JButton b : botones) b.setEnabled(false);
    }

    private void verificarEmpate() {
        boolean lleno = true;
        for (JButton b : botones) {
            if (b.getText().equals("")) lleno = false;
        }
        if (lleno) {
            javax.swing.JOptionPane.showMessageDialog(this, "¡Es un empate!");
        }
    }
}