package trabalho;

import java.time.LocalDate;

public class TestePessoa {


	public static void main(String[] args) {

		
		Dependente dep1 = new Dependente("Igor", "13456", LocalDate.of(2016, 4, 16), GrauParentesco.FILHO);
		Dependente dep2 = new Dependente("Maria", "132458", LocalDate.of(2015, 9, 28), GrauParentesco.SOBRINHO);
		Funcionario f1 = new Funcionario("thaisa","54987987", LocalDate.of(1989, 9, 8), 7000.);
		

		f1.adicionarDependentes(dep2);
		f1.adicionarDependentes(dep1);
		
		System.out.println(f1);
	
}}
