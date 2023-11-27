import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "OPERADOR"),
                new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("2284.38"), "OPERADOR"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "COORDENADOR"),
                new Funcionario("Minguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "DIRETOR"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "RECEPCIONISTA"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "OPERADOR"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "CONTADOR"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "GERENTE"),
                new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "ELETRICISTA"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("19119.88"), "DIRETOR")
        ));

        System.out.println("\n");
        imprimirBancoDeDados();



        //deleta joão dalista, ou quem definir
        funcionarios.removeIf(funcionario -> funcionario.nome.equals("João"));

        System.out.println("\nTABELA DE FUNCIONARIOS");
        FuncionarioUtils.imprimirFuncionarios(funcionarios);
        System.out.println(FuncionarioUtils.separador);
        System.out.println("\n");

        //aumenta salario do funcionario em 10%
        funcionarios.forEach(funcionario -> {
            funcionario.salario = funcionario.salario.multiply(new BigDecimal("1.1"));
        });

        //lista todos os funcionarios agrupado
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(funcionario -> funcionario.funcao));

        //lista funcionarios por função
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFUNCIONARIOS NA FUNÇÃO " + funcao + ":");
            FuncionarioUtils.imprimirFuncionarios(lista);
            System.out.println(FuncionarioUtils.separador);
            System.out.println("\n");
        });

        //lista funcionario que faz aniversario entre o mes 10 e 12
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 || funcionario.dataNascimento.getMonthValue() == 12)
                .collect(Collectors.toList());
        System.out.println("FUNCIONARIOS QUE FAZEM ANIVERSARIO MÊS 10 E 12:");
        FuncionarioUtils.imprimirFuncionarios(aniversariantes);
        System.out.println(FuncionarioUtils.separador);
        System.out.println("\n");

        //lista funcionario mais velho
        Funcionario maisVelho = funcionarios.stream()
                .max(Comparator.comparing(funcionario -> funcionario.dataNascimento))
                .orElse(null);
        if (maisVelho != null) {
            int idade = LocalDate.now().getYear() - maisVelho.dataNascimento.getYear();
            System.out.println("FUNCIONARIO MAIS VELHO");
            System.out.println(FuncionarioUtils.separador);
            System.out.println(maisVelho.nome + ", IDADE: " + idade);
            System.out.println(FuncionarioUtils.separador);
            System.out.println("\n");
        }

        //lista funcionario por ordem alfabetica
        funcionarios.sort(Comparator.comparing(funcionario -> funcionario.nome));
        System.out.println("LISTA DE FUNCIONARIOS POR ORDEM ALFABÉTICA:");
       FuncionarioUtils.imprimirFuncionarios(funcionarios);
        System.out.println(FuncionarioUtils.separador);
        System.out.println("\n");


        //soma total de salario dos funcionarios
        BigDecimal totalSalarios = funcionarios.stream()
                .map(funcionario -> funcionario.salario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("TOTAL DOS SALÁRIOS DOS FUNCIONARIOS");
        System.out.println(FuncionarioUtils.separador);
        System.out.println(FuncionarioUtils.formatarValor(totalSalarios));
        System.out.println(FuncionarioUtils.separador);
        System.out.println("\n");


        //lista quantos salarios minimos ganha cada funcionario
        System.out.println("SALÁRIOS EM SALÁRIOS MÍNIMOS");
        System.out.println(FuncionarioUtils.separador);
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioMinimo = new BigDecimal("1212.00");
            BigDecimal salariosMinimos = funcionario.salario.divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println(funcionario.nome + " ganha " + salariosMinimos + " salários mínimos.\n" + FuncionarioUtils.separador);
        });
        System.out.println("\n");
    }



    private static void imprimirBancoDeDados() {
        int tamanhoQuadrado = 50;
        String nomeBancoDeDados = "BANCO DE DADOS";

        int espacosEsquerda = (tamanhoQuadrado - nomeBancoDeDados.length()) / 2;
        int espacosDireita = tamanhoQuadrado - nomeBancoDeDados.length() - espacosEsquerda;

        System.out.println("+" + "-".repeat(tamanhoQuadrado - 2) + "+");
        System.out.println("|" + " ".repeat(espacosEsquerda) + nomeBancoDeDados + " ".repeat(espacosDireita) + "|");
        System.out.println("+" + "-".repeat(tamanhoQuadrado - 2) + "+");
    }
}
