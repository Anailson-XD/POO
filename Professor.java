public class Professor extends Pessoa {
    private String siape;
    private String formacao;

    public Professor(String nome, String nascimento, String siape, String formacao) {
        super(nome, nascimento);
        this.siape = siape;
        this.formacao = formacao;
    }

    public String getSiape() { return siape; }
    public void setSiape(String siape) { this.siape = siape; }

    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }
}
