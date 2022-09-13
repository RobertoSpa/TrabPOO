package trabalho;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TestePessoa {


	public static void main(String[] args) {

		/* 
		Dependente dep1 = new Dependente("Igor", "13456", LocalDate.of(2016, 4, 16), GrauParentesco.FILHO);
		Dependente dep2 = new Dependente("Maria", "132458", LocalDate.of(2015, 9, 28), GrauParentesco.SOBRINHO);
		Funcionario f1 = new Funcionario("thaisa","54987987", LocalDate.of(1989, 9, 8), 7000.);
		
		f1.adicionarDependentes(dep2);
		f1.adicionarDependentes(dep1);
		
		System.out.println(f1);
		*/
		
		File arquivo = new File("/exemplos/Dados.txt");
        try {
            Scanner scan = new Scanner(arquivo);
            List<Funcionario> f2 = new ArrayList<>();
			Set<Dependente> d1 = new HashSet<>();
            System.out.println("-----Leitura Arquivo-----");
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                if (!linha.isEmpty()) {
                    String vetor[] = linha.split(";");
                    f2.add(new Funcionario(vetor[0], vetor[1], LocalDate.parse(vetor[2]), Double.parseDouble(vetor[3])));
                }
            }
            scan.close();

            System.out.println("-----Dados do arquivo-----");
            for (Funcionario funcionario : f2) {
                System.out.println(funcionario);
            }

            System.out.println("-----Gravação Arquivo-----");
            FileWriter arquivoGravar = new FileWriter("/exemplos/DadosAtual.txt");
            PrintWriter gravacaoArquivo = new PrintWriter(arquivoGravar);
            for (Funcionario fun : f2) {
                String linha = fun.getCpf()+";"+ fun.getNome() + "\n";
                gravacaoArquivo.print(linha);
            }
            System.out.println("Arquivo gravado com sucesso!");
            gravacaoArquivo.close();

        } catch (IOException e) {
            System.out.println("Não funcionou!");
        }
	
}}
