import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    private static final Scanner sc = new Scanner(System.in);

    // Armazenamentos "in memory" do app
    private static final List<Aluno> alunos = new ArrayList<>();
    private static final List<Professor> professores = new ArrayList<>();
    private static final List<Diario> turmas = new ArrayList<>();

    public static void main(String[] args) {
        boolean rodando = true;
        while (rodando) {
            mostrarMenu();
            int op = lerInt("Opção: ");
            switch (op) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarProfessor();
                case 3 -> cadastrarTurma();
                case 4 -> atribuirAlunoADisciplina();
                case 5 -> cadastrarAlunoEmTurma();
                case 6 -> configurarTipoMedia();
                case 7 -> exibirDiario();
                case 0 -> { rodando = false; System.out.println("Encerrando..."); }
                default -> System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Cadastrar Professor");
        System.out.println("3 - Cadastrar Turma (Diário)");
        System.out.println("4 - Atribuir Aluno a Disciplina");
        System.out.println("5 - Cadastrar alunos em Turma");
        System.out.println("6 - Configurar tipo de média da Turma");
        System.out.println("7 - Exibir todas as informações de um Diário");
        System.out.println("0 - Sair");
    }

    private static void cadastrarAluno() {
        String nome = lerString("Nome do aluno: ");
        String nascimento = lerString("Nascimento (yyyy-mm-dd): ");
        String matricula = lerString("Matrícula: ");
        Aluno a = new Aluno(nome, nascimento, matricula);
        alunos.add(a);
        System.out.println("Aluno cadastrado.");
    }

    private static void cadastrarProfessor() {
        String nome = lerString("Nome do professor: ");
        String nascimento = lerString("Nascimento (yyyy-mm-dd): ");
        String siape = lerString("SIAPE: ");
        String formacao = lerString("Formação: ");
        Professor p = new Professor(nome, nascimento, siape, formacao);
        professores.add(p);
        System.out.println("Professor cadastrado.");
    }

    private static void cadastrarTurma() {
        String codigo = lerString("Código da turma (Diário): ");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado. Cadastre um professor antes.");
            return;
        }
        System.out.println("Escolha o professor regente (pelo índice):");
        for (int i = 0; i < professores.size(); i++) {
            System.out.printf("%d - %s%n", i, professores.get(i).getNome());
        }
        int idx = lerInt("Índice: ");
        if (idx < 0 || idx >= professores.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        Diario d = new Diario(codigo, professores.get(idx));
        turmas.add(d);
        System.out.println("Turma criada.");
    }

    private static void atribuirAlunoADisciplina() {
        if (alunos.isEmpty()) { System.out.println("Nenhum aluno cadastrado."); return; }
        if (professores.isEmpty()) { System.out.println("Nenhum professor cadastrado."); return; }

        System.out.println("Escolha o aluno (índice):");
        for (int i = 0; i < alunos.size(); i++) System.out.printf("%d - %s%n", i, alunos.get(i).getNome());
        int ia = lerInt("Índice do aluno: ");
        if (ia < 0 || ia >= alunos.size()) { System.out.println("Índice inválido."); return; }
        Aluno aluno = alunos.get(ia);

        System.out.println("Escolha o professor da disciplina (índice):");
        for (int i = 0; i < professores.size(); i++) System.out.printf("%d - %s%n", i, professores.get(i).getNome());
        int ip = lerInt("Índice do professor: ");
        if (ip < 0 || ip >= professores.size()) { System.out.println("Índice inválido."); return; }
        Professor prof = professores.get(ip);

        String nomeDisc = lerString("Nome da disciplina: ");
        Disciplina d = new Disciplina(nomeDisc, prof);
        double n1 = lerDouble("Nota N1 (0-10): ");
        double n2 = lerDouble("Nota N2 (0-10): ");
        d.setN1(n1);
        d.setN2(n2);
        aluno.adicionarDisciplina(d);

        System.out.println("Disciplina atribuída ao aluno.");
    }

    private static void cadastrarAlunoEmTurma() {
        if (turmas.isEmpty()) { System.out.println("Nenhuma turma criada."); return; }
        if (alunos.isEmpty()) { System.out.println("Nenhum aluno cadastrado."); return; }

        System.out.println("Escolha a turma (índice):");
        for (int i = 0; i < turmas.size(); i++) System.out.printf("%d - %s%n", i, turmas.get(i).getCodigo());
        int it = lerInt("Índice da turma: ");
        if (it < 0 || it >= turmas.size()) { System.out.println("Índice inválido."); return; }
        Diario diario = turmas.get(it);

        System.out.println("Escolha o aluno (índice):");
        for (int i = 0; i < alunos.size(); i++) System.out.printf("%d - %s%n", i, alunos.get(i).getNome());
        int ia = lerInt("Índice do aluno: ");
        if (ia < 0 || ia >= alunos.size()) { System.out.println("Índice inválido."); return; }
        Aluno aluno = alunos.get(ia);

        // cuidado: evita duplicata
        if (diario.getAlunos().contains(aluno)) {
            System.out.println("Aluno já está matriculado nesta turma.");
            return;
        }
        diario.matricularAluno(aluno);
        System.out.println("Aluno matriculado na turma.");
    }

    private static void configurarTipoMedia() {
        if (turmas.isEmpty()) { System.out.println("Nenhuma turma criada."); return; }
        System.out.println("Escolha a turma (índice):");
        for (int i = 0; i < turmas.size(); i++) System.out.printf("%d - %s%n", i, turmas.get(i).getCodigo());
        int it = lerInt("Índice da turma: ");
        if (it < 0 || it >= turmas.size()) { System.out.println("Índice inválido."); return; }
        Diario diario = turmas.get(it);

        System.out.println("Escolha o tipo de média:");
        System.out.println("1 - Aritmética");
        System.out.println("2 - Ponderada (peso 0.6/0.4 para 2 notas)");
        int op = lerInt("Opção: ");
        if (op == 1) diario.setCalculador(new MediaAritmetica());
        else if (op == 2) diario.setCalculador(new MediaPonderada());
        else System.out.println("Opção inválida.");
        System.out.println("Tipo de média configurado.");
    }

    private static void exibirDiario() {
        if (turmas.isEmpty()) { System.out.println("Nenhuma turma criada."); return; }
        System.out.println("Escolha a turma (índice):");
        for (int i = 0; i < turmas.size(); i++) System.out.printf("%d - %s%n", i, turmas.get(i).getCodigo());
        int it = lerInt("Índice da turma: ");
        if (it < 0 || it >= turmas.size()) { System.out.println("Índice inválido."); return; }
        Diario diario = turmas.get(it);
        diario.imprimirDiarioCompleto();
    }

    // ---------- utilitários ----------
    private static String lerString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }
    private static int lerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            sc.nextLine();
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }
    private static double lerDouble(String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            System.out.print("Digite um número válido: ");
            sc.nextLine();
        }
        double v = sc.nextDouble();
        sc.nextLine();
        return v;
    }
}

