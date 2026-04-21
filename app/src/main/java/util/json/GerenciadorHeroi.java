package util.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidades.Heroi;

public class GerenciadorHeroi {

    private static final String DIR_SAVE = "util/json/save";
    private static final String ARQUIVO_SAVE_LOCAL = DIR_SAVE + "/save_heroi.json";
    private static final String RECURSO_CLASSPATH = "/dados/heroi.json";

    private static final ExclusionStrategy IGNORA_SCANNER = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return fieldAttributes.getDeclaredType() == Scanner.class;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return clazz == Scanner.class;
        }
    };

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setExclusionStrategies(IGNORA_SCANNER)
            .create();
   
    public static void salvar(Heroi heroi) {
        try {
            Files.createDirectories(Path.of(DIR_SAVE));
        } catch (IOException ignored) {
            return;
        }

        try (Writer writer = Files.newBufferedWriter(Path.of(ARQUIVO_SAVE_LOCAL), StandardCharsets.UTF_8)) {
            gson.toJson(heroi, writer);
        } catch (IOException ignored) {
        }
    }

    public static Heroi carregar() {
        Heroi heroiSalvo = carregarDoArquivo();
        if (heroiSalvo != null) {
            return heroiSalvo;
        }

        return carregarDoRecurso();
    }

    private static Heroi carregarDoArquivo() {
        try (Reader reader = Files.newBufferedReader(Path.of(ARQUIVO_SAVE_LOCAL), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Heroi.class);
        } catch (Exception ignored) {
            return null;
        }
    }

    private static Heroi carregarDoRecurso() {
        try (InputStream is = GerenciadorHeroi.class.getResourceAsStream(RECURSO_CLASSPATH)) {
            if (is == null) {
                return null;
            }

            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                return gson.fromJson(reader, Heroi.class);
            }
        } catch (Exception ignored) {
            return null;
        }
    }
}