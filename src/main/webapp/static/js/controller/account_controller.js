'use strict';

angular.module('accountsApp').controller('AccountController', ['$scope', 'AccountService', function($scope, AccountService) {
    var self = this;
    self.account={id:null,name:'',userid:'',password:'',url:'',remarks:'',accountHolder:'', accountHolderName:''};
    self.accounts=[];
    self.submit = submit;
    self.reset = reset;
    self.edit = edit;
    self.remove = remove;
    
    self.accountHolders=[];

    fetchAllAccounts();
    fetchAllAccountHolders();

    function fetchAllAccounts(){
    	AccountService.fetchAllAccounts()
            .then(
            function(d) {
                self.accounts = d;
            },
            function(errResponse){
                console.error('Error while fetching Accounts');
            }
        );
    }
    
    function submit() {
        if(self.account.id===null){
        	
            console.log('Saving New Account', self.account);
            createAccount(self.account);
        }else{
        	updateAccount(self.account, self.account.id);
            console.log('Account updated with id ', self.account.id);
        }
        reset();
    }
    
    function createAccount(account){
    	console.log('creating Account');
    	AccountService.createAccount(account)
            .then(
            fetchAllAccounts,
            function(errResponse){
                console.error('Error while creating Account');
            }
        );
    }
    
    function updateAccount(account, id){
    	console.log('Updating Account', self.account);
    	AccountService.updateAccount(account, id)
            .then(
            fetchAllAccounts,
            function(errResponse){
                console.error('Error while updating Account');
            }
        );
    }
    

    function deleteAccount(id){
    	AccountService.deleteAccount(id)
            .then(
            fetchAllAccounts,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.accounts.length; i++){
            if(self.accounts[i].id === id) {
                self.account = angular.copy(self.accounts[i]);
                break;
            }
        }
    }
    
    function remove(id){
    	console.log('id to be deleted', id);
        if(self.account.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteAccount(id);
    }
    
    function reset(){
        self.account={id:null,name:'',userid:'',password:'',url:'',remarks:''};
        $scope.accountForm.$setPristine(); //reset Form
    }
    
    function fetchAllAccountHolders(){
    	AccountService.fetchAllAccountHolders()
            .then(
            function(d) {
                self.accountHolders = d;
            },
            function(errResponse){
                console.error('Error while fetching Account Holders');
            }
        );
    }

   
}]);
