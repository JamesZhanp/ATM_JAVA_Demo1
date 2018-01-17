package com.features;

import com.dao.CardDao;
import com.entity.CardEntity;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Load extends JFrame {
    private CardDao cardDao = new CardDao();
    public static String cardNumForPublic = null;
    JTextField cardNumField;
    JPasswordField passwordField;
    JLabel cardNumLabel,pasLabel;
    JPanel jp1,jp2,jp3;
    JButton loadBtn;
//    登录函数
    @Test
    public void Load(){
        cardNumField = new JTextField(13);
        passwordField = new JPasswordField(13);
        cardNumLabel = new JLabel("账号");
        pasLabel = new JLabel("密码");
        loadBtn = new JButton("登录");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

//        设置布局
        this.setBounds(800,400,500,200);
        this.setLayout(new GridLayout(3,1));
        jp1.add(cardNumLabel);
        jp1.add(cardNumField);

        jp2.add(pasLabel);
        jp2.add(passwordField);

        jp3.add(loadBtn);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登录");

//        登录按钮单击事件
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取输入的内容
                String cardNum = cardNumField.getText().trim();
                //将卡号赋值
                cardNumForPublic = cardNum;
                String password = String.valueOf(passwordField.getPassword());
                setVisible(false);
                //输入为空
                if(cardNum.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null,"卡号和密码不能为空","ERROR_MESSAGE",
                            JOptionPane.WARNING_MESSAGE);
                    new Load().Load();
                }else{
                    CardEntity cardEntity = cardDao.getCardPas(cardNum);
//                    输入错误的卡号
                    if (cardEntity == null){
                        JOptionPane.showMessageDialog(null,"您输入的卡号存在问题，请重新输入","ERROR_MESSAGE",
                                JOptionPane.WARNING_MESSAGE);
                        new Load().Load();
                    }else{
//                        输入的密码错误
                        if(password.equals(cardEntity.getPassword())){
                            new FirstPage().FirstPage();
                        }else{
                            JOptionPane.showMessageDialog(null,"密码错误，请重新输入","ERROR_MESSAGE",
                                    JOptionPane.WARNING_MESSAGE);
                            new Load().Load();
                        }
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        new Load().Load();
    }
}
