public class Aluno {
    private int codigo;
    private String nome;
    private String email;
    private double[] notas;

    public Aluno(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.notas = new double[3];
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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
        return calcularMedia() >= 7.0;
    }
}
