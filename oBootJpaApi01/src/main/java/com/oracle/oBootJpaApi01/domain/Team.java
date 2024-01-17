package com.oracle.oBootJpaApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "team5")
@SequenceGenerator(
				   name = "tean_seq_gen5",
				   sequenceName= "team_seq_generator5", //매핑할 db 시퀸스 이름
				   initialValue = 1,
				   allocationSize =1
				   )

public class Team {
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "team_seq_gen5"
					)
	
	private Long teamId;
	@Column(name = "teamname", length = 80)
	private String name;
	
}
