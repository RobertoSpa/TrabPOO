package trabalho;

import java.io.BufferedReader;
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
		
		File arquivo = new File("/exemplos/Dados.txt");
        try {
            Scanner scan = new Scanner(arquivo);
            List<Funcionario> f2 = new ArrayList<>();
			Set<Dependente> d1 = new HashSet<>();
            System.out.println("-----Leitura Arquivo-----");
            if (scan.hasNextLine()) {
                String linha = scan.nextLine();
                if (!linha.isEmpty()) {
                    String vetor[] = linha.split(";");
                    f2.add(new Funcionario(vetor[0], vetor[1], LocalDate.parse(vetor[2]), Double.parseDouble(vetor[3])));
                }
            }
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                if (!linha.isEmpty()) {
                    String vetor[] = linha.split(";");
                    d1.add(new Dependente(vetor[0], vetor[1], LocalDate.parse(vetor[2]), GrauParentesco.valueOf(vetor[3])));
                }else {
                	break;
                }
            }
            scan.close();

            System.out.println("-----Dados do arquivo-----");
            for (Funcionario funcionario : f2) {
                System.out.println(funcionario);
            }
            for (Dependente dependente : d1) {
				System.out.println(dependente);
			}

            System.out.println("-----Gravação Arquivo-----");
            FileWriter arquivoGravar = new FileWriter("/exemplos/DadosAtual.txt");
            PrintWriter gravacaoArquivo = new PrintWriter(arquivoGravar);
            for (Funcionario fun : f2) {
                String linha = fun.getNome() +";"+ fun.getCpf()+";"+ fun.getDataNascimento()+";"+ fun.getSalarioBruto()+"\n";
                gravacaoArquivo.print(linha);
            }
            System.out.println("Arquivo gravado com sucesso!");
            gravacaoArquivo.close();

        } catch (IOException e) {
            System.out.println("Não funcionou!");
        }
        
	
}}
