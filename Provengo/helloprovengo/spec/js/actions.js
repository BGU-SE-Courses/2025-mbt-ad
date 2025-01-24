
function AdminLogIn(session) {
with(session) {
  writeText(xpaths.AdminLogInWindow.AdminLogInUsernameElement, "admin");
  writeText(xpaths.AdminLogInWindow.AdminLogInPasswordElement, "1234");
  click(xpaths.AdminLogInWindow.LogInButton);
  Ctrl.doSleep(2000)
}
}

function OpenCustomersList(session) {
    with(session) {
  click(xpaths.AdminDashboard.expandCustomers);
  click(xpaths.AdminDashboard.ToListCustomers);
  Ctrl.doSleep(2000)
}
}


function DeactivateFirstUser(session) {
with(session) {
 click(xpaths.UserDeactivation.editFirst);
 scrollToBottom();
 Ctrl.doSleep(2000);
 click(xpaths.UserDeactivation.ChangeStatus);
 scrollToTop();
 Ctrl.doSleep(2000);
 click(xpaths.UserDeactivation.saveButton);
}
}


function DeactivationValidation(session) {
with(session) {
click(xpaths.AdminDashboard.ToListCustomers);
assertText(xpaths.UserDeactivation.successMessage, "Disabled");
 Ctrl.doSleep(2000);
}
}

function AdminActivateUserForSetUpNextTest(session) {
with(session) {
//if(xpaths.UserDeactivation.successMessage == "Disabled"){

  click(xpaths.UserDeactivation.editFirst);
  scrollToBottom();
  Ctrl.doSleep(2000);
  click(xpaths.UserDeactivation.ChangeStatus);
  scrollToTop();
  Ctrl.doSleep(2000);
  click(xpaths.UserDeactivation.saveButton);
}
}



function UserLogIn(session) {
with(session) {
  click(xpaths.LandPage.MyAccount);
  click(xpaths.LandPage.LogIn);
  writeText(xpaths.UserLogInPage.EmailBox, "guyamzaleg@gmail.com");
  writeText(xpaths.UserLogInPage.PasswordBox, "1234");
  click(xpaths.UserLogInPage.LogInButton);
  Ctrl.doSleep(2000);
}
}


function EditAccount(session) {
with(session) {
  click(xpaths.UserAccount.EditAccount);
  clear(xpaths.UserAccount.FirstName);
  writeText(xpaths.UserAccount.FirstName, "changedName");
  Ctrl.doSleep(2000);
  click(xpaths.UserAccount.ContinueButton);
  Ctrl.doSleep(2000);
  assertText(xpaths.UserAccount.successMessage, "Success: Your account has been successfully updated.");
}
}


