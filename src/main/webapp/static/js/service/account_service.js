'use strict';

angular.module('accountsApp').factory('AccountService', ['$http', '$q', function($http, $q){

   // var REST_SERVICE_URI = 'http://localhost:9090/AccountManagement/account/';
	var REST_SERVICE_URI = 'account/';

    var factory = {
        fetchAllAccounts: fetchAllAccounts,
        createAccount: createAccount,
        updateAccount: updateAccount,
        deleteAccount: deleteAccount,
        fetchAllAccountHolders : fetchAllAccountHolders
    };

    return factory;

    function fetchAllAccounts() {
        var deferred = $q.defer();
      //  $http.get(REST_SERVICE_URI)
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Accounts');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function createAccount(account) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, account)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Account');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateAccount(account, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, account)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Account');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteAccount(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Account');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchAllAccountHolders() {
        var deferred = $q.defer();
        $http.get('accountholder/')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Account Holders');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
