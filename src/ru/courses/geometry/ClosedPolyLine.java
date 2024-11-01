package ru.courses.geometry;

public class ClosedPolyLine extends PolyLine {
    public ClosedPolyLine(Point... points) {
        super(points);
        // Дополнительная логика для замыкания ломаной, если требуется
        if (points.length > 0 && points[0] != points[points.length - 1]) {
            // Если начальная и конечная точки не совпадают, замкнем ломаную
            Point[] closedPoints = new Point[points.length + 1];
            System.arraycopy(points, 0, closedPoints, 0, points.length);
            closedPoints[closedPoints.length - 1] = points[0];
            this.points = closedPoints;
        }
    }

    // Другие методы, если они требуются
}
