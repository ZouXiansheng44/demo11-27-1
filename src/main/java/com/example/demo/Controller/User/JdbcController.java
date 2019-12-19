package com.example.demo.Controller.User;

import com.example.demo.dataobject.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/jdbc/test")
@Slf4j
public class JdbcController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/userlist")
    public String getUserList(ModelMap map){
        String sql = "SELECT * FROM user_info";
        List<UserInfo> userList = jdbcTemplate.query(sql, new RowMapper<UserInfo>() {
            UserInfo user = null;
            @Override
            public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new UserInfo();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setUserEmail(rs.getString("Email"));
                user.setUserPhone(rs.getString("Phone"));
                return user;
            }});
        for(UserInfo user:userList){
            System.out.println(user.getUserName());
        }
        map.addAttribute("users", userList);
        return "user";
    }
}
