import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

/**
 * Abstract class representing a shape.
 */
abstract class CShape {
    private static int shapeCount = 0; 
    private int id;

    public CShape() {
        this.id = ++shapeCount;
    }

    /**
     * Abstract method to get the dimensions of the shape.
     *
     * @return String representation of the shape's dimensions.
     */
    abstract String getDimensions();

    /**
     * Gets the ID of the shape.
     *
     * @return The unique ID of the shape.
     */
    public int getId() {
        return id;
    }
}

class COval extends CShape {
    private int horizontalRadius;
    private int verticalRadius;

    /**
     * Constructs a COval object with specified dimensions.
     *
     * @param horizontalRadius 
     * @param verticalRadius   
     */
    public COval(int horizontalRadius, int verticalRadius) {
        super();
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    /**
     * Gets the dimensions of the oval.
     *
     * @return String representation of the oval's dimensions.
     */
    @Override
    String getDimensions() {
        return "OVAL " + horizontalRadius + "x" + verticalRadius;
    }
}

class CCircle extends COval {
    /**
     * Constructs a CCircle object with a specified radius.
     *
     * @param radius The radius of the circle.
     */
    public CCircle(int radius) {
        super(radius, radius);
    }

    /**
     * Gets the dimensions of the circle.
     *
     * @return String representation of the circle's dimensions.
     */
    @Override
    String getDimensions() {
        return "CIRCLE " + super.getDimensions().split(" ")[1];
    }
}

class CRectangle extends CShape {
    private int length;
    private int width;

    /**
     * Constructs a CRectangle object with specified dimensions.
     *
     * @param length The length of the rectangle.
     * @param width  The width of the rectangle.
     */
    public CRectangle(int length, int width) {
        super();
        this.length = length;
        this.width = width;
    }

    /**
     * Gets the dimensions of the rectangle.
     *
     * @return String representation of the rectangle's dimensions.
     */
    @Override
    String getDimensions() {
        return "RECTANGLE " + length + "x" + width;
    }
}

class CSquare extends CRectangle {
    /**
     * Constructs a CSquare object with a specified side length.
     *
     * @param sideLength The side length of the square.
     */
    public CSquare(int sideLength) {
        super(sideLength, sideLength);
    }

    /**
     * Gets the dimensions of the square.
     *
     * @return String representation of the square's dimensions.
     */
    @Override
    String getDimensions() {
        return "SQUARE " + super.getDimensions().split(" ")[1];
    }
}

class CCanvas {
    private List<CShape> shapes;

    /**
     * Constructs a CCanvas object with a list of shapes.
     *
     * @param shapes The list of shapes to be added to the canvas.
     */
    public CCanvas(List<CShape> shapes) {
        // Ensure no duplicate shapes
        Set<String> shapeDimensions = new HashSet<>();
        this.shapes = new ArrayList<>();

        for (CShape shape : shapes) {
            if (shapeDimensions.add(shape.getDimensions())) {
                this.shapes.add(shape);
            }
        }
    }

    /**
     * Gets the list of shapes on the canvas.
     *
     * @return The list of shapes on the canvas.
     */
    public List<CShape> getShapes() {
        return shapes;
    }
}

public class Main {
    public static void main(String[] args) {
        List<CShape> shapes = generateRandomShapes();

        System.out.println("Canvas has the following random shapes:\n");

        for (CShape shape : shapes) {
            System.out.println("Shape " + shape.getId() + ": " + shape.getDimensions());
        }
    }

    /**
     * Generates a list of 10 random shapes.
     *
     * @return List of 10 random shapes.
     */
    private static List<CShape> generateRandomShapes() {
        List<CShape> shapes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            shapes.add(ShapeFactory.generateRandomShape());
        }

        return shapes;
    }
}

class ShapeFactory {
    /**
     * Generates a random shape with valid dimensions.
     *
     * @return A randomly generated shape.
     */
    public static CShape generateRandomShape() {
        Random random = new Random();
        int shapeType = random.nextInt(4); // 0: Oval, 1: Circle, 2: Rectangle, 3: Square

        switch (shapeType) {
            case 0:
                int ovalHorizontalRadius = random.nextInt(100) + 1;
                int ovalVerticalRadius = random.nextInt(100) + 1;
                return new COval(ovalHorizontalRadius, ovalVerticalRadius);
            case 1:
                int circleRadius = random.nextInt(100) + 1;
                return new CCircle(circleRadius);
            case 2:
                int rectangleLength = random.nextInt(100) + 1;
                int rectangleWidth = random.nextInt(100) + 1;
                return new CRectangle(rectangleLength, rectangleWidth);
            case 3:
                int squareSideLength = random.nextInt(100) + 1;
                return new CSquare(squareSideLength);
            default:
                // Handle default case or throw an exception
                return null;
        }
    }
}
