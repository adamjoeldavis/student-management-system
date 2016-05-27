var app = angular.module('StudentManagementApp', []);

/**
 * App controller. Manages both the "list" and "edit" panes
 */
app.controller('StudentManagementController', function($http) {
	var ctrl = this;

	/**
	 * List pane contents
	 */
	ctrl.students = [];
	/**
	 * List pane filter
	 */
	ctrl.nameFilter = "";

	/**
	 * Whether or not a student has been selected for edit or add.
	 * If a new student is being added, this will be true.  Otherwise, it
	 * will contain the unique ID of the student being edited
	 */
	ctrl.selectedStudent = false;
	/**
	 * Whether the "Edit" view contains an existing student (value = true) or a new student (value = false)
	 */
	ctrl.isEdit = false;
	/**
	 * Edit pane header display message.  Will be different based on isEdit status
	 */
	ctrl.editMessage = "";
	/**
	 * Model for the edit form
	 */
	ctrl.currentStudent = {};

	/**
	 * Loads all students from the server, applying the current value in the nameFilter field.
	 */
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
	
	/**
	 * Re-sets server data to "demo mode"
	 */
	ctrl.reloadDemoData = function() {
		ctrl.clearSelectedStudent();
		
		$http.post('/demo').success(function() {
			ctrl.loadStudents();
		});
	};

	/**
	 * Whether or not we should show the edit pane
	 */
	ctrl.showEditPane = function() {
		return ctrl.selectedStudent !== false;
	}

	/**
	 * Clears the currently selected student, resetting all "edit" properties back to their defaults
	 */
	ctrl.clearSelectedStudent = function() {
		ctrl.selectedStudent = false;
		ctrl.isEdit = false;
		ctrl.editMessage = "";
		ctrl.currentStudent = {};
		$('tr.active').removeClass('active');
	};

	/**
	 * Loads the student with the given ID from the database, and populates the edit pane with the response
	 */
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

	/**
	 * Opens the edit pane for adding a new student
	 */
	ctrl.addStudent = function() {
		ctrl.clearSelectedStudent();
		ctrl.editMessage = "Add New Student";
		ctrl.selectedStudent = true;
		ctrl.isEdit = false;
	};

	/**
	 * Submits the student currently being edited to the server, and then reloads the student list
	 */
	ctrl.submitSelectedStudent = function() {
		if(!ctrl.showEditPane()) {
			alert('No Student Selected.');
			return;
		}
		
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

	/**
	 * Formats the name fields of the given student object to the form of "Last, First M."
	 */
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

	/**
	 * Prompts the user to veriy they actually want to delete the current student, and upon confirmation
	 * performs the delete. Reloads the student list on successful deletion.
	 */
	ctrl.verifyDelete = function() {
		if(!ctrl.showEditPane()) {
			alert('No Student Selected.');
			return;
		}
		
		if(!ctrl.isEdit) {
			alert('Can\'t Delete a new Student.');
			return;
		}
		
		if (confirm("Deleting student " + ctrl.selectedStudent + ".  Are you sure?")) {
			$http.delete('/students/' + ctrl.selectedStudent).success(function() {
				ctrl.clearSelectedStudent();
				ctrl.loadStudents();
			})
			.catch(function() { alert('Error deleting student.'); });
		}
	};

	// load the students on page load
	ctrl.loadStudents();
});