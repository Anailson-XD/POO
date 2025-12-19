package br.com.ifce.academico;

public class Professor extends Pessoa    {
    private int siape;
    private String formacao;

    public int getSiape() {
        return siape;
    }

    public void setSiape(int siape) {
        this.siape = siape;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
}
