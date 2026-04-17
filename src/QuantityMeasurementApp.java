public class QuantityMeasurementApp {

    // Feet class
    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double toInches() {
            return this.value * 12;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Inch class
    static class Inch {
        private final double value;

        public Inch(double value) {
            this.value = value;
        }

        public double toInches() {
            return this.value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inch other = (Inch) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Static method for Feet equality
    public static boolean compareFeet(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    // Static method for Inch equality
    public static boolean compareInch(double v1, double v2) {
        Inch i1 = new Inch(v1);
        Inch i2 = new Inch(v2);
        return i1.equals(i2);
    }

    // Cross comparison using conversion
    public static boolean compareFeetAndInch(double feet, double inch) {
        Feet f = new Feet(feet);
        Inch i = new Inch(inch);

        return Double.compare(f.toInches(), i.toInches()) == 0;
    }

    public static void main(String[] args) {

        // Feet vs Feet
        System.out.println("Feet Equal: " + compareFeet(1.0, 1.0));

        // Inch vs Inch
        System.out.println("Inch Equal: " + compareInch(12.0, 12.0));

        // Feet vs Inch (conversion)
        System.out.println("Feet-Inch Equal: " + compareFeetAndInch(1.0, 12.0));
    }
}