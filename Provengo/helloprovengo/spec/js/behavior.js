///* @provengo summon selenium */
//
///**
// * This story opens a new browser window, Login as admin and deactivate user from customers list.
// */

bthread('Admin deactivate user', function () {

  let s = new SeleniumSession('admin');
  // Open a new session and sync it with the event "new session - Admin"
  sync({request: Ctrl.markEvent("new session - Admin")});
  s.start(AdminURL , 'chrome');

  // Activate the admin user, perform login, and sync it with the event "AdminLogIn"
  sync({request: Ctrl.markEvent("AdminLogIn")});
  AdminLogIn(s);

  // Open the customers list and sync it with the event "OpenCustomersList"
  sync({request: Ctrl.markEvent("OpenCustomersList")});
  OpenCustomersList(s);

  // Deactivate the first user and sync it with the event "FirstUserDeactivated"
  sync({request: Ctrl.markEvent("FirstUserDeactivated")});
  DeactivateFirstUser(s);

  // Validate the deactivation and sync it with the event "DeactivationValidation"
  sync({request: Ctrl.markEvent("DeactivationValidation")});
  DeactivationValidation(s);

  // Reactivate the user for the next test setup and sync it with the event "ReActivateUserForSetUpNextTestNextTest"
  sync({request: Ctrl.markEvent("ReActivateUserForSetUpNextTestNextTest")});
  AdminActivateUserForSetUpNextTest(s);

  // Close the session and sync it with the event "close session - Admin"
  sync({request: Ctrl.markEvent("close session - Admin")});
  s.close();
})

//this story is about the user changing his name
bthread('user changes name', function () {
  let s = new SeleniumSession('user');
  // Open a new session and sync it with the event "new session - User"
  sync({request: Ctrl.markEvent("new session - User")});
  s.start(UserURL , 'chrome');

  // Activate the user, perform login, and sync it with the event "UserLogIn"
  sync({request: Ctrl.markEvent("UserLogIn")});
  UserLogIn(s);

  // Edit the account and sync it with the event "EditAccount"
  EditAccount(s);
  sync({request: Ctrl.markEvent("EditAccount")});

  // Close the session and sync it with the event "close session - User"
  sync({request: Ctrl.markEvent("close session - User")});
  s.close();
})



bthread("Constraint1", function () {
  sync({ waitFor: Ctrl.markEvent("EditAccount"), block: Ctrl.markEvent("FirstUserDeactivated") });
})

bthread("Constraint2", function () {
  sync({ waitFor: Ctrl.markEvent("close session - User"), block: Ctrl.markEvent("ReActivateUserForSetUpNextTestNextTest") });
})
