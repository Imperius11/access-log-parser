package ru.courses.main;

import ru.courses.geometry.Point;
import ru.courses.geometry.PolyLine;

public class MainApp {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        PolyLine polyline = new PolyLine(p1, p2, p3);

        // Геттер для доступа к points
        Point[] points = polyline.getPoints();
        for (Point point : points) {
            System.out.println("Point: (" + point.getX() + ", " + point.getY() + ")");
        }
    }
}
