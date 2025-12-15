import java.util.ArrayList;
import java.util.List;

public class Diario {
    private String codigo;
    private Professor professor;
    private List<Aluno> alunos = new ArrayList<>();
    private CalcularMedia calculadora;

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public void setCalculadora(CalcularMedia calc) { this.calculadora = calc; }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void calcular(Disciplina disciplina) {
        double media = calculadora.calc(disciplina.getN1(), disciplina.getN2());
        disciplina.setMedia(media);
        disciplina.setAprovacao(media >= 6);
    }
}
