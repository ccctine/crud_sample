package com.example.demo.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.BeanValidator;
import com.example.demo.bean.ResultDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BeanValidator beanValidator;

    @PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User reqData) {
		System.err.println(":::  UserController.createUser :::");
		ResultDto<?> responsePacket = null;
		try {
			ArrayList<String> errorList = beanValidator.userValidate(reqData);
			if (errorList.size() != 0) {
				ResultDto<ArrayList<String>> errorPacket = new ResultDto<>(errorList,
						"Above fields values must not be empty", false);
				return new ResponseEntity<>(errorPacket, HttpStatus.BAD_REQUEST);
			}
			User isData = userService.isDataExist(reqData);
			if (isData == null) {
				responsePacket = new ResultDto<>(userService.createUser(reqData), "User Created Successfully", true);
				return new ResponseEntity<>(responsePacket, HttpStatus.OK);
			} else {
				responsePacket = new ResultDto<>("Record already exist", false);
				return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responsePacket = new ResultDto<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

    @GetMapping("/allUsers")
	public ResponseEntity<?> allUsers() {
		System.err.println(":::  UserController.allUsers :::");
		ResultDto<?> responsePacket = null;
		try {
			responsePacket = new ResultDto<>(userService.getAllUsers(), "Users fetched successfully !!", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDto<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
		System.err.println(":::  UserController.getUserById :::");
		ResultDto<?> responsePacket = null;
		try {
			responsePacket = new ResultDto<>(userService.getUserById(id), "User fetched successfully !!", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDto<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User reqData) {
		System.err.println(":::  UserController.updateUser :::");
		ResultDto<?> responsePacket = null;
		try {
			User isData = userService.isDataExist(reqData);
			if (isData != null) {
				responsePacket = new ResultDto<>(userService.updateUser(reqData, isData), "User Updated Successfully",
						true);
				return new ResponseEntity<>(responsePacket, HttpStatus.OK);
			} else {
				responsePacket = new ResultDto<>("Record not exist", false);
				return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responsePacket = new ResultDto<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
		System.err.println(":::  UserController.deleteUserById :::");
		ResultDto<?> responsePacket = null;
		try {
			responsePacket = new ResultDto<>(userService.deleteUserById(id), "User deleted successfully !!", true);
			return new ResponseEntity<>(responsePacket, HttpStatus.OK);
		} catch (Exception e) {
			responsePacket = new ResultDto<>(e.getMessage(), false);
			return new ResponseEntity<>(responsePacket, HttpStatus.BAD_REQUEST);
		}
	}
    
}
