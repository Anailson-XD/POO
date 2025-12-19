package br.com.ifce.academico;

public class MediaPonderada implements CalcularMedia{
    public double calc(double... notas){
        return (notas[0]*0.3 + notas[1]*0.7);
    }
}
