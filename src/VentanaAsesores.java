
import javax.swing.*;
import java.awt.*;

public class VentanaAsesores extends JFrame implements TurnoObserver {
    private ListaTurnos lista;
    private String currentAsesor;
    private JTextArea turnosArea;

    public VentanaAsesores(ListaTurnos lista) {
        this.lista = lista;
        this.lista.addObserver(this);

        setTitle("Panel de Asesores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600, 200);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout());
        JComboBox<String> asesorCombo = new JComboBox<>(new String[]{"Asesor 1", "Asesor 2"});
        JButton atenderButton = new JButton("Atender el siguiente turno");

        atenderButton.addActionListener(e -> {
            currentAsesor = (String) asesorCombo.getSelectedItem();
            atenderSiguiente();
        });

        topPanel.add(asesorCombo);
        topPanel.add(atenderButton);

        // Panel central
        turnosArea = new JTextArea(10, 30);
        turnosArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(turnosArea);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        actualizarTurnos();
    }

    private void atenderSiguiente() {
        if (currentAsesor != null) {
            String resultado = lista.atenderSiguienteTurno(currentAsesor);
            JOptionPane.showMessageDialog(this, resultado);
        }
    }

    private void actualizarTurnos() {
        turnosArea.setText("============== TURNOS PENDIENTES ===============\n\n");
        turnosArea.append(lista.getTurnosPendientes());
        turnosArea.append("\n============= TURNOS EN ATENCIÓN =============\n");
        turnosArea.append(lista.getTurnosAsignados());
    }

    @Override
    public void onTurnoUpdated() {
        actualizarTurnos();
    }
}