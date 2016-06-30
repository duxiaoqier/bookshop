/**
 * Created by singo on 2014-10-28.
 */
$(function () {
    var searchSubmit = {
        init: function () {
            var self = this;
            self.__btnSearch = $('#btn-search');
            self.__formSearch = $('#form-search');
            self.__btnSearch.on('click', function () {
            	self.__formSearch.submit();
            });
        }
    };
    searchSubmit.init();
});