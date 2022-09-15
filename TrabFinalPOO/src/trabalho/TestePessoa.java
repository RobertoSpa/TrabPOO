package trabalho;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TestePessoa {

	public static void main(String[] args) {

		File arquivo = new File("/exemplos/Dados.txt");
		Set<Funcionario> funcionarios = new HashSet<>();
		// Set<Dependente> dep = new HashSet<>();

		try {
			Scanner scan = new Scanner(arquivo);
			System.out.println("-----Leitura Arquivo-----");
			Funcionario funcionario = null;
			while (scan.hasNextLine()) {
				String linha = scan.nextLine();

				if (!linha.isEmpty()) {
					String vetor[] = linha.split(";");
					if (funcionario != null) {
						String nomeDep = vetor[0];
						String cpfDep = vetor[1];
						String dataNascDep = vetor[2];
						String parentescoDep = vetor[3];
						try {
							Dependente d = new Dependente(nomeDep, cpfDep, LocalDate.parse(dataNascDep),
									GrauParentesco.valueOf(parentescoDep));
							funcionario.getDependentes().add(d);
						} catch (Exception e) {
							System.out.println("Erro!");
						}

						continue;
					}
					String nomeFunc = vetor[0];
					String cpfFun = vetor[1];
					String dataNascFunc = vetor[2];
					String salarioFunc = vetor[3];

					funcionario = new Funcionario(nomeFunc, cpfFun, LocalDate.parse(dataNascFunc),
							Double.parseDouble(salarioFunc));
					funcionarios.add(funcionario);
				} else {
					funcionarios.add(funcionario);
					funcionario = null;
				}

			}
			funcionarios.add(funcionario);

			scan.close();

			System.out.println("-----Dados do arquivo-----");
			for (Funcionario func : funcionarios) {
				func.calcularINSS();
				func.calcularIR();
				func.calcularSalarioLiquido();
				System.out.println(func);
				System.out.println("----------------------");
				System.out.println(func.getDescontoInss());
				System.out.println(func.getDescontoIR());
				System.out.println(func.getDependentes().size());
			}

			System.out.println("-----Gravação Arquivo-----");
			FileWriter arquivoGravar = new FileWriter("/exemplos/DadosAtual.txt");
			PrintWriter gravacaoArquivo = new PrintWriter(arquivoGravar);
			for (Funcionario func : funcionarios) {
				String linha = func.getNome() + ";" + func.getCpf() + ";" + func.getDataNascimento() + ";"
						+ func.getSalarioBruto() + "\n";
				gravacaoArquivo.print(linha);
			}
			System.out.println("Arquivo gravado com sucesso!");
			gravacaoArquivo.close();

		} catch (IOException e) {
			System.out.println("Não funcionou!");
		}

	}
}
