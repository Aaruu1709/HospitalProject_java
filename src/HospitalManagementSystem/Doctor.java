//package HospitalManagementSystem;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Doctor {
//    private Connection connection;
//
//    public Doctor(Connection connection){
//        this.connection=connection;
//
//    }
//
//
//    public  void viewDoctors(){
//        String query= "select * from doctors";
//        try{
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            ResultSet resultSet=preparedStatement.executeQuery();
//            System.out.println("Doctors: ");
//            System.out.println(" +_____________________+___________________________+________________________");
//            System.out.println("| Doctor Id           | Name                         |Specialization      | ");
//            System.out.println(" +_____________________+___________________________+_____________________");
//            while (resultSet.next()){
//                int id=resultSet.getInt("id");
//                String name=resultSet.getString("name");
//                String specialization=resultSet.getString("specialization");
//                System.out.printf("|%-22s|%-24|%-22s|\n",id,name,specialization);
//                System.out.println(" +_____________________+___________________________+_____________________");
//
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public boolean getDoctorById(int id){
//        String query="select * from doctors where id=?";
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
//}




package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Display table header
            System.out.println("Doctors: ");
            System.out.println("+---------------------+---------------------------+---------------------------+");
            System.out.println("| Doctor Id           | Name                      | Specialization             |");
            System.out.println("+---------------------+---------------------------+---------------------------+");

            // Fetch and display each doctor record
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                // Correctly formatted printf statement for displaying doctor details
                System.out.printf("| %-20d | %-25s | %-25s |\n", id, name, specialization);
                System.out.println("+---------------------+---------------------------+---------------------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true; // Record found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // No record found
    }
}
