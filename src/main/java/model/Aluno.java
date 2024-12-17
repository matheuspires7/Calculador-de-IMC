package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String cpf;
    private String nome;
    private String dataNascimento;
    private Float peso;
    private Float altura;

    // Constructor
    public Aluno(String cpf, String nome, String dataNascimento, Float peso, Float altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {this.altura = altura;}

    public List<String> calcularIMC(){
        List<String> resultado = new ArrayList<>();
        float imc = peso / (altura * altura);
        LocalDate currentDate = LocalDate.now();
        resultado.add(String.format("IMC: %.2f", imc));
        resultado.add(String.format("Nome: %s", nome));
        resultado.add(String.format("Data do Teste: %s", currentDate));
        resultado.add(String.format("CPF: %s", cpf));
        if (imc<18.5){
            resultado.add("Abaixo do peso");
        } else if (imc>=18.5 && imc<=24.9){
            resultado.add("Peso ideal");
        } else if (imc>=25 && imc<=29.9){
            resultado.add("Sobrepeso");
        } else if (imc>=30 && imc<=39.9){
            resultado.add("Obesidade");
        }
        resultado.add("");
        return resultado;
    }

    @Override
    public String toString() {
        return
                "Nome= " + nome + '\n' +
                "CPF= " + cpf + '\n' +
                "Data de Nascimento= " + dataNascimento + '\n' +
                "Peso= " + peso + '\n' +
                "Altura= " + altura;
    }
}