package com.oracle.oBootDBConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.print.attribute.standard.PresentationDirection;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.oracle.oBootDBConnect.domain.Member1;

public class JdbcMemberRepository implements MemberRepository {
	//jdbc 사용
	private final DataSource dataSource;
	
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	
	
	@Override
	public Member1 save(Member1 member1) {
		//member_seq DB 만들기
		String sql = "insert into member1(id,name) values(member_seq.nextval,?)";
		System.out.println("JdbcMemberRepository sql->"+sql);
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member1.getName());
			pstmt.executeUpdate();
			System.out.println("JdbcMemberRepositroy pstmt.executeUpdate After");
			return member1;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
		
		
		
		
	}

	@Override
	public List<Member1> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
