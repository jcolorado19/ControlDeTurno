import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListaTurnos lists = new ListaTurnos();
            VentanaUsuarios ventanaUsuarios = new VentanaUsuarios(lists);
            VentanaAsesores ventanaAsesores = new VentanaAsesores(lists);

            ventanaUsuarios.setVisible(true);
            ventanaAsesores.setVisible(true);
        });
    }
}