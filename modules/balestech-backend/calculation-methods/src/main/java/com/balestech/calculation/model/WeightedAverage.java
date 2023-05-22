package com.balestech.calculation.model;

/**
 * A média ponderada é um método de cálculo que atribui pesos diferentes aos valores individuais de um conjunto de dados,
 * com base na importância relativa de cada valor. No contexto de ações listadas na B3,
 * a média ponderada é utilizada para calcular o Índice Bovespa, também conhecido como Ibovespa.
 *
 * No cálculo do Ibovespa, as ações são selecionadas com base em sua liquidez e volume de negociação.
 * Em seguida, é atribuído um peso a cada ação no índice, com base na sua participação no valor total de mercado das ações selecionadas.
 *
 * Por exemplo, suponha que o Índice Bovespa contenha três ações: Ação A, Ação B e Ação C.
 * O valor total de mercado das ações selecionadas é de R$ 1.000.000. Ação A representa 40% do valor total de mercado,
 * Ação B representa 30% e Ação C representa 30%.
 *
 * Nesse caso, para calcular a média ponderada do Ibovespa, você multiplicaria o preço de cada ação pelo seu peso relativo
 * e, em seguida, somaria esses valores ponderados. O resultado é o valor do índice.
 *
 * Suponha que o preço da Ação A seja R$ 10, da Ação B seja R$ 20 e da Ação C seja R$ 30. O cálculo seria o seguinte:
 *
 * (10 * 0,4) + (20 * 0,3) + (30 * 0,3) = 4 + 6 + 9 = 19
 *
 * Portanto, nesse exemplo hipotético, o valor do Ibovespa seria 19.
 *
 * Essa é apenas uma ilustração simples para mostrar como a média ponderada é aplicada no contexto do cálculo do Índice Bovespa. Na prática, o Ibovespa é recalculado em tempo real,
 * levando em consideração o preço e o peso atualizado de cada ação no portfólio do índice.
 *
 * É importante ressaltar que, na realidade, a metodologia de cálculo do Ibovespa é mais complexa e envolve ajustes para levar em conta eventos corporativos,
 * como desdobramentos de ações e distribuição de dividendos.
 */
public class WeightedAverage {
}
