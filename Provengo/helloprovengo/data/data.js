/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

//this file contains the data that is used in the test cases.
//mainely the urls and the xpaths that are used in the test cases.
const AdminURL = 'http://localhost/opencartsite/admin12345/';
const UserURL = 'http://localhost/opencartsite/';

const AdminName = 'admin';
const AdminPassword = '1234';

const UserEmail = 'guyamzaleg@gmail.com';
const UserPassword = '1234';

const xpaths = {
  AdminLogInWindow: {
    AdminLogInUsernameElement: "//*[@id='input-username']",
    AdminLogInPasswordElement: "//*[@id='input-password']",
    LogInButton: "//button[1]"
  },
  AdminDashboard: {
    expandCustomers: "//body/div[1]/nav[1]/ul[1]/li[6]/a[1]",
    ToListCustomers: "//li[6]/ul[1]/li[1]/a[1]"
  },
  UserDeactivation: {
    editFirst: "//td[6]/div[1]/a[1]/i[1]",
    ChangeStatus:"//*[@id='tab-general']/fieldset[3]/div[2]/div[1]/div[1]/input[2]",
    saveButton:"//div[1]/div[1]/button[1]/i[1]",
    successMessage: "//small[1]"
  },
  LandPage: {
    MyAccount: "//li[2]/div[1]/a[1]/span[1]",
    LogIn: "//nav[1]/div[1]/div[2]/ul[1]/li[2]/div[1]/ul[1]/li[2]/a[1]"
  },
  UserLogInPage: {
    EmailBox: "//*[@id='input-email']",
    PasswordBox: "//*[@id='input-password']",
    LogInButton: "//div[3]/button[1]"
  },
  UserAccount: {
    EditAccount: "//div[2]/div[1]/aside[1]/div[1]/a[2]",
    FirstName: "//*[@id='input-firstname']",
    ContinueButton: "//div[2]/button[1]",
    successMessage: "//div[contains(@class, 'alert alert-success alert-dismissible')]"
  }
}