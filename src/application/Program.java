package application;

import model.entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String test = br.readLine();

            while (test != null) {
                String[] vet = test.split(",");
                list.add(new Employee(vet[0], vet[1], Double.parseDouble(vet[2])));
                test = br.readLine();
            }

            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
            list.stream()
                    .filter(x -> x.getSalary() > salary)
                    .sorted(Comparator.comparing(Employee::getEmail))
                    .map(Employee::getEmail)
                    .forEach(System.out::println);

            double sum = list.stream()
                    .filter(x -> x.getName().startsWith("M"))
                    .map(Employee::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
