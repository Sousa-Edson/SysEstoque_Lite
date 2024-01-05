/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author edson
 */
public enum SituacaoEnum {
    CALCULADO("1-CALCULADO"), PRONTO("2-PRONTO"), AGUARDANDO("4-DEVOLVIDO"), ENVIADO("5-ENVIADO"), OUTRO("6-OUTRO");

    private final String nome;

    SituacaoEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static SituacaoEnum getByName(String nome) {
        for (SituacaoEnum situacao : values()) {
            if (situacao.nome.equals(nome)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Name inválido para SituacaoEnum: " + nome);
    }

    public String getValorDescricao() {
        return nome;
    }

    public static void main(String[] args) {
        // Exemplo de uso 

        SituacaoEnum cal = SituacaoEnum.CALCULADO;
        SituacaoEnum ag = SituacaoEnum.AGUARDANDO;

        System.out.println("Name de calculado: " + cal.getNome());
        System.out.println("Name de aguardando: " + ag.getNome());

        // Obtendo enum pelo NAME
        String desejado = "5-ENVIADO";
        SituacaoEnum peloDesejo = SituacaoEnum.getByName(desejado);
        System.out.println("Nome de SituacaoEnum pelo name " + desejado + ": " + peloDesejo);
    }

}
