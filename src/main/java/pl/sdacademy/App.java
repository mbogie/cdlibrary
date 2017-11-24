package pl.sdacademy;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {

    private static final String FILENAME = "library.txt";

    private Scanner scanner;
    private Library library;

    public App() {
        scanner = new Scanner(System.in);
        library = new Library();

    }

    public void processMainMenu() {
        int input;
        do {
            showMainMenu();
            input = readInt(1, 6);
            switch (input) {
                case 1:
                    showAllCDs();
                    break;
                case 2:
                    showCDsByGenre();
                    break;
                case 3:
                    showCDsByTitle();
                    break;
                case 4:
                    showCDsByTrackTitle();
                    break;
                case 5:
                    addCD();
                    break;
            }
        } while (input != 6);
    }

    private void addCD() {
        CD cd = new CD();
        addMainInfoToCD(cd);
        addGenresToCD(cd);
        addTracksToCD(cd);
        library.addCD(cd);
    }

    private void addTracksToCD(CD cd) {
        int input;
        do {
            System.out.println("1. Dodaj utwor");
            System.out.println("2. Wyjscie do glownego menu");
            input = readInt(1, 2);
            if (input == 1) {
                addOneTrackToCD(cd);
            }
        } while (input < 2);
    }

    private void addOneTrackToCD(CD cd) {
        System.out.println("Podaj tytul utworu: ");
        String title = readLine();
        System.out.println("Podaj autora: ");
        String author = readLine();
        System.out.println("Podaj dlugosc utworu: ");
        int length = readInt(1, 4800);
        System.out.println("Podaj notatki: ");
        String notes = scanner.nextLine().trim();
        Track track = new Track(author, length, title, notes);
        cd.addTrack(track);
    }

    private void addGenresToCD(CD cd) {
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i].toString());
        }
        int exit = genres.length + 1;
        int selected;
        System.out.println(exit + ". Wyjscie do glownego menu");
        do {
            selected = readInt(1, exit);
            if (selected < exit) {
                cd.addGenre(genres[selected - 1]);
            }
        } while (selected < exit);
    }

    private void addMainInfoToCD(CD cd) {
        System.out.println("Podaj wykonawce: ");
        String band = readLine();
        System.out.println("Podaj tytul: ");
        String title = readLine();
        System.out.println("Podaj wytwornie: ");
        String publisher = readLine();
        LocalDate releaseDate = readDate();
        cd.setBand(band);
        cd.setTitle(title);
        cd.setPublisher(publisher);
        cd.setReleaseDate(releaseDate);
    }

    private LocalDate readDate() {
        System.out.println("Podaj rok: ");
        int year = readInt(1950, 2050);
        return LocalDate.of(year, 1, 1);
    }

    //TODO zaimplementować
    private void showCDsByTrackTitle() {

    }

    private void showCDsByTitle() {
        System.out.println("Podaj tytul do wyszukania: ");
        String title = readLine();
        List<CD> result = library.searchByCDTitle(title);
        System.out.println("Znalezione plyty");
        showCdsFromList(result);
    }

    //TODO zaimplementować
    private void showCDsByGenre() {

    }

    private void showAllCDs() {
        System.out.println("Plyty z biblioteki");
        showCdsFromList(library.getCDs());
    }

    private void showCdsFromList(List<CD> CDs) {
        if (CDs.isEmpty()) {
            System.out.println("Brak plyt");
            return;
        }
        for (int i = 0; i < CDs.size(); i++) {
            System.out.println((i + 1) + ". " + CDs.get(i));
        }
    }

    private String readLine() {
        String line;
        do {
            line = scanner.nextLine();
        } while (line.trim().length() == 0);
        return line;
    }

    private int readInt(int min, int max) {
        boolean success;
        int choice = 0;
        System.out.print("Wpisz liczbe od " + min + " do " + max + ": ");
        do {
            success = true;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                success = false;
            }
        } while (!success || (choice < min || choice > max));
        return choice;
    }

    //TODO dodać opcje do dodawania nowego Genre
    private void showMainMenu() {
        System.out.println("============== MENU =============");
        System.out.println("1. Wyswietl liste wszystkich plyt");
        System.out.println("2. Wyswietl liste plyt po gatunku");
        System.out.println("3. Wyswietl liste plyt po tytule");
        System.out.println("4. Wyswietl liste plyt po tytule utworu");
        System.out.println("5. Dodaj plyte");
        System.out.println("6. Wyjscie z programu");
    }

    //TODO zapisać Genre do pliku tekstowego
    public static void main(String[] args) {
        App app = new App();
        app.loadLibraryFromFile();
        app.processMainMenu();
        app.saveLibraryToFile();
    }

    //TODO odczytywać z formatu JSON
    private void loadLibraryFromFile() {
        try {
            FileReader fileReader = new FileReader(FILENAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int numberOfCDs = Integer.parseInt(line);
            for (int i = 0; i < numberOfCDs; i++) {
                loadCDFromFile(bufferedReader);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            System.out.println("Nie udalo sie odczytac pliku " + FILENAME);
        }
    }

    private void loadCDFromFile(BufferedReader bufferedReader) throws IOException {
        String band = bufferedReader.readLine();
        String title = bufferedReader.readLine();
        String publisher = bufferedReader.readLine();
        String releaseDate = bufferedReader.readLine();
        CD cd = new CD(band, title, publisher, LocalDate.parse(releaseDate));
        loadGenresFromFile(bufferedReader, cd);
        loadTracksFromFile(bufferedReader, cd);
        library.addCD(cd);
    }

    private void loadTracksFromFile(BufferedReader bufferedReader, CD cd) throws IOException {
        String line = bufferedReader.readLine();
        int numberOfTracks = Integer.parseInt(line);
        for (int i = 0; i < numberOfTracks; i++) {
            String author = bufferedReader.readLine();
            int length = Integer.parseInt(bufferedReader.readLine());
            String title = bufferedReader.readLine();
            String notes = bufferedReader.readLine();
            cd.addTrack(new Track(author, length, title, notes));
        }
    }

    private void loadGenresFromFile(BufferedReader bufferedReader, CD cd) throws IOException {
        String line = bufferedReader.readLine();
        int numberOfGenres = Integer.parseInt(line);
        for (int i = 0; i < numberOfGenres; i++) {
            line = bufferedReader.readLine();
            cd.addGenre(Genre.valueOf(line));
        }
    }

    //TODO nie zapisywać do pliku na zakończenie aplikacji, jeżeli nie było zmian w danych
    //TODO w plikach tekstowych zapisywać w formacie JSON, wykorzystać np. Google Gson
    private void saveLibraryToFile() {
        try {
            File file = new File(FILENAME);
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(library.getCDs().size());
            for (CD cd : library.getCDs()) {
                saveCDToFile(printWriter, cd);
            }
            printWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nie udalo sie zapisac pliku " + FILENAME);
        }
    }

    private void saveCDToFile(PrintWriter printWriter, CD cd) {
        printWriter.println(cd.getBand());
        printWriter.println(cd.getTitle());
        printWriter.println(cd.getPublisher());
        printWriter.println(cd.getReleaseDate());
        saveGenresToFile(printWriter, cd.getGenres());
        saveTracksToFile(printWriter, cd.getTracks());
    }

    private void saveTracksToFile(PrintWriter printWriter, List<Track> tracks) {
        printWriter.println(tracks.size());
        for (Track track : tracks) {
            printWriter.println(track.getAuthor());
            printWriter.println(track.getLength());
            printWriter.println(track.getTitle());
            printWriter.println(track.getNotes());
        }
    }

    private void saveGenresToFile(PrintWriter printWriter, Set<Genre> genres) {
        printWriter.println(genres.size());
        for (Genre genre : genres) {
            printWriter.println(genre.name());
        }
    }
}
