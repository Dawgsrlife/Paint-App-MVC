package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Point> points=new ArrayList<Point>();
        private ArrayList<Integer> lineBreaks=new ArrayList<>();
        private ArrayList<Circle> circles=new ArrayList<Circle>();
        private ArrayList<Rectangle> rectangels=new ArrayList<>();


        public void addPoint(Point p){
                this.points.add(p);
                this.setChanged();
                this.notifyObservers();
        }

        public ArrayList<Point> getPoints(){
                return points;
        }

        public void addLineBreak() {
                this.lineBreaks.add(points.size()-1);
        }

        public ArrayList<Integer> getLineBreaks(){
                return lineBreaks;
        }

        public void addCircle(Circle c){
                this.circles.add(c);
                this.setChanged();
                this.notifyObservers();
        }
        public ArrayList<Circle> getCircles(){
                return circles;
        }

        /**
         * Add a Rectangle into model list
         * @param r a Rectangle instance
         */
        public void addRectangle(Rectangle r){
                this.rectangels.add(r);
                this.setChanged();
                this.notifyObservers();
        }

        /**
         * Get all added Rectangle instances
         * @return an ArrayList of added Rectangle instances
         */
        public ArrayList<Rectangle> getRectangles(){
                return rectangels;
        }
}
