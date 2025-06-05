package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp; // SQL TIMESTAMP 타입 매핑을 위해 필요

@Setter
@Getter

public class User {
    private int userId;
    private String username;
    private String passwordHash; // 실제 비밀번호가 아닌 해시 값을 저장
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp lastLoginAt;
    private boolean isActive;
    private String userRole; // ENUM 타입은 String으로 매핑 (혹은 Enum 타입 정의)

    // 기본 생성자
    public User() {
    }

    // 모든 필드를 포함하는 생성자 (비밀번호 해시는 별도 처리 권장)
    public User(int userId, String username, String passwordHash, String email, String fullName,
                String phoneNumber, String address, String postalCode, Timestamp createdAt,
                Timestamp updatedAt, Timestamp lastLoginAt, boolean isActive, String userRole) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginAt = lastLoginAt;
        this.isActive = isActive;
        this.userRole = userRole;
    }

    // --- Getter와 Setter 메소드 ---
    // 각 필드에 대한 getter와 setter를 여기에 작성합니다.
    // 예시:

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Timestamp lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isActive=" + isActive +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
