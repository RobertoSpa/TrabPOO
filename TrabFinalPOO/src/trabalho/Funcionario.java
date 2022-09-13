package trabalho;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Funcionario extends Pessoa implements Calculo {
	private Double salarioBruto;
	private Double descontoInss;
	private Double descontoIR;
	private Set<Dependente> dependentes = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	@Override
	public String toString() {
		return super.toString() + "Funcionario [salarioBruto=" + salarioBruto + dependentes + "]";
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

	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(Set<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public void calcularIR() {
		if (salarioBruto <= 1903.98) {
			descontoIR = 0.;
		} else if (salarioBruto <= 1903.98 && salarioBruto >= 2826.65) {
			descontoIR = (salarioBruto - dependentes.size() * 189.59 - descontoInss) * 0.075 - 142.8;
		} else if (salarioBruto <= 2826.66 && salarioBruto >= 3751.05) {
			descontoIR = (salarioBruto - dependentes.size() * 189.59 - descontoInss) * 0.15 - 354.8;
		} else if (salarioBruto <= 3751.06 && salarioBruto >= 4664.68) {
			descontoIR = (salarioBruto - dependentes.size() * 189.59 - descontoInss) * 0.225 - 636.13;
		} else {
			descontoIR = (salarioBruto - dependentes.size() * 189.59 - descontoInss) * 0.275 - 869.36;
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
	public Double calcularSalarioLiquido() {
		return salarioBruto - descontoInss - descontoIR;

	}

	public void adicionarDependentes(Dependente dependente) {
		dependentes.add(dependente);
	}

	// teste
}