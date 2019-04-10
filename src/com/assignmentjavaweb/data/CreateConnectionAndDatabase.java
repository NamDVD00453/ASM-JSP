package com.assignmentjavaweb.data;

import com.assignmentjavaweb.security.Security;
import java.sql.Connection;
import java.util.Date;

public class CreateConnectionAndDatabase {
    public static void main(String[] args) {
        // Khởi tạo class Security và DataConnectionHelper
//        Security security = new Security();
        DataConnectionHelper dch = new DataConnectionHelper();

        // Mở connection
        Connection connection = dch.getConnecttion();

        // ghi connection vào log
        System.out.println(new Date() + " - LOG : " + connection);
//        security.writeLog(connection + "");
    }
}
