package com.features;

import com.dao.CardDao;
import com.dao.CityDao;
import com.dao.UserDao;
import com.entity.CardEntity;
import com.entity.CityEntity;
import com.entity.UserEntity;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFunctions extends JFrame {

    private UserDao userDao = new UserDao();
    private CardDao cardDao = new CardDao();
    private CityDao cityDao = new CityDao();
    /**
     * 添加用户
     */
    @Test
    public void addUser(){
        this.setTitle("增加用户");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,400,600,500);
        JLabel nameLabel,genderLabel,ageLabel,addrLabel,telLabel,IDLabel;
        JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
        JTextField nameField,ageField,addrField,telField,IDField;
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton jRadioButton1 = new JRadioButton("男",true);
        JRadioButton jRadioButton2 = new JRadioButton("女");
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        JButton jButton = new JButton("确定");

        nameLabel = new JLabel("姓名");
        genderLabel = new JLabel("性别");
        ageLabel = new JLabel("年龄");
        addrLabel = new JLabel("地址");
        telLabel = new JLabel("电话号码");
        IDLabel = new JLabel("身份证号");

        nameField = new JTextField(13);
        ageField = new JTextField(13);
        addrField = new JTextField(13);
        telField = new JTextField(13);
        IDField = new JTextField(13);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        this.setLayout(new GridLayout(7,1));

        jp1.add(nameLabel);
        jp1.add(nameField);
        jp2.add(genderLabel);
        jp2.add(jRadioButton1);
        jp2.add(jRadioButton2);
        jp3.add(ageLabel);
        jp3.add(ageField);
        jp4.add(addrLabel);
        jp4.add(addrField);
        jp5.add(telLabel);
        jp5.add(telField);
        jp6.add(IDLabel);
        jp6.add(IDField);
        jp7.add(jButton);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp6);
        this.add(jp7);
        this.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                int gender = 2;
                String name = nameField.getText().trim();
                if (jRadioButton1.isSelected()){
                    gender = 1;
                }else if (jRadioButton2.isSelected()){
                    gender = 0;
                }
                String age = ageField.getText().trim();
                String address = addrField.getText().trim();
                String telNum = telField.getText().trim();
                String IDNum = IDField.getText().trim();
                if (name.isEmpty() || age.isEmpty() || address.isEmpty() || telNum.isEmpty() || IDNum.isEmpty()){
                    JOptionPane.showMessageDialog(null,"输入的信息不能为空","ERROR_MESSAGE",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    //判断用户输入的数字是否为整数
                    char ageChar[] = age.toCharArray();
                    boolean flag = true;
                    for (int i = 0 ; i < ageChar.length ; i++){
                        if (ageChar[i] == '.'){
                            flag = false;
                            break;
                        }
                    }
                    if (!flag){
                        JOptionPane.showMessageDialog(null,"输入的年龄为正整数","ERROR_MESSAGE",
                                JOptionPane.ERROR_MESSAGE);
                        new ManagerFunctions().addUser();
                    }else{
                        int ageNum = Integer.valueOf(age);
                        UserEntity userEntity = new UserEntity();
                        userEntity.setUserName(name);
                        userEntity.setGender(gender);
                        userEntity.setAge(ageNum);
                        userEntity.setAddress(address);
                        userEntity.setTelNum(telNum);
                        userEntity.setIdNum(IDNum);

                        userDao.insert(userEntity);
                        JOptionPane.showMessageDialog(null,"添加用户成功","MESSAGE",
                                JOptionPane.INFORMATION_MESSAGE);
                        new ManagerWork().managerWork();
                    }

                }

            }
        });
    }

    /**
     * 添加银行卡
     */
    @Test
    public void addBankCard(){
        this.setTitle("增加银行卡");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,400,600,350);
        JLabel cardNumLabel,passwordLabel,registeredCity,userLabel,moneyLabel;
        JPanel jp1,jp2,jp3,jp4,jp5,jp6;
        JTextField cardNumField,userField,moneyField;
        JPasswordField passwordField;
        JButton jButton = new JButton("确定");

        cardNumLabel = new JLabel("卡号");
        passwordLabel = new JLabel("密码");
        userLabel = new JLabel("持卡人");
        registeredCity = new JLabel("注册城市");
        moneyLabel = new JLabel("预存金额");

        cardNumField = new JTextField(13);
        passwordField = new JPasswordField(13);
        userField = new JTextField(13);
        moneyField = new JTextField(13);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("杭州");
        comboBox.addItem("上海");
        comboBox.addItem("北京");
        comboBox.addItem("武汉");
        comboBox.addItem("深圳");
        comboBox.addItem("其他");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        this.setLayout(new GridLayout(6,1));

        jp1.add(cardNumLabel);
        jp1.add(cardNumField);
        jp2.add(passwordLabel);
        jp2.add(passwordField);
        jp3.add(userLabel);
        jp3.add(userField);
        jp4.add(registeredCity);
        jp4.add(comboBox);
        jp5.add(jButton);
        jp6.add(moneyLabel);
        jp6.add(moneyField);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp6);
        this.add(jp4);
        this.add(jp5);
        this.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                String cardNum = cardNumField.getText().trim();
                String password = String.valueOf(passwordField.getPassword());
                String money = moneyField.getText().trim();
                String registeredCity = String.valueOf(comboBox.getSelectedItem());
                String userName = userField.getText().trim();
                if (cardNum.isEmpty() || password.isEmpty() || userName.isEmpty()){
                    JOptionPane.showMessageDialog(null,"输入的信息不能为空","ERROR_MESSAGE",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    CityEntity cityEntity = cityDao.getCity(registeredCity);
                    CardEntity cardEntity = new CardEntity();
                    UserEntity userEntity = userDao.getUser(userName);
                    if (userEntity == null){
                        JOptionPane.showMessageDialog(null,"该用户不存在，请先添加用户","ERROR_MESSAGE",
                                JOptionPane.WARNING_MESSAGE);
                    }else{
                        cardEntity.setBalance(Double.valueOf(money));
                        cardEntity.setCardNum(cardNum);
                        cardEntity.setRegisteredCity(cityEntity.getCityId());
                        cardEntity.setIsUsed(1);
                        cardEntity.setPassword(password);
                        cardEntity.setUserId(userEntity.getUserId());
                        cardDao.insert(cardEntity);
                        JOptionPane.showMessageDialog(null,"添加银行卡成功","SUCCESS",
                                JOptionPane.INFORMATION_MESSAGE);
                        new ManagerWork().managerWork();
                    }
                }
            }
        });
    }

    /**
     * 修改客户信息
     */
    @Test
    public void changeUserMes(){
        this.setTitle("您要修改的内容");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(800,300,600,600);
        JLabel nameLabel,ageLabel,addrLabel,telLabel,IDLabel;
        JPanel jp1,jp2,jp3,jp4,jp5,jp6;
        JTextField nameField,ageField,addrField,telField,IDField;
        JButton jButton = new JButton("确定");

        nameLabel = new JLabel("姓名");
        ageLabel = new JLabel("年龄");
        addrLabel = new JLabel("地址");
        telLabel = new JLabel("电话号码");
        IDLabel = new JLabel("身份证号");

        nameField = new JTextField(13);
        ageField = new JTextField(13);
        addrField = new JTextField(13);
        telField = new JTextField(13);
        IDField = new JTextField(13);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        this.setLayout(new GridLayout(6,1));

        jp1.add(ageLabel);
        jp1.add(ageField);
        jp2.add(addrLabel);
        jp2.add(addrField);
        jp3.add(telLabel);
        jp3.add(telField);
        jp4.add(IDLabel);
        jp4.add(IDField);
        jp5.add(jButton);
        jp6.add(nameLabel);
        jp6.add(nameField);

        this.add(jp6);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                String name = nameField.getText().trim();
                String age = ageField.getText().trim();
                String address = addrField.getText().trim();
                String telphoneNum = telField.getText().trim();
                String IDNum = IDField.getText().trim();
                if (name.isEmpty() || age.isEmpty() || address.isEmpty() || telphoneNum.isEmpty() || IDNum.isEmpty()){
                    JOptionPane.showMessageDialog(null,"请填入修改的信息","ERROR_MESSAGE",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    //判断用户输入的数字是否为整数
                    char ageChar[] = age.toCharArray();
                    boolean flag = true;
                    for (int i = 0 ; i < ageChar.length ; i++){
                        if (ageChar[i] == '.'){
                            flag = false;
                            break;
                        }
                    }
                    if (!flag){
                        JOptionPane.showMessageDialog(null,"输入的年龄为正整数","ERROR_MESSAGE",
                                JOptionPane.ERROR_MESSAGE);
                        new ManagerFunctions().changeUserMes();
                    }else{
                        UserEntity userEntity = userDao.getUser(name);
                        if (userEntity == null){
                            JOptionPane.showMessageDialog(null,"该用户不存在","ERROR_MESSAGE",
                                    JOptionPane.ERROR_MESSAGE);
                        }else{
                            int ageNum = Integer.valueOf(age);
                            userDao.managerUpdateMes(name,ageNum,address,telphoneNum,IDNum);
                            JOptionPane.showMessageDialog(null,"修改信息成功","SUCCESS",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new ManagerWork().managerWork();
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new ManagerFunctions().changeUserMes();
    }
}
