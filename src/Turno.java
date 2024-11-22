public class Turno {
    private String codigo;
    private String operario;
    private Turno siguiente;
    private boolean completado;
    private String documento;

    public Turno(String codigo, String documento) {
        this.codigo = codigo;
        this.operario = null;
        this.siguiente = null;
        this.completado = false;
        this.documento = documento;
    }

    // Getters and setters
    public String getCodigo() { return codigo; }
    public String getOperario() { return operario; }
    public void setOperario(String operario) { this.operario = operario; }
    public Turno getSiguiente() { return siguiente; }
    public void setSiguiente(Turno siguiente) { this.siguiente = siguiente; }
    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }
    public String getDocumento() { return documento; }
}