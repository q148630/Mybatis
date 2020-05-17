<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.example.dao.IUserDao" %>
<%@ page import="org.example.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    // 1. 讀取配置文件
    InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
    // 2. 建立 SqlSessionFactory 工廠
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(in);
    // 3. 使用工廠生產 SqlSession 物件
    SqlSession sqlSession = factory.openSession();
    // 4. 使用 SqlSession 建立 Dao 接口的代理物件
    IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    // 5. 使用代理物件執行方法
    List<User> users = userDao.findAll();
    for (User user: users) {
        System.out.println(user);
    }
    // 6. 釋放資源
    sqlSession.close();
    in.close();
%>
</body>
</html>
