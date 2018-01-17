package com.features;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//当用户打开本APP的时候，对于用户的身份进行判断，管理员？，用户？
public class SelectorRole extends JFrame {
    @Test
    public void selectRole(){
        this.setTitle("请选择您的身份");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,400,600,150);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        this.setLayout(new GridLayout(2,1));

        ButtonGroup buttonGroup = new ButtonGroup();
        JLabel label = new JLabel("您的身份：");
        JRadioButton jRadioButton1 = new JRadioButton("用户",true);
        JRadioButton jRadioButton2 = new JRadioButton("管理员");

        JButton sureBtn = new JButton("确定");
//        将radiobutton加入到buttongroup当中，是的每次只能选择一个
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);

        jp1.add(label);
        jp1.add(jRadioButton1);
        jp1.add(jRadioButton2);

        jp2.add(sureBtn);
        this.add(jp1);
        this.add(jp2);
        this.setVisible(true);

        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //登录的为用户
                if (jRadioButton1.isSelected()){
                    new Load().Load();
                }
                //登录的为管理员
                else if (jRadioButton2.isSelected()){
                    new ManagerLoad().managerLoad();
                }
            }
        });
    }

    public static void main(String[] args) {
        new SelectorRole().selectRole();
    }
}
