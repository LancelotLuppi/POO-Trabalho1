package application.domain.entidades.instituicao;

import application.domain.entidades.Entity;
import application.domain.entidades.usuario.Aluno;
import application.domain.entidades.usuario.Professor;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class InstituicaoTI {
    private Turma[] turmas = new Turma[30];

    private final Curso[] cursos = new Curso[4];
    private final Professor[] professores = new Professor[30];

    public InstituicaoTI() {
        cursos[0] = new Curso(1, "Ciência da Computação");
        cursos[1] = new Curso(2, "Engenharia de Software");
        cursos[2] = new Curso(3, "Engenharia de Computação");
        cursos[3] = new Curso(4, "Análise e Desenvolvimento de Sistemas");
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public Curso getCursoByIndex(int index) {
        if(index >= 0 && index < 4) {
            return cursos[index];
        }
        return null;
    }

    public void adicionaProfessor(Professor professor) {
        int index = (int) Arrays.stream(professores).filter(Objects::nonNull).count();
        if(index < 30) {
            professores[index] = professor;
        } else {
            System.out.println("Numero máximo de professores atingido (30).");
        }
    }
    public void consultaNomesProfessores() {
        System.out.println("Lista de nomes dos professores:");
        System.out.println();
        Arrays.stream(professores).filter(Objects::nonNull).forEach(professor -> System.out.println(professor.getNome()));
        System.out.println();
    }
    public Optional<Aluno> consultaTodosAlunosPorCodigo(int codigo) {
        Optional<Aluno> aluno = Optional.empty();
        for(Turma turma : turmas) {
            if(Objects.nonNull(turma)) {
                aluno = turma.consultaAlunoPorCodigo(codigo);
                if(aluno.isPresent())
                    break;
            }
        }
        return aluno;
    }

    public Optional<Professor> getProfessorByName(String nome) {
        if(getNumProfessores() != 0)
            return Arrays.stream(professores)
                    .filter(Objects::nonNull)
                    .filter(professor -> professor.getNome().equalsIgnoreCase(nome))
                    .findFirst();
        else
            return Optional.empty();
    }
    public long getNumProfessores() {
        return Arrays.stream(professores).filter(Objects::nonNull).count();
    }

    public void adicionarTurma(Turma turma) {
        int index = (int) getNumTurmas();
        if (index >= 0 && index < 30)
            turmas[index] = turma;
        else
            System.out.println("Maximo de turmas alcançado (30)");
    }

    public Turma[] getTurmas() {
        return turmas;
    }

    public long getNumTurmas() {
        return Arrays.stream(turmas).filter(Objects::nonNull).count();
    }

    public void listarTodasTurmas() {
        if(getNumTurmas() != 0) {
            System.out.println("----- Lista de Turmas -----");
            Arrays.stream(turmas).filter(Objects::nonNull).forEach(Turma::printDadosBasicos);
        }
        else
            System.out.println("Sem turmas para consulta.");
    }

    public void consultaTurmaDetalhado(int index) {
        Optional<Turma> optTurma = Arrays.stream(turmas)
                .filter(Objects::nonNull)
                .filter(turma -> turma.getCodigo().equals(index))
                .findFirst();
        if(optTurma.isPresent()) {
            optTurma.get().printDadosDetalhados();
        } else
            System.out.println("Código de turma inválido.");
    }

    public void mostrarEstatisticasGerais() {
        if(getNumTurmas() != 0) {
            int totalAlunos = 0;
            int totalAprovados = 0;

            for (Turma turma : turmas) {
                if(turma != null) {
                    totalAlunos += turma.getAlunosSize();
                    totalAprovados += turma.obterQuantidadeAprovados();
                }
            }

            double percentualAprovacaoTotal = totalAlunos > 0 ? (double) totalAprovados / totalAlunos * 100 : 0;

            System.out.println("----- Estatísticas Gerais -----");
            System.out.println("Total de Turmas: " + getNumTurmas());
            System.out.println("Total de Alunos Matriculados: " + totalAlunos);
            System.out.println("Total de Alunos Aprovados: " + totalAprovados);
            System.out.println("Percentual de Aprovação Geral: " + String.format("%.2f", percentualAprovacaoTotal) + "%");
            System.out.println();
            System.out.println("----- Estatísticas por turma -----");
            Arrays.stream(turmas).filter(Objects::nonNull).forEach(Turma::printDadosEstatistica);
        } else {
            System.out.println("Sem turmas para consulta.");
        }

    }
}
