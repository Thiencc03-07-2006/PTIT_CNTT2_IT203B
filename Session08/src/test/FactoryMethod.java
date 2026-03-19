package test;

public class FactoryMethod {
    public static Shape create(String type) {
        switch (type.toLowerCase()) {
            case "circle": {
                return new Circle();
            }
            case "triangle": {
                return new Triangle();
            }
            default: {
                throw new RuntimeException("Khong tao hinh thanh cong");
            }
        }
    }

    public static void main(String[] args) {
        Shape circle = create("test.Circle");
        circle.draw();
    }
}

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("roll");
    }
}

class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("slap");
    }
}