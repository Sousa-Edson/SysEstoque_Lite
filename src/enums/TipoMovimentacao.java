/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author edson
 */
public enum TipoMovimentacao {
    ENTRADA(0),
    SAIDA(1);

    private final int id;

    TipoMovimentacao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TipoMovimentacao getById(int id) {
        for (TipoMovimentacao tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID inválido para TipoMovimentacao: " + id);
    }

    public static void main(String[] args) {
        // Exemplo de uso
        TipoMovimentacao entrada = TipoMovimentacao.ENTRADA;
        TipoMovimentacao saida = TipoMovimentacao.SAIDA;

        System.out.println("ID de Entrada: " + entrada.getId());
        System.out.println("ID de Saída: " + saida.getId());

        // Obtendo enum pelo ID
        int idDesejado = 0;
        TipoMovimentacao tipoPeloId = TipoMovimentacao.getById(idDesejado);
        System.out.println("Tipo de Movimentação pelo ID " + idDesejado + ": " + tipoPeloId);
    }
}
