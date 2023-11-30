/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.DAO;
import Model.Customer;
import Model.Ticket;
import Model.TrainRide;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huutuan
 */
public class LoginForm extends javax.swing.JFrame {
    private DAO d = new DAO();    
    private List<Ticket> listTK;
    /**
     * Creates new form LoginForm
     */
    private DefaultTableModel tmCus, tmTrain;
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(this);
        
        setLoginBtn(true);
        eventLogin();
    }
    
    private void setLoginBtn(boolean b){
        loginBtn.setEnabled(b);
    }
    
    private void eventLogin(){
        loginBtn.addActionListener(e -> {
            String username = userTxt.getText();
            String password = passTxt.getText();
            Customer c = d.getCustomer(username, password);
            if(c != null){
                BookingTicket b = new BookingTicket();
                CustomerInformation(b);
                BookingTicket(b);
                CancelBooking(b);
                ViewDetailTicket(b);
                b.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");
                userTxt.requestFocus();
            }
        });        
    }
    
    private void CustomerInformation(BookingTicket b){        
        String username = userTxt.getText();
        String password = passTxt.getText();
        Customer c = d.getCustomer(username, password);
        
        b.fullnameTxt.setText(c.getName());
        b.idCusTxt.setText(c.getId());
        b.phoneTxt.setText(c.getPhoneNumber());
        b.addressTxt.setText(c.getAddress());
        
        tmCus = (DefaultTableModel) b.tbCus.getModel();
        
        List<Ticket> listTK = d.getAllTicketInBranchOfCus(c.getId(), "BD");
        
        tmCus.setRowCount(0);
        for(Ticket i : listTK){
            tmCus.addRow(i.toObjects());
        }
    }
    
    private void BookingTicket(BookingTicket b){
        String username = userTxt.getText();
        String password = passTxt.getText();
        Customer c = d.getCustomer(username, password);
        listTK = d.getAllTicketOfBranch("BD");
               
        b.bookingBtn.addActionListener(e -> {
            String id = "TKBD" + String.format("%03d", listTK.size()+1);
            double ticketPrice, discount;
            if(b.seatCB.getSelectedItem().toString().equals("hạng 1")){
                ticketPrice = 8;
                discount = 0.02;
            }else{
                ticketPrice = 5;
                discount = 0.01;
            }
            int row = b.tbTrain.getSelectedRow();
            String idTrainRide = b.listTR.get(row).getId();
            String idCustomer = c.getId();
            String idEmployee = b.employeeCB.getSelectedItem().toString();
            String seatType = b.seatCB.getSelectedItem().toString();
            
            Ticket t = new Ticket(id, ticketPrice, discount, idTrainRide, idCustomer, idEmployee, seatType);
            listTK.add(t);
            d.insertTicket(t);
            List<Ticket> listTK2 = d.getAllTicketInBranchOfCus(c.getId(), "BD");
        
            tmCus.setRowCount(0);
            for(Ticket i : listTK2){
                tmCus.addRow(i.toObjects());
            }
            JOptionPane.showMessageDialog(this, "Tạo vé thành công!!");
        });
    }
    
    private void CancelBooking(BookingTicket b){
        b.cancelBtn.addActionListener(e -> {
            String username = userTxt.getText();
            String password = passTxt.getText();
            Customer c = d.getCustomer(username, password);
            List<Ticket> listTK = d.getAllTicketInBranchOfCus(c.getId(), "BD");
            
            int row = b.tbCus.getSelectedRow();
            d.CancelTicket(listTK.get(row).getId());
            listTK.remove(row);

        
            tmCus.setRowCount(0);
            for(Ticket i : listTK){
                tmCus.addRow(i.toObjects());
            }
            JOptionPane.showMessageDialog(this, "Hủy đặt vé thành công!!");
        });
    }
    
    private void ViewDetailTicket(BookingTicket b){
        b.viewDetailBtn.addActionListener(e -> {
            String username = userTxt.getText();
            String password = passTxt.getText();
            Customer c = d.getCustomer(username, password);
            List<Ticket> listTK = d.getAllTicketInBranchOfCus(c.getId(), "BD");
            
            int row = b.tbCus.getSelectedRow();
            
            String idTicket = listTK.get(row).getId();
            String idTrainRide = listTK.get(row).getIdTrainRide();
            String departureStation = d.getTrainRideById(idTrainRide).getDepatureStation();
            String destination = d.getTrainRideById(idTrainRide).getDestination();
            String depatureTime = d.getTrainRideById(idTrainRide).getDepartureTime();
            String cusName = c.getName();
            String emName = d.getEmployeeById(listTK.get(row).getIdEmployee()).getFullname();
            double price = listTK.get(row).getTicketPrice();
            
            DetailTicket detail = new DetailTicket();
            
            detail.idTicketTxt.setText(idTicket);
            detail.idTrainRideTxt.setText(idTrainRide);
            detail.departureStationTxt.setText(departureStation);
            detail.destinationTxt.setText(destination);
            detail.departureTimeTxt.setText(depatureTime);
            detail.cusNameTxt.setText(cusName);
            detail.emNameTxt.setText(emName);
            detail.priceTxt.setText(price + "");
            
            detail.setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        passTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        jLabel2.setText("Password");

        userTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTxtActionPerformed(evt);
            }
        });

        loginBtn.setText("Login");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Login Form");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(passTxt)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(loginBtn)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTxtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField passTxt;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}
