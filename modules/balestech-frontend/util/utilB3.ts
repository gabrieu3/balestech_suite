

export function encontrarMenorMaiorQueZero(valores: number[]): number | undefined {
    let menorMaiorQueZero: number | undefined = undefined;
  
    for (const valor of valores) {
      if (valor > 0 && (menorMaiorQueZero === undefined || valor < menorMaiorQueZero)) {
        menorMaiorQueZero = valor;
      }
    }
  
    return menorMaiorQueZero;
  }
  

  export function encontrarMaior(valores: number[]): number {
    let maior = 0 as number ;

    for (const valor of valores) {
      if (valor > maior) {
        maior = valor;
      }
    }
  
    return maior;
  }
 