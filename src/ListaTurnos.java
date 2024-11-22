import java.util.ArrayList;
import java.util.List;

public class ListaTurnos {
    private Turno primero;
    private int contador;
    private Turno turnoAsesor1;
    private Turno turnoAsesor2;
    private List<TurnoObserver> observers;

    public ListaTurnos() {
        primero = null;
        contador = 0;
        turnoAsesor1 = null;
        turnoAsesor2 = null;
        observers = new ArrayList<>();
    }

    public boolean documentoTieneTurnoPendiente(String documento) {
        Turno actual = primero;
        while (actual != null) {
            if (actual.getDocumento().equals(documento) && !actual.isCompletado()) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public String generarTurno(String documento) {
        if (!documento.matches("\\d+")) {
            return "Error: El documento debe contener solo números";
        }

        if (documentoTieneTurnoPendiente(documento)) {
            return "Error: El documento " + documento + " ya tiene un turno pendiente o asignado.";
        }

        contador++;
        String codigo = "T" + String.format("%03d", contador);
        Turno nuevo = new Turno(codigo, documento);

        if (primero == null) {
            primero = nuevo;
        } else {
            Turno actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        String result = "Turno generado, el número de turno es: " + codigo + " para el documento: " + documento;
        notifyObservers();
        return result;
    }

    public String getTurnosAsignados() {
        StringBuilder sb = new StringBuilder("\n");
        if (turnoAsesor1 != null && !turnoAsesor1.isCompletado()) {
            sb.append("Asesor 1: ").append(turnoAsesor1.getCodigo()).append("\n");
        } else {
            sb.append("Asesor 1: disponible\n");
        }

        if (turnoAsesor2 != null && !turnoAsesor2.isCompletado()) {
            sb.append("Asesor 2: ").append(turnoAsesor2.getCodigo()).append("\n");
        } else {
            sb.append("Asesor 2: disponible\n");
        }
        return sb.toString();
    }

    public String getTurnosPendientes() {
        if (primero == null) {
            return "No hay turnos pendientes\n\n";
        }

        StringBuilder sb = new StringBuilder("Turnos Pendientes:\n");
        Turno actual = primero;
        while (actual != null) {
            if (actual.getOperario() == null) {
                sb.append("Código: ").append(actual.getCodigo())
                        .append(" - Estado: Pendiente\n");
            }
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    public String atenderSiguienteTurno(String asesor) {
        // Marca el turno actual como completado si existe
        if (asesor.equals("Asesor 1") && turnoAsesor1 != null) {
            turnoAsesor1.setCompletado(true);
        } else if (asesor.equals("Asesor 2") && turnoAsesor2 != null) {
            turnoAsesor2.setCompletado(true);
        }

        // Buscar siguiente turno pendiente
        Turno actual = primero;
        while (actual != null) {
            if (actual.getOperario() == null) {
                actual.setOperario(asesor);
                // Asignar el nuevo turno actual al asesor correspondiente
                if (asesor.equals("Asesor 1")) {
                    turnoAsesor1 = actual;
                } else {
                    turnoAsesor2 = actual;
                }
                String result = "Turno " + actual.getCodigo() + " con el " + asesor;
                notifyObservers();
                return result;
            }
            actual = actual.getSiguiente();
        }
        return "No hay turnos pendientes para atender";
    }

    public void addObserver(TurnoObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (TurnoObserver observer : observers) {
            observer.onTurnoUpdated();
        }
    }
}