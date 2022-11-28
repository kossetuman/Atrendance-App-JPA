package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.DepartmentRepository;

@Controller
public class AttendanceController {
	
	private final AttendanceRepository repository;
	private final DepartmentRepository departmentRepository;
	
	@Autowired //←コンストラクタが１つの場合は、＠Autowiredは省略可
	public AttendanceController(AttendanceRepository repository, DepartmentRepository departmentRepository) {
		this.repository = repository;
		this.departmentRepository = departmentRepository;
		
	}
	

	

	@GetMapping("/")
	public String showAttendance(@ModelAttribute Attendance attendance,
			Model model) {
		model.addAttribute("attendances", repository.findAll());
		model.addAttribute("departments", departmentRepository.findAll());
		model.addAttribute("title", "登録フォーム");
		model.addAttribute("indexList", "勤怠一覧");
		return "index";
	}
	
	@PostMapping("/")
	public String addAttendance(@Validated @ModelAttribute Attendance attendance,
			Model model,
			BindingResult result) {
		model.addAttribute("title", "登録フォーム");
		model.addAttribute("attendances", repository.findAll());
		model.addAttribute("departments", departmentRepository.findAll());
		if(result.hasErrors()) {
			return "index";
		}
		repository.save(attendance);
		return "redirect:/";
	}
	
	@PostMapping("/edit")
	public String editAttendance(@Validated @ModelAttribute Attendance attendance,
			BindingResult result, Model model) {
		model.addAttribute("attendances", repository.findAll());
		if(result.hasErrors()) {
			return "edit";
		}
		repository.save(attendance);
		
		//ルートパス（"/"）にリダイレクト
		return "redirect:/";

	}
	
	@GetMapping("/edit/{id}")
	public String editAttendance(@PathVariable Long id, Model model) {
		model.addAttribute("attendance", repository.findById(id));
		return "/attendance/edit";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAttendance(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/";
	}
	
	

}
