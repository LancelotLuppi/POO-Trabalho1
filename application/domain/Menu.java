package application.domain;

import application.domain.entidades.instituicao.InstituicaoTI;
import application.domain.entidades.instituicao.Turma;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.Arrays;
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




        Turma turma = new Turma(codigoTurma, disciplina, professor);


        adicionarAlunosTurma(turma);


        instituicaoTI.adicionarTurma(turma, 0);

        System.out.println("Dados da turma e alunos informados com sucesso!");
    }

    private Turma informeTurma() {
        while(true) {
            print("------- Informe de dados da turma -------");
            print("Codigo: ");
            int codigo = scanner.nextInt();
            scanner.nextLine();
            print("Disciplina: ");
            String nome = scanner.nextLine();
            print("Nome do professor: ");
            String professor = scanner.nextLine();
            if(!instituicaoTI.isNomeExistenteProfessor(professor)) {
                print("Nome de professor invalido. Refaça o informe da turma.");
                break;
            }
            Aluno[] alunos = informeAlunos();
            return new Turma(codigo, nome, professor);

        }
    }
    private Aluno[] informeAlunos() {
        Aluno[] alunos = new Aluno[30];
        int i = 0;
        while (true) {
            print("--- Informe de dados do aluno ---");
            print("Codigo: ");
            int codigo = scanner.nextInt();
            scanner.nextLine();
            print("Nome: ");
            String nome = scanner.nextLine();
            print("Email: ");
            String email = scanner.nextLine();
            double n1;
            double n2;
            double n3;
            while(true) {
                print("Nota 1: ");
                n1 = scanner.nextFloat();
                scanner.nextLine();
                if(n1 >= 0 && n1 <= 10) {
                    n2 = scanner.nextFloat();
                    scanner.nextLine();
                    if(n2 >= 0 && n2 <= 10) {
                        n3 = scanner.nextFloat();
                        scanner.nextLine();
                        if(n3 >= 0 && n3 <= 10) {
                            break;
                        }
                    }
                }
                print("A nota deve ser entre 0 a 10");
            }
            double notas[] = new double[3];
            notas[0] = n1;
            notas[1] = n2;
            notas[2] = n3;
            alunos[i] = new Aluno(codigo, nome, email, notas);
            i++;

            print("Deseja continuar informando? S/N");
            print(null);
            String resposta = scanner.nextLine();
            if(resposta.equalsIgnoreCase("N") || i == 30) {
                break;
            }
        }
        return alunos;
    }
    private Professor informeProfessor() {
        while(true) {
            print("------- Informe de dados do professor -------");
            print("Codigo: ");
            int codigo = scanner.nextInt();
            scanner.nextLine();
            print("Nome: ");
            String nome = scanner.nextLine();
            print("Email: ");
            String email = scanner.nextLine();
            print("Universidade de formação: ");
            String universidadeFormacao = scanner.nextLine();
            if(instituicaoTI.isCodigoExistenteProfessor(codigo))
                return new Professor(codigo, nome, email, universidadeFormacao);
            else {
                print("O código informado já está em uso.");
            }
        }
    }
    private void print(Object object) {
        System.out.println(object);
    }

    private void adicionarAlunosTurma(Turma turma) {
        char continuar;
        do {
            if (turma.getAlunosSize() < 30) {
                System.out.println("Informações do aluno:");

                System.out.print("Código do aluno: ");
                int codigoAluno = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nome do aluno: ");
                String nomeAluno = scanner.nextLine();

                System.out.print("E-mail do aluno: ");
                String emailAluno = scanner.nextLine();

                System.out.println("Código do curso do aluno: ");
                System.out.println("1-) Ciência da Computação");
                System.out.println("2-) Engenharia de Software");
                System.out.println("3-) Engenharia de Computação");
                System.out.println("4-) Análise e Desenvolvimento de Software");
                System.out.println();
                int codigoCurso = scanner.nextInt();
                scanner.nextLine();


                Aluno aluno = new Aluno(codigoAluno, nomeAluno, emailAluno, instituicaoTI.getCursoByIndex(codigoCurso));


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
            turmaConsultada.printDadosDetalhados();
        } else {
            System.out.println("Turma não encontrada.");
        }
    }
}
