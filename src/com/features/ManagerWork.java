package com.features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//管理员工作
public class ManagerWork extends JFrame{
    public void managerWork(){
        //        构建简单的选择界面
        this.setTitle("选择您要的操作");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,400,600,300);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        this.setLayout(new GridLayout(4,1));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton jRadioButton1 = new JRadioButton("增加用户",true);
        JRadioButton jRadioButton2 = new JRadioButton("增加银行卡");
        JRadioButton jRadioButton3 = new JRadioButton("修改信息");

        JButton sureBtn = new JButton("确定");
//        将radiobutton加入到buttongroup当中，是的每次只能选择一个
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);


        jp1.add(jRadioButton1);
        jp3.add(jRadioButton3);
        jp2.add(jRadioButton2);
        jp4.add(sureBtn);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.setVisible(true);

        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //增加用户
                if (jRadioButton1.isSelected()){
                    new ManagerFunctions().addUser();
                }
                //增加银行卡
                if (jRadioButton2.isSelected()){
                    new ManagerFunctions().addBankCard();
                }
                //修改信息
                if (jRadioButton3.isSelected()){
                    new ManagerFunctions().changeUserMes();
                }
            }
        });
    }
}
