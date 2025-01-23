/* @provengo summon selenium */

/**
 * This story opens a new browser window, goes to google.com, and searches for "Pizza".
 */
bthread('Admin deactivate user', function () {
  let s = new SeleniumSession('admin');
  s.start(AdminURL , 'chrome');
  AdminLogIn(s);
  OpenCustomersList(s);
  DeactivateFirstUser(s);
  DeactivationValidation(s);
  sync({request: Event("FirstUserDeactivated")});//syncing the user deactivation
  s.close();
})


bthread('user changes name', function () {
  let s = new SeleniumSession('user');
  s.start(UserURL , 'chrome');
  UserLogIn(s);
  EditAccount(s);
  s.close();
})





//

///**
// * This story opens a new browser window, goes to google.com, and searches for "Pasta" using the "I Feel Lucky" feature.
// */
//bthread('Feeling lucky', function () {
//  let s = new SeleniumSession('lucky').start(URL)
//  composeQuery(s, { text: 'Pasta' })
//  feelLucky(s)
//})