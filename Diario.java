import java.util.ArrayList;

public class Diario {

    private String codigo;
    private Professor professor;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private CalcularMedia calculo;

    // getters e setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public void setCalculo(CalcularMedia calculo) {
        this.calculo = calculo;
    }

    // adicionar aluno ao diário
    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // calcular médias de cada disciplina
    public void aplicarMedias() {
        for (Aluno a : alunos) {
            for (Disciplina d : a.getDisciplinas()) {
                double m = calculo.calc(d.getN1(), d.getN2());
                d.setMedia(m);
                d.setAprovacao(m >= 6);
            }
        }
    }

    // exibir tudo
    public void exibir() {
        System.out.println("Diário: " + codigo);
        System.out.println("Professor: " + professor.getNome());

        for (Aluno a : alunos) {
            System.out.println("\nAluno: " + a.getNome());
            for (Disciplina d : a.getDisciplinas()) {
                System.out.println(" - " + d.getNome() +
                        " | Média: " + d.getMedia() +
                        " | Aprovado: " + d.isAprovacao());
            }
        }
    }
}
