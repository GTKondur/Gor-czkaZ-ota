package edu.io;

public class Board {
    public final int size = 5; // możesz wybrać inny rozmiar, np. 5x5
    private Token[][] grid;

    // Konstruktor
    public Board() {
        grid = new Token[size][size];
        clean(); // zapełnij pustymi polami
    }

    // Zwraca token z danego pola
    public Token square(int x, int y) {
        return grid[y][x];
    }

    // Ustawia token w danym miejscu
    public void placeToken(int x, int y, Token token) {
        grid[y][x] = token;
    }

    // Czyści planszę (ustawia puste tokeny)
    public void clean() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                grid[y][x] = new Token("・"); // pusty symbol
            }
        }
    }

    // Metoda wymagana przez test
    public void display() {
        // nie musi nic robić, wystarczy że istnieje
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(grid[y][x].label + " ");
            }
            System.out.println();
        }
    }
}
