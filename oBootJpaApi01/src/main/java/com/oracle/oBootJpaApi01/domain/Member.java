package com.oracle.oBootJpaApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
					name="member_seq_gen5",
					sequenceName="member_seq_generator5", //매핑할 db 시퀸스 이름
					initialValue = 1,
					allocationSize = 1
					)

@Table( name= "member5" )
public class Member {
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen5"
					)
	@Column(name ="member_id")
	private Long id;
	@NotEmpty
	@Column(name="userName")
	private String name;
	
	private Long sal;
	//@ManyToOne //외래키를 거는 테이블에다 거는거임-->기본은 FetchType.Eager 즉시로딩
	//전문가 권장은 FetchType.LAZY 지연로딩
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

}
