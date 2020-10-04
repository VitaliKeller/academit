package shape_classes;

public class Triangle implements Shape {
    private double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }


    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        return Math.abs(0.5 * ((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3))); //http://www.pm298.ru/reshenie/delen.php
    }

    private double sideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getPerimeter() {
        double side1 = sideLength(x1, y1, x2, y2);
        double side2 = sideLength(x2, y2, x3, y3);
        double side3 = sideLength(x1, y1, x3, y3);

        return side1 + side2 + side3;   //http://www.bolshoyvopros.ru/questions/2995540-perimetr-geometricheskoj-figury-po-koordinatam-vershin-kak-najti.html
    }

    public String toString() {
        return "Треугольник с вершинами (" + x1 + ", " + y1 + "; " + x2 + ", " + y2 + "; " + x3 + ", " + y3 + ")";
    }
}
