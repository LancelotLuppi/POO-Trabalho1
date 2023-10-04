package application.domain;

import application.domain.entidades.instituicao.InstituicaoTI;
import application.domain.entidades.instituicao.Turma;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.Scanner;

public class Menu {
    private InstituicaoTI instituicaoTI = new InstituicaoTI();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            System.out.println("------------------------------------------------------------");
            System.out.println("Instituição TI");
            System.out.println("------------------------------------------------------------");
            System.out.println("Escolha uma das opções a seguir:");
            System.out.println("1) Listar todas as turmas");
            System.out.println("2) Informar dados de uma turma");
            System.out.println("3) Consultar os dados de uma turma");
            System.out.println("4) Consultar estatísticas gerais");
            System.out.println("5) Sair do sistema");
            System.out.println("------------------------------------------------------------");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    instituicaoTI.listarTodasTurmas();
                    break;
                case 2:
                    informarDadosTurma();
                    break;
                case 3:
                    consultarDadosTurma();
                    break;
                case 4:
                    instituicaoTI.mostrarEstatisticasGerais();
                    break;
                case 5:
                    System.out.print("Deseja realmente sair? S-Sim/N-Não: ");
                    String confirmacao = scanner.nextLine();
                    if (confirmacao.equalsIgnoreCase("S")) {
                        scanner.close();
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private void informarDadosTurma() {
        System.out.print("Informe o código da turma: ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe a disciplina: ");
        String disciplina = scanner.nextLine();

        System.out.print("Informe o nome do professor: ");
        String nomeProfessor = scanner.nextLine();


        Professor professor = new Professor(1, nomeProfessor, "email@exemplo.com", "Universidade XYZ");


        Turma turma = new Turma(codigoTurma, disciplina, professor);


        adicionarAlunosTurma(turma);


        instituicaoTI.adicionarTurma(turma);

        System.out.println("Dados da turma e alunos informados com sucesso!");
    }

    private void adicionarAlunosTurma(Turma turma) {
        char continuar;
        do {
            if (turma.obterQuantidadeAlunos() < 30) {
                System.out.println("Informações do aluno:");

                System.out.print("Código do aluno: ");
                int codigoAluno = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nome do aluno: ");
                String nomeAluno = scanner.nextLine();

                System.out.print("E-mail do aluno: ");
                String emailAluno = scanner.nextLine();


                Aluno aluno = new Aluno(codigoAluno, nomeAluno, emailAluno);


                for (int i = 0; i < 3; i++) {
                    System.out.print("Nota " + (i + 1) + " do aluno: ");
                    double nota = scanner.nextDouble();
                    aluno.adicionarNota(i, nota);
                }


                turma.adicionarAluno(aluno);
            } else {
                System.out.println("Limite de alunos atingido para esta turma.");
                break;
            }

            System.out.print("Deseja adicionar outro aluno? (S/N): ");
            continuar = scanner.next().charAt(0);
            scanner.nextLine();
        } while (continuar == 'S' || continuar == 's');
    }

    private void consultarDadosTurma() {
        System.out.print("Informe o código da turma que deseja consultar: ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        Turma turmaConsultada = null;


        for (Turma turma : instituicaoTI.getTurmas()) {
            if (turma.getCodigo() == codigoTurma) {
                turmaConsultada = turma;
                break;
            }
        }

        if (turmaConsultada != null) {
            turmaConsultada.mostrarDados();
        } else {
            System.out.println("Turma não encontrada.");
        }
    }
}
