package utility;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import data.StudyGroup;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * Operates the file for saving/loading collection.
 */

public class FileManager {

    private Gson gson = new Gson();

    private String envVariable;

    public FileManager(String envVariable) {this.envVariable = envVariable;}

    /**
     * Writes collection to a file.
     * @param collection Collection to write.
     */

    public void writeCollection(Collection<?> collection) {
        if (System.getenv().get(envVariable) != null) {
            try (FileWriter collectionFileWriter = new FileWriter(new File(System.getenv().get(envVariable)))) {
                collectionFileWriter.write(gson.toJson(collection));
                utility.Console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                utility.Console.printerror("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else utility.Console.printerror("Системная переменная с загрузочным файлом не найдена!");
    }

    /**
     * Reads collection from a file.
     * @return Read collection.
     */

    public LinkedList<StudyGroup> readCollection() {
        if (System.getenv().get(envVariable) != null) {
            try (FileInputStream collectionFile = new FileInputStream(System.getenv().get(envVariable));
                 BufferedInputStream collectionFileBuffer = new BufferedInputStream(collectionFile)) {

                JsonReader jsonReader = new JsonReader(new InputStreamReader(collectionFileBuffer));


                LinkedList<StudyGroup> collection = new LinkedList<>();

                jsonReader.beginArray();

                while (jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    StudyGroup studyGroup = gson.fromJson(jsonReader, StudyGroup.class);
                    collection.add(studyGroup);
                    jsonReader.endObject();
                }
                jsonReader.endArray();
                System.out.println(jsonReader);

                utility.Console.println("Коллекция успешна загружена!");
                return collection;
            } catch (FileNotFoundException exception) {
                utility.Console.printerror("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                utility.Console.printerror("Загрузочный файл пуст!");
            } catch (JsonParseException | NullPointerException exception) {
                utility.Console.printerror("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                utility.Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
        return new LinkedList<StudyGroup>();
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}




