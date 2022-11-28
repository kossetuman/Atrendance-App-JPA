package com.example.demo.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;

@Component
//ApplicationRunnerを実装したクラスはSpringBoot起動時に初期処理を実行できます。処理内容はrunメソッドに記載します。
public class DataLoader implements ApplicationRunner {
	
	private final AttendanceRepository repository;
	
	public DataLoader(AttendanceRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		var attendance = new Attendance();
		attendance.setWorkStart("09:00");
		attendance.setWorkFinish("18:00");
		//attendance.setDepartment.name("営業部");
		repository.save(attendance);
		
		
	}
}