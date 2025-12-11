public class MediaAritmetica implements CalcularMedia {
    @Override
    public double calc(double... notas) {
        if (notas == null || notas.length == 0) return 0.0;
        double soma = 0.0;
        for (double n : notas) soma += n;
        return soma / notas.length;
    }
}
