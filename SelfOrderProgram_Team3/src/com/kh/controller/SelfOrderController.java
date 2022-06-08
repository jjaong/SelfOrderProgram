package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.SelfOrderService;
import com.kh.model.vo.CafeProduct;
import com.kh.model.vo.CafeUser;
import com.kh.model.vo.Cart;
import com.kh.view.SelfOrderView;

public class SelfOrderController {

	// 상품 전체 조회
	public void selectAll(){
		ArrayList<CafeProduct> list = new SelfOrderService().selectAll();
		
		if(list.isEmpty()) {
			new SelfOrderView().displayFail("상품이 없습니다.");
		} else {
			new SelfOrderView().displayList(list);
		}
	}
	
	// 상품아이디로 상품 1개 조회
	public CafeProduct selectProduct(int productId) {
		//select문
		
		CafeProduct cp = new SelfOrderService().selectProduct(productId);
		
		/*
		if(cp == null) {
			new SelfOrderView().displayFail("해당 상품이 없습니다.");
		} else {
			new SelfOrderView().displayDetail(cp);
		}
		*/
		
		return cp;
	}
	
	// 상품 검색
	public void searchProduct(String keyword) {
			
			ArrayList<CafeProduct> list = new SelfOrderService().searchProduct(keyword);
			
			if(list.isEmpty()) {
				new SelfOrderView().displayNodata("해당 상품이 없습니다");
			}else {
				new SelfOrderView().displayList(list);
			}
			
			
		}
	
	// 상품 추가
	public void insertProduct(String productName, String detail, int price, String tempType) {
		
		CafeProduct cp = new CafeProduct(productName, detail, price, tempType);
//		c.setProductName(productName);
//        c.setDetail(detail);
//        c.setPrice(price);
//        c.setTempType(temptype);
		int result = new SelfOrderService().insertProduct(cp);
		
		if(result > 0) {
			new SelfOrderView().displaySuccess("상품 추가 성공");
		} else {
			new SelfOrderView().displayFail("상품 추가 실패");
		}
		
	}
	
	// 상품 수정
	public void updateProduct(int productId, String detail, int price, String temptype) {
        CafeProduct cp = new CafeProduct();
        
        cp.setProductId(productId);
        cp.setDetail(detail);
        cp.setPrice(price);
        cp.setTempType(temptype);
        
        int result = new SelfOrderService().updateProduct(cp);
        
        if(result > 0) {
            new SelfOrderView().displaySuccess("변경 성공입니다.");
        }else {
            new SelfOrderView().displayFail("변경 실패입니다.");
        }
        
    }

	// 상품 삭제
	public void deleteProduct(int productId) {
		
		int result = new SelfOrderService().deleteProduct(productId);
		
		if(result > 0) {
			new SelfOrderView().displaySuccess("상품 삭제 완료");
		} else {
			new SelfOrderView().displayFail("상품 삭제 실패");
		}
	}

	// 유저아이디로 유저 조회
	public CafeUser selectUser(String userId) {
		
		CafeUser user = new SelfOrderService().selectUser(userId);
		
		return user;
	}

	public ArrayList<Cart> showOrder() {

		ArrayList<Cart> list = new SelfOrderService().showOrder();
		
		return list;
	}

	


}
