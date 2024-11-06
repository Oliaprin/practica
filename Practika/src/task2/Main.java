package task2;

public class Main {
    public static void main(String[] args) {
        Interval interval1 = new Interval(1, 5, true, false);
        Interval interval2 = new Interval(4, 7, true, true);


        Interval sum = interval1.add(interval2);
        Interval difference = interval1.subtract(interval2);
        Interval product = interval1.multiply(2);
        Interval quotient = interval1.divide(2);


        Interval intersection = interval1.intersection(interval2);
        Interval union = interval1.union(interval2);


        Interval[] intervals = {interval1, interval2};
        double distance = Interval.distanceBetween(intervals);

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + difference);
        System.out.println("Произведение: " + product);
        System.out.println("Частное: " + quotient);
        System.out.println("Пересечение: " + intersection);
        System.out.println("Объединение: " + union);
        System.out.println("Расстояние между крайними значениями: " + distance);
    }
}
