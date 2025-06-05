package com.example.shop.dao;

import com.example.shop.dto.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private Connection connection; // 데이터베이스 연결 객체

    // 생성자: 데이터베이스 연결을 주입받거나 내부에서 생성
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    // 새 사용자 추가 (회원가입)
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (username, password_hash, email, full_name, phone_number, " +
                "address, postal_code, is_active, user_role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getPostalCode());
            pstmt.setBoolean(8, user.isActive());
            pstmt.setString(9, user.getUserRole());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // AUTO_INCREMENT로 생성된 user_id를 User 객체에 다시 설정
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setUserId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    // 사용자 ID로 사용자 정보 조회
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT user_id, username, password_hash, email, full_name, phone_number, " +
                "address, postal_code, created_at, updated_at, last_login_at, is_active, user_role " +
                "FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null; // 사용자 없음
    }

    // 사용자 이름으로 사용자 정보 조회 (로그인 시 활용)
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT user_id, username, password_hash, email, full_name, phone_number, " +
                "address, postal_code, created_at, updated_at, last_login_at, is_active, user_role " +
                "FROM user WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        }
        return null; // 사용자 없음
    }

    // 사용자 정보 업데이트
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET username = ?, password_hash = ?, email = ?, full_name = ?, " +
                "phone_number = ?, address = ?, postal_code = ?, is_active = ?, user_role = ?, " +
                "last_login_at = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getPostalCode());
            pstmt.setBoolean(8, user.isActive());
            pstmt.setString(9, user.getUserRole());
            pstmt.setTimestamp(10, user.getLastLoginAt()); // last_login_at도 업데이트 가능
            pstmt.setInt(11, user.getUserId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // 사용자 삭제
    public boolean deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // 모든 사용자 조회
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, email, full_name, phone_number, " +
                "address, postal_code, created_at, updated_at, last_login_at, is_active, user_role " +
                "FROM user";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    // ResultSet에서 User 객체로 매핑하는 헬퍼 메서드
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setEmail(rs.getString("email"));
        user.setFullName(rs.getString("full_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setAddress(rs.getString("address"));
        user.setPostalCode(rs.getString("postal_code"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        user.setLastLoginAt(rs.getTimestamp("last_login_at"));
        user.setActive(rs.getBoolean("is_active"));
        user.setUserRole(rs.getString("user_role"));
        return user;
    }

    // 데이터베이스 연결 닫기 (필요 시)
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
