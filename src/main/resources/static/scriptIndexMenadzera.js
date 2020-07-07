$("#dobrodosao").ready(function () {
	$("#dobrodosao").append(localStorage.getItem("user"));
});






















































$(document).ready(function () {
	$('iframe').hide();
	$('#easteregg').click(function () {
		$("h1").hide();
		$("a").hide();
		$("h3").hide();
		$('#ftn-web-cinema-header').hide();
		$(".row").hide();
		$('body').attr('style', 'background-color: black !important');
		$('iframe').show();
		$('iframe').attr('style', 'border-width: 0px');
		$('iframe').attr('src', 'https://rickrolled.fr/rickroll.mp4');
	});
});