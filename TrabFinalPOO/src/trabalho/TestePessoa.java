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
		
		File arquivo = new File("/exemplos/Dados.txt");
		List<Funcionario> fun = new ArrayList<>();
		Set<Dependente> dep = new HashSet<>();
		
        try {
            Scanner scan = new Scanner(arquivo);
            System.out.println("-----Leitura Arquivo-----");
            while (scan.hasNextLine()) {
            if (scan.hasNextLine()) {
                String linha = scan.nextLine();
                if (!linha.isEmpty()) {
                    String vetor[] = linha.split(";");
                    Funcionario f = new Funcionario(vetor[0], vetor[1], LocalDate.parse(vetor[2]), Double.parseDouble(vetor[3]));
                    fun.add(f);
              
                }
            }
            while (scan.hasNextLine()) {
                String linha = scan.nextLine();
                if (!linha.isEmpty()) {
                    String vetor[] = linha.split(";");
                    Dependente d = new Dependente(vetor[0], vetor[1], LocalDate.parse(vetor[2]), GrauParentesco.valueOf(vetor[3]));
                    dep.add(d);
                    if (!linha.isEmpty()) {
                    	for (Funcionario f1 : fun) {
						f1.adicionarDependentes(d);
							}
                    }else {
                    	continue;
                    }
                    
                }else {
                	break;
                }
            }
            }
                
            scan.close();

            System.out.println("-----Dados do arquivo-----");
            for (Funcionario funcionario : fun) {
                System.out.println(funcionario);
            }
            for (Dependente dependente : dep) {
				System.out.println(dependente);
			}

            System.out.println("-----Gravação Arquivo-----");
            FileWriter arquivoGravar = new FileWriter("/exemplos/DadosAtual.txt");
            PrintWriter gravacaoArquivo = new PrintWriter(arquivoGravar);
            for (Funcionario func : fun) {
                String linha = func.getNome() +";"+ func.getCpf()+";"+ func.getDataNascimento()+";"+ func.getSalarioBruto()+"\n";
                gravacaoArquivo.print(linha);
            }
            System.out.println("Arquivo gravado com sucesso!");
            gravacaoArquivo.close();

        } catch (IOException e) {
            System.out.println("Não funcionou!");
        }
        
        for (Funcionario funcionario : fun) {
        	funcionario.getDescontoInss();
        	
            System.out.println(funcionario.calcularSalarioLiquido());
        }
        
        
        
        
}}
