package pl.sdacademy;

import java.util.Scanner;

public class App {

    private Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    public void processMainMenu() {
        showMainMenu();
        int input = readInput(1, 6);


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
