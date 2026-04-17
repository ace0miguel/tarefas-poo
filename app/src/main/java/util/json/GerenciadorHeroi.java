package util.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
        public boolean shouldSkipField(FieldAttributes f) {
            return Scanner.class.isAssignableFrom(f.getDeclaredClass());
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return Scanner.class.isAssignableFrom(clazz);
        }
    };

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .addSerializationExclusionStrategy(IGNORA_SCANNER)
            .addDeserializationExclusionStrategy(IGNORA_SCANNER)
            .create();
   
    public static void salvar(Heroi heroi) {
        File dir = new File(DIR_SAVE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (FileWriter writer = new FileWriter(ARQUIVO_SAVE_LOCAL)) {
            gson.toJson(heroi, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Heroi carregar() {
        try (FileReader reader = new FileReader(ARQUIVO_SAVE_LOCAL)) {
            return gson.fromJson(reader, Heroi.class);
        } catch (IOException e) {
            try (InputStream is = GerenciadorHeroi.class.getResourceAsStream(RECURSO_CLASSPATH)) {
                if (is == null) {
                    return null;
                }
                try (Reader reader = new InputStreamReader(is)) {
                    return gson.fromJson(reader, Heroi.class);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}