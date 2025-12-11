public class MediaPonderada implements CalcularMedia {
    @Override
    public double calc(double... notas) {
        if (notas == null || notas.length == 0) return 0.0;
        if (notas.length == 1) return notas[0];
        // Para duas notas assume pesos 0.6 e 0.4
        if (notas.length == 2) {
            return notas[0] * 0.6 + notas[1] * 0.4;
        }

        double somaPesos = 0.0;
        double soma = 0.0;
        double peso = notas.length; // peso inicial
        for (int i = 0; i < notas.length; i++) {
            soma += notas[i] * peso;
            somaPesos += peso;
            peso -= 1.0;
            if (peso <= 0) peso = 1.0;
        }
        return soma / somaPesos;
    }
}
