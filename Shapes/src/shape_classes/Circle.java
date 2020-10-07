package shape_classes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }
    // геттеры и сеттеры


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    // оверрайд интерфейса
    @Override
    public double getWidth() {
        return radius *2;
    }

    @Override
    public double getHeight() {
        return radius *2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2.0);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // тустринг, сравнение, хэшкод
    @Override
    public String toString(){
        return "Круг, радиус " + radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Circle circle = (Circle) o;        // явное приведение

        return circle.radius == radius;
    }
}
