import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

class Tarefa {
    // Atributos privados
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private int prioridade;

    // Desafio 2: Método privado para validar prioridade
    private int validarPrioridade(int prioridade) {
        if (prioridade < 1 || prioridade > 5) {
            throw new IllegalArgumentException("Prioridade deve estar entre 1 e 5.");
        }
        return prioridade;
    }

    // Método para validar prazo
    private boolean isPrazoValido(LocalDate prazo) {
        return prazo != null;
    }

    // Construtor 1: Construtor completo.
    public Tarefa(String titulo, String descricao, String prazo, int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = LocalDate.parse(prazo);
        this.prioridade = validarPrioridade(prioridade); // Desafio 2
    }

    // Construtor 2: Construtor com menos parâmetros.
    public Tarefa(String titulo, String prazo) {
        this.titulo = titulo;
        this.prazo = LocalDate.parse(prazo);
        this.descricao = "";    // Descrição padrão
        this.prioridade = 1;    // Prioridade padrão e válida
    }

    // Métodos públicos para acessar os atributos
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return this.prazo;
    }

    public void setPrazo(String prazo) {
        try {
            LocalDate data = LocalDate.parse(prazo);
            if (isPrazoValido(data)) {
                this.prazo = data;
            } else {
                System.out.println("Data inválida.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido.");
        }
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = validarPrioridade(prioridade); // Desafio 2
    }

    // Método para exibir os detalhes da tarefa
    public void exibirDetalhes() {
        System.out.println("Título: " + this.titulo);
        System.out.println("Descrição: " + this.descricao);
        System.out.println("Prazo: " + this.prazo);
        System.out.println("Prioridade: " + this.prioridade);
    }

    // Método para calcular dias restantes
    public long calcularDiasRestantes() {
        if (prazo != null) {
            LocalDate hoje = LocalDate.now();
            return ChronoUnit.DAYS.between(hoje, prazo);
        } else {
            throw new IllegalStateException("O prazo da tarefa não está definido.");
        }
    }
}

// Desafio 1 e Desafio 2
public class Main {
    public static void main(String[] args) {
        // Testando o Desafio 1: Construtor completo
        System.out.println("=============== Testando Desafio 1 ===============");
        Tarefa tarefa1 = new Tarefa("Estudar POO", "Revisar os conceitos de classes e objetos", "2024-09-15", 1);

        System.out.println("=============== Testando métodos gets ===============");
        System.out.println("Título: " + tarefa1.getTitulo());
        System.out.println("Descrição: " + tarefa1.getDescricao());
        System.out.println();

        System.out.println("=============== Imprimindo com método exibirDetalhes() ===============");
        tarefa1.exibirDetalhes();

        // Testando o Desafio 2: Construtor com menos parâmetros e validação de prioridade
        System.out.println("=============== Testando Desafio 2 ===============");
        Tarefa tarefa2 = new Tarefa("Estudar para provas", "2050-10-25");

        tarefa2.exibirDetalhes();

        try {
            System.out.println("Dias restantes para a tarefa 2: " + tarefa2.calcularDiasRestantes());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("Novo prazo da tarefa 1: ");
        tarefa1.setPrazo("2024-09-30");
    }
}
