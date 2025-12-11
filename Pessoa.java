public abstract class Pessoa {
    private String nome;
    private String nascimento; // formato simples, p.ex. "2001-05-20"

    public Pessoa(String nome, String nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNascimento() { return nascimento; }
    public void setNascimento(String nascimento) { this.nascimento = nascimento; }
}

