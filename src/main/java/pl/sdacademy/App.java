package pl.sdacademy;

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
            input = readInput(1, 6);
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

    private int readInput(int min, int max) {
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
        System.out.println("6. Wyjdz z programu");
    }

    public static void main(String[] args) {
        App app = new App();
        app.processMainMenu();
    }
}
