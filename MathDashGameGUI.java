import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase que implementa la interfaz gráfica de usuario (GUI) para el juego Math
 * Dash.
 * Desarrollado con Java Swing.
 * 
 * @author Ricardo Martínez (Rickcm22) & Team B04
 */
public class MathDashGameGUI extends JFrame {

    private final OperationGenerator operationGenerator;
    private final JLabel lblOperacion;
    private final JButton btnOpcionA;
    private final JButton btnOpcionB;
    private final JButton btnOpcionC;
    private int[] opciones;
    private boolean juegoIniciado = false;

    /**
     * Constructor de la clase MathDashGameGUI.
     * Configura la ventana principal y los componentes de la interfaz.
     */
    public MathDashGameGUI() {
        operationGenerator = new OperationGenerator();

        // Configuración de la ventana principal
        setTitle("Math Dash - Sistema de Operaciones Matemáticas");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(new BorderLayout());

        // Panel superior para mostrar la operación
        JPanel panelOperacion = new JPanel();
        panelOperacion.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        lblOperacion = new JLabel("Haz clic en 'Nueva Operación' para comenzar");
        lblOperacion.setFont(new Font("Arial", Font.BOLD, 15));
        panelOperacion.add(lblOperacion);
        add(panelOperacion, BorderLayout.NORTH);

        // Panel central para las opciones de respuesta
        JPanel panelOpciones = new JPanel();
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelOpciones.setLayout(new GridLayout(1, 3, 12, 12));

        btnOpcionA = new JButton("A");
        btnOpcionB = new JButton("B");
        btnOpcionC = new JButton("C");

        estilizarBotonOpcion(btnOpcionA);
        estilizarBotonOpcion(btnOpcionB);
        estilizarBotonOpcion(btnOpcionC);

        panelOpciones.add(btnOpcionA);
        panelOpciones.add(btnOpcionB);
        panelOpciones.add(btnOpcionC);
        add(panelOpciones, BorderLayout.CENTER);

        // Panel inferior para el botón de nueva operación
        JPanel panelInferior = new JPanel();
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        JButton btnNuevaOperacion = new JButton("Nueva Operación");
        btnNuevaOperacion.setFont(new Font("Arial", Font.BOLD, 13));
        panelInferior.add(btnNuevaOperacion);
        add(panelInferior, BorderLayout.SOUTH);

        // Listeners para las opciones
        btnOpcionA.addActionListener(new OpcionListener(0));
        btnOpcionB.addActionListener(new OpcionListener(1));
        btnOpcionC.addActionListener(new OpcionListener(2));

        // Listener para el botón de nueva operación
        btnNuevaOperacion.addActionListener(e -> generarNuevaOperacion());
    }

    /**
     * Aplica un estilo uniforme y limpio a los botones de opciones.
     */
    private void estilizarBotonOpcion(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
    }

    /**
     * Genera una nueva operación y actualiza la interfaz gráfica.
     */
    private void generarNuevaOperacion() {
        String operacion = operationGenerator.generarOperacion();
        opciones = operationGenerator.generarRespuestas();

        lblOperacion.setText(operacion);
        btnOpcionA.setText("A) " + opciones[0]);
        btnOpcionB.setText("B) " + opciones[1]);
        btnOpcionC.setText("C) " + opciones[2]);
        juegoIniciado = true;
    }

    /**
     * Clase interna para manejar los eventos de selección de las opciones.
     */
    private class OpcionListener implements ActionListener {
        private final int indice;

        public OpcionListener(int indice) {
            this.indice = indice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!juegoIniciado || opciones == null) {
                JOptionPane.showMessageDialog(
                        MathDashGameGUI.this,
                        "Por favor, haz clic en 'Nueva Operación' primero.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int resultadoCorrecto = operationGenerator.getResultado();
            if (opciones[indice] == resultadoCorrecto) {
                JOptionPane.showMessageDialog(
                        MathDashGameGUI.this,
                        "¡Correcto!",
                        "Resultado",
                        JOptionPane.INFORMATION_MESSAGE);
                generarNuevaOperacion(); // Genera automáticamente la siguiente tras acertar
            } else {
                JOptionPane.showMessageDialog(
                        MathDashGameGUI.this,
                        "Incorrecto. La respuesta correcta era: " + resultadoCorrecto,
                        "Resultado",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método principal para ejecutar la interfaz gráfica del juego.
     * 
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MathDashGameGUI gui = new MathDashGameGUI();
            gui.setVisible(true);
        });
    }
}