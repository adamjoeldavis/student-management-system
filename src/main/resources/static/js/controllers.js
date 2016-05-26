var app = angular.module('StudentManagementApp', []);

app.controller('StudentManagementController', function($http) {
	var ctrl = this;

	ctrl.students = [];
	ctrl.nameFilter = "";

	ctrl.selectedStudent = false;
	ctrl.isEdit = false;
	ctrl.editMessage = "";
	ctrl.currentStudent = {};

	ctrl.loadStudents = function() {
		ctrl.students.length = 0;

		$http.get('/students', {
			params : {
				lastName : ctrl.nameFilter
			}
		}).success(function(data) {
			data.forEach(function(student) {
				ctrl.students.push({
					id : student.studentId,
					name : ctrl.formatStudentName(student)
				});
			});
		});
	};
	
	ctrl.reloadDemoData = function() {
		ctrl.clearSelectedStudent();
		
		$http.post('/demo').success(function() {
			ctrl.loadStudents();
		});
	};

	ctrl.showEditPane = function() {
		return ctrl.selectedStudent !== false;
	}

	ctrl.clearSelectedStudent = function() {
		ctrl.selectedStudent = false;
		ctrl.isEdit = false;
		ctrl.editMessage = "";
		ctrl.currentStudent = {};
		$('tr.active').removeClass('active');
	};

	ctrl.editStudent = function(id) {
		ctrl.clearSelectedStudent();

		$http.get('/students/' + id).success(function(data) {
			ctrl.currentStudent = data;
			ctrl.selectedStudent = id;
			ctrl.editMessage = "Edit Existing Student";
			ctrl.isEdit = true;

			$('#student-row-' + id).addClass('active');
		});
	}

	ctrl.addStudent = function() {
		ctrl.clearSelectedStudent();
		ctrl.editMessage = "Add New Student";
		ctrl.selectedStudent = true;
		ctrl.isEdit = false;
	};

	ctrl.submitSelectedStudent = function() {
		console.log('Saving student');
		console.log(ctrl.currentStudent);

		var onSuccess = function() {
			ctrl.clearSelectedStudent();
			ctrl.loadStudents();
		};

		if (ctrl.isEdit) {
			$http.put('/students/' + ctrl.selectedStudent, ctrl.currentStudent).success(onSuccess)
				.catch(function() { alert('Error updating student.'); });
		} else {
			$http.post('/students', ctrl.currentStudent).success(onSuccess)
				.catch(function() { alert('Error adding new student. Student ID must be unique.'); });
		}
	}

	ctrl.formatStudentName = function(student) {
		var name = student.lastName;

		var firstExists = student.firstName && student.firstName.length;
		var middleExists = student.middleName && student.middleName.length;
		if (firstExists || middleExists) {
			name += ',';

			if (firstExists) {
				name += ' ' + student.firstName;
			}

			if (middleExists) {
				name += ' ' + student.middleName.charAt(0) + '.';
			}
		}

		return name;
	}

	ctrl.verifyDelete = function() {
		if (confirm("Deleting student " + ctrl.selectedStudent + ".  Are you sure?")) {
			$http.delete('/students/' + ctrl.selectedStudent).success(function() {
				ctrl.clearSelectedStudent();
				ctrl.loadStudents();
			})
			.catch(function() { alert('Error deleting student.'); });
		}
	};

	ctrl.loadStudents();
});