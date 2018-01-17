package com.features;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPage extends JFrame{
    @Test
//    登录成功之后的功能页面
    public void FirstPage() {
//        构建简单的选择界面
        this.setTitle("欢迎来到JamesZhan的自主平台");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,400,600,300);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        this.setLayout(new GridLayout(4,1));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton jRadioButton1 = new JRadioButton("查询",true);
        JRadioButton jRadioButton2 = new JRadioButton("修改信息");
        JRadioButton jRadioButton3 = new JRadioButton("取款");
        JRadioButton jRadioButton4 = new JRadioButton("存款");
        JRadioButton jRadioButton5 = new JRadioButton("转账");
        JRadioButton jRadioButton6 = new JRadioButton("查询余额");
        JRadioButton jRadioButton7 = new JRadioButton("修改密码");
        JRadioButton jRadioButton8 = new JRadioButton("查询历史交易记录");
        JRadioButton jRadioButton9 = new JRadioButton("注销账户");
        JButton sureBtn = new JButton("确定");
//        将radiobutton加入到buttongroup当中，是的每次只能选择一个
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);
        buttonGroup.add(jRadioButton4);
        buttonGroup.add(jRadioButton5);
        buttonGroup.add(jRadioButton6);
        buttonGroup.add(jRadioButton7);
        buttonGroup.add(jRadioButton8);
        buttonGroup.add(jRadioButton9);

        jp1.add(jRadioButton1);
        jp1.add(jRadioButton2);
        jp1.add(jRadioButton3);
        jp2.add(jRadioButton4);
        jp2.add(jRadioButton5);
        jp2.add(jRadioButton6);
        jp3.add(jRadioButton7);
        jp3.add(jRadioButton8);
        jp3.add(jRadioButton9);
        jp4.add(sureBtn);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.setVisible(true);

//        按钮单击事件
        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                关闭初始页面
                setVisible(false);
//                查询
                if(jRadioButton1.isSelected()){
                    new Functions().InformationInquiry();
                }
//                修改信息
                if(jRadioButton2.isSelected()){
                    new Functions().InformationModification();
                }
//                取款
                if(jRadioButton3.isSelected()){
                    new Functions().Withdrawal();
                }
//                存款
                if(jRadioButton4.isSelected()){
                    new Functions().Deposit();
                }
//                转账
                if (jRadioButton5.isSelected()){
                    new Functions().Transfor();
                }
//                查询余额
                if (jRadioButton6.isSelected()){
                    new Functions().CheckBalance();
                }

//                修改密码
                if (jRadioButton7.isSelected()){
                    new Functions().changePas();
                }
//                查询历史交易记录
                if(jRadioButton8.isSelected()){
                    new Functions().TransforReconds();
                }
//                注销
                if(jRadioButton9.isSelected()){
                    new Functions().LogOut();
                }
                setVisible(false);
            }
        });
       }
}
