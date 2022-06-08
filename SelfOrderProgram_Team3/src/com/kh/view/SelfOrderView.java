package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.SelfOrderController;
import com.kh.model.vo.CafeProduct;
import com.kh.model.vo.CafeUser;
import com.kh.model.vo.Cart;

public class SelfOrderView {
	
	private Scanner sc = new Scanner(System.in);
	private SelfOrderController soc = new SelfOrderController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("================ 어서오세요 ================");
			System.out.println("==============열심히했삼 메뉴판==============");
			selectAll(); // 상품 전체 조회
			System.out.println("========================================");
			System.out.println("1. 음료 정보 조회");
			System.out.println("2. 음료 검색");
			System.out.println("3. 음료 장바구니 추가");
			System.out.println("4. 장바구니 조회");
			System.out.println("5. 지갑 충전");
			System.out.println("6. 회원가입");
			System.out.println("----------------");
			System.out.println("10. 직원 전용"); // 음료추가, 음료수정, 음료삭제
			System.out.println("----------------");
			System.out.print("메뉴 번호 >");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: showDetail(); break;
			case 2: searchProduct(); break;
			case 3: insertCart(); break;
			case 4: showCart(); break;
			case 5: chargeWallet(); break;
			case 6: insertUser(); break;
			case 10: staffMainMenu(); break;
			case 0: System.out.println("프로그램을 종료합니다."); return;
			default: System.out.println("없는 번호입니다. 다시 입력해주세요.");
			}
			
		}
	}
	
	// 상품 전체 조회
	public void selectAll() {
		soc.selectAll();
	}
	
	//해당 음료에 대한 detail 보여주기
	public void showDetail() {	
		/*
		System.out.println("정보를 원하시는 음료를 입력해주세요 > ");
		String menu = sc.nextLine();
		soc.showDetail(menu);
 		CAFE_PRODUCT 테이블에 음료 이름이 중복되는 경우도 존재해서 id로 입력받아서 조회하는 것으로 수정했습니다
		그리고 deleteProduct()에서도 showDetail()과 동일하게, 상품 1개를 조회해오는 요청이 필요해서
		Controller의 showDeatil메소드명을 selectProduct(), 반환값을 CafeProduct타입로 수정했습니다!! 
		*/
		System.out.print("정보를 원하시는 음료id를 입력해주세요 > ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		CafeProduct cp = soc.selectProduct(menu);
		
		if(cp == null) {
			displayFail("해당 상품이 없습니다.");
		} else {
			displayDetail(cp);
		}
	}
	
	// 상품 검색
	public void searchProduct() {
		System.out.println("-----음료 검색-----");
		
		System.out.println("음료 키워드 > ");
		String keyword = sc.nextLine();
		
		soc.searchProduct(keyword);
	}
	
	/**
	 * TODO 장바구니 추가 요청 (김민아)
	 */
	public void insertCart() {
		System.out.println("-----장바구니 추가-----");
		
		// 회원아이디, 상품번호, 수량을 입력 받아서 장바구니에 추가하기
	}
	
	/**
	 * TODO 장바구니 조회 요청 (김유진)
	 */
	public void showCart() {
		System.out.println("-----장바구니 조회-----");
		// 유저아이디 입력 받아서 해당 유저가 장바구니에 추가한 목록 중 ORDER_YN이 'N'인 정보들만 출력하기
		// 장바구니에 담긴 상품들의 가격 총합도 출력하기
		
		//+)product_id가 NULL인 상품들은 <삭제된 상품>으로 띄우기
		//+) 주문하기 버튼 -> 유저의 wallet값이 상품 총합보다 크거나 같을 경우에만 주문 가능 -> ORDER_YN을 Y로 변경
		//+) 수량 수정하기 버튼 ->  cartId, amount 입력 받아서 update
		//+) 삭제하기 버튼 -> cartId 입력 받아서 delete
		//+) 뒤로가기 버튼
	}
	
	/**
	 * TODO 유저 Wallet 충전 요청 (김민아)
	 */
	public void chargeWallet() {
		System.out.println("-----유저 Wallet 충전-----");
		
		// 유저아이디, 비밀번호, 충전 금액 입력 받아서 해당 유저의 wallet 금액 충전하기
		// isValidLogin(userId, userPwd) 로 로그인 성공/실패 확인 가능!
		// isValidLogin(userId, userPwd)가 true일 경우에만 wallet 금액을 충전
	}
	
	/**
	 * TODO 유저 추가(회원가입) 요청 (최승희)
	 */
	public void insertUser() {
		System.out.println("-----회원 가입-----");
		
		// 아이디, 비밀번호, 이름, 핸드폰번호 입력받아서 유저 추가하기
	}
	
	
	
	// ----------- 직원 전용 화면 -----------
	
	public void staffMainMenu() {
		
		while(true) {
			System.out.println("===== 직원 로그인 =====");
			System.out.print("직원 아이디 입력 : ");
			String userId = sc.nextLine();
			System.out.print("직원 비밀번호 입력 : ");
			String userPwd = sc.nextLine();
			
			if(isStaffLogin(userId, userPwd)) {
				System.out.println("직원 로그인 성공! 전용 화면으로 이동합니다...");
				break;
			}
			
			System.out.print("다시 입력(아무 키) / 뒤로가기(EXIT) > ");
			String key = sc.nextLine().toUpperCase();
			
			if(key.equals("EXIT")) return;
			
		}
		
		
		while(true) {
			System.out.println("=============================");
			System.out.println("========= 직원 전용 화면 =========");
			System.out.println("1. 음료 추가");
			System.out.println("2. 음료 수정");
			System.out.println("3. 음료 삭제");
			System.out.println("4. 주문 확인하기");
			System.out.println("5. 유저정보 확인하기");
			System.out.println("0. 뒤로가기(메인메뉴로 이동)");
			System.out.println("----------------------------");
			System.out.print("메뉴 번호 > ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: insertProduct(); break;
			case 2: updateProduct(); break;
			case 3: deleteProduct(); break;
			case 4: showOrder(); break;
			case 5: selectAllUser(); break;
			case 0: return;
			default: System.out.println("없는 번호입니다. 다시 입력해주세요.");
			}
		}
	}
	
	// 유효한 로그인인지 확인 (존재하는 아이디인지, 비밀번호가 일치하는지)
	public boolean isValidLogin(String userId, String userPwd) {
		
		boolean flag = false;
		
		CafeUser user = soc.selectUser(userId);
		
		if(user == null) {
			System.out.println("아이디가 존재하지 않습니다.");
		} else if(!userPwd.equals(user.getUserPwd())) {
			System.out.println("비밀번호가 틀렸습니다.");
		} else {
			flag = true;
		}
		
		return flag;
		
	}
	
	// 직원 아이디로 로그인한 것인지 확인 ( + 유효한 로그인인지 확인)
	public boolean isStaffLogin(String userId, String userPwd) {
		
		boolean flag = false;
		
		// 없는 아이디이거나 비밀번호가 틀렸을 경우 false
		if(!isValidLogin(userId, userPwd)) return flag;
		
		CafeUser user = soc.selectUser(userId);
		
		// 직원전용 아이디가 아니면 false
		if(!user.getUserType().equals("S")) { 
			System.out.println("직원 전용 아이디가 아닙니다.");
		} else {// 직원 전용 아이디일 경우 true
			flag = true;
		}
		
		return flag;
	}
	
	// 상품 추가
	public void insertProduct() {
		System.out.println("----- 상품 추가 -----");
		
		System.out.print("메뉴 이름 : ");
		String productName = sc.nextLine();
		System.out.print("메뉴 상세 정보 : ");
		String detail = sc.nextLine();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("핫/아이스 (H/C) : ");
		String tempType = sc.nextLine();
		
		soc.insertProduct(productName, detail, price, tempType);
	}
	
	// 상품 정보 변경
	public void updateProduct() {
	    System.out.println("----- 상품 정보 변경 -----");
	    /*
	    System.out.print("변경할 메뉴의 이름을 입력해주세요 : ");
	    String productName = sc.nextLine();
	    CAFE_PRODUCT 테이블에 음료 이름이 중복되는 경우도 존재해서 id로 입력받는 것으로 수정했습니다!
	    */
	    System.out.print("변경할 메뉴의 id를 입력해주세요 : ");
	    int productId = sc.nextInt();
	    System.out.print("변경할 메뉴의 상세 정보를 입력해주세요 : ");
	    String detail = sc.nextLine();
	    System.out.print("변경할 메뉴의 가격을 입력해주세요 : ");
	    int price = sc.nextInt();
	    sc.nextLine();
	    System.out.println("변경할 메뉴가 hot/cold 음료인가요(H/C) : ");
	    String temptype = sc.nextLine();
	        
	    soc.updateProduct(productId, detail,price,temptype);
	}
	
	// 상품 삭제
	public void deleteProduct() {
		
		while(true) {
			System.out.println("----- 상품 삭제 -----");
			
			soc.selectAll();
			
			System.out.print("삭제할 상품 번호 > ");
			int productId = sc.nextInt();
			sc.nextLine();
			
			CafeProduct cp = soc.selectProduct(productId);
			if(cp == null) {
				displayFail("해당 상품이 없습니다.");
				break;
			}
			
			soc.deleteProduct(productId);
		}
		
	}
	
	/**
	 * TODO 들어온 주문 조회 요청 (윤자원)
	 */
	public void showOrder() {
		System.out.println("-----들어온 주문 확인-----");
		
		//Cart 테이블에서 Order_Yn이 'Y'인 정보들만 출력하기
		//테이블 JOIN 이용해서 주문자명+주문자아이디 와 주문메뉴명+(COLD/HOT)으로 보이게 출력
		//정렬은 CART_ID순으로 (ORDER BY CARD_ID)
		// 예시 ) 카트번호: 2, 주문자: 유저1(user1), 주문메뉴: 아메리카노(COLD), 주문수량: 1개
		//		 카트번호: 3, 주문자: 유저2(user2), 주문메뉴: 카페모카(HOT), 주문수량: 3개

		ArrayList<Cart> list = soc.showOrder();
		
		if(list.isEmpty()) {
			System.out.println("주문이 없습니다.");
		}else {
			for(Cart c : list) {
				System.out.println(c);
			}
		}
	}
	
	/**
	 * TODO 유저 전체 조회 (최승희)
	 */
	public void selectAllUser() {
		System.out.println("-----회원 정보 확인-----");
		
		//User테이블에 존재하는 모든 유저 정보들 출력하기
	}
	
	
	
	// ------------------------ 응답화면 ------------------------
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	}
	
	public void displayFail(String message) {
		System.out.println("서비스 요청 실패 : " + message);
	}
	
	public void displayNodata(String message) {
		System.out.println(message);
	}

	public void displayList(ArrayList<CafeProduct> list) {
		for(CafeProduct cp : list)
			System.out.println(cp);
	}

	public void displayDetail(CafeProduct cp) {
		System.out.println("상품 설명 : " + cp.getDetail());
	}
}
