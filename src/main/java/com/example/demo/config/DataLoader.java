package com.example.demo.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

@Component
//ApplicationRunnerを実装したクラスはSpringBoot起動時に初期処理を実行できます。処理内容はrunメソッドに記載します。
public class DataLoader implements ApplicationRunner {
	

	private final DepartmentRepository repository;
	
	
	public DataLoader(DepartmentRepository repository) {
		this.repository = repository;
	
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		var eigyou = new Department();
		eigyou.setName("営業部");
		repository.save(eigyou);
		
		var kyouiku = new Department();
		kyouiku.setName("教育部");
		repository.save(kyouiku);
		
		
		
		
	}
	
	
}