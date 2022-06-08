package com.kh.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.SelfOrderDao;
import com.kh.model.vo.CafeProduct;
import com.kh.model.vo.CafeUser;
import com.kh.model.vo.Cart;

public class SelfOrderService {

	// 상품 전체 조회
	public ArrayList<CafeProduct> selectAll(){
		
		Connection conn = getConnection();
		
		ArrayList<CafeProduct> list = new SelfOrderDao().selectAll(conn);
		
		close(conn);
		
		return list;
	}
	
	// 해당 상품 1개에 대한 상세 정보 조회
	public CafeProduct selectProduct(int productId) {
		Connection conn = getConnection();
		
		CafeProduct cp = new SelfOrderDao().selectProduct(conn, productId);
		
		close(conn);
		
		return cp;
	}
	
	// 키워드로 상품 조회
	public ArrayList<CafeProduct> searchProduct(String keyword){
		Connection conn = getConnection();
		
		ArrayList<CafeProduct> list = new SelfOrderDao().searchProduct(conn, keyword);
		
		close(conn);
		
		return list;
	}
	
	// 상품 추가
	public int insertProduct(CafeProduct cp) {
		Connection conn = getConnection();
		
		int result = new SelfOrderDao().insertProduct(conn, cp);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 상품 정보 수정
	public int updateProduct(CafeProduct c) {
	    Connection conn = getConnection();
	        
	    int result = new SelfOrderDao().updateProduct(conn, c);
	        
	    if(result > 0) {
	        commit(conn);
	    }else {
	        rollback(conn);
	    }
	    
	    close(conn);
	    
	    return result;
	}

	// 상품 삭제
	public int deleteProduct(int productId) {
		Connection conn = getConnection();
		
		int result = new SelfOrderDao().deleteProduct(conn, productId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 유저아이디에 해당하는 유저 조회
	public CafeUser selectUser(String userId) {
		Connection conn = getConnection();
		
		CafeUser user = new SelfOrderDao().selectUser(conn, userId);
		
		close(conn);
		
		return user;
	}

	public ArrayList<Cart> showOrder() {
		
		Connection conn = getConnection();
		
		ArrayList<Cart> list = new SelfOrderDao().showOrder(conn);
		
		close(conn);

		return list;
	}

	
}
