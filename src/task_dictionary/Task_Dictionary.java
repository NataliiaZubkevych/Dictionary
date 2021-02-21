
package task_dictionary;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class Task_Dictionary {

    private final HashMap<String, ArrayList<Words>> dictionary = new HashMap<>();

    public HashMap<String, ArrayList<Words>> getDictionary() {
        return dictionary;
    }
    private final String filename = "Words.txt";

    public Task_Dictionary() {
        CreateDictionary();
    }
    
    public static void main(String[] args) {
        System.out.println("Создать программу по работе со словарем. Например,\n" +
"англо-испанский или французско-немецкий, или любое\n" +
"другое направление. Программа должна:\n" +
"■■Обеспечивать начальный ввод данных для словаря;\n" +
"■■Позволять отобразить слово и его переводы;\n" +
"■■Позволять добавить, заменить, удалить переводы слова;\n" +
"■■Позволять добавить, заменить, удалить слово;\n" +
"■■Отображать топ-10 самых популярных слов (определя-\n" +
"ем популярность на основании счетчика обращений);\n" +
"■■Отображать топ-10 самых непопулярных слов (опре-\n" +
"деляем непопулярность на основании счетчика обращений).");      
        System.out.println();
    
        Task_Dictionary dictionary = new Task_Dictionary();
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------");
            System.out.println("Enter your choice:");
            System.out.println("----------------------");
            System.out.println(""
                    + "1. Show all words with translations in the current dictionary \n"
                    + "2. Add word with translations in the current dictionary \n"                     
                    + "3. Add translation to word \n"  
                    + "4. Change translation \n"  
                    + "5. Remove translation \n"  
                    + "6. Add new word \n"  
                    + "7. Change word \n" 
                    + "8. Remove word \n" 
                    + "9. Show top 10 popular words \n" 
                    + "10. Show top 10 not popular words \n" 
                    + "0. Exit");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextInt()) {
                int input = - 1;
                input = scan.nextInt();
                switch (input) {
                    case 1:
                        System.out.println();
                //ShowAllWords();
                        System.out.println("Set current language (English-Russian): ");
                        dictionary.GetLanguages().forEach((lang) -> {
                            System.out.println( lang );
                });
                        if (scan.hasNextInt()) {
                            int language;
                            language = scan.nextInt();

                            ArrayList<Words> words = dictionary.GetWords(dictionary.GetLanguages().get(language - 1));
                            words.forEach((word) -> {
                                System.out.println(word.getWord() + ":" + word.getTranslations() + ", " + word.getCount());
                    });
                        }
                        break;
                    case 2:
                        System.out.println();
                        AddAllForWord();
                        break;
                    case 3:
                        System.out.println();
                        System.out.print("Enter word for translate: ");
                        if (scan.hasNext()) {
                            String word = scan.next();
                            System.out.print("Enter your translation: ");
                            String translation;
                            if (scan.hasNext()) {
                                translation = scan.next();
                                if (dictionary.AddTranslationWord(word, translation)) {
                                    System.out.println("SUCCESS! Translation has been added");
                                } 
                            } 
                        }
                        break;
                    case 4:
                        System.out.println();
                        System.out.print("Enter word for change translation: ");
                        if (scan.hasNext()) {
                            String word = scan.next();
                            System.out.print("Enter previous translation: ");
                            String prevTranslation;
                            if (scan.hasNext()) {
                                prevTranslation = scan.next();
                                System.out.print("Enter new translation: ");
                                String newTranslation;
                                if (scan.hasNext()) {
                                    newTranslation = scan.next();
                                    if (dictionary.ChangeTranslation(word, prevTranslation, newTranslation)) {
                                        System.out.println("SUCCESS! Word has been changed");
                                    }
                                } 
                            }
                        }
                        break;
                    case 5:
                        System.out.println();
                        System.out.print("Enter word for remove translation: ");
                        if (scan.hasNext()) {
                            String word = scan.next();
                            System.out.print("Enter translation: ");
                            String translation;
                            if (scan.hasNext()) {
                                translation = scan.next();
                                if (dictionary.RemoveTranslation(word, translation)) {
                                    System.out.println("SUCCESS! Translation has been removed");
                                }
                            } 
                        }
                        break;    
                    case 6:
                        System.out.println();
                        System.out.print("Enter word for add: ");
                        if (scan.hasNext()) {
                            String word = scan.next();
                            System.out.println("Set current language (English-Russian): ");
                            int j = 1;
                            for (String lang : dictionary.GetLanguages()) {
                                System.out.println(j + ". " + lang);
                                ++j;
                            }
                            int language;
                            if (scan.hasNextInt()) {
                                language = scan.nextInt();
                                if (dictionary.AddWord(word, dictionary.GetLanguages().get(language - 1))) {
                                    System.out.println("SUCCESS! Word has been added");
                                }
                            }
                        }
                        break;  
                    case 7:
                        System.out.println();
                        System.out.print("Enter new word: ");
                        if (scan.hasNext()) {
                            String newWord = scan.next();
                            System.out.print("Enter previous word: ");
                            String prevWord;
                            if (scan.hasNext()) {
                                prevWord = scan.next();
                                if (dictionary.ChangeWord(prevWord, newWord)) {
                                    System.out.println("SUCCESS! Word has been changed");
                                }
                            } 
                        }
                        break;
                    case 8:
                        System.out.println();
                        System.out.print("Enter word for remove: ");
                        if (scan.hasNext()) {
                            String word = scan.next();
                            if (dictionary.RemoveWord(word)) {
                                System.out.println("SUCCESS! Word has been removed");
                            } 
                        } 
                        break;                    
                    case 9:
                        System.out.println();
                        dictionary.GetTop10Words().forEach((word) -> {
                            System.out.println(word.getWord() + ":" + word.getTranslations() + ", " + word.getCount());
                });
                        break;
                    case 10:
                        System.out.println();
                        dictionary.GetLower10Words().forEach((word) -> {
                            System.out.println(word.getWord() + ":" + word.getTranslations() + ", " + word.getCount());
                });
                        break;
                    case 0:
                        System.out.println();
                        exit = true;
                        break;
                    default:
                        System.out.println();
                        System.out.println("Error, input correct info");
                }
                try {
                    if (System.getProperty("os.name").contains("Windows")) {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } else {
                        Runtime.getRuntime().exec("clear");
                    }
                } catch (IOException | InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        dictionary.SaveDictionary();
    }

    private void CreateDictionary() {
        File file = new File("./" + filename);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (file.length() == 0) {
            ArrayList<Words> words = new ArrayList<>();
            words.add(new Words("apple", new ArrayList<String>(Arrays.asList("яблоко", "яблоня"))));
            words.add(new Words("analysis", new ArrayList<String>(Arrays.asList("анализ"))));
            words.add(new Words("test", new ArrayList<String>(Arrays.asList("тест"))));
            words.add(new Words("study", new ArrayList<String>(Arrays.asList("исследование", "изучение"))));
            words.add(new Words("work", new ArrayList<String>(Arrays.asList("работать", "трудиться"))));
            words.add(new Words("moil", new ArrayList<String>(Arrays.asList("мучение", "путаница"))));
            words.add(new Words("splash", new ArrayList<String>(Arrays.asList("всплеск", "брызганье"))));
            words.add(new Words("lace", new ArrayList<String>(Arrays.asList("кружево", "шнурок"))));
            words.add(new Words("material", new ArrayList<String>(Arrays.asList("материал", "вещество"))));
            words.add(new Words("details", new ArrayList<String>(Arrays.asList("детали", "элементы"))));
            words.add(new Words("skill", new ArrayList<String>(Arrays.asList("умение", "мастерство"))));
            words.add(new Words("fineness", new ArrayList<String>(Arrays.asList("тонкость", "крупность"))));
            words.add(new Words("level", new ArrayList<String>(Arrays.asList("уровень", "ступень"))));
            dictionary.put("English-Russian", words);
            SaveDictionary();
        }
        String language = "";
        
        ArrayList<Words> words = null;
        if (file.isFile() && file.length() > 0) {
            try ( FileReader reader = new FileReader(file);  
                    Scanner scan = new Scanner(reader)) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (line.contains("Language \n")) {
                        if (words == null) {
                            words = new ArrayList<>();
                        } else {
                            dictionary.put(language, words);
                            words = new ArrayList<>();
                        }
                        language = line.substring(line.indexOf(":") + 1).strip(); // strip() заменить на trim(), в случае ошибки!!!
                    } else {
                        if (words == null) {
                            System.out.println("ERROR! Check file");
                            return;
                        }
                        Words word = new Words();
                        ArrayList<String> translations = new ArrayList<>();
                        word.setWord(line.split(":")[0].replace("\t", ""));
                        if (line.charAt(line.indexOf("[") + 1) != ']') {
                            translations.addAll(Arrays.asList(line.substring(line.indexOf("[") + 1, line.indexOf("]")).split(",")));
                        }                        
                        word.setTranslations(translations);
                        word.setCount(Integer.parseInt(line.substring(line.indexOf("]") + 2).strip())); // strip() заменить на trim(), в случае ошибки!!!
                        words.add(word);
                    }
                }
                dictionary.put(language, words);
            } catch (Exception e) {}
        }
    }
    
    public void SaveDictionary() {
        String infile = "";
        for (String language : dictionary.keySet()) {
            infile = infile.concat("Language: \n" + language + "\n");
            for (Words word : dictionary.get(language)) {
                String translations = "";
                if (word.getTranslations() == null) {
                    translations = "[]";
                } else {
                    translations = word.getTranslations().toString();
                }
                infile = infile + "\t" + word.getWord() + ":" + translations + ", " + word.getCount() + "\n";
            }
        }
        infile = infile.replaceAll("\s", ""); // в случае, если запись сохраняется в строку, смените "\s" на "/s" !!!
        try ( FileWriter writer = new FileWriter(filename)) {
            writer.write(infile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
//    public static String ShowAllWords(){
//        
//         try(FileReader reader = new FileReader("Words.txt")) {
//               int sym;
//               while((sym = reader.read()) !=-1){
//                   System.out.print((char)sym);                  
//               }
//               
//           } catch(IOException ex) {
//               System.out.println(ex.getMessage());
//           }
//         return null;
//    }

    public static String  AddAllForWord(){ 
        try(FileWriter f = new FileWriter ("Words.txt", true))   
        {  
            Words word = new Words();   
            Scanner scan = new Scanner (System.in);
            System.out.print("Enter English word: ");
                    String str1 = scan.next();
                    System.out.print("Enter first translate for English word: ");
                    String str2 = scan.next();
                    System.out.print("Enter second translate for English word: ");
                    String str3 = scan.next();
                    System.out.println();
                    f.write("\n    " + str1 + ":[" + str2 + ", " + str3 + "]," + word.getCount() + "\n");
                    System.out.println();                            
        }catch(IOException ex) {
               System.out.println(ex.getMessage());
           }
        return null;
    }

    public ArrayList<String> GetLanguages() {
        ArrayList<String> languages = new ArrayList<>();
        dictionary.keySet().forEach(language -> {
            languages.add(language);
        });
        return languages;
    }

    public ArrayList<String> GetTranslations(String word) {
        ArrayList<String> translations = null;
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor : words) {
                if (wor.getWord().equalsIgnoreCase(word)) {
                    translations = wor.getTranslations();
                }
            }
        }
        return translations;
    }
    
    public ArrayList<Words> GetWords(String language) {   
        if (dictionary.containsKey(language)) {
            return dictionary.get(language);
        } else {
            System.out.println("Language NOT exist");
            return null;
        }
    }

    public boolean AddTranslationWord(String word, String translation) {
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor : words) {
                if (wor.getWord().equalsIgnoreCase(word)) {
                    if (wor.getTranslations() == null) {
                        wor.setTranslations(new ArrayList<>());
                    } else if (wor.getTranslations().contains(translation)) {
                        return false;
                    }
                    wor.getTranslations().add(translation);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean AddTranslationWord(String word, ArrayList<String> translations) {
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor : words) {
                if (wor.getWord().equalsIgnoreCase(word)) {
                    ArrayList<String> repeateTranslations = new ArrayList<>();
                    wor.getTranslations().forEach((prevTranslation) -> {         // проверка на повторное добавление
                        translations.stream().filter((newTranslations) -> (prevTranslation.equalsIgnoreCase(newTranslations))).forEachOrdered((newTranslations) -> {
                            repeateTranslations.add(newTranslations);
                        });
                    });
                    if (repeateTranslations.size() == translations.size()) {
                        return false;
                    }
                    repeateTranslations.forEach((repeateTranslation) -> {
                        translations.remove(repeateTranslation);
                    });
                    wor.getTranslations().addAll(translations);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ChangeTranslation(String word, String prevTransaltion, String newTranslation) {
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor : words) {
                if (wor.getWord().equalsIgnoreCase(word)) {
                    if (wor.getTranslations().contains(prevTransaltion) && !wor.getTranslations().contains(newTranslation)) {

                        wor.getTranslations().remove(prevTransaltion);
                        wor.getTranslations().add(newTranslation);
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
     public boolean RemoveTranslation(String word, String translation) {
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor: words) {
                if (wor.getWord().equalsIgnoreCase(word)) {
                    if (wor.getTranslations().contains(translation)) {

                        wor.getTranslations().remove(translation);
                        return true;
                    }
                }
            }
        }
        return false;
    }
     
     public boolean AddWord(String word, String language) {
        if (dictionary.containsKey(language)) {
            if (!dictionary.get(language).stream().noneMatch((wor) -> (wor.getWord().equalsIgnoreCase(word)))) {
                return false;
            }
            dictionary.get(language).add(new Words(word));
            return true;
        }
        return false;
    }

    public boolean AddWord(String word, String language, ArrayList<String> translations) {
        if (dictionary.containsKey(language)) {
            if (!dictionary.get(language).stream().noneMatch((w) -> (w.getWord().equalsIgnoreCase(word)))) {
                return false;
            }
            dictionary.get(language).add(new Words(word, translations));
            return true;
        }
        return false;
    }
     
    public boolean ChangeWord(String prevWord, String newWord) {
        Words word = null;
        boolean existChange = false;
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor : words) {
                if (wor.getWord().equalsIgnoreCase(prevWord)) {
                    word = wor;
                } else if (wor.getWord().equalsIgnoreCase(newWord)) {
                    existChange = true;
                    return false;
                }
            }
        }
        if (word != null) {
            word.setWord(newWord);
            return true;
        }
        return false;
    }
    
    public boolean RemoveWord(String word) {
        Words remove = null;
        ArrayList<Words> wd = null;
        for (ArrayList<Words> words : dictionary.values()) {
            for (Words wor : words) {
                if (wor.getWord().equalsIgnoreCase(word)) {
                    remove = wor;
                    wd = words;
                }
            }
        }
        if (remove != null) {
            wd.remove(remove);
            return true;
        }
        return false;
    }

    public ArrayList<Words> GetTop10Words() {
        ArrayList<Words> top = new ArrayList<>();
        for (ArrayList<Words> value : dictionary.values()) {
            top.addAll(value);
        }
        top.sort(Comparator.comparing(Words::getCount).reversed());
        if (top.size() < 10) {
            top = new ArrayList<>(top.subList(0, top.size()));
        } else {
            top = new ArrayList<>(top.subList(0, 9));
        }
        return top;
    }

    public ArrayList<Words> GetLower10Words() {
        ArrayList<Words> lower = new ArrayList<>();
        for (ArrayList<Words> value : dictionary.values()) {
            lower.addAll(value);
        }
        lower.sort(Comparator.comparing(Words::getCount));
        if (lower.size() < 10) {
            lower = new ArrayList<>(lower.subList(0, lower.size()));
        } else {
            lower = new ArrayList<>(lower.subList(0, 9));
        }
        return lower;
    }
}
