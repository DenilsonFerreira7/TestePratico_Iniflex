import java.math.BigDecimal;
import java.time.LocalDate;

class Funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
}
