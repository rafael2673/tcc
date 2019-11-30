//efeito nas redes sociais//
$(document).ready(function() {
	$("#insta").hide();
	$("#face").hide();
	$("#twit").hide();
	$("#p3").mouseover(function() {
		$("#insta").show();
	});
	$("#p2").mouseover(function() {
		$("#face").show();
	});
	$("#p1").mouseover(function() {
		$("#twit").show();
	});

	$("#p3").mouseout(function() {
		$("#insta").hide();
	});
	$("#p2").mouseout(function() {
		$("#face").hide();
	});
	$("#p1").mouseout(function() {
		$("#twit").hide();
	});

});
