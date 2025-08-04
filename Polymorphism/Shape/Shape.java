package Polymorphism.Shape;

abstract class Shape {

        protected String color;
        protected boolean filled;

        public Shape(){
            color = null;
            filled = false;
        }
        public Shape(String color, boolean filled){
                this.color = color;
                this.filled = filled;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public boolean isFilled() {
            return filled;
        }
        public void setFilled(boolean filled) {
            this.filled = filled;
        }

        abstract double getArea();
        abstract double getPerimeter();
        public String toString(){
            if (filled) 
                return String.format("A Shape with color of %s and filled.", color); 
            else
                return String.format("A Shape with color of %s and not filled.", color);
            
        }
    
}
