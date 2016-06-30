/**
 * Created by singo on 2014-11-12.
 */
$(function () {
    var pageSwitch = {
        init: function () {
            
            var self = this;
            
            self.__hdPage = $('#pageNo');
            self.__formSearch = $('#form-search');
            
            self.__btnPage = $('.page ul li a');
            self.__btnPage.on('click', function () {
            	self.__hdPage.val($(this).data('no'));
            	self.__formSearch.submit();
            });
            
            self.__btnGo = $('.page div :button');
            self.__txGo = $('.page div :text');
            self.__btnGo.on('click', function () {
            	self.__hdPage.val(self.__txGo.val());
            	self.__formSearch.submit();
            });
        }
    };
    pageSwitch.init();
});