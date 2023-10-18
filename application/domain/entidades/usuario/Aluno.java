package application.domain.entidades.usuario;

import application.domain.entidades.instituicao.Curso;

public class Aluno extends Usuario {

    private double[] notas;
    private Curso curso;

    public Aluno(int codigo, String nome, String email, Curso curso) {
        super.codigo = codigo;
        super.nome = nome;
        super.email = email;
        this.curso = curso;
        this.notas = new double[3];
    }

    public Aluno(int codigo, String nome, String email, Curso curso, double[] notas) {
        super.codigo = codigo;
        super.nome = nome;
        super.email = email;
        this.curso = curso;
        this.notas = notas;
    }

    public double calcularMedia() {
        double sum = 0;
        for (double nota : notas) {
            sum += nota;
        }
        return sum / notas.length;
    }

    public boolean estaAprovado() {
        return calcularMedia() >= 6.0;
    }
    public double getNotaByIndex(int index) {
        if(index >= 0 && index <= 3)
            return this.notas[index];
        return 0;
    }

    public void printDadosBasicos() {
        System.out.println("----- Dados do aluno -----");
        System.out.println("Codigo: " + super.getCodigo());
        System.out.println("Nome: " + super.getNome());
        System.out.println("Email: " + super.getEmail());
        System.out.println("Curso: " + this.getCurso().getNome());
    }

    public void printDadosDetalhados() {
        System.out.println("Codigo do Aluno: " + this.getCodigo());
        System.out.println("Nome: " + this.getNome());
        System.out.println(
                "N1: " + String.format("%.2f", this.getNotaByIndex(0))
                        + " | N2: " + String.format("%.2f", this.getNotaByIndex(1))
                        + " | N3: " + String.format("%.2f", this.getNotaByIndex(2))
        );
        System.out.println("Media: " + String.format("%.2f", this.calcularMedia()));
        System.out.println("Situacao: " + (this.estaAprovado() ? "Aprovado" : "Reprovado"));
        System.out.println("---------------");
    }

    public Curso getCurso() {
        return this.curso;
    }
}
