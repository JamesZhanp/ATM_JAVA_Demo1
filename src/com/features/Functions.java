package com.features;

import com.dao.CardDao;
import com.dao.CityDao;
import com.dao.UserDao;
import com.dao.WorkRecordDao;
import com.entity.CardEntity;
import com.entity.CityEntity;
import com.entity.InformationAccessDB;
import com.entity.WorkrecordEntity;
import org.junit.Test;

import javax.swing.*;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Functions extends JFrame {
    private CardDao cardDao = new CardDao();
    private WorkRecordDao workRecordDao = new WorkRecordDao();
    private CityDao cityDao = new CityDao();
    private UserDao userDao = new UserDao();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //获取公共的卡号
    String cardNum = new Load().cardNumForPublic;
    //查询得异地存取款的手续费为每笔取款的0.5%
    private final double FEE = 0.005;
    /**
     * 信息查询
     * */
    @Test
    public void InformationInquiry(){
        List resultList = cardDao.InformationAccess(cardNum);
        InformationAccessDB informationAccessDB = null;
        for (int i = 0 ; i < resultList.size() ; i++) {
            Object[] objects = (Object[]) resultList.get(i);
            String cardNumPrint = (String) objects[0];
            String userName = (String) objects[1];
            int gender = (Integer) objects[2];
            int age = (Integer) objects[3];
            String IDNum = (String) objects[4];
            String address = (String) objects[5];
            String gender1 = null;
            if (gender == 1) {
                gender1 = "男";
            } else {
                gender1 = "女";
            }
            informationAccessDB = new InformationAccessDB(cardNumPrint, userName, gender1, age, IDNum, address);
            //创建表头
            Object[] columnNames = {"卡号", "姓名", "性别", "年龄", "身份证号", "注册城市"};
//        数据
            Object[][] userInfo = {{informationAccessDB.getCardNum(),
                    informationAccessDB.getName(),
                    informationAccessDB.getGender(),
                    informationAccessDB.getAge(),
                    informationAccessDB.getIDNum(),
                    informationAccessDB.getRegisterCity()}};
            JTable table = new JTable();
            this.setLayout(new GridLayout(2,1));
            table = new JTable(userInfo, columnNames);
            //以表单的形式输出信息
            JFrame jFrame = new JFrame("本卡的数据");
            JScrollPane scrollPane = new JScrollPane(table);
            jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            jFrame.setVisible(true);
            jFrame.setBounds(600,400,800,150);
            //            当关闭窗口时选择隐藏当前的视图
            jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            当前的视图被关闭时，显示上一层的视图
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    new FirstPage().FirstPage();
                }
            });
        }
    }

    /**
     * 信息修改
     * 修改内容包括家庭住址和电话
     * 获取特定权限才可以对数据库进行操作
     * */
    @Test
    public void InformationModification(){
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JButton sureBtn = new JButton(" 确认");
        JLabel addLabel = new JLabel("家庭住址");
        JLabel telLabel = new JLabel("联系电话");
        JTextField addField = new JTextField(13);
        JTextField telField = new JTextField(13);
        this.setBounds(800,400,500,200);
        this.setLayout(new GridLayout(3,1));

        jp1.add(addLabel);
        jp1.add(addField);

        jp2.add(telLabel);
        jp2.add(telField);

        jp3.add(sureBtn);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("修改信息");
        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardEntity cardEntity = cardDao.getCardPas(cardNum);
                setVisible(false);
                String address = addField.getText().trim();
                String telNum = telField.getText().trim();
                if (address.isEmpty() || telNum.isEmpty()){
                    JOptionPane.showMessageDialog(null,"修改的内容不能为空","警告",
                            JOptionPane.ERROR_MESSAGE);
                    new Functions().InformationModification();
                }else{
//                    更新user数据表
                    userDao.updateUser(address,telNum,cardEntity.getUserId());
                    JOptionPane.showMessageDialog(null,"修改信息成功","通知",
                            JOptionPane.INFORMATION_MESSAGE);
                    new FirstPage().FirstPage();
                }
            }
        });
    }

    /**
     * 取款
     * 两个输入，取款金额和取款地址
     * 取款金额不能大于卡中的金额
     * 手续费
     * */
    @Test
    public void Withdrawal(){
        JOptionPane.showMessageDialog(null,"若取款不在注册地址，将根据取款金额收取一定的手续费","通知",
                JOptionPane.INFORMATION_MESSAGE);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JButton sureBtn = new JButton("确认");
        JLabel moneyLabel = new JLabel("取款数量");
        JLabel cityLabel= new JLabel("取款地址");
        JTextField moneyField = new JTextField(13);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("杭州");
        comboBox.addItem("上海");
        comboBox.addItem("北京");
        comboBox.addItem("武汉");
        comboBox.addItem("深圳");
        comboBox.addItem("其他");

        this.setBounds(800,400,500,200);
        this.setLayout(new GridLayout(3,1));
        jp1.add(moneyLabel);
        jp1.add(moneyField);

        jp2.add(cityLabel);
        jp2.add(comboBox);

        jp3.add(sureBtn);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("取款");

        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                返回选取的值
//                System.out.println(comboBox.getSelectedItem());
                String citySelected = String.valueOf(comboBox.getSelectedItem());
                CityEntity cityEntity = cityDao.getCity(citySelected);
                CardEntity cardEntity = cardDao.getCardPas(cardNum);
                String money = moneyField.getText().trim();
//                判空
                if (money.isEmpty()){
                    JOptionPane.showMessageDialog(null,"您尚未输入取钱的金额","警告",
                            JOptionPane.ERROR_MESSAGE);
                    new Functions().Withdrawal();
                }else {
                    double moneyNum = Double.valueOf(money);
                    int city = 0;
                    //本地取款
                    if(cityEntity.getCityId() == cardEntity.getRegisteredCity()){
                        moneyNum = moneyNum;
                        city = 1;
                    }
//                异地取款
                    else{
                        //                        手续费
                        double fee = moneyNum * FEE;
                        moneyNum += fee;
                        city = 2;
                    }
                    if (cardEntity.getBalance() < moneyNum ){
                        JOptionPane.showMessageDialog(null,"您的余额少于您所需的金额，取款失败","警告",
                                JOptionPane.ERROR_MESSAGE);
                        new Functions().Withdrawal();
                    }else{
                        cardDao.withDrwal(moneyNum,cardNum);
                        JOptionPane.showMessageDialog(null,"取款成功","通知",
                                JOptionPane.INFORMATION_MESSAGE);
//                    将操作保存到数据库当中
//                    获取存取款之后的数据
//                    获取当前的时间
                        CardEntity cardEntity1 = cardDao.getCardPas(cardNum);
                        WorkrecordEntity workrecordEntity = new WorkrecordEntity();
                        workrecordEntity.setCardNum(cardEntity1.getCardId());
                        workrecordEntity.setType("取款");
//                    余额
                        workrecordEntity.setBalance(cardEntity1.getBalance());
                        try{
                            workrecordEntity.setTime(df.parse(df.format(new Date())));
                        }catch (ParseException pe){
                            pe.getErrorOffset();
                        }

//                    交易数额
                        workrecordEntity.setMoneyNum(moneyNum);
                        workrecordEntity.setWorkCity(cityEntity.getCityId());
//                    保存到数据库当中
                        workRecordDao.insert(workrecordEntity);
                        new FirstPage().FirstPage();

                    }
                }

            }
        });



    }

    /**
     * 存款
     * */
    @Test
    public void Deposit(){
        JOptionPane.showMessageDialog(null,"若存款不在注册地址，将根据存款金额收取一定的手续费","SUCCESSFUL",
                JOptionPane.INFORMATION_MESSAGE);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JButton sureBtn = new JButton("确认");
        JLabel moneyLabel = new JLabel("存款数量");
        JLabel cityLabel= new JLabel("存款地址");
        JTextField moneyField = new JTextField(13);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("杭州");
        comboBox.addItem("上海");
        comboBox.addItem("北京");
        comboBox.addItem("武汉");
        comboBox.addItem("深圳");
        comboBox.addItem("其他");

        this.setBounds(800,400,500,200);
        this.setLayout(new GridLayout(3,1));
        jp1.add(moneyLabel);
        jp1.add(moneyField);

        jp2.add(cityLabel);
        jp2.add(comboBox);

        jp3.add(sureBtn);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("存款");

        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                String citySelected = String.valueOf(comboBox.getSelectedItem());
                CityEntity cityEntity = cityDao.getCity(citySelected);
                CardEntity cardEntity = cardDao.getCardPas(cardNum);
                String money = moneyField.getText().trim();
                if (money.isEmpty()){
                    JOptionPane.showMessageDialog(null,"您尚未输入存钱的金额","警告",
                            JOptionPane.ERROR_MESSAGE);
                    new Functions().Withdrawal();
                }else{
                    double moneyNum = Double.valueOf(money);
                    //异地取款
                    if (cityEntity.getCityId() != cardEntity.getRegisteredCity()){
                        double fee = moneyNum * FEE;
                        moneyNum -= fee;
                    }
                    cardDao.despoit(moneyNum,cardNum);
                    JOptionPane.showMessageDialog(null,"存款成功","通知",
                            JOptionPane.INFORMATION_MESSAGE);
                    CardEntity cardEntity1 = cardDao.getCardPas(cardNum);
                    WorkrecordEntity workrecordEntity = new WorkrecordEntity();
                    workrecordEntity.setCardNum(cardEntity1.getCardId());
                    workrecordEntity.setType("存款");
//                    余额
                    workrecordEntity.setBalance(cardEntity1.getBalance());
                    try{
                        workrecordEntity.setTime(df.parse(df.format(new Date())));
                    }catch (ParseException pe){
                        pe.getErrorOffset();
                    }

//                    交易数额
                    workrecordEntity.setMoneyNum(moneyNum);
                    workrecordEntity.setWorkCity(cityEntity.getCityId());
//                    保存到数据库当中
                    workRecordDao.insert(workrecordEntity);
                    new FirstPage().FirstPage();
                }

            }
        });
    }

    /**
     * 转账
     * */

    @Test
    public void Transfor(){
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        JButton sureBtn = new JButton("确认");
        JLabel giveMoneyLabel = new JLabel("汇款账号");
        JLabel getMoneyLabel = new JLabel("收款账号");
        JLabel moneyLabel = new JLabel("汇款金额");
        JLabel transforCityLabel = new JLabel("操作地址");
        JTextField giveMoneyField = new JTextField(13);
        JTextField getMoneyField = new JTextField(13);
        JTextField moneyField = new JTextField(13);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("杭州");
        comboBox.addItem("上海");
        comboBox.addItem("北京");
        comboBox.addItem("武汉");
        comboBox.addItem("深圳");
        comboBox.addItem("其他");


        this.setBounds(800,400,500,250);
        this.setLayout(new GridLayout(5,1));

        jp1.add(giveMoneyLabel);
        jp1.add(giveMoneyField);

        jp2.add(getMoneyLabel);
        jp2.add(getMoneyField);

        jp3.add(moneyLabel);
        jp3.add(moneyField);

        jp4.add(transforCityLabel);
        jp4.add(comboBox);

        jp5.add(sureBtn);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("转账");

        sureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                String giveMoneyCardNum = giveMoneyField.getText().trim();
                String getMoneyCardNum = getMoneyField.getText().trim();
                String money = moneyField.getText().trim();
//                获取操作地址
                String citySelected = String.valueOf(comboBox.getSelectedItem());
                CityEntity cityEntity = cityDao.getCity(citySelected);
//                用户输入的异常情况
//                转账数额忘记填写，账号忘记填写，填写异常的账号，转账额度大于卡内余额等
                if (money.isEmpty()){
                    JOptionPane.showMessageDialog(null,"转账数额不能为空","警告",
                            JOptionPane.ERROR_MESSAGE);
                    new Functions().Transfor();
                }else{
                    double moneyNum = Double.valueOf(money);
                    if(giveMoneyCardNum.isEmpty()||(getMoneyCardNum.isEmpty())){
                        JOptionPane.showMessageDialog(null,"账号不能为空","警告",
                                JOptionPane.ERROR_MESSAGE);
                        new Functions().Transfor();
                    }else{
                        //1.查询这两个账号是否存在，且是否被注销
                        CardEntity cardEntity = cardDao.getCardPas(giveMoneyCardNum);
                        CardEntity cardEntity1 = cardDao.getCardPas(getMoneyCardNum);
                        if(cardEntity == null || cardEntity1 == null){
                            JOptionPane.showMessageDialog(null,"请输入正确的账号","警告",
                                    JOptionPane.WARNING_MESSAGE);
                            new Functions().Transfor();
                        }else if(cardEntity.getIsUsed() == 0 || cardEntity1.getIsUsed() == 0){
                            JOptionPane.showMessageDialog(null,"请输入尚未注销的账号","警告",
                                    JOptionPane.WARNING_MESSAGE);
                            new Functions().Transfor();
                        }
                        //2.转账金额是否小于转账方的余额
                        double fee = 0.00;
                        if (cityEntity.getCityId() != cardEntity1.getRegisteredCity()){
                            fee = moneyNum * FEE;
                        }
                        if (cardEntity.getBalance() < moneyNum + fee){
                            JOptionPane.showMessageDialog(null,"转账的额度高于账户余额，转账无法完成，请重新输入金额","警告",
                                    JOptionPane.WARNING_MESSAGE);
                            new Functions().Transfor();
                        }
                        else if (cardEntity.getBalance() >= moneyNum){
                            //3.甲方减少的钱的数量应该等于乙方的数量
                            cardDao.transfor(giveMoneyCardNum,getMoneyCardNum,moneyNum,FEE);
                            JOptionPane.showMessageDialog(null,"转账成功","通知",
                                    JOptionPane.INFORMATION_MESSAGE);

//                    将本次的操作放入到数据库当中
                            CardEntity cardEntity2 = cardDao.getCardPas(cardNum);
                            WorkrecordEntity workrecordEntity = new WorkrecordEntity();
                            workrecordEntity.setCardNum(cardEntity2.getCardId());
                            workrecordEntity.setType("转账");
//                    余额
                            workrecordEntity.setBalance(cardEntity2.getBalance());
                            try{
                                workrecordEntity.setTime(df.parse(df.format(new Date())));
                            }catch (ParseException pe){
                                pe.getErrorOffset();
                            }

//                    交易数额
                            workrecordEntity.setMoneyNum(moneyNum);
                            workrecordEntity.setWorkCity(cityEntity.getCityId());
//                    保存到数据库当中
                            workRecordDao.insert(workrecordEntity);
                            //输出条目信息
                            JOptionPane.showMessageDialog(null,"您卡内的余额为 " + cardEntity1.getBalance() + ",本次操作的手续费为" + fee ,"通知",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new FirstPage().FirstPage();
                        }
                    }
                }
            }
        });
    }

    /**
     * 查询余额
     * */
    @Test
    public void CheckBalance(){
        CardEntity cardEntity = cardDao.getCardPas(cardNum);
        JOptionPane.showMessageDialog(null,"您的余额为：" + cardEntity.getBalance() + "RMB","您的余额",
                JOptionPane.INFORMATION_MESSAGE);
        new FirstPage().FirstPage();
    }

    /**
     * 修改密码
     * 两个输入框，密码和确认密码，两者相同时，将修改的密码保存到数据库当中
     * 反之，则提示用户再次输入
     * */
    @Test
    public void changePas(){
        JLabel pasLabel,confirmPasLabel;
        JPasswordField pasField,confirmPasField;
        JButton confirmBtn;
        JPanel jp1,jp2,jp3;
        pasField = new JPasswordField(13);
        confirmPasField = new JPasswordField(13);
        pasLabel = new JLabel("修改密码");
        confirmPasLabel = new JLabel("确认密码");
        confirmBtn = new JButton("修改密码");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        this.setLayout(new GridLayout(3,1));
        jp1.add(pasLabel);
        jp1.add(pasField);

        jp2.add(confirmPasLabel);
        jp2.add(confirmPasField);

        jp3.add(confirmBtn);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setBounds(800,400,400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("修改密码");

        //按钮添加单击事件
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(pasField.getPassword());
                String confirmPas = String.valueOf(confirmPasField.getPassword());
                setVisible(false);
                if (password.isEmpty() || confirmPas.isEmpty()){
                    JOptionPane.showMessageDialog(null,"修改的密码不能为空","ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }else{
                    //                如果两次输入的密码一样，则修改成功
                    if (password.equals(confirmPas)){
                        cardDao.updatePas(password,cardNum);
                        CardEntity cardEntity = cardDao.getCardPas(cardNum);
                        if (cardEntity.getPassword().equals(password)){
                            JOptionPane.showMessageDialog(null,"修改密码成功","SUCCESSFUL",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        new FirstPage().FirstPage();
                    }
                    //修改失败
                    else{
                        JOptionPane.showMessageDialog(null,"您两次输入的密码不同，请重新输入","ERROR_MESSAGE",
                                JOptionPane.WARNING_MESSAGE);
//                    重新加载
                        new Functions().changePas();
                    }
                }
            }
        });

    }

    /**
     * 查询历史交易记录
     * */
    @Test
    public void TransforReconds(){
        CardEntity cardEntity = cardDao.getCardPas(cardNum);
        //获取所有的交易的记录
        List<WorkrecordEntity> workrecordEntityList = workRecordDao.getAllRecords(cardEntity.getCardId());
        int num = workrecordEntityList.size();
        Object[] columnNames = {"卡号","操作类型","时间","金额","余额","城市"};
        Object[][] columnValues = new Object[num][7];
        for (int i = 0 ; i < workrecordEntityList.size() ; i++){
//            将类列表当中的数据保存到二维数组当中
            CityEntity cityEntity = cityDao.getCityName(workrecordEntityList.get(i).getWorkCity());
            CardEntity cardEntity1 = cardDao.getCardMessage(workrecordEntityList.get(i).getCardNum());
            Object[] value = {cardEntity1.getCardNum(),
                    workrecordEntityList.get(i).getType(),
                    workrecordEntityList.get(i).getTime(),
                    workrecordEntityList.get(i).getMoneyNum(),
                    workrecordEntityList.get(i).getBalance(),
                    cityEntity.getCityName()};
            columnValues[i] = value;
        }
        JTable jTable = new JTable();
        jTable = new JTable(columnValues,columnNames);

        //以表单的形式输出信息
        JFrame jFrame = new JFrame("历史操作数据");
        JScrollPane scrollPane = new JScrollPane(jTable);
        jFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
         jFrame.setVisible(true);
        jFrame.setBounds(600,400,800,150);
        //            当关闭窗口时选择隐藏当前的视图
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            当前的视图被关闭时，显示上一层的视图
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                new FirstPage().FirstPage();
            }
        });

    }
    /**
     * 注销用户
     * */
    @Test
    public void LogOut(){
        cardDao.logOut(cardNum);
        CardEntity cardEntity = cardDao.getCardPas(cardNum);
        if (cardEntity.getIsUsed() == 0){
            JOptionPane.showMessageDialog(null,"注销用户成功","SUCCESSFUL",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        new FirstPage().FirstPage();
    }

//    当操作结束时，返回上层操作
}
