import br.com.ifce.academico.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    static Scanner sc = new Scanner(System.in);

    static List<Aluno> alunos = new ArrayList<>();
    static Professor professor;
    static Diario diario = new Diario();
    static Disciplina disciplina = new Disciplina();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA ACADÊMICO =====");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Cadastrar Turma");
            System.out.println("4 - Atribuir Aluno à Disciplina (Notas)");
            System.out.println("5 - Cadastrar Alunos na Turma");
            System.out.println("6 - Configurar Tipo de Média");
            System.out.println("7 - Exibir Diário Completo");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpa buffer

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarProfessor();
                    break;
                case 3:
                    cadastrarTurma();
                    break;
                case 4:
                    atribuirNotas();
                    break;
                case 5:
                    cadastrarAlunosNaTurma();
                    break;
                case 6:
                    configurarMedia();
                    break;
                case 7:
                    exibirDiario();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }



    static void cadastrarAluno() {
        Aluno a = new Aluno();

        System.out.print("Nome do aluno: ");
        a.setNome(sc.nextLine());

        System.out.print("Nascimento: ");
        a.setNascimento(sc.nextLine());

        System.out.print("Matrícula: ");
        a.setMatricula(sc.nextInt());
        sc.nextLine();

        alunos.add(a);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    static void cadastrarProfessor() {
        professor = new Professor();

        System.out.print("Nome do professor: ");
        professor.setNome(sc.nextLine());

        System.out.print("Nascimento: ");
        professor.setNascimento(sc.nextLine());

        System.out.print("SIAPE: ");
        professor.setSiape(sc.nextInt());
        sc.nextLine();

        System.out.print("Formação: ");
        professor.setFormacao(sc.nextLine());

        System.out.println("Professor cadastrado com sucesso!");
    }

    static void cadastrarTurma() {
        System.out.print("Código da turma: ");
        diario.setCodigo(sc.nextLine());

        if (professor == null) {
            System.out.println("Cadastre um professor primeiro!");
            return;
        }

        diario.setProfessor(professor);

        System.out.print("Código da disciplina: ");
        disciplina.setCodigo(sc.nextLine());

        System.out.println("Turma cadastrada com sucesso!");
    }

    static void atribuirNotas() {

        if (diario.getCalculadora() == null) {
            System.out.println("Configure o tipo de média antes de lançar notas!");
            return;
        }

        System.out.print("Nota N1: ");
        disciplina.setN1(sc.nextDouble());

        System.out.print("Nota N2: ");
        disciplina.setN2(sc.nextDouble());

        diario.calcular(disciplina);

        System.out.println("Notas lançadas e média calculada!");
    }


    static void cadastrarAlunosNaTurma() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
            return;
        }

        for (Aluno a : alunos) {
            diario.addAluno(a);
        }

        System.out.println("Alunos adicionados à turma!");
    }

    static void configurarMedia() {
        System.out.println("1 - Média Aritmética");
        System.out.println("2 - Média Ponderada");
        System.out.print("Escolha: ");

        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1) {
            diario.setCalculadora(new MediaAritmetica());
        } else {
            diario.setCalculadora(new MediaPonderada());
        }

        System.out.println("Tipo de média configurado!");
    }

    static void exibirDiario() {
        System.out.println("\n===== DIÁRIO =====");
        System.out.println("Turma: " + diario.getCodigo());
        System.out.println("Disciplina: " + disciplina.getCodigo());

        Professor p = diario.getProfessor();
        System.out.println("Professor: " + p.getNome() + " (SIAPE: " + p.getSiape() + ")");

        for (Aluno a : diario.getAlunos()) {
            System.out.println("\nAluno: " + a.getNome());
            System.out.println("Matrícula: " + a.getMatricula());
            System.out.println("Média: " + disciplina.getMedia());
            System.out.println("Resultado: " +
                    (disciplina.isAprovacao() ? "APROVADO" : "REPROVADO"));
        }
    }
}
