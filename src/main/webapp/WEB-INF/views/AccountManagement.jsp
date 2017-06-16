<html>
<head>
<title>Account Management</title>
<style>
      .accountname.ng-valid {
          background-color: lightgreen;
      }
      .accountname.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .accountname.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
      .userid.ng-valid {
          background-color: lightgreen;
      }
      .userid.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .userid.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
      .accountpassword.ng-valid {
          background-color: lightgreen;
      }
      .accountpassword.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .accountpassword.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
     
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="static/css/app.css">
</head>
<body ng-app="accountsApp" class="ng-cloak">
   <div class="generic-container" ng-controller="AccountController as ctrl">
   		<div class="panel panel-default">
   		  <div class="panel-heading"><span class="lead">Account Management</span></div>
   		  <div class="formcontainer">
   		   <form ng-submit="ctrl.submit()" name="accountForm" class="form-horizontal">
   		        <input type="hidden" ng-model="ctrl.account.id" />
   		        <div class="row">
                   <div class="form-group col-md-12">
                          <label class="col-md-2 control-lable" for="file">Account Name</label>
                          <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.account.name" name="accountname" class="accountname form-control input-sm" placeholder="Enter name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="accountForm.$dirty">
                                      <span ng-show="accountForm.accountname.$error.required">This is a required field</span>
                                      <span ng-show="accountForm.accountname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="accountForm.accountname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                   </div>
                 </div>
                  <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">User Id</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.account.userid" name="userid" class="userid form-control input-sm" placeholder="Enter User Id." required/>
                                   <div class="has-error" ng-show="accountForm.$dirty">
                                      <span ng-show="accountForm.userid.$error.required">This is a required field</span>
                                      <span ng-show="accountForm.userid.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Password</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.account.password" name="accountpassword" class="accountpassword form-control input-sm" placeholder="Enter Password" required/>
                                  <div class="has-error" ng-show="accountForm.$dirty">
                                      <span ng-show="accountForm.accountpassword.$error.required">This is a required field</span>
                                      <span ng-show="accountForm.accountpassword.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Url</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.account.url" name="url" class="userurl form-control input-sm" placeholder="Enter url"/>
                              </div>
                          </div>
                      </div>
                      
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Remarks</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.account.remarks" name="remarks" class="form-control input-sm" placeholder="Enter Remarks" required/>
                              </div>
                          </div>
                      </div>

                     <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Account Holder</label>
                              <div class="col-md-7">
                                  <select ng-model="ctrl.account.accountHolder" class="form-control input-sm">
									<option ng-repeat="accountholder in ctrl.accountHolders" value="{{accountholder.accountHolderId}}">{{accountholder.accountHolderName}}</option>
								  </select>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.account.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="accountForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="accountForm.$pristine">Reset Form</button>
                          </div>
                      </div>
   		   </form>
   		  </div>
        </div>
        <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Accounts </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Id</th>
                              <th>Account Name</th>
                              <th>User Id</th>
                              <th>Password</th>
                              <th>Url</th>
                              <th>Remarks</th>
                              <th>Account Holder</th>
                              <th>Actions</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="account in ctrl.accounts">
                              <td><span ng-bind="account.id"></span></td>
                              <td><span ng-bind="account.name"></span></td>
                              <td><span ng-bind="account.userid"></span></td>
                              <td><span ng-bind="account.password"></span></td>
                              <td><span ng-bind="account.url"></span></td>
                              <td><span ng-bind="account.remarks"></span></td>
                              <td><span ng-bind="account.accountHolderName"></span></td>
                               <td>
                              <button type="button" ng-click="ctrl.edit(account.id)" class="btn btn-success custom-width">Edit</button>  
                              <button type="button" ng-click="ctrl.remove(account.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
   </div>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="static/js/app.js"></script>
      <script src="static/js/service/account_service.js"></script>
      <script src="static/js/controller/account_controller.js"></script>
</body>
</html>