public class Disciplina {
    private String nome;
    private double n1;
    private double n2;
    private double media;
    private boolean aprovado;
    private Professor professor; // instância de professor responsável por essa disciplina

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
        this.n1 = 0.0;
        this.n2 = 0.0;
        this.media = 0.0;
        this.aprovado = false;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getN1() { return n1; }
    public void setN1(double n1) { this.n1 = n1; }

    public double getN2() { return n2; }
    public void setN2(double n2) { this.n2 = n2; }

    public double getMedia() { return media; }
    public void setMedia(double media) { this.media = media; }

    public boolean isAprovado() { return aprovado; }
    public void setAprovado(boolean aprovado) { this.aprovado = aprovado; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    @Override
    public String toString() {
        return String.format("Disciplina: %s | Prof: %s | N1: %.2f N2: %.2f | Media: %.2f | Aprovado: %s",
                nome,
                (professor != null ? professor.getNome() : "Sem prof"),
                n1, n2, media, aprovado ? "Sim" : "Não");
    }
}
