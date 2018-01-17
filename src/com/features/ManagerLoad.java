package com.features;

import com.dao.ManagerDao;
import com.entity.ManagerEntity;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerLoad extends JFrame {
    private ManagerDao managerDao = new ManagerDao();
//    public static String cardNumForPublic = null;
    JTextField managerField;
    JPasswordField passwordField;
    JLabel managerLabel,pasLabel;
    JPanel jp1,jp2,jp3;
    JButton loadBtn;
    //    登录函数
    @Test
    public void managerLoad(){
        managerField = new JTextField(13);
        passwordField = new JPasswordField(13);
        managerLabel = new JLabel("员工号");
        pasLabel = new JLabel("密码");
        loadBtn = new JButton("登录");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

//        设置布局
        this.setBounds(800,400,500,200);
        this.setLayout(new GridLayout(3,1));
        jp1.add(managerLabel);
        jp1.add(managerField);

        jp2.add(pasLabel);
        jp2.add(passwordField);

        jp3.add(loadBtn);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登录");
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                String managerID = managerField.getText().trim();
                String pas = String.valueOf(passwordField.getPassword());
                if (managerID.isEmpty() || pas.isEmpty()){
                    JOptionPane.showMessageDialog(null,"工号和密码不能为空","ERROR_MESSAGE",
                            JOptionPane.WARNING_MESSAGE);
                    new ManagerLoad().managerLoad();
                }else{
                    ManagerEntity managerEntity = managerDao.getManager(managerID);
                    if (managerEntity == null)
                    {
                        JOptionPane.showMessageDialog(null,"该工号不存在","ERROR_MESSAGE",
                                JOptionPane.WARNING_MESSAGE);
                    } else if (!managerEntity.getPassword().equals(pas)){
                        JOptionPane.showMessageDialog(null,"密码错误","ERROR_MESSAGE",
                                JOptionPane.WARNING_MESSAGE);
                    }else if (managerEntity.getPassword().equals(pas)){
                        new ManagerWork().managerWork();
                    }
                }
            }
        });
    }
}