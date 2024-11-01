package ru.courses.geometry;

public class PolyLine {
     Point[] points;

    // Конструктор
    public PolyLine(Point... points) {
        this.points = points;
    }

    // Геттер для points
    public Point[] getPoints() {
        return points;
    }

    // Остальные методы класса, например метод для вычисления длины
}
