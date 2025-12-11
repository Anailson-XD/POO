import java.util.ArrayList;
import java.util.List;

public class Diario {
    private String codigo;
    private Professor professorRegente;
    private final List<Aluno> alunos = new ArrayList<>();
    private CalcularMedia calculador; // estratégia de cálculo

    public Diario(String codigo, Professor professorRegente) {
        this.codigo = codigo;
        this.professorRegente = professorRegente;
        this.calculador = new MediaAritmetica(); // default
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Professor getProfessorRegente() { return professorRegente; }
    public void setProfessorRegente(Professor professorRegente) { this.professorRegente = professorRegente; }

    public List<Aluno> getAlunos() { return alunos; }

    public void setCalculador(CalcularMedia calculador) {
        this.calculador = calculador;
    }

    public CalcularMedia getCalculador() { return calculador; }

    public boolean matricularAluno(Aluno aluno) {
        if (aluno == null) return false;
        if (alunos.contains(aluno)) return false; // evita duplicata
        alunos.add(aluno);
        return true;
    }

    public boolean removerAluno(Aluno aluno) {
        return alunos.remove(aluno);
    }

    /**
     * Calcula a média das disciplinas dos alunos e atualiza aprovacao em cada disciplina.
     * Também calcula média geral do aluno (média das médias das disciplinas) e a retorna
     * no mapa de exibição (ou apenas imprime nos métodos abaixo).
     */
    public void calcularMediasETodasAprovacoes() {
        if (calculador == null) calculador = new MediaAritmetica();
        for (Aluno a : alunos) {
            for (Disciplina d : a.getDisciplinas()) {
                double m = calculador.calc(d.getN1(), d.getN2());
                d.setMedia(m);
                d.setAprovado(m >= 6.0); // regra: aprovado se >= 6.0
            }
        }
    }

    public void imprimirDiarioCompleto() {
        calcularMediasETodasAprovacoes();
        System.out.println("=== DIÁRIO: " + codigo + " | Professor regente: " +
                (professorRegente != null ? professorRegente.getNome() : "Nenhum") + " ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno matriculado.");
            return;
        }
        for (Aluno a : alunos) {
            System.out.printf("Aluno: %s (Mat: %s)%n", a.getNome(), a.getMatricula());
            if (a.getDisciplinas().isEmpty()) {
                System.out.println("  - Nenhuma disciplina cadastrada para este aluno.");
                continue;
            }
            double somaMedias = 0;
            int cont = 0;
            for (Disciplina d : a.getDisciplinas()) {
                System.out.println("  - " + d);
                somaMedias += d.getMedia();
                cont++;
            }
            double mediaAluno = cont > 0 ? somaMedias / cont : 0.0;
            System.out.printf("  Média geral do aluno: %.2f%n", mediaAluno);
            System.out.println();
        }
    }
}
