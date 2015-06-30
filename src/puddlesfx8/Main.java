package puddlesfx8;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author hakim
 */
public class Main extends Application {
    
    Canvas canvas = new Canvas(400, 100);
    static int[] bars = new int[40];

    private void drawAll(GraphicsContext gc) {
        initCanvas(gc);
        drawRactangles(gc, (ArrayList<ArrayList<Integer>>) Puddles.calculatesPuddles(bars));
        drawBars(gc, bars);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("use the mouse to change the bars height");
        Group root = new Group();
        Random randomNumbers = new Random();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < bars.length; i++) {
            bars[i] = randomNumbers.nextInt(10);
        }
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            int y = (int) e.getX() / 10;
            if (y < bars.length) {
                bars[y] = 10 - (int) e.getY() / 10;
                drawAll(gc);
            }
        });
        root.getChildren().add(canvas);
        drawAll(gc);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initCanvas(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawBars(GraphicsContext gc, int[] heights) {
        for (int i = 0; i < heights.length; i++) {
            gc.setFill(Color.WHITE);
            gc.fillRect(i * 10, 100 - 10 * heights[i], 10, 10 * heights[i]);
            gc.setStroke(Color.RED);
            gc.strokeRect(i * 10, 100 - 10 * heights[i], 10, 10 * heights[i]);
        }
    }

    private void drawRactangles(GraphicsContext gc, ArrayList<ArrayList<Integer>> rects) {
        gc.setFill(Color.BLUE);
        rects.stream().forEach((rect) -> {
            gc.fillRect(rect.get(0) * 10, 100 - 10 * rect.get(2), 10 * (rect.get(1) - rect.get(0) + 1), 10 * rect.get(2));
        });
    }    
}
