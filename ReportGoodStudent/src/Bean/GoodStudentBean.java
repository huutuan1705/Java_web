/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author huutuan
 */
public class GoodStudentBean extends StudentBean{
    private int sumOfCredits;
    private float mediumScore;
    private String semester;

    public GoodStudentBean() {
    }

    public GoodStudentBean(int sumOfCredits, float mediumScore, String semester) {
        this.sumOfCredits = sumOfCredits;
        this.mediumScore = mediumScore;
        this.semester = semester;
    }

    public GoodStudentBean(int sumOfCredits, float mediumScore, String semester, String id, String fullname, String academicYear) {
        super(id, fullname, academicYear);
        this.sumOfCredits = sumOfCredits;
        this.mediumScore = mediumScore;
        this.semester = semester;
    }

    public int getSumOfCredits() {
        return sumOfCredits;
    }

    public void setSumOfCredits(int sumOfCredits) {
        this.sumOfCredits = sumOfCredits;
    }

    public float getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(float mediumScore) {
        this.mediumScore = mediumScore;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName= QLDKTC", 
                    "sa", "12345");
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<GoodStudentBean> getAllGoodStudent(){
        StudentBean stuB = new StudentBean();
        RegistrationBean regB = new RegistrationBean();
        ResultBean resB = new ResultBean();
        
        List<GoodStudentBean> listGoodStudent = new ArrayList<>();
        
        List<StudentBean> listStuB = stuB.getAllStudent();
        for(StudentBean studentBean : listStuB){
            List<RegistrationBean> listRegis = regB.getAllResByStudentID(studentBean.getId());
            
            String semester = "";
            int sumOfCredits = 0;
            float mediumScore = 0;
            
            for(RegistrationBean registrationBean : listRegis){
                semester = registrationBean.getSemester().getName();
                
                int credit = registrationBean.getClassBean().getSubjectBean().getCredits();
                sumOfCredits += credit;
                
                float rate1 = registrationBean.getClassBean().getSubjectBean().getRateScoreBean().getPoint1();
                float rate2 = registrationBean.getClassBean().getSubjectBean().getRateScoreBean().getPoint2();
                float rate3 = registrationBean.getClassBean().getSubjectBean().getRateScoreBean().getPoint3();
                float rateFinalScore = registrationBean.getClassBean().getSubjectBean().getRateScoreBean().getFinalScore();
                
                ResultBean resultBean = resB.getResultByResGisID(registrationBean.getId());
                float point1 = resultBean.getPoint1();
                float point2 = resultBean.getPoint2();
                float point3 = resultBean.getPoint3();
                float finalScore = resultBean.getFinalScore();
                
                float result = point1*rate1 + point2*rate2 + point3*rate3 + finalScore*rateFinalScore;
                mediumScore += result*credit;
            }
            
            mediumScore /= sumOfCredits;
            
            if(mediumScore >= 8){
                listGoodStudent.add(new GoodStudentBean(sumOfCredits, mediumScore, semester, studentBean.getId(), 
                    studentBean.getFullname(), studentBean.getAcademicYear()));
            }
            
            listGoodStudent.sort(new Comparator<GoodStudentBean>() {
                @Override
                public int compare(GoodStudentBean o1, GoodStudentBean o2) {
                    if(o1.getMediumScore()<o2.getMediumScore()){
                        return 1;
                    }
                    return -1;
                }
            });
        }        
        return listGoodStudent;
    }
    
    public Object[] toObjects(){
        return new Object[]{
            super.getId(), super.getFullname(), super.getAcademicYear() ,semester, sumOfCredits,
            String.format("%.1f", mediumScore)
        };
    }
    
    public static void main(String[] args) {
        StudentBean stuB = new StudentBean();
        GoodStudentBean gStuB = new GoodStudentBean();
        
        List<GoodStudentBean> listGood = new ArrayList<>();
        List<StudentBean> list = stuB.getAllStudent();
        for(StudentBean studentBean : list){
            listGood.add(new GoodStudentBean(9,8,"2020",studentBean.getId(),
                    studentBean.getFullname(),studentBean.getAcademicYear()));
        }
        System.out.println(listGood.get(0).getFullname());
    }
}
