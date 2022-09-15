package teste;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import enums.GrauParentesco;
import exceptions.DependenteException;
import model.Dependente;
import model.Funcionario;

public class TestePessoa {

	public static void main(String[] args) {

		File arquivo = new File("/exemplos/dadosTrab.txt");
		Set<Funcionario> funcionarios = new HashSet<>();

		try {
			try (Scanner scan = new Scanner(arquivo)) {
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
							LocalDate testeIdade = LocalDate.parse(dataNascDep);
							Period diferencaIdade = Period.between(testeIdade, LocalDate.now());
							if (diferencaIdade.getYears() > 18) {
								throw new DependenteException("Maior de idade não se classifica como dependente.");
							}

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
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			System.out.println("-----Dados do arquivo-----");
			for (Funcionario func : funcionarios) {
				func.calcularINSS();
				func.calcularIR();
				func.calcularSalarioLiquido();
				System.out.println(func);
				System.out.println("----------------------");
				System.out.println(func.getDependentes().size());

			}

			System.out.println("-----Gravação Arquivo-----");
			FileWriter arquivoGravar = new FileWriter("/exemplos/dadosGravados.txt");
			PrintWriter gravacaoArquivo = new PrintWriter(arquivoGravar);
			for (Funcionario func : funcionarios) {
				String linha = func.getNome() + ";" + func.getCpf() + ";"
						+ String.format("%.2f", func.getDescontoInss()) + ";"
						+ String.format("%.2f", func.getDescontoIR()) + ";"
						+ String.format("%.2f", func.getSalarioLiquido()) + "\n";
				gravacaoArquivo.print(linha);
			}
			System.out.println("Arquivo gravado com sucesso!");
			gravacaoArquivo.close();

		} catch (IOException e) {
			System.out.println("Não funcionou!");
		}

	}
}
