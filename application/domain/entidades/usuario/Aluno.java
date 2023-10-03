package application.domain.entidades.usuario;

public class Aluno extends Usuario {

    private double[] notas;

    public Aluno(int codigo, String nome, String email) {
        super.codigo = codigo;
        super.nome = nome;
        super.email = email;
        this.notas = new double[3];
    }

    public double[] getNotas() {
        return notas;
    }

    public void adicionarNota(int indice, double nota) {
        if (indice >= 0 && indice < notas.length) {
            notas[indice] = nota;
        }
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
}
