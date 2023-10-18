package application.domain;

import application.domain.entidades.instituicao.Curso;
import application.domain.entidades.instituicao.InstituicaoTI;
import application.domain.entidades.instituicao.Turma;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final InstituicaoTI instituicaoTI = new InstituicaoTI();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            print("------------------------------------------------------------");
            print("Instituicao TI");
            print("------------------------------------------------------------");
            print("Escolha uma das opcoes a seguir:");
            print("1) Listar todas as turmas");
            print("2) Informar dados de uma turma");
            print("3) Consultar os dados de uma turma");
            print("4) Consultar estatísticas gerais");
            print("5) Sair do sistema");
            print("------------------------------------------------------------");

            int opcao = lerInt();
            switch (opcao) {
                case 1 -> listarTurmas();
                case 2 -> informeTurma();
                case 3 -> consultarDadosTurma();
                case 4 -> estatisticasGerais();
                case 5 -> {
                    print("Deseja realmente sair? S-Sim/N-Nao: ");
                    String confirmacao = scanner.nextLine();
                    if (confirmacao.equalsIgnoreCase("S")) {
                        scanner.close();
                        System.exit(0);
                    }
                }
                default -> print("Opção invalida. Tente novamente.");
            }
        }
    }

    private void estatisticasGerais() {
        instituicaoTI.mostrarEstatisticasGerais();
        continuar();
    }

    private void listarTurmas() {
        instituicaoTI.listarTodasTurmas();
        continuar();
    }

    private void informeTurma() {
        int codigo;
        String disciplina;
        Optional<Professor> optProfessor;
        while (true) {
            print("------- Informe de dados da turma -------");
            print("Codigo: ");
            codigo = lerInt();
            print("Disciplina: ");
            disciplina = scanner.nextLine();
            print("Nome do professor: ");
            String professor = scanner.nextLine();
            optProfessor = instituicaoTI.getProfessorByName(professor);
            if (optProfessor.isEmpty()) {
                print();
                print("Nome de professor incorreto ou inexistente no banco de dados.");
                print("Deseja adicionar? S/N");
                String opcao = scanner.nextLine();
                if (opcao.equalsIgnoreCase("S")) {
                    adicionaProfessor();
                } else {
                    print("Refaca o informe com o nome correto do professor.");
                    print("Deseja consultar a base de nomes? S/N");
                    opcao = scanner.nextLine();
                    if (opcao.equalsIgnoreCase("S"))
                        instituicaoTI.consultaNomesProfessores();
                    else
                        print();
                }
            } else
                break;
        }
        Aluno[] alunos = informeAlunos();
        Turma turma = new Turma(codigo, disciplina, optProfessor.get(), alunos);
        instituicaoTI.adicionarTurma(turma);
        print("Turma adicionada com sucesso.");
        continuar();
    }

    private void adicionaProfessor() {
        print("------- Informe os dados do professor que deseja adicionar -------");
        print("Codigo: ");
        int codigo = lerInt();
        print("Nome: ");
        String nome = scanner.nextLine();
        print("Email: ");
        String email = scanner.nextLine();
        print("Universidade de formação: ");
        String universidade = scanner.nextLine();
        instituicaoTI.adicionaProfessor(new Professor(codigo, nome, email, universidade));
    }

    private Aluno[] informeAlunos() {
        Aluno[] alunos = new Aluno[30];
        int i = 0;
        while (true) {
            print("--- Informe de dados do aluno ---");
            print("Codigo: ");
            int codigo = lerInt();
            Optional<Aluno> optAluno = instituicaoTI.consultaTodosAlunosPorCodigo(codigo);
            if (optAluno.isPresent()) {
                print("Código do aluno informado já existente, pertence a: " + optAluno.get().getNome() + " | " + optAluno.get().getEmail());
                print("Deseja adicionar o aluno existente na turma? S/N");
                String opcao = scanner.nextLine();
                if (opcao.equalsIgnoreCase("S")) {
                    alunos[i] = optAluno.get();
                    print("Deseja continuar informando? S/N");
                    i++;
                    opcao = scanner.nextLine();
                    if (opcao.equalsIgnoreCase("N") || i == 30) {
                        break;
                    }
                } else
                    print("Informe os dados do aluno novamente com um codigo ainda não utilizado.");
            } else {
                print("Nome: ");
                String nome = scanner.nextLine();
                print("Email: ");
                String email = scanner.nextLine();
                Curso curso = selecionarCurso();
                double[] notas = informeNotas();
                alunos[i] = new Aluno(codigo, nome, email, curso, notas);
                i++;
                print("Deseja continuar informando? S/N");
                print();
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("N") || i == 30) {
                    break;
                }
            }

        }
        return alunos;
    }

    private double[] informeNotas() {
        double n1;
        double n2;
        double n3;
        while (true) {
            print("Nota 1 (formato 0,0): ");
            n1 = lerFloat();
            if (n1 >= 0 && n1 <= 10) {
                print("Nota 2 (formato 0,0): ");
                n2 = lerFloat();
                if (n2 >= 0 && n2 <= 10) {
                    print("Nota 3 (formato 0,0): ");
                    n3 = lerFloat();
                    if (n3 >= 0 && n3 <= 10) {
                        break;
                    }
                }
            }
            print("A nota deve ser entre 0 a 10");
        }
        double[] notas = new double[3];
        notas[0] = n1;
        notas[1] = n2;
        notas[2] = n3;
        return notas;
    }

    private Curso selecionarCurso() {
        do {
            print("Código do curso do aluno: ");
            print("1-) Ciência da Computação");
            print("2-) Engenharia de Software");
            print("3-) Engenharia de Computação");
            print("4-) Análise e Desenvolvimento de Software");
            int codigoCurso = lerInt();
            Curso curso = instituicaoTI.getCursoByIndex(codigoCurso - 1);
            if (curso != null)
                return curso;
            else
                print("Curso invalido, selecione novamente.");
        } while (true);


    }

    private void consultarDadosTurma() {
        System.out.print("Informe o código da turma que deseja consultar: ");
        int codigoTurma = lerInt();
        instituicaoTI.consultaTurmaDetalhado(codigoTurma);
        continuar();
    }

    private int lerInt() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException ignored) {
                print("Informe um numero inteiro.");
                scanner.nextLine();
            }
        }
    }

    private float lerFloat() {
        while (true) {
            try {
                float input = scanner.nextFloat();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException ignored) {
                print("Informe um numero float: 0,0.");
                scanner.nextLine();
            }
        }
    }

    private void continuar() {
        print();
        print("Pressione qualquer tecla para continuar.");
        scanner.nextLine();
    }

    private void print(Object object) {
        System.out.println(object);
    }

    private void print() {
        System.out.println();
    }
}
