///* @provengo summon selenium */
//
///**
// * This story opens a new browser window, goes to google.com, and searches for "Pizza".
// */

bthread('Admin deactivate user', function () {
  // let s = new SeleniumSession('admin');
  sync({ request: Ctrl.markEvent("new session - Admin") }); // Marked
  // s.start(AdminURL , 'chrome');

  sync({ request: Ctrl.markEvent("AdminLogIn") }); // Marked
  // AdminLogIn(s);

  sync({ request: Ctrl.markEvent("OpenCustomersList") }); // Marked
  // OpenCustomersList(s);

  sync({ request: Ctrl.markEvent("FirstUserDeactivated") }); // Marked
  // DeactivateFirstUser(s);

  sync({ request: Ctrl.markEvent("DeactivationValidation") }); // Marked
  // DeactivationValidation(s);

  sync({ request: Ctrl.markEvent("ReActivateUserForSetUpNextTestNextTest") }); // Marked
  // AdminActivateUserForSetUpNextTest(s);

  sync({ request: Ctrl.markEvent("close session - Admin") }); // Marked
  // s.close();
});

bthread('user changes name', function () {
  // let s = new SeleniumSession('user');
  sync({ request: Ctrl.markEvent("new session - User") }); // Marked
  // s.start(UserURL , 'chrome');

  sync({ request: Ctrl.markEvent("UserLogIn") }); // Marked
  // UserLogIn(s);

  sync({ request: Ctrl.markEvent("EditAccount") }); // Marked
  // EditAccount(s);

  sync({ request: Ctrl.markEvent("close session - User") }); // Marked
  // s.close();
});

bthread("Constraint1", function () {
  sync({
    waitFor: Ctrl.markEvent("EditAccount"),
    block: Ctrl.markEvent("FirstUserDeactivated")
  });
});

bthread("Constraint2", function () {
  sync({
    waitFor: Ctrl.markEvent("close session - User"),
    block: Ctrl.markEvent("ReActivateUserForSetUpNextTestNextTest")
  });
});