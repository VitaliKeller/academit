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

    // геттеры и сеттеры
    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    // оверрайд интерфейса
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

    // тустринг, сравнение, хэшкод
    @Override
    public String toString() {
        return "Треугольник с вершинами (" + x1 + ", " + y1 + "; " + x2 + ", " + y2 + "; " + x3 + ", " + y3 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;       // явное приведение
        return triangle.x1 == x1 && triangle.y1 == y1 && triangle.x2 == x2 && triangle.y2 == y2 && triangle.x3 == x3 && triangle.y3 == y3;
    }
}
