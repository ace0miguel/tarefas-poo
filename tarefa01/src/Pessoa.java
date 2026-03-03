// Classe imutável: os atributos só podem ser lidos (somente getters), não alterados após a criação.
public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa.");
        }
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void apresentar() {
        System.out.println("Olá, meu nome é " + nome + " e tenho " + idade + " anos.");
    }
}
