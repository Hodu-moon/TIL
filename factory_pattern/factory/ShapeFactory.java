package factory;

import shape.Shape;
import shape.impl.Circle;
import shape.impl.Rectangle;
import shape.impl.Square;

import java.util.Objects;

public class ShapeFactory {

    public static Shape getShape(String shape){

        if(Objects.isNull(shape)){
            return null;
        } else if (shape.equals("circle")) {
            return new Circle();
        } else if (shape.equals("rectangle")) {
            return new Rectangle();
        } else if (shape.equals("square")) {
            return new Square();
        }

        return null;
    }
}
