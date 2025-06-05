package com.example.shop.dao;

import com.example.shop.dto.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal; // BigDecimal import

@Repository
public class ProductDao {
    private Connection connection; // 데이터베이스 연결 객체

    // 생성자: 데이터베이스 연결을 주입받거나 내부에서 생성
    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    // 새 상품 추가
    public boolean addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (product_name, description, price, stock_quantity, " +
                "category_id, brand_id, main_image_url, is_active, average_rating, review_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            if (product.getCategoryId() != null) {
                pstmt.setInt(5, product.getCategoryId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            if (product.getBrandId() != null) {
                pstmt.setInt(6, product.getBrandId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.setString(7, product.getMainImageUrl());
            pstmt.setBoolean(8, product.isActive());
            pstmt.setBigDecimal(9, product.getAverageRating());
            pstmt.setInt(10, product.getReviewCount());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        product.setProductId(rs.getInt(1)); // 생성된 ID를 객체에 설정
                    }
                }
                return true;
            }
            return false;
        }
    }

    // 상품 ID로 상품 정보 조회
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT product_id, product_name, description, price, stock_quantity, " +
                "category_id, brand_id, main_image_url, created_at, updated_at, is_active, " +
                "average_rating, review_count FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProduct(rs);
                }
            }
        }
        return null; // 상품 없음
    }

    // 모든 상품 조회
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT product_id, product_name, description, price, stock_quantity, " +
                "category_id, brand_id, main_image_url, created_at, updated_at, is_active, " +
                "average_rating, review_count FROM product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        }
        return products;
    }

    // 상품 정보 업데이트
    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET product_name = ?, description = ?, price = ?, " +
                "stock_quantity = ?, category_id = ?, brand_id = ?, main_image_url = ?, " +
                "is_active = ?, average_rating = ?, review_count = ? WHERE product_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getDescription());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            if (product.getCategoryId() != null) {
                pstmt.setInt(5, product.getCategoryId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            if (product.getBrandId() != null) {
                pstmt.setInt(6, product.getBrandId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.setString(7, product.getMainImageUrl());
            pstmt.setBoolean(8, product.isActive());
            pstmt.setBigDecimal(9, product.getAverageRating());
            pstmt.setInt(10, product.getReviewCount());
            pstmt.setInt(11, product.getProductId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // 상품 삭제
    public boolean deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // ResultSet에서 Product 객체로 매핑하는 헬퍼 메서드
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setStockQuantity(rs.getInt("stock_quantity"));

        // FK가 NULL일 수 있으므로 getObject 또는 isNull 체크
        int categoryId = rs.getInt("category_id");
        if (rs.wasNull()) {
            product.setCategoryId(null);
        } else {
            product.setCategoryId(categoryId);
        }

        int brandId = rs.getInt("brand_id");
        if (rs.wasNull()) {
            product.setBrandId(null);
        } else {
            product.setBrandId(brandId);
        }

        product.setMainImageUrl(rs.getString("main_image_url"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        product.setUpdatedAt(rs.getTimestamp("updated_at"));
        product.setActive(rs.getBoolean("is_active"));
        product.setAverageRating(rs.getBigDecimal("average_rating"));
        product.setReviewCount(rs.getInt("review_count"));
        return product;
    }

    // 데이터베이스 연결 닫기 (필요 시)
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
