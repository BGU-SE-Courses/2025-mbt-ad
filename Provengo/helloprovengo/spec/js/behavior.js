///* @provengo summon selenium */
//
///**
// * This story opens a new browser window, Login as admin and deactivate user from customers list.
// */

bthread('Admin deactivate user', function () {

  let s = new SeleniumSession('admin');
//open a new session , and sync it with the event "new session - Admin"
  sync({request: Event("new session - Admin" ) });
  s.start(AdminURL , 'chrome');

//activate the admin user , doing the login , and sync it with the event "AdminLogIn"
  sync({request: Event("AdminLogIn")});
  AdminLogIn(s);

//open the customers list , and sync it with the event "OpenCustomersList"
  sync({request: Event("OpenCustomersList") });
  OpenCustomersList(s);

//deactivate the first user , and sync it with the event "FirstUserDeactivated"
  sync({request: Event("FirstUserDeactivated")});//syncing the user deactivation
  DeactivateFirstUser(s);

//validate the deactivation , and sync it with the event "DeactivationValidation"
  sync ({request: Event("DeactivationValidation")});
  DeactivationValidation(s);

//close the session , and sync it with the event "close session - Admin"
sync ({request: Event("ReActivateUserForSetUpNextTestNextTest")});
   AdminActivateUserForSetUpNextTest(s);
//close the session , and sync it with the event "close session - Admin"
sync({request: Event(" close session - Admin" ) });
  s.close();
})

//this story is about the user changing his name
bthread('user changes name', function () {
  let s = new SeleniumSession('user');
  //open a new session , and sync it with the event "new session - User"
  sync({request: Event("new session - User" ) });
  s.start(UserURL , 'chrome');

//activate the user , doing the login , and sync it with the event "UserLogIn"
  sync({request: Event("UserLogIn")});
  UserLogIn(s);

//edit the account , and sync it with the event "EditAccount"
  EditAccount(s);
  sync({request: Event("EditAccount")});

//close the session , and sync it with the event "close session - User"
    sync({request: Event("close session - User" ) });
  s.close();
})

bthread("Constraint1", function () {
 sync({ waitFor: Event("EditAccount"), block: Event("FirstUserDeactivated") })
})

bthread("Constraint2", function () {
 sync({ waitFor: Event("close session - User"), block: Event("ReActivateUserForSetUpNextTestNextTest") })
})