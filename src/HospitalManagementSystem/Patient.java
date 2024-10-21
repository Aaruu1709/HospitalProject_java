//package HospitalManagementSystem;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Patient {
//    private Connection connection;
//    private Scanner scanner;
//    public Patient(Connection connection,Scanner scanner){
//        this.connection=connection;
//        this.scanner=scanner;
//    }
//    public void addPatient(){
//        System.out.println("Enter patient name:");
//        String name=scanner.next();
//        System.out.println("Enter patient age:");
//        int age=scanner.nextInt();
//        System.out.println("enter patient Gender:");
//        String gender=scanner.next();
//
//        try{
//            String query="insert into patient(name,age,gender)values(?,?,?)";
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            preparedStatement.setString(1,name);
//            preparedStatement.setInt(2,age);
//            preparedStatement.setString(3,gender);
//            int affectedRows=preparedStatement.executeUpdate();
//            if(affectedRows>0){
//                System.out.println("patient added succesfully...");
//            }else{
//                System.out.println("failed to add patient....");
//            }
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    public  void viewPatients(){
//        String query= "select * from patients";
//        try{
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            ResultSet resultSet=preparedStatement.executeQuery();
//            System.out.println("Patients: ");
//            System.out.println(" +_____________________+___________________________+_____________________+_______________________");
//            System.out.println("| Patient Id           | Name                      |Age                   |Gender                ");
//            System.out.println(" +_____________________+___________________________+_____________________+_______________________");
//            while (resultSet.next()){
//                int id=resultSet.getInt("id");
//                String name=resultSet.getString("name");
//                int age=resultSet.getInt("age");
//                String gender=resultSet.getString("gender");
//                System.out.printf("|%-22s|%-24|%-22s|%-22s|\n",id,name,age,gender);
//                System.out.println(" +_____________________+___________________________+_____________________+_______________________");
//
//            }
//
//        }catch (SQLException e){
//e.printStackTrace();
//        }
//
//    }
//
//    public boolean getPatientById(int id){
//        String query="select * from patients where id=?";
//        try{
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            preparedStatement.setInt(1,id);
//            ResultSet resultSet=preparedStatement.executeQuery();
//            if (resultSet.next()){
//                return false;
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//
//}



package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    // Method to add a new patient
    public void addPatient() {
        System.out.println("Enter patient name:");
        String name = scanner.next();
        System.out.println("Enter patient age:");
        int age = scanner.nextInt();
        System.out.println("Enter patient Gender:");
        String gender = scanner.next();

        String query = "INSERT INTO patients(name, age, gender) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Patient added successfully.");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all patients
    public void viewPatients() {
        String query = "SELECT * FROM patients";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Patients: ");
            System.out.println("+---------------------+---------------------------+---------------------+-----------------------+");
            System.out.println("| Patient Id          | Name                      | Age                 | Gender                |");
            System.out.println("+---------------------+---------------------------+---------------------+-----------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                System.out.printf("| %-21d | %-25s | %-19d | %-21s |\n", id, name, age, gender);
                System.out.println("+---------------------+---------------------------+---------------------+-----------------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check if a patient exists by ID
    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;  // Patient found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Patient not found
    }
}
