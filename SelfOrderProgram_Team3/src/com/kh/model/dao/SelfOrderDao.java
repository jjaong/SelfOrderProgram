package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.CafeProduct;
import com.kh.model.vo.CafeUser;
import com.kh.model.vo.Cart;

public class SelfOrderDao {
	
//	private Properties prop = new Properties();
//	
//	public SelfOrderDao() {
//		try {
//			prop.loadFromXML(new FileInputStream("resources/query.xml"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
	
	// 상품 전체 조회
	public ArrayList<CafeProduct> selectAll(Connection conn){
		
		ArrayList<CafeProduct> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM CAFE_PRODUCT ORDER BY PRODUCT_ID ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new CafeProduct(rset.getInt("PRODUCT_ID")
						                 ,rset.getString("PRODUCT_NAME")
						                 ,rset.getString("DETAIL")
						                 ,rset.getInt("PRICE")
						                 ,rset.getString("TEMPTYPE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}

	// 상품id에 해당하는 상품 1개 조회
	public CafeProduct selectProduct(Connection conn, int productId) {
		
		CafeProduct cp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM CAFE_PRODUCT WHERE PRODUCT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cp = new CafeProduct();
				cp.setProductId(rset.getInt("PRODUCT_ID")); 
				cp.setProductName(rset.getString("PRODUCT_NAME")); 
				cp.setDetail(rset.getString("DETAIL")); 
				cp.setPrice(rset.getInt("PRICE"));
				cp.setTempType(rset.getString("TEMPTYPE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cp;
	}
	
	// 키워드로 상품 조회
	public ArrayList<CafeProduct> searchProduct(Connection conn, String keyword){
		
		ArrayList<CafeProduct> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM CAFE_PRODUCT WHERE PRODUCT_NAME LIKE '%'||?||'%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new CafeProduct(rset.getInt("PRODUCT_ID")
						,rset.getString("PRODUCT_NAME")
						,rset.getString("DETAIL")
						,rset.getInt("PRICE")
						,rset.getString("TEMPTYPE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	// 상품 추가
	public int insertProduct(Connection conn, CafeProduct cp) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO CAFE_PRODUCT VALUES(SEQ_CAFE_PROD.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  cp.getProductName());
			pstmt.setString(2, cp.getDetail());
			pstmt.setInt(3, cp.getPrice());
			pstmt.setString(4, cp.getTempType());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	// 상품 정보 수정
	public int updateProduct(Connection conn, CafeProduct cp) {
		
		int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = "UPDATE CAFE_PRODUCT SET DETAIL = ?, PRICE = ?, TEMPTYPE =? WHERE PRODUCT_ID = ?";
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, cp.getDetail());
            pstmt.setInt(2, cp.getPrice());
            pstmt.setString(3, cp.getTempType());
            pstmt.setInt(4, cp.getProductId());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return result;
	   }

	// 상품 삭제
	public int deleteProduct(Connection conn, int productId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		//String sql = prop.getProperty("deleteProduct");
		String sql = "DELETE FROM CAFE_PRODUCT WHERE PRODUCT_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 유저아이디에 해당하는 유저 조회
	public CafeUser selectUser(Connection conn, String userId) {
		CafeUser user = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM CAFE_USER WHERE USER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				user = new CafeUser(rset.getString("USER_ID")
								   ,rset.getString("USER_PWD")
								   ,rset.getString("USER_NAME")
								   ,rset.getString("PHONE")
								   ,rset.getDate("JOIN_DATE")
								   ,rset.getString("USER_TYPE")
								   ,rset.getInt("WALLET")
								   );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return user;
	}

	public ArrayList<Cart> showOrder(Connection conn) {
		
		ArrayList<Cart> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT TEMPTYPE, CART_ID, U.USER_NAME, C.USER_ID, C.PRODUCT_ID, AMOUNT, ORDER_YN , D.PRODUCT_NAME "
				+ "FROM CART C ,CAFE_USER U ,CAFE_PRODUCT D "
				+ "WHERE C.USER_ID = U.USER_ID "
				+ "AND D.PRODUCT_ID = C.PRODUCT_ID "
				+ "AND ORDER_YN = 'Y' "
				+ "ORDER BY CART_ID";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				   String tempTypeDetail = rset.getString("TEMPTYPE");
				   if(tempTypeDetail.equals("C")) tempTypeDetail = "(COLD)";
				   else if(tempTypeDetail.equals("H")) tempTypeDetail = "(HOT)";
				               
				   list.add(new Cart(rset.getInt("CART_ID")
				            ,rset.getString("USER_NAME") +"(" + rset.getString("USER_ID") + ")"
				            ,rset.getInt("PRODUCT_ID")
				            ,rset.getInt("AMOUNT")
				            ,rset.getString("ORDER_YN")
				            ,rset.getString("PRODUCT_NAME") + tempTypeDetail));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	
}
