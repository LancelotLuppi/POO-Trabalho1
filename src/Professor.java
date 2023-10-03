public class Professor {
    private int codigo;
    private String nome;
    private String email;
    private String universidade;

    public Professor(int codigo, String nome, String email, String universidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.universidade = universidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
