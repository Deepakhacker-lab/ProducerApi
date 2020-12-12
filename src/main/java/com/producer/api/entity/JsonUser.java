package com.producer.api.entity;
import java.util.List;

public class JsonUser {		
		
		private int id;
		
		private String username;
		
		private String firstName;
		
		private String lastName;
		
		private String email;
		
		private String password;
		
	    private List<Address> address;

		private roles role;
		
		private String department; 
		
		public JsonUser() {
			
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getId() {
			return id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", password=" + password + ", address=" + address + ", role=" + role + ", department=" + department
					+ "]";
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public List<Address> getAddress() {
			return address;
		}

		public void setAddress(List<Address> address) {
			this.address = address;
		}

		public roles getRole() {
			return role;
		}

		public void setRole(roles role) {
			this.role = role;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		
	}

