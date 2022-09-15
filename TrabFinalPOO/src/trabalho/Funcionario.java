package trabalho;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Funcionario extends Pessoa implements Calculo {
	private Double salarioBruto = 0.;
	private Double descontoInss = 0.;
	private Double descontoIR = 0.;
	private Double salarioLiquido = 0.;
	private Set<Dependente> dependentes = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	@Override
	public String toString() {
		return super.getNome() + ";" + super.getCpf() + ";" + String.format("%.2f", descontoInss) + ";" + String.format("%.2f", descontoIR) + ";" + String.format("%.2f", salarioLiquido);
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Double getDescontoInss() {
		return descontoInss;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(Set<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public void calcularIR() {
		Double salarioBase = salarioBruto - (dependentes.size() * 189.59) - descontoInss;
		if (salarioBase <= 1903.98) {
			descontoIR = 0.;
		} else if (salarioBase >= 1903.98 && salarioBase <= 2826.65) {
			descontoIR = salarioBase * 0.075 - 142.8;
		} else if (salarioBase >= 2826.66 && salarioBase <= 3751.05) {
			descontoIR = salarioBase * 0.15 - 354.8;
		} else if (salarioBase >= 3751.06 && salarioBase <= 4664.68) {
			descontoIR = salarioBase * 0.225 - 636.13;
		} else {
			descontoIR = salarioBase * 0.275 - 869.36;
		}
	}

	@Override
	public void calcularINSS() {
		if (salarioBruto <= 1212.) {
			descontoInss = (salarioBruto * 0.075) - 0.;
		} else if (salarioBruto >= 1212.01 && salarioBruto <= 2427.35) {
			descontoInss = (salarioBruto * 0.09) - 18.18;
		} else if (salarioBruto >= 2427.36 && salarioBruto <= 3641.03) {
			descontoInss = (salarioBruto * 0.12) - 91.0;
		} else if (salarioBruto >= 3641.04 && salarioBruto <= 7087.22) {
			descontoInss = (salarioBruto * 0.14) - 163.82;
		} else {
			descontoInss = 828.39;
		}

	}

	@Override
	public void calcularSalarioLiquido() {
		salarioLiquido = salarioBruto - descontoInss - descontoIR;

	}

	public void adicionarDependentes(Dependente dependente) {
		dependentes.add(dependente);
	}

}