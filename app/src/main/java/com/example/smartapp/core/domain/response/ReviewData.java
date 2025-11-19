package com.example.smartapp.core.domain.response;

public class ReviewData {
    private int total_avaliacoes;
    private double media_avaliacoes;
    private int nota_5;
    private int nota_4;
    private int nota_3;
    private int nota_2;
    private int nota_1;

    public ReviewData(int total_avaliacoes, double media_avaliacoes, int nota_5, int nota_4, int nota_3, int nota_2, int nota_1) {
        this.total_avaliacoes = total_avaliacoes;
        this.media_avaliacoes = media_avaliacoes;
        this.nota_5 = nota_5;
        this.nota_4 = nota_4;
        this.nota_3 = nota_3;
        this.nota_2 = nota_2;
        this.nota_1 = nota_1;
    }

    public ReviewData() {
    }

    public int getTotal_avaliacoes() {
        return total_avaliacoes;
    }

    public void setTotal_avaliacoes(int total_avaliacoes) {
        this.total_avaliacoes = total_avaliacoes;
    }

    public double getMedia_avaliacoes() {
        return media_avaliacoes;
    }

    public void setMedia_avaliacoes(double media_avaliacoes) {
        this.media_avaliacoes = media_avaliacoes;
    }

    public int getNota_5() {
        return nota_5;
    }

    public void setNota_5(int nota_5) {
        this.nota_5 = nota_5;
    }

    public int getNota_4() {
        return nota_4;
    }

    public void setNota_4(int nota_4) {
        this.nota_4 = nota_4;
    }

    public int getNota_3() {
        return nota_3;
    }

    public void setNota_3(int nota_3) {
        this.nota_3 = nota_3;
    }

    public int getNota_2() {
        return nota_2;
    }

    public void setNota_2(int nota_2) {
        this.nota_2 = nota_2;
    }

    public int getNota_1() {
        return nota_1;
    }

    public void setNota_1(int nota_1) {
        this.nota_1 = nota_1;
    }
}
