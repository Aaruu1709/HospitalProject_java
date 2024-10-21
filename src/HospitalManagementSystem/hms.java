package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class hms {
    private static final String url="jdbc:mysql://127.0.0.1:3306/hospitalmgnt_oops";
    private static final String username="root";
    private  static  final  String password="root";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner=new Scanner(System.in);
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Patient patient=new Patient(connection,scanner);
            Doctor doctor=new Doctor(connection);
            while (true){
                System.out.println("__________HOSPITAL MGNT SYSTEM__________");
                System.out.println("1.Add patient");
                System.out.println("2.view patient");
                System.out.println("3.view doctors");
                System.out.println("4.Book Appointment");
                System.out.println("5.Exit");
                System.out.println("entr yor choice:");
                int choice=scanner.nextInt();
                switch (choice){
                    case 1:
                        //add patient
                        patient.addPatient();
                        System.out.println();
                    case 2:
                        //view patient
                        patient.viewPatients();
                        System.out.println();

                    case 3:
                        //view doctor
                        doctor.viewDoctors();
                        System.out.println();

                    case 4:
                        //book appointment
                        bookAppointment(patient,doctor,connection,scanner);
                        System.out.println();


                    case 5:
                        return;
                    default:
                        System.out.println("Enter valid choice:");
                }
            }
            }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor,Connection connection,Scanner scanner){
        System.out.println("Enter patient id:");
        int patientid=scanner.nextInt();
        System.out.println("Enter doctor id");
        int doctorid=scanner.nextInt();
        System.out.println("enter appointment date(YYYY-MM-DD):");
        String appointmentDate=scanner.next();
        if(patient.getPatientById(patientid)&&doctor.getDoctorById(doctorid)) {
            if (checkDoctorAvailability(doctorid,appointmentDate,connection)){
                String appointmentQuery="insert into appointments(patient_id,doctor_id,appointment_date)values(?,?,?)";
                try{
                    PreparedStatement preparedStatement=connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1,patientid);
                    preparedStatement.setInt(2,doctorid);
                    preparedStatement.setString(3,appointmentDate);
                    int rowsAffected=preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment booked successfully..");

                    }else{
                        System.out.println("failed to book Appointment");
                    }
                }catch (SQLException e){
                    e.printStackTrace();

                }
            }else{
                System.out.println("doctor not available on this date");
            }
        }
        else{
            System.out.println("Either doctor or patient does'nt exists.");

        }
    }

    public static boolean checkDoctorAvailability (int doctorid,String appointmentDate, Connection connection){
        String query="select count(*) from appointments where doctor_id=? and appointment_date=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorid);
            preparedStatement.setString(2,appointmentDate);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                int count=resultSet.getInt(1);
                if (count==0){
                    return true;

                }else{
                    return false;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
