import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String matricula;
    private final List<Disciplina> disciplinas = new ArrayList<>();

    public Aluno(String nome, String nascimento, String matricula) {
        super(nome, nascimento);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public List<Disciplina> getDisciplinas() { return disciplinas; }

    public void adicionarDisciplina(Disciplina d) {
        if (d == null) return;
        disciplinas.add(d);
    }

    public void removerDisciplina(Disciplina d) {
        disciplinas.remove(d);
    }
}
