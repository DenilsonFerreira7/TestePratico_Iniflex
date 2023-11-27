import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FuncionarioUtils {


    static String separador = "------------------------------------------------------------------------------------";

    static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(funcionario -> {
            System.out.println(separador);
            System.out.println("Nome: " + funcionario.nome +
                    ", Data de Nascimento: " + funcionario.dataNascimento.format(formatter) +
                    ", Salário: " + formatarValor(funcionario.salario) +
                    ", Função: " + funcionario.funcao);
        });
    }

    static String formatarValor(BigDecimal valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(valor);
    }
}
