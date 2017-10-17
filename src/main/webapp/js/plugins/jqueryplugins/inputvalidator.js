/**
 * input 데이터 검사 스크립트
 */
document.write("<script src='/js/plugins/jqueryplugins/jquery.inputlimiter.1.3.1.js'></script><script src='/js/plugins/jqueryplugins/jquery.validate.js'></script>");

jQuery(function ($) {
	/*
	 * inputlimiter
	 */
	$('#id').inputlimiter({
		limit: 20,
		boxId: 'limitingid',
		boxAttach: false
	});
	$('#pwd').inputlimiter({
		limit: 30,
		boxId: 'limitingpwd',
		boxAttach: false
	});
	$('#user').inputlimiter({
		limit: 20,
		boxId: 'limitinguser',
		boxAttach: false
	});
	$('#telno').inputlimiter({
		limit: 11,
		boxId: 'limitingtelno',
		boxAttach: false
	});
	$('#paper').inputlimiter({
		limit: 100,
		boxId: 'limitingemail',
		boxAttach: false
	});
	$('#company').inputlimiter({
		limit: 50,
		boxId: 'limitingemail',
		boxAttach: false
	});
	$('#email').inputlimiter({
		limit: 30,
		boxId: 'limitingemail',
		boxAttach: false
	});
	$('#abbr').inputlimiter({
		limit: 3,
		boxId: 'limitingabbr',
		boxAttach: false
	});
	$('#name').inputlimiter({
		limit: 100,
		boxId: 'limitingname',
		boxAttach: false
	});
	$('#proj_name').inputlimiter({
		limit: 100,
		boxId: 'limitingname',
		boxAttach: false
	});
	$('#ph_name').inputlimiter({
		limit: 100,
		boxId: 'limitingname',
		boxAttach: false
	});
	$('#desc').inputlimiter({
		limit: 1000,
		boxId: 'limitingdesc',
		boxAttach: false
	});
	$('#dev_name').inputlimiter({
		limit: 50,
		boxId: 'limitingdev_name',
		boxAttach: false
	});
	$('#app_name').inputlimiter({
		limit: 30,
		boxId: 'limitingapp_name',
		boxAttach: false
	});
	$('#expect').inputlimiter({
		limit: 50,
		boxId: 'limitingexpect',
		boxAttach: false
	});
	$('#senaabbr').inputlimiter({
		limit: 10,
		boxId: 'limitingsenaabbr',
		boxAttach: false
	});
	$('#senadesc').inputlimiter({
		limit: 250,
		boxId: 'limitingsenadesc',
		boxAttach: false
	});
	$('#evtdesc').inputlimiter({
		limit: 50,
		boxId: 'limitingevtdesc',
		boxAttach: false
	});
	$('#evtact').inputlimiter({
		limit: 30,
		boxId: 'limitingevtact',
		boxAttach: false
	});
	$('#objid').inputlimiter({
		limit: 30,
		boxId: 'limitingobjid',
		boxAttach: false
	});
	$('#keyword').inputlimiter({
		limit: 30,
		boxId: 'limitingobjid',
		boxAttach: false
	});
	$('#procedure').inputlimiter({
		limit: 1000,
		boxId: 'limitingprocedure',
		boxAttach: false
	});
	$('#reason').inputlimiter({
		limit: 100,
		boxId: 'limitingreason',
		boxAttach: false
	});
	$('#log').inputlimiter({
		limit: 102400,
		boxId: 'limitinglog',
		boxAttach: false
	});
	/*
	 * only number
	 */
	$('#telno').keypress(function (event) {
		if(event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
				event.preventDefault();
			}
		});
	$('#repeat').keypress(function (event) {
		if(event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
				event.preventDefault();
			}
		});
	$('#interval').keypress(function (event) {
		if(event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
				event.preventDefault();
			}
		});
});