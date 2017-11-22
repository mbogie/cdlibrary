package pl.sdacademy;

import java.time.LocalDate;
import java.util.Scanner;

public class App {

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

    private void showCDsByTrackTitle() {

    }

    private void showCDsByTitle() {

    }

    private void showCDsByGenre() {

    }

    private void showAllCDs() {
        if (library.getCDs().isEmpty()) {
            System.out.println("Biblioteka nie zawiera zadnych plyt");
            return;
        }
        for (CD cd : library.getCDs()) {
            System.out.println(cd.toString());
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

    private void showMainMenu() {
        System.out.println("1. Wyswietl liste wszystkich plyt");
        System.out.println("2. Wyswietl liste plyt po gatunku");
        System.out.println("3. Wyswietl liste plyt po tytule");
        System.out.println("4. Wyswietl liste plyt po tytule utworu");
        System.out.println("5. Dodaj plyte");
        System.out.println("6. Wyjscie z programu");
    }

    public static void main(String[] args) {
        App app = new App();
        app.processMainMenu();
    }
}
