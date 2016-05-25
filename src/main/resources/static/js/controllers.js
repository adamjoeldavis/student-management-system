var app = angular.module('StudentManagementApp', []);

app.controller('StudentManagementController', function() {
	this.students = [];
	this.nameFilter = "";

	this.selectedStudent = false;
	this.isEdit = false;
	this.editMessage = "";
	this.currentStudent = {};

	this.loadStudents = function() {
		// TODO AJAX
		for (var i = 0; i < 25; i++) {
			this.students[i] = {
				id : i,
				name : 'Student # ' + i
			}
		}
	};

	this.showEditPane = function() {
		return this.selectedStudent !== false;
	}

	this.clearSelectedStudent = function() {
		this.selectedStudent = false;
		this.isEdit = false;
		this.editMessage = "";
		this.currentStudent = {};
		$('tr.active').removeClass('active');
	};

	this.editStudent = function(id) {
		this.clearSelectedStudent();

		console.log('Student # ' + id + ' Clicked! (' + this.nameFilter + ')'); //TODO AJAX
		this.currentStudent = {
			studentId : id
		}
		this.selectedStudent = id;
		this.editMessage = "Edit Existing Student";
		this.isEdit = true;

		$('#student-row-' + id).addClass('active');
	}

	this.addStudent = function() {
		this.clearSelectedStudent();
		this.editMessage = "Add New Student";
		this.selectedStudent = true;
		this.isEdit = false;
	};

	this.formatStudentName = function(student) {
		//TODO account for missing parts
		return student.lastName + ", " + student.firstName + " "
				+ student.middleName;
	}

	this.verifyDelete = function() {
		if (confirm("Deleting student " + this.currentStudent.studentId + "("
				+ this.formatStudentName(this.currentStudent)
				+ ").  Are you sure?")) {
			alert('deleted!'); //TODO AJAX
			this.clearSelectedStudent();
			this.loadStudents();
		}
	};

	this.loadStudents();
});