var app = angular.module('StudentManagementApp', []);

app.controller('StudentManagementController', function() {
	this.students = [];
	this.nameFilter = "";

	this.selectedStudent = false;
	this.isEdit = false;
	this.editMessage = "";
	this.currentStudent = {};

	this.loadStudents = function() {
		console.log('loadStudents(' + this.nameFilter + ')');
		// TODO AJAX
		this.students.length = 0;
		for (var i = 0; i < 25; i++) {
			var studentName = 'Student # ' + i;

			if (!this.nameFilter || !this.nameFilter.length
					|| studentName.startsWith(this.nameFilter)) {
				this.students.push({
					id : i,
					name : 'Student # ' + i
				});
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

		console.log('Student # ' + id + ' Clicked! (' + this.nameFilter + ')'); // TODO
		// AJAX
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

	this.submitSelectedStudent = function() {
		// TODO ajax
		console.log('Saving student');
		console.log(this.currentStudent);

		this.clearSelectedStudent();
	}

	this.formatStudentName = function(student) {
		// TODO account for missing parts
		var name = student.lastName;

		var firstExists = student.firstName && student.firstName.length;
		var middleExists = student.middleName && student.middleName.length;
		if (firstExists || middleExists) {
			name += ',';

			if (firstExists) {
				name += ' ' + student.firstName;
			}

			if (middleExists) {
				name += ' ' + student.middleName;
			}
		}

		return name;
	}

	this.verifyDelete = function() {
		if (confirm("Deleting student " + this.currentStudent.studentId + " ("
				+ this.formatStudentName(this.currentStudent)
				+ ").  Are you sure?")) {
			alert('deleted!'); // TODO AJAX
			this.clearSelectedStudent();
			this.loadStudents();
		}
	};

	this.loadStudents();
});