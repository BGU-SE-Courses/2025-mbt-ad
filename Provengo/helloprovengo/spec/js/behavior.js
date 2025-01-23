///* @provengo summon selenium */
//
///**
// * This story opens a new browser window, goes to google.com, and searches for "Pizza".
// */

bthread('Admin deactivate user', function () {
  let s = new SeleniumSession('admin');
  sync({request: Event("new session - Admin" ) });
  s.start(AdminURL , 'chrome');

  sync({request: Event("AdminLogIn")});
  AdminLogIn(s);

  sync({request: Event("OpenCustomersList") });
  OpenCustomersList(s);

  sync({request: Event("FirstUserDeactivated")});//syncing the user deactivation
  DeactivateFirstUser(s);

  sync ({request: Event("DeactivationValidation")});
  DeactivationValidation(s);

sync ({request: Event("ReActivateUserForSetUpNextTestNextTest")});
   AdminActivateUserForSetUpNextTest(s);
sync({request: Event(" close session - Admin" ) });
  s.close();
})

bthread('user changes name', function () {
  let s = new SeleniumSession('user');
  sync({request: Event("new session - User" ) });
  s.start(UserURL , 'chrome');

  sync({request: Event("UserLogIn")});
  UserLogIn(s);


  EditAccount(s);
  sync({request: Event("EditAccount")});
//
    sync({request: Event("close session - User" ) });
  s.close();
})

bthread("Constraint1", function () {
 sync({ waitFor: Event("EditAccount"), block: Event("FirstUserDeactivated") })
})

bthread("Constraint2", function () {
 sync({ waitFor: Event("close session - User"), block: Event("ReActivateUserForSetUpNextTestNextTest") })
})