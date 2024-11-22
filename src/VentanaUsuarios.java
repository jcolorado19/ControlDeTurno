import javax.swing.*;
import java.awt.*;

public class VentanaUsuarios extends JFrame implements TurnoObserver {
    private ListaTurnos lista;
    private JTextArea turnosArea;

    public VentanaUsuarios(ListaTurnos lista) {
        this.lista = lista;
        this.lista.addObserver(this);

        setTitle("Solicitud de Turnos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 200);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con botón
        JPanel topPanel = new JPanel();
        JButton solicitarButton = new JButton("Solicitar Turno");
        solicitarButton.addActionListener(e -> solicitarTurno());
        topPanel.add(solicitarButton);

        // Panel central con área de texto
        turnosArea = new JTextArea(10, 30);
        turnosArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(turnosArea);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        actualizarTurnos();
    }

    private void solicitarTurno() {
        String documento = JOptionPane.showInputDialog(this, "Ingrese su documento de identificación (solo números, 6-10 dígitos):");
        if (documento != null && !documento.trim().isEmpty()) {
            if (!documento.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                        "Error: El documento debe contener solo números",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (documento.length() < 6 || documento.length() > 10) {
                JOptionPane.showMessageDialog(this,
                        "Error: El documento debe tener entre 6 y 10 dígitos",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String resultado = lista.generarTurno(documento);
            JOptionPane.showMessageDialog(this, resultado);
        }
    }

    public void actualizarTurnos() {
        turnosArea.setText("=============== TABLERO DE TURNOS ==============\n");
        turnosArea.append(lista.getTurnosAsignados());
    }

    @Override
    public void onTurnoUpdated() {
        actualizarTurnos();
    }
}