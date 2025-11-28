package com.example.smartapp.core;

public class Mask {
    public static String maskDocument(String raw) {
        raw = raw.replaceAll("\\D", "");

        if (raw.length() <= 11) {
            if (raw.length() > 9)
                return raw.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1.$2.$3-$4");
            else if (raw.length() > 6)
                return raw.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1.$2.$3");
            else if (raw.length() > 3)
                return raw.replaceFirst("(\\d{3})(\\d+)", "$1.$2");
            else
                return raw;
        } else {
            if (raw.length() > 12)
                return raw.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d+)", "$1.$2.$3/$4-$5");
            else if (raw.length() > 8)
                return raw.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d+)", "$1.$2.$3/$4");
            else if (raw.length() > 5)
                return raw.replaceFirst("(\\d{2})(\\d{3})(\\d+)", "$1.$2.$3");
            else if (raw.length() > 2)
                return raw.replaceFirst("(\\d{2})(\\d+)", "$1.$2");
            else
                return raw;
        }
    }

    public static String applyMaskCep(String raw) {
        if (raw == null) return "";
        raw = raw.replaceAll("\\D", ""); // só números

        if (raw.length() > 5)
            return raw.replaceFirst("(\\d{5})(\\d+)", "$1-$2");
        else
            return raw;
    }

    public static String unmask(String cep) {
        if (cep == null) return "";
        return cep.replaceAll("\\D", "");
    }

    public static String applyMaskTelefone(String raw) {
        if (raw == null) return "";
        raw = raw.replaceAll("\\D", ""); // só números

        if (raw.length() > 2 && raw.length() <= 7)
            return raw.replaceFirst("(\\d{2})(\\d+)", "($1) $2");
        else if (raw.length() > 7)
            return raw.replaceFirst("(\\d{2})(\\d{5})(\\d+)", "($1) $2-$3");
        else
            return raw;
    }




}
